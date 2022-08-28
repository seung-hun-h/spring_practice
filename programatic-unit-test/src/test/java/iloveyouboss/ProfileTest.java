package iloveyouboss;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iloveyouboss.domain.Answer;
import iloveyouboss.domain.Bool;
import iloveyouboss.domain.BooleanQuestion;
import iloveyouboss.domain.Criteria;
import iloveyouboss.domain.Criterion;
import iloveyouboss.domain.PercentileQuestion;
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

	int[] ids(Collection<Answer> answers) {
		return answers.stream()
			.mapToInt(answer -> answer.getQuestion().getId())
			.toArray();
	}

	@Test
	void findAnswersBasedOnPredicate() {
		// given
		profile.add(new Answer(new BooleanQuestion(1, "1"), Bool.FALSE));
		profile.add(new Answer(new PercentileQuestion(2, "2", new String[]{}), 0));
		profile.add(new Answer(new PercentileQuestion(3, "3", new String[]{}), 0));

		// when
		List<Answer> answers = profile.find(answer -> answer.getQuestion().getClass() == PercentileQuestion.class);

		// then
		assertArrayEquals(ids(answers), new int[]{2, 3});
	}

	@Test
	void findAnswersUnionComplementBasedOnPredicate() {
		// given
		profile.add(new Answer(new BooleanQuestion(1, "1"), Bool.FALSE));
		profile.add(new Answer(new PercentileQuestion(2, "2", new String[]{}), 0));
		profile.add(new Answer(new PercentileQuestion(3, "3", new String[]{}), 0));

		List<Answer> allAnswers = new ArrayList<>();
		// when
		List<Answer> answers = profile.find(answer -> answer.getQuestion().getClass() == PercentileQuestion.class);
		List<Answer> answersComplement = profile.find(answer -> answer.getQuestion().getClass() != PercentileQuestion.class);

		allAnswers.addAll(answersComplement);
		allAnswers.addAll(answers);

		// then
		assertArrayEquals(ids(allAnswers), new int[]{1, 2, 3});
	}
}