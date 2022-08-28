package iloveyouboss.domain;

import java.util.Map;

public class MatchSet {
	private Map<String, Answer> answers;
	private Criteria criteria;
	private int score;

	public MatchSet(Map<String, Answer> answers, Criteria criteria) {
		this.answers = answers;
		this.criteria = criteria;
		calculateScore(criteria);
	}

	private void calculateScore(Criteria criteria) {
		score = 0;
		for (Criterion criterion : criteria) {
			if (criterion.matches(answerMatching(criterion))) {
				score += criterion.getWeight().getValue();
			}
		}
	}

	private Answer answerMatching(Criterion criterion) {
		return answers.get(criterion.getAnswer().getQuestionText());
	}

	public int getScore() {
		return score;
	}

	public boolean matches() {
		if (doesNotMeetAnyMustMatchCriterion(criteria)) {
			return false;
		}

		return anyMatches(criteria);
	}

	private boolean anyMatches(Criteria criteria) {
		boolean anyMatches = false;
		for (Criterion criterion : criteria) {
			boolean match = criterion.matches(answerMatching(criterion));
			anyMatches |= match;
		}

		return anyMatches;
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
}
