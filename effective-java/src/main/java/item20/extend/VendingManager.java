package item20.extend;

import item20.impl.CandyInvending;
import item20.impl.DrinkInvending;
import item20.impl.Invending;

public class VendingManager {
	public static void main(String[] args) {
		Invending candyInvending = new CandyInvending();
		Invending drinkInvending = new DrinkInvending();

		candyInvending.process();
		drinkInvending.process();
	}
}
