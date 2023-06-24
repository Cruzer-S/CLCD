package com.mythos;

import java.util.*;

public class IntegerListArgumentMarshaler extends ArgumentMarshaler<List<Integer>> {
	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;

		value = new ArrayList<Integer>();
		try {
			parameter = currentArgument.next();
			value.add(Integer.parseInt(parameter));

			while (currentArgument.hasNext()) {
				parameter = currentArgument.next();
				if (parameter.startsWith("-")) {
					((ListIterator<String>) currentArgument).previous();
					break;
				}

				value.add(Integer.parseInt(parameter));
			}
		} catch (NoSuchElementException e) {
			throw new ArgsException(ErrorCode.MISSING_INTEGER);
		} catch (NumberFormatException e) {
			throw new ArgsException(ErrorCode.INVALID_INTEGER, parameter);
		}
	}
}
