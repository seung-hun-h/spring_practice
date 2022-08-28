package iloveyouboss.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Profile {
	private AnswerCollection answers = new AnswerCollection();
	private String name;

	public Profile(String name) {
		this.name = name;
	}

	public void add(Answer answer) {
		answers.add(answer);
	}

	public String getName() {
		return name;
	}

	public MatchSet getMatchSet(Criteria criteria) {
		return new MatchSet(answers, criteria);
	}
}
