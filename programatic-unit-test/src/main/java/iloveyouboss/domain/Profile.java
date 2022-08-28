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
		MatchSet matchSet = new MatchSet(answers, criteria);
		score = matchSet.getScore();
		return matchSet.matches();
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