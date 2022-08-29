package transmission;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import transmission.Car;
import transmission.Gear;
import transmission.Transmission;

class TransmissionTest {
	private Transmission transmission;
	private Car car;

	@BeforeEach
	public void create() {
		car = new Car();
		transmission = new Transmission(car);
	}

	@Test
	void remainsInDriveAfterAcceleration() {
	    // given
	    transmission.shift(Gear.DRIVE);

	    // when
		car.accelerateTo(35);

	    // then
		assertEquals(transmission.getGear(), Gear.DRIVE);
	}

	@Test
	void ignoresShiftToParkWhileInDrive() {
	    // given
	    transmission.shift(Gear.DRIVE);
		car.accelerateTo(35);

	    // when
	    transmission.shift(Gear.PARK);

	    // then
		assertEquals(transmission.getGear(), Gear.DRIVE);
	}

	@Test
	void allowsShiftToParkWhenNotMoving() {
	    // given
	    transmission.shift(Gear.DRIVE);
		car.accelerateTo(30);
		car.brakeToStop();

	    // when
		transmission.shift(Gear.PARK);

	    // then
		assertEquals(transmission.getGear(), Gear.PARK);
	}
}