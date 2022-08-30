package iloveyouboss.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfileTest {
	private Profile profile;
	private Question questionIsThereRelocation;
	private Answer answerThereIsRelocation;

	@BeforeEach
	void createProfile() {
		profile = new Profile();
	}

	@BeforeEach
	void createQuestion() {
		questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
	}

	@BeforeEach
	void createAnswer() {
		answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
	}

	@Test
	void matchesNothingWhenProfileEmpty() {
	    // given
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.DontCare);

		// when
		boolean result = profile.matches(criterion);

	    // then
		assertFalse(result);
	}

	@Test
	void matchesNothingWhenProfileContainsMatchingAnswer() {
	    // given
		profile.add(answerThereIsRelocation);
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.DontCare);

		// when
		boolean result = profile.matches(criterion);

	    // then
		assertTrue(result);
	}

}
