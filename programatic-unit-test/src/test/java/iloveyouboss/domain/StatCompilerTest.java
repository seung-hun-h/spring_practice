package iloveyouboss.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import iloveyouboss.controller.QuestionController;

@ExtendWith(MockitoExtension.class)
public class StatCompilerTest {
	@Test
	public void responsesByQuestionAnswersCountsByQuestionText() {
		StatCompiler stats = new StatCompiler();
		List<BooleanAnswer> answers = new ArrayList<>();
		answers.add(new BooleanAnswer(1, true));
		answers.add(new BooleanAnswer(1, true));
		answers.add(new BooleanAnswer(1, true));
		answers.add(new BooleanAnswer(1, false));
		answers.add(new BooleanAnswer(2, true));
		answers.add(new BooleanAnswer(2, true));
		Map<Integer,String> questions = new HashMap<>();
		questions.put(1, "Tuition reimbursement?");
		questions.put(2, "Relocation package?");

		Map<String, Map<Boolean, AtomicInteger>> responses = stats.responsesByQuestion(answers, questions);

		assertEquals(responses.get("Tuition reimbursement?").get(Boolean.TRUE).get(), 3);
		assertEquals(responses.get("Tuition reimbursement?").get(Boolean.FALSE).get(),1);
		assertEquals(responses.get("Relocation package?").get(Boolean.TRUE).get(), 2);
		assertEquals(responses.get("Relocation package?").get(Boolean.FALSE).get(), 0);
	}

	@Mock
	private QuestionController controller;
	@InjectMocks
	private StatCompiler stats;

	@Test
	public void questionTextDoesStuff() {
		given(controller.find(1)).willReturn(new BooleanQuestion("text1"));
		given(controller.find(2)).willReturn(new BooleanQuestion("text2"));
		List<BooleanAnswer> answers = new ArrayList<>();
		answers.add(new BooleanAnswer(1, true));
		answers.add(new BooleanAnswer(2, true));

		Map<Integer, String> questionText = stats.questionText(answers);

		Map<Integer, String> expected = new HashMap<>();
		expected.put(1, "text1");
		expected.put(2, "text2");
		assertEquals(questionText, expected);
	}
}