package iloveyouboss.scratch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BearingTest {
	@Test
	void throwsOnNegativeNumber() {
		assertThrows(BearingOutOfRangeException.class, () -> new Bearing(-1));
	}

	@Test
	void throwsWhenBearingTooLarge() {
		assertThrows(BearingOutOfRangeException.class, () -> new Bearing(Bearing.MAX + 1));
	}

	@Test
	void answersValidBearing() {
		assertEquals(new Bearing(Bearing.MAX).value(), Bearing.MAX);
	}

	@Test
	void answersAngelsBetweenItAndAnotherBearing() {
		assertEquals(new Bearing(15).angelBetween(new Bearing(12)), 3);
	}

	@Test
	void answersBetweenIsNegativeWhenThisBearingSmaller() {
		assertEquals(new Bearing(12).angelBetween(new Bearing(15)), -3);
	}
}