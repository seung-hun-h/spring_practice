package com.example.cleancode.args;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
	private String[] stringArrayValue;

	public static String[] getValue(ArgumentMarshaler argumentMarshaler) {
		if (argumentMarshaler instanceof StringArrayArgumentMarshaler stringArrayArgumentMarshaler) {
			return stringArrayArgumentMarshaler.stringArrayValue;
		}
		return new String[0];
	}

	@Override
	public void set(ListIterator<String> currentArgument) throws ArgsException {
		List<String> stringArrayList = new ArrayList<>();

		for (String stringArgument = currentArgument.next(); currentArgument.hasNext(); ) {
			stringArrayList.add(stringArgument);
		}

		stringArrayValue = stringArrayList.toArray(new String[0]);
	}
}
