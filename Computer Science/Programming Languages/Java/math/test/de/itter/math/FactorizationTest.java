package de.itter.math;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class FactorizationTest {

	@Test
	public void test() {

		long[] factors = { 2 };
		assertArrayEquals(Factorization.factorization(2), factors);

		long[] factors1024 = { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		assertArrayEquals(Factorization.factorization(1024), factors1024);
	}

}
