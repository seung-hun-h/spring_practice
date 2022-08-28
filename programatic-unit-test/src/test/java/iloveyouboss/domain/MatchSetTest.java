package iloveyouboss.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatchSetTest {
	private Criteria criteria;
	private Question questionReimbursesTuition;
	private Answer answerReimbursesTuition;
	private Answer answerDoesNotReimburseTuition;

	private Question questionIsThereRelocation;
	private Answer answerThereIsRelocation;
	private Answer answerThereIsNoRelocation;

	private Question questionOnsiteDaycare;
	private Answer answerNoOnsiteDaycare;
	private Answer answerHasOnsiteDaycare;

	private Map<String, Answer> answers;

	@BeforeEach
	public void createAnswers() {
		answers = new HashMap<>();
	}

	@BeforeEach
	public void createCriteria() {
		criteria = new Criteria();
	}

	@BeforeEach
	public void createQuestionAndAnswers() {
		questionIsThereRelocation =
			new BooleanQuestion(1, "Relocation package?");
		answerThereIsRelocation =
			new Answer(questionIsThereRelocation, Bool.TRUE);
		answerThereIsNoRelocation =
			new Answer(questionIsThereRelocation, Bool.FALSE);

		questionReimbursesTuition =
			new BooleanQuestion(1, "Reimburses tuition?");
		answerReimbursesTuition =
			new Answer(questionReimbursesTuition, Bool.TRUE);
		answerDoesNotReimburseTuition =
			new Answer(questionReimbursesTuition, Bool.FALSE);

		questionOnsiteDaycare =
			new BooleanQuestion(1, "Onsite daycare?");
		answerHasOnsiteDaycare =
			new Answer(questionOnsiteDaycare, Bool.TRUE);
		answerNoOnsiteDaycare =
			new Answer(questionOnsiteDaycare, Bool.FALSE);
	}

	private void add(Answer answer) {
		answers.put(answer.getQuestionText(), answer);
	}

	private MatchSet createMatchSet() {
		return new MatchSet(answers, criteria);
	}

	@Test
	void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
		// given
		add(answerDoesNotReimburseTuition);

		// when
		criteria.add(new Criterion(answerReimbursesTuition, Weight.MustMatch));

		// then
		assertFalse(createMatchSet().matches());
	}

	@Test
	void matchAnswersTrueForAnyDontCareCriteria() {
	    // given
		add(answerDoesNotReimburseTuition);

	    // when
		criteria.add(new Criterion(answerReimbursesTuition, Weight.DontCare));

	    // then
		assertTrue(createMatchSet().matches());
	}
	
}