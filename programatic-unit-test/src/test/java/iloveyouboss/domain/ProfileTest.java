package iloveyouboss.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProfileTest {
	@Test
	void matchesNothingWhenProfileEmpty() {
	    // given
		Profile profile = new Profile();
		BooleanQuestion question = new BooleanQuestion(1, "Relocation package?");
		Criterion criterion = new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare);

		// when
		boolean result = profile.matches(criterion);

	    // then
		assertFalse(result);
	}

	@Test
	void matchesNothingWhenProfileContainsMatchingAnswer() {
	    // given
		Profile profile = new Profile();
		BooleanQuestion question = new BooleanQuestion(1, "Relocation package?");
		Answer answer = new Answer(question, Bool.TRUE);
		profile.add(answer);
		Criterion criterion = new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare);

		// when
		boolean result = profile.matches(criterion);

	    // then
		assertTrue(result);
	}

}
