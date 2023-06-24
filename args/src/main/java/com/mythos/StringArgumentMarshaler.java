package com.mythos;

import java.util.*;

public class StringArgumentMarshaler extends ArgumentMarshaler<String> {
	public void set(Iterator<String> currentArgument) throws ArgsException {
		try {
			value = currentArgument.next();
		} catch (NoSuchElementException e) {
			throw new ArgsException(ErrorCode.MISSING_STRING);
		}
	}
}
