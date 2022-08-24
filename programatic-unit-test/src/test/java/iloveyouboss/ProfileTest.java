package iloveyouboss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iloveyouboss.domain.Answer;
import iloveyouboss.domain.Bool;
import iloveyouboss.domain.BooleanQuestion;
import iloveyouboss.domain.Criteria;
import iloveyouboss.domain.Criterion;
import iloveyouboss.domain.Profile;
import iloveyouboss.domain.Question;
import iloveyouboss.domain.Weight;

class ProfileTest {
	private Profile profile;
	private Question question;
	private Criteria criteria;

	@BeforeEach
	void setUp() {
		profile = new Profile("Bull Hockey, Inc.");
		question = new BooleanQuestion("Got bonuses?");
		criteria = new Criteria();
	}

	@Test
	void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
	    // given
		profile.add(new Answer(question, Bool.FALSE));
		Criterion criterion = new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch);
		criteria.add(criterion);

		// when
		boolean result = profile.matches(criteria);

		// then
		assertFalse(result);
	}

	@Test
	void matchAnswersTrueForAnyDontCareCriteria() {
	    // given
		profile.add(new Answer(question, Bool.FALSE));
		Criterion criterion = new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare);
		criteria.add(criterion);

		// when
		boolean result = profile.matches(criteria);

		// then
		assertTrue(result);
	}
}