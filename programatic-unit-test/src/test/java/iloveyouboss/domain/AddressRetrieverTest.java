package iloveyouboss.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import util.Http;

@ExtendWith(MockitoExtension.class)
class AddressRetrieverTest {
	@Mock
	private Http http;

	@InjectMocks
	private AddressRetriever addressRetriever;

	@Test
	void answersAppropriateAddressForValidCoordinates() throws IOException, ParseException {
	    // given
		given(http.get(contains("lat=38.000000&lon=-104.000000")))
			.willReturn("{\"address\":{"
				+ "\"house_number\":\"324\","
				+ "\"road\":\"North Tejon Street\","
				+ "\"city\":\"Colorado Springs\","
				+ "\"state\":\"Colorado\","
				+ "\"postcode\":\"80903\","
				+ "\"country_code\":\"us\"}"
				+ "}");

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