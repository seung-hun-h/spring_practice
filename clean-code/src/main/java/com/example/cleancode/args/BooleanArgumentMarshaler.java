package com.example.cleancode.args;

import java.util.ListIterator;

public class BooleanArgumentMarshaler implements ArgumentMarshaler {
	private boolean booleanValue;

	public static boolean getValue(ArgumentMarshaler argumentMarshaler) {
		if (argumentMarshaler instanceof BooleanArgumentMarshaler booleanArgumentMarshaler) {
			return booleanArgumentMarshaler.booleanValue;
		}
		return false;
	}

	@Override
	public void set(ListIterator<String> currentArgument) throws ArgsException {
		booleanValue = true;
	}
}
