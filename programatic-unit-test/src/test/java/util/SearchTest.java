package util;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static util.ContainsMatches.*;

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
		stream = streamOn("rest of text here"
			+ "1234567890search term1234567890"
			+ "more rest of text");
		Search search = new Search(stream, "search term", A_TITLE);
		search.setSurroundingCharacterCount(10);

		search.execute();

		assertThat(search.getMatches(), containsMatches(new Match[]
			{ new Match(A_TITLE,
				"search term",
				"1234567890search term1234567890") }));
	}

	@Test
	void noMatchesReturnedWhenSearchStringNotInContent() throws IOException {
		stream = streamOn("any text");
		Search search = new Search(stream, "text that doesn't match", A_TITLE);

		search.execute();

		assertTrue(search.getMatches().isEmpty());
	}

	@Test
	void returnsErroredWhenUnableToReadStream() {
	    // given
	    stream = createStreamThrowingErrorWhenRead();
		Search search = new Search(stream, " ", " ");

		// when
		search.execute();

	    // then
		assertTrue(search.errored());
	}

	@Test
	void erroredReturnsFalseWhenReadSucceeds() {
	    // given
		stream = streamOn(" ");
		Search search = new Search(stream, " ", " ");

		// when
		search.execute();

	    // then
		assertFalse(search.errored());
	}

	private InputStream createStreamThrowingErrorWhenRead() {
		return new InputStream() {
			@Override
			public int read() throws IOException {
				throw new IOException();
			}
		};
	}

	private ByteArrayInputStream streamOn(String pageContent) {
		return new ByteArrayInputStream(pageContent.getBytes());
	}
}