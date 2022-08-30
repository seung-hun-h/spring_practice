package util;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SearchTest {

	public static final String A_TITLE = "1";
	private InputStream stream;

	@BeforeEach
	void turnOffLogging() {
		Search.LOGGER.setLevel(Level.OFF);
	}

	@AfterEach
	void closeResources() throws IOException {
		stream.close();
	}

	@Test
	public void returnsMatchesShowingContextWhenSearchStringInContent() throws Exception {
		stream = streamOn("There are certain queer times and occasions "
			+ "in this strange mixed affair we call life when a man "
			+ "takes this whole universe for a vast practical joke, "
			+ "though the wit thereof he but dimly discerns, and more "
			+ "than suspects that the joke is at nobody's expense but "
			+ "his own.");

		Search search = new Search(stream, "practical joke", A_TITLE);
		search.setSurroundingCharacterCount(10);
		search.execute();
		assertThat(search.getMatches(), ContainsMatches.containsMatches(
			new Match[] {
				new Match(A_TITLE, "practical joke", "or a vast practical joke, though t")
			}
		));
	}

	@Test
	void noMatchesReturnedWhenSearchStringNotInContent() throws IOException {
		URLConnection connection = new URL("http://bit.ly/15sYPA7").openConnection();
		stream = connection.getInputStream();
		Search search = new Search(stream, "smelt", A_TITLE);
		search.execute();
		assertTrue(search.getMatches().isEmpty());
		stream.close();
	}


	private ByteArrayInputStream streamOn(String pageContent) {
		return new ByteArrayInputStream(pageContent.getBytes());
	}
}