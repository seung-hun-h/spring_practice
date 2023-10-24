package com.example.cleancode.args;

import static com.example.cleancode.args.ArgsException.*;
import static com.example.cleancode.args.ArgsException.ErrorCode.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class StringArgumentMarshaler implements ArgumentMarshaler {
	private String stringValue;

	public static String getValue(ArgumentMarshaler argumentMarshaler) {
		if (argumentMarshaler instanceof StringArgumentMarshaler stringArgumentMarshaler) {
			return stringArgumentMarshaler.stringValue;
		}
		return "";
	}

	@Override
	public void set(ListIterator<String> currentArgument) throws ArgsException {
		try {
			stringValue = currentArgument.next();
		} catch (NoSuchElementException e) {
			throw new ArgsException(MISSING_STRING);
		}
	}
}
