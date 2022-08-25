package iloveyouboss.domain;

import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {
	private List<Scoreable> scores = new ArrayList<>();

	public void add(Scoreable score) {
		if (score == null) {
			throw new IllegalStateException();
		}
		scores.add(score);
	}

	public int arithmeticMean() {
		if (scores.isEmpty()) return 0;

		long sum = scores.stream().mapToLong(Scoreable::getScore).sum();
		return (int)(sum / scores.size());
	}
}
