package item03.notsingleton;

import java.io.Serializable;

public class Elvis implements Serializable {
	private static final Elvis INSTANCE = new Elvis();

	private Elvis() {

	}

	public static Elvis getInstance() {
		return INSTANCE;
	}
}
