package com.mythos;

import java.util.*;

import com.mythos.ArgsException.ErrorCode;

public class DoubleArgumentMarshaler implements ArgumentMarshaler{
	private double doubleValue = Double.NaN;

	public void set(Iterator<String> currentArgument) throws ArgsException {
		String parameter = null;

		try {
			parameter = currentArgument.next();
			doubleValue = Double.parseDouble(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(ErrorCode.MISSING_DOUBLE);
		} catch (NumberFormatException e) {
			throw new ArgsException(ErrorCode.INVALID_DOUBLE, parameter);
		}
	}

	public static double getValue(ArgumentMarshaler am)
	{
		if (am != null && am instanceof DoubleArgumentMarshaler)
			return ((DoubleArgumentMarshaler) am).doubleValue;

		return Double.NaN;
	}
}
