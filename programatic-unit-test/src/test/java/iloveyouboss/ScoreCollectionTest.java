package iloveyouboss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import iloveyouboss.domain.ScoreCollection;

class ScoreCollectionTest {

	@Test
	void answersArithmeticMeanOfTwoNumbers() {
	    // given
		ScoreCollection scoreCollection = new ScoreCollection();
		scoreCollection.add(() -> 6);
		scoreCollection.add(() -> 7);

		// when
		int actual = scoreCollection.arithmeticMean();

		// then
		assertEquals(actual, 6);
	}

}