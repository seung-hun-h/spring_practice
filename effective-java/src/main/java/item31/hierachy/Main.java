package item31.hierachy;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<AImpl> list = new ArrayList<>();
		B a = new B();
		list.add(a);
	}
}
