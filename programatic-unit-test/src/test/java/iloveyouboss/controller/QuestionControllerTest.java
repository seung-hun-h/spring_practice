package iloveyouboss.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iloveyouboss.domain.Question;

public class QuestionControllerTest {
	private QuestionController controller;

	@BeforeEach
	public void create() {
		controller = new QuestionController();
		controller.deleteAll();
	}

	@AfterEach
	public void cleanup() {
		controller.deleteAll();
	}

	@Test
	public void findsPersistedQuestionById() {
		int id = controller.addBooleanQuestion("question text");

		Question question = controller.find(id);

		Assertions.assertEquals(question.getText(), "question text");
	}

	@Test
	public void questionAnswersDateAdded() {
		Instant now = new Date().toInstant();
		controller.setClock(Clock.fixed(now, ZoneId.of("America/Denver")));
		int id = controller.addBooleanQuestion("text");

		Question question = controller.find(id);

		assertEquals(question.getCreateTimestamp(), now);
	}

	@Test
	public void answersMultiplePersistedQuestions() {
		controller.addBooleanQuestion("q1");
		controller.addBooleanQuestion("q2");
		controller.addPercentileQuestion("q3", new String[] { "a1", "a2"});

		List<Question> questions = controller.getAll();

		assertIterableEquals(questions.stream()
				.map(Question::getText)
				.collect(Collectors.toList()),
			Arrays.asList("q1", "q2", "q3"));
	}
	@Test
	public void findsMatchingEntries() {
		controller.addBooleanQuestion("alpha 1");
		controller.addBooleanQuestion("alpha 2");
		controller.addBooleanQuestion("beta 1");

		List<Question> questions = controller.findWithMatchingText("alpha");

		assertIterableEquals(questions.stream()
				.map(Question::getText)
				.collect(Collectors.toList()),
			Arrays.asList("alpha 1", "alpha 2"));
	}
}