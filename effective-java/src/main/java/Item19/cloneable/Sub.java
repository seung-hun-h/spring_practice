package Item19.cloneable;

public class Sub extends Super {
	Sub() {
	}

	@Override
	void doSomething() {
		System.out.println("doSomething from clone");
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Super bc = new Sub();
		bc.clone();
	}
}
