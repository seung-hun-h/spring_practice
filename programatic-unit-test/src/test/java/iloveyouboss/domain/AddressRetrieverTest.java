package iloveyouboss.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import util.Http;

class AddressRetrieverTest {
	@Test
	void answersAppropriateAddressForValidCoordinates() throws IOException, ParseException {
	    // given
		Http http = mock(Http.class);
		given(http.get(contains("lat=38.000000&lon=-104.000000")))
			.willReturn("{\"address\":{"
				+ "\"house_number\":\"324\","
				+ "\"road\":\"North Tejon Street\","
				+ "\"city\":\"Colorado Springs\","
				+ "\"state\":\"Colorado\","
				+ "\"postcode\":\"80903\","
				+ "\"country_code\":\"us\"}"
				+ "}");

		AddressRetriever addressRetriever = new AddressRetriever(http);

		// when
		Address address = addressRetriever.retrieve(38.0, -104.0);

		// then
		assertEquals(address.houseNumber, "324");
		assertEquals(address.road, "North Tejon Street");
		assertEquals(address.city, "Colorado Springs");
		assertEquals(address.state, "Colorado");
		assertEquals(address.zip, "80903");
	}
}