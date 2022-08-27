package iloveyouboss.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfilePoolTest {
	private ProfilePool pool;
	private Profile langrsoft;
	private Profile smeltInc;
	private BooleanQuestion doTheyReimburseTuition;

	@BeforeEach
	public void create() {
		pool = new ProfilePool();
		langrsoft = new Profile("Langrsoft");
		smeltInc = new Profile("Smelt Inc.");
		doTheyReimburseTuition = new BooleanQuestion(1, "Reimburses tuition?");
	}

	@Test
	void answersResultsInScoredOrder() {
	    // given
		smeltInc.add(new Answer(doTheyReimburseTuition, Bool.FALSE));
		pool.add(smeltInc);
		langrsoft.add(new Answer(doTheyReimburseTuition, Bool.TRUE));
		pool.add(langrsoft);

	    pool.score(soleNeed(doTheyReimburseTuition, Bool.TRUE, Weight.Important));

		// when
		List<Profile> ranked = pool.ranked();

		// then
		assertArrayEquals(ranked.toArray(), new Profile[] {langrsoft, smeltInc});
	}

	private Criteria soleNeed(Question question, int value, Weight weight) {
		Criteria criteria = new Criteria();
		criteria.add(new Criterion(new Answer(question, value), weight));
		return criteria;
	}
}