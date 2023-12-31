package com.mythos;

import java.util.*;

public class Args {
	private Map<Character, ArgumentMarshaler<?>> marshalers;
	private Set<Character> argsFound;
	private ListIterator<String> currentArgument;

	public Args(String schema, String[] args) throws ArgsException {
		marshalers = new HashMap<Character, ArgumentMarshaler<?>>();
		argsFound = new HashSet<Character>();

		parseSchema(schema);
		parseArgumentStrings(Arrays.asList(args));
	}

	private void parseSchema(String schema) throws ArgsException {
		for (String element : schema.split(","))
			if  (element.length() > 0)
				parseSchemaElement(element.trim());
	}

	private void parseSchemaElement(String element) throws ArgsException {
		char elementId = element.charAt(0);
		String elementTail = element.substring(1);

		validateSchemaElementId(elementId);
		if (elementTail.length() == 0)
			marshalers.put(elementId, new BooleanArgumentMarshaler());
		else if (elementTail.equals("*"))
			marshalers.put(elementId, new StringArgumentMarshaler());
		else if (elementTail.equals("#"))
			marshalers.put(elementId, new IntegerArgumentMarshaler());
		else if (elementTail.equals("##"))
			marshalers.put(elementId, new IntegerListArgumentMarshaler());
		else if (elementTail.equals("[*]"))
			marshalers.put(elementId, new StringArgumentMarshaler());
		else
			throw new ArgsException(ErrorCode.INVALID_ARGUMENT_FORMAT, elementId, elementTail);
	}

	private void validateSchemaElementId(char elementId) throws ArgsException {
		if ( !Character.isLetter(elementId) )
			throw new ArgsException(ErrorCode.INVALID_ARGUMENT_NAME, elementId, null);
	}

	private void parseArgumentStrings(List<String> argsList) throws ArgsException
	{
		for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); ) {
			String argString = currentArgument.next();
			if ( !argString.startsWith("-") ) {
				currentArgument.previous();
				break;
			}

			parseArgumentCharacters(argString.substring(1));
		}
	}

	private void parseArgumentCharacters(String argChars) throws ArgsException {
		for (int i = 0; i < argChars.length(); i++)
			parseArgumentCharacter(argChars.charAt(i));
	}

	private void parseArgumentCharacter(char argChar) throws ArgsException {
		ArgumentMarshaler<?> m = marshalers.get(argChar);
		if (m == null)
			throw new ArgsException(ErrorCode.UNEXPECTED_ARGUMENT, argChar, null);

		argsFound.add(argChar);
		try {
			m.set(currentArgument);
		} catch (ArgsException e) {
			e.setErrorArgumentId(argChar);
			throw e;
		}
	}

	public boolean has(char arg) {
		return argsFound.contains(arg);
	}

	public <T> T get(char arg) throws ArgsException
	{
		return ((ArgumentMarshaler<T>) marshalers.get(arg)).get();
	}
}
