package scratch;

import static scratch.ConstrainsSidesTo.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class RectangleTest {
	private scratch.Rectangle rectangle;

	@AfterEach
	public void ensureInvariant() {
		assertThat(rectangle, constrainsSidesTo(100));
	}

	@Test
	void answersArea() {
		rectangle = new scratch.Rectangle(new Point(5, 5), new Point(15, 10));
		assertEquals(rectangle.area(), 50);
	}

	@Test
	void allowsDynamicallyChangingSize() {
		rectangle = new Rectangle(new Point(5, 5));
		rectangle.setOppositeCorner(new Point(130, 130));
		assertEquals(rectangle.area(), 15625);
	}
}