package item20.extend;

public abstract class AbstractVending {
	public void start() {
		System.out.println("Start Vending machine");
	}

	public abstract void chooseProduct();

	public void stop() {
		System.out.println("Stop Vending machine");
	}

	public void process() {
		start();
		chooseProduct();
		start();
	}
}
