package com.mythos;

import java.util.*;

public class IntegerListArgumentMarshaler implements ArgumentMarshaler {
	private List<Integer> integerListValue = null;

	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;

		integerListValue = new ArrayList<Integer>();
		try {
			parameter = currentArgument.next();
			integerListValue.add(Integer.parseInt(parameter));

			while (currentArgument.hasNext()) {
				parameter = currentArgument.next();
				if (parameter.startsWith("-"))
					break;

				integerListValue.add(Integer.parseInt(parameter));
			}
		} catch (NoSuchElementException e) {
			throw new ArgsException(ErrorCode.MISSING_INTEGER);
		} catch (NumberFormatException e) {
			throw new ArgsException(ErrorCode.INVALID_INTEGER, parameter);
		}
	}

	public static List<Integer> getValue(ArgumentMarshaler am)
	{
		if (am != null && am instanceof IntegerListArgumentMarshaler)
			return ((IntegerListArgumentMarshaler) am).integerListValue;

		return null;
	}
}
