package item03.singleton;

import java.io.Serial;
import java.io.Serializable;

public class Elvis implements Serializable {
	private static final Elvis INSTANCE = new Elvis();

	private Elvis() {

	}

	public static Elvis getInstance() {
		return INSTANCE;
	}

	@Serial
	private Object readResolve() {
		return INSTANCE;
	}
}
