package com.example.cleancode.args;

import java.util.ListIterator;

public interface ArgumentMarshaler {
	void set(ListIterator<String> currentArgument) throws ArgsException;
}
