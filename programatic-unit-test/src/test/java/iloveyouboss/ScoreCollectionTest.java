package iloveyouboss;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import iloveyouboss.domain.Answer;
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


	@Test
	void throwsExceptionWhenAddingNull() {
	    // given
		ScoreCollection scoreCollection = new ScoreCollection();

		// when & then
		assertThrows(IllegalStateException.class, () -> scoreCollection.add(null));
	}

	@Test
	void answerZeroWhenNoElementsAdded() {
	    // given
		ScoreCollection scoreCollection = new ScoreCollection();

		// when
		int result = scoreCollection.arithmeticMean();

		// then
		assertEquals(result, 0);
	}

	@Test
	void dealsWithIntegerOverflow() {
	    // given
		ScoreCollection scoreCollection = new ScoreCollection();
		scoreCollection.add((() -> Integer.MAX_VALUE));
		scoreCollection.add(() -> 1);

		// when
		int result = scoreCollection.arithmeticMean();

		// then
		assertEquals(result, 1073741824);
	}
}