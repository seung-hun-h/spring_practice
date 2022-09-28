package item20.skeletal;

public abstract class AbstractVending implements Invending {
	public void start() {
		System.out.println("Start Vending machine");
	}

	public void stop() {
		System.out.println("Stop Vending machine");
	}

	public void process() {
		start();
		chooseProduct();
		start();
	}
}
