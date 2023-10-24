package com.example.cleancode.args;

import static com.example.cleancode.args.ArgsException.*;
import static com.example.cleancode.args.ArgsException.ErrorCode.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class Args {
	private Map<Character, ArgumentMarshaler> mashalers;
	private Set<Character> argsFound;
	private ListIterator<String> currentArgument;

	public Args(String schema, String[] args) throws ArgsException {
		mashalers = new HashMap<>();
		argsFound = new HashSet<>();

		parseSchema(schema);
		parseArgumentStrings(Arrays.asList(args));
	}

	private void parseSchema(String schema) throws ArgsException {
		for (String element : schema.split(",")) {
			if (!element.isEmpty()) {
				parseSchemaElement(element.trim());
			}
		}
	}

	private void parseSchemaElement(String element) throws ArgsException {
		char elementId = element.charAt(0);
		String elementTail = element.substring(1);
		validateSchemaElementId(elementId);
		if (elementTail.isEmpty()) {
			mashalers.put(elementId, new BooleanArgumentMarshaler());
		} else if (elementTail.equals("*")) {
			mashalers.put(elementId, new StringArgumentMarshaler());
		} else if (elementTail.equals("#")) {
			mashalers.put(elementId, new IntegerArgumentMarshaler());
		} else if (elementTail.equals("##")) {
			mashalers.put(elementId, new DoubleArgumentMarshaler());
		} else if (elementTail.equals("[*]")) {
			mashalers.put(elementId, new StringArrayArgumentMarshaler());
		} else {
			throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
		}
	}

	private void validateSchemaElementId(char elementId) throws ArgsException {
		if (!Character.isLetter(elementId)) {
			throw new ArgsException(INVALID_ARGUMENT_NAME, elementId, null);
		}
	}

	private void parseArgumentStrings(List<String> argsList) throws ArgsException {
		for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); ) {
			String argString = currentArgument.next();
			if (argString.startsWith("-")) {
				parseArgumentCharacters(argString.substring(1));
			} else {
				currentArgument.previous();
				break;
			}
		}
	}

	private void parseArgumentCharacters(String argChars) throws ArgsException {
		for (int i = 0; i < argChars.length(); i++) {
			parseArgumentCharacter(argChars.charAt(i));
		}
	}

	private void parseArgumentCharacter(char argChar) throws ArgsException {
		ArgumentMarshaler argumentMarshaler = mashalers.get(argChar);
		if (argumentMarshaler == null) {
			throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
		} else {
			argsFound.add(argChar);
			try {
				argumentMarshaler.set(currentArgument);
			} catch (ArgsException e) {
				e.setErrorArgumentId(argChar);
				throw e;
			}
		}
	}

	public boolean has(char arg) {
		return argsFound.contains(arg);
	}

	public int nextArgument() {
		return currentArgument.nextIndex();
	}

	public boolean getBoolean(char arg) {
		return BooleanArgumentMarshaler.getValue(mashalers.get(arg));
	}

	public int getInt(char arg) {
		return IntegerArgumentMarshaler.getValue(mashalers.get(arg));
	}

	public String getString(char arg) {
		return StringArgumentMarshaler.getValue(mashalers.get(arg));
	}

	public String[] getStringArray(char arg) {
		return StringArrayArgumentMarshaler.getValue(mashalers.get(arg));
	}
}
