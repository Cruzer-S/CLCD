package com.mythos;

import java.util.*;

public class IntegerArgumentMarshaler extends ArgumentMarshaler<Integer> {
	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;

		try {
			parameter = currentArgument.next();
			value = Integer.parseInt(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(ErrorCode.MISSING_INTEGER);
		} catch (NumberFormatException e) {
			throw new ArgsException(ErrorCode.INVALID_INTEGER, parameter);
		}
	}
}
