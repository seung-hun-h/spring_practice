package iloveyouboss.domain;

import java.util.HashMap;
import java.util.Map;

public class Profile {
	private Map<String, Answer> answers = new HashMap<>();
	private String id;

	public Profile(String id) {
		this.id = id;
	}

	public boolean matches(Criterion criterion) {
		return criterion.getWeight() == Weight.DontCare
		|| criterion.getAnswer().match(getMatchingProfileAnswer(criterion));
	}

	public void add(Answer answer) {
		answers.put(answer.getQuestionText(), answer);
	}

	private Answer getMatchingProfileAnswer(Criterion criterion) {
		return answers.get(criterion.getAnswer().getQuestionText());
	}

	public boolean matches(Criteria criteria) {
		boolean matches = false;
		for (Criterion criterion : criteria) {
			if (matches(criterion)) {
				matches = true;
			} else if (criterion.getWeight() == Weight.MustMatch) {
				return false;
			}
		}

		return matches;
	}

	public ProfileMatch match(Criteria criteria) {
		return new ProfileMatch();
	}

	public String getId() {
		return id;
	}

	public MatchSet getMatchSet(Criteria criteria) {
		return new MatchSet(id, answers, criteria);
	}

}
