package item20.impl;

public class CandyInvending implements Invending{
	@Override
	public void start() {
		System.out.println("Start Vending machine");
	}

	@Override
	public void chooseProduct() {
		System.out.println("Produce different candies");
		System.out.println("Choose a type of candy");
		System.out.println("pay for candy");
		System.out.println("collect candy");
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
