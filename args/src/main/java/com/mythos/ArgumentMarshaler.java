package com.mythos;

import java.util.*;

public abstract class ArgumentMarshaler<T> {
	protected T value;

	abstract void set(Iterator<String> currentArgument) throws ArgsException;
	T get()
	{
		return value;
	}
}
