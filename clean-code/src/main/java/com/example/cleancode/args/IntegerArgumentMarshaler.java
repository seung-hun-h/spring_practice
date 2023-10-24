package com.example.cleancode.args;

import static com.example.cleancode.args.ArgsException.*;
import static com.example.cleancode.args.ArgsException.ErrorCode.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
	private int intValue = 0;

	public static int getValue(ArgumentMarshaler argumentMarshaler) {
		if (argumentMarshaler instanceof IntegerArgumentMarshaler integerArgumentMarshaler) {
			return integerArgumentMarshaler.intValue;
		}
		return 0;
	}

	@Override
	public void set(ListIterator<String> currentArgument) throws ArgsException {
		String parameter;
		try {
			parameter = currentArgument.next();
			intValue = Integer.parseInt(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(MISSING_STRING);
		} catch (NumberFormatException e) {
			throw new ArgsException(INVALID_INTEGER);
		}
	}
}
