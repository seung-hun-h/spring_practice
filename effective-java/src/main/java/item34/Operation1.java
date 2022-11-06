package item34;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operation1 {
	PLUS("+") {
		@Override
		public double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS("-") {
		@Override
		public double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		@Override
		public double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE("/") {
		@Override
		public double apply(double x, double y) {
			return x / y;
		}
	};

	public abstract double apply(double x, double y);

	private static Map<String, Operation1> stringToEnum = Stream.of(values()).collect(Collectors.toMap(Objects::toString, e -> e));

	private final String symbol;

	Operation1(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}

	public static Optional<Operation1> fromString(String symbol) {
		return Optional.ofNullable(stringToEnum.get(symbol));
	}
}
