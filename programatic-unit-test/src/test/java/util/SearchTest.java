package util;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class SearchTest {

	public static final String A_TITLE = "1";

	@Test
	public void returnsMatchesShowingContextWhenSearchStringInContent() throws Exception {
		ByteArrayInputStream stream = streamOn("There are certain queer times and occasions "
			+ "in this strange mixed affair we call life when a man "
			+ "takes this whole universe for a vast practical joke, "
			+ "though the wit thereof he but dimly discerns, and more "
			+ "than suspects that the joke is at nobody's expense but "
			+ "his own.");

		// search
		Search search = new Search(stream, "practical joke", A_TITLE);
		Search.LOGGER.setLevel(Level.OFF);
		search.setSurroundingCharacterCount(10);
		search.execute();
		assertFalse(search.errored());
		assertThat(search.getMatches(),
			ContainsMatches.containsMatches(
				new Match[] {
					new Match(A_TITLE, "practical joke", "or a vast practical joke, through t")
				}
			));
		stream.close();

	}

	@Test
	void noMatchesReturnedWhenSearchStringNotInContent() throws IOException {
		// negative
		URLConnection connection = new URL("http://bit.ly/15sYPA7").openConnection();
		InputStream inputStream = connection.getInputStream();
		Search search = new Search(inputStream, "smelt", A_TITLE);
		search.execute();
		assertTrue(search.getMatches().isEmpty());
		inputStream.close();
	}

	
	private ByteArrayInputStream streamOn(String pageContent) {
		return new ByteArrayInputStream(pageContent.getBytes());
	}
}