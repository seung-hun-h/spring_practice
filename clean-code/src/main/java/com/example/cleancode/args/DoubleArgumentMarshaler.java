package com.example.cleancode.args;

import static com.example.cleancode.args.ArgsException.*;
import static com.example.cleancode.args.ArgsException.ErrorCode.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
	private double doubleValue;

	public static double getValue(ArgumentMarshaler argumentMarshaler) {
		if (argumentMarshaler instanceof DoubleArgumentMarshaler doubleArgumentMarshaler) {
			return doubleArgumentMarshaler.doubleValue;
		}
		return 0.0;
	}
	@Override
	public void set(ListIterator<String> currentArgument) throws ArgsException {
		String parameter;
		try {
			parameter = currentArgument.next();
			doubleValue = Double.parseDouble(parameter);
		} catch (NoSuchElementException e) {
			throw new ArgsException(MISSING_DOUBLE);
		} catch (NumberFormatException e) {
			throw new ArgsException(INVALID_DOUBLE);
		}
	}
}
