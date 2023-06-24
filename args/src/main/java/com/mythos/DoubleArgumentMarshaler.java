package com.mythos;

import java.util.*;

public class DoubleArgumentMarshaler extends ArgumentMarshaler<Double> {
	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;

		try {
			parameter = currentArgument.next();
			value = Double.parseDouble(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(ErrorCode.MISSING_DOUBLE);
		} catch (NumberFormatException e) {
			throw new ArgsException(ErrorCode.INVALID_DOUBLE, parameter);
		}
	}
}
