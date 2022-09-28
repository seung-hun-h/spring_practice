package item20.skeletal;

public class CandyVending implements Invending {
	private final AbstractVendingDelegator delegator = new AbstractVendingDelegator();

	@Override
	public void start() {
		delegator.start();
	}

	@Override
	public void chooseProduct() {
		delegator.chooseProduct();
	}

	@Override
	public void stop() {
		delegator.stop();
	}

	@Override
	public void process() {
		delegator.process();
	}

	private class AbstractVendingDelegator extends AbstractVending {

		@Override
		public void chooseProduct() {
			System.out.println("Produce different candies");
			System.out.println("Choose a type of candy");
			System.out.println("pay for candy");
			System.out.println("collect candy");
		}
	}


	public static void main(String[] args) {
		Invending candyVending = new CandyVending();
		candyVending.process();
	}
}
