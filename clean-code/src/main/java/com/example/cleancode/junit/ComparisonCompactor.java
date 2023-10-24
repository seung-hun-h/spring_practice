package com.example.cleancode.junit;

import java.util.function.Supplier;

public class ComparisonCompactor {
	private static final String ELLIPSIS = "...";
	private static final String DELTA_END = "]";
	private static final String DELTA_START = "[";

	private final int contextLength;
	private final String expected;
	private final String actual;

	private int prefixLength;
	private int suffixLength;
	public ComparisonCompactor(int contextLength, String expected, String actual) {
		this.contextLength = contextLength;
		this.expected = expected;
		this.actual = actual;
	}

	public String formatCompactedComparison(String message) {
		String compactExpected = expected;
		String compactActual = actual;
		if (canBeCompacted()) {
			findCommonPrefixAndSuffix();
			compactExpected = compact(expected);
			compactActual = compact(actual);
		}

		return format(message, compactExpected, compactActual).get();
	}

	private boolean canBeCompacted() {
		return expected != null && actual != null && !expected.equals(actual);
	}

	private void findCommonPrefix() {
		prefixLength = 0;
		int end = Math.min(expected.length(), actual.length());
		for (; prefixLength < end; prefixLength++) {
			if (expected.charAt(prefixLength) != actual.charAt(prefixLength)) {
				break;
			}
		}
	}

	private void findCommonPrefixAndSuffix() {
		findCommonPrefix();
		suffixLength = 0;

		for (; !isSuffixOverlapsPrefix(suffixLength); suffixLength++) {
			if (charFromEnd(expected, suffixLength) != charFromEnd(actual, suffixLength)) {
				break;
			}
		}
	}

	private char charFromEnd(String s, int i) {
		return s.charAt(s.length() - i - 1);
	}

	private boolean isSuffixOverlapsPrefix(int suffixLength) {
		return actual.length() - suffixLength <= prefixLength ||
			expected.length() - suffixLength <= prefixLength;
	}

	private String compact(String s) {
		return new StringBuilder()
			.append(startingEllipsis())
			.append(startingContext())
			.append(DELTA_START)
			.append(delta(s))
			.append(DELTA_END)
			.append(endingContext())
			.append(endingEllipsis())
			.toString();
	}

	private String startingEllipsis() {
		return prefixLength > contextLength ? ELLIPSIS : "";
	}

	private String startingContext() {
		int contextStart = Math.max(0, prefixLength - contextLength);
		int contextEnd = prefixLength;
		return expected.substring(contextStart, contextEnd);
	}

	private String delta(String s) {
		int deltaStart = prefixLength;
		int deltaEnd = s.length() - suffixLength;
		return s.substring(deltaStart, deltaEnd);
	}

	private String endingContext() {
		int contextStart = expected.length() - suffixLength;
		int contextEnd = Math.min(contextStart + contextLength, expected.length());
		return expected.substring(contextStart, contextEnd);
	}

	private String endingEllipsis() {
		return suffixLength > contextLength ? ELLIPSIS : "";
	}

	private Supplier<String> format(String message, String expected, String actual) {
		if (message == null) {
			return () -> String.format("expected: <%s> but was: <%s>", expected, actual);
		}
		return () -> String.format("%s expected: <%s> but was: <%s>", message, expected, actual);
	}

	public static void main(String[] args) {
		String result = new ComparisonCompactor(2, "abcddefg", "abcdefg").formatCompactedComparison(null);
		System.out.println(result);
	}
}
