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
		score = 0;

		boolean kill = false;
		boolean anyMatches = false;

		for (Criterion criterion : criteria) {
			Answer answer = answers.get(criterion.getAnswer().getQuestionText());
			boolean match = criterion.getWeight() == Weight.DontCare || answer.match(criterion.getAnswer());

			if (!match && criterion.getWeight() == Weight.MustMatch) {
				kill = true;
			}

			if (match) {
				score += criterion.getWeight().getValue();
			}

			anyMatches |= match;
		}

		if (kill) {
			return false;
		}

		return anyMatches;
	}

	public List<Answer> find(Predicate<Answer> pred) {
		return answers.values().stream()
			.filter(pred)
			.collect(Collectors.toList());
	}
}
