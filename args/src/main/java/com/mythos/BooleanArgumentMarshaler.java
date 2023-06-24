package com.mythos;

import java.util.*;

public class BooleanArgumentMarshaler extends ArgumentMarshaler<Boolean> {
	public void set(Iterator<String> currentArgument) throws ArgsException {
		value = true;
	}
}
