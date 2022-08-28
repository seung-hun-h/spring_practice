package iloveyouboss.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Profile {
	private Map<String, Answer> answers = new HashMap<>();
	private int score;
	private String name;

	public Profile(String name) {
		this.name = name;
	}

	public void add(Answer answer) {
		answers.put(answer.getQuestionText(), answer);
	}

	public String getName() {
		return name;
	}

	public boolean matches(Criteria criteria) {
		calculateScore(criteria);
		if (doesNotMeetAnyMustMatchCriterion(criteria)) {
			return false;
		}

		return anyMatches(criteria);
	}

	private void calculateScore(Criteria criteria) {
		score = 0;
		for (Criterion criterion : criteria) {
			if (criterion.matches(answerMatching(criterion))) {
				score += criterion.getWeight().getValue();
			}
		}
	}

	private boolean doesNotMeetAnyMustMatchCriterion(Criteria criteria) {
		for (Criterion criterion : criteria) {
			boolean match = criterion.matches(answerMatching(criterion));
			if (!match && criterion.getWeight() == Weight.MustMatch) {
				return true;
			}
		}

		return false;
	}

	private Answer answerMatching(Criterion criterion) {
		return answers.get(criterion.getAnswer().getQuestionText());
	}

	private boolean anyMatches(Criteria criteria) {
		boolean anyMatches = false;
		for (Criterion criterion : criteria) {
			boolean match = criterion.matches(answerMatching(criterion));
			anyMatches |= match;
		}

		return anyMatches;
	}

	public List<Answer> find(Predicate<Answer> pred) {
		return answers.values().stream()
			.filter(pred)
			.collect(Collectors.toList());
	}

	public int score() {
		return score;
	}
}
