package iloveyouboss.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfileTest {
	private Profile profile;
	private Question questionIsThereRelocation;
	private Question questionReimbursesTuition;
	private Answer answerThereIsRelocation;
	private Answer answerThereIsNotRelocation;
	private Answer answerDoesNotReimburseTuition;
	private Answer answerReimburseTuition;

	private Criteria criteria;

	@BeforeEach
	void createProfile() {
		profile = new Profile("1");
	}

	@BeforeEach
	void createQuestion() {
		questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
		questionReimbursesTuition = new BooleanQuestion(1, "Reimburses tuition?");
	}

	@BeforeEach
	void createAnswer() {
		answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
		answerThereIsNotRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);
		answerDoesNotReimburseTuition = new Answer(questionReimbursesTuition, Bool.FALSE);
		answerReimburseTuition = new Answer(questionReimbursesTuition, Bool.TRUE);
	}

	@BeforeEach
	void createCriteria() {
		criteria = new Criteria();
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

	@Test
	void doesNotMatchWhenNoMatchingAnswer() {
	    // given
		profile.add(answerThereIsNotRelocation);
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

		// when
		boolean result = profile.matches(criterion);

	    // then
		assertFalse(result);
	}

	@Test
	void matchesWhenContainsMultipleAnswers() {
	    // given
		profile.add(answerThereIsRelocation);
		profile.add(answerDoesNotReimburseTuition);
		Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

		// when
		boolean result = profile.matches(criterion);

	    // then
		assertTrue(result);
	}
	@Test
	void doesNotMatchWhenNoneOfMultipleCriteriaMatch() {
	    // given
		profile.add(answerThereIsRelocation);
		criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
		criteria.add(new Criterion(answerReimburseTuition, Weight.Important));

		// when
		boolean result = profile.matches(criteria);

	    // then
		assertTrue(result);
	}

	@Test
	void doesNotMatchWhenAnyMustMeetCriteriaNotMet() {
	    // given
		profile.add(answerThereIsRelocation);
		criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
		criteria.add(new Criterion(answerReimburseTuition, Weight.MustMatch));

		// when
		boolean result = profile.matches(criteria);

	    // then
		assertFalse(result);
	}

	@Test
	void doesNotMatchWhenCriterionIsDontCare() {
	    // given
		profile.add(answerDoesNotReimburseTuition);
		criteria.add(new Criterion(answerReimburseTuition, Weight.DontCare));

		// when
		boolean result = profile.matches(criteria);

	    // then
		assertTrue(result);
	}

	@Test
	void scoreIsZeroWhenThereAreNoMatches() {
	    // given
	    criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

	    // when
		ProfileMatch profileMatch = profile.match(criteria);

	    // then
		assertEquals(profileMatch.getScore(), 0);
	}
}
