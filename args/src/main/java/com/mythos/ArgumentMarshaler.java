package com.mythos;

import java.util.*;

public interface ArgumentMarshaler {
	void set(Iterator<String> currentArgument) throws ArgsException;
}
