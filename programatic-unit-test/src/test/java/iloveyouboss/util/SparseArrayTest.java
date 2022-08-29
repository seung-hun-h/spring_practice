package iloveyouboss.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.SparseArray;

class SparseArrayTest {
	private SparseArray<Object> array;

	@BeforeEach
	public void create() {
		array = new SparseArray<>();
	}

	@Test
	void handlesInsertionDescendingOrder() {
	    // given
	    array.put(7, "seven");
		array.checkInvariants();
		array.put(6, "six");
		array.checkInvariants();

	    // when && then
		assertEquals(array.get(6), "six");
		assertEquals(array.get(7), "seven");
		assertEquals(array.size(), 2);
	}
}