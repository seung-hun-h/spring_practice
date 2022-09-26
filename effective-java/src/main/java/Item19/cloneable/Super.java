package Item19.cloneable;

public class Super implements Cloneable {

	Super() {
	}

	@Override
	public Super clone() throws CloneNotSupportedException {
		final Super clone = (Super) super.clone();
		clone.doSomething();
		return clone;
	}
	void doSomething() { // Overridable
		System.out.println("doSomething from origin");
	}
}
