package item38;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public enum ExtendedOperation implements Operation{
	EXP("^") {
		@Override
		public double apply(double x, double y) {
			return Math.pow(x, y);
		}
	},
	REMAINDER("%") {
		@Override
		public double apply(double x, double y) {
			return x % y;
		}
	};

	private final String symbol;

	ExtendedOperation(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}
/*
	public static void main(String[] args) {
		double x = 1.4;
		double y = 2.1;
		test(ExtendedOperation.class, x, y);
	}

	private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
		for (Operation op : opEnumType.getEnumConstants()) {
			System.out.println("op.apply(x, y) = " + op.apply(x, y));
		}
	}
 */

	public static void main(String[] args) {
		double x = 1.4;
		double y = 2.1;
		test(Arrays.asList(ExtendedOperation.values()), x, y);
	}

	private static void test(Collection<? extends Operation> opSet, double x, double y) {
		for (Operation operation : opSet) {
			System.out.println("operation.apply(x, y) = " + operation.apply(x, y));
		}
	}
}
