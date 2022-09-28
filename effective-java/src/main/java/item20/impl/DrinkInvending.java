package item20.impl;

public class DrinkInvending implements Invending{
	@Override
	public void start() {
		System.out.println("Start Vending machine");
	}

	@Override
	public void chooseProduct() {
		System.out.println("Produce different soft drinks");
		System.out.println("Choose a type of soft drinks");
		System.out.println("pay for drinks");
		System.out.println("collect drinks");
	}

	@Override
	public void stop() {
		System.out.println("Stop Vending machine");
	}

	@Override
	public void process() {
		start();
		chooseProduct();
		stop();
	}
}
