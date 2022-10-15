package item31;

import java.math.BigInteger;
import java.util.Random;

public class MyInteger extends BigInteger {
	private final int value;

	public MyInteger(int value) {
		super(value, new Random());
		this.value = value;
	}

	@Override
	public byte byteValue() {
		return super.byteValue();
	}

	@Override
	public short shortValue() {
		return super.shortValue();
	}

	@Override
	public int intValue() {
		return value;
	}

	@Override
	public long longValue() {
		return value;
	}

	@Override
	public float floatValue() {
		return value;
	}

	@Override
	public double doubleValue() {
		return value;
	}
}
