package de.itteerde.euler;

import java.math.BigInteger;

public class Problem0025 {

	public static void main(String[] args) {
		BigInteger[] m = { BigInteger.ONE, BigInteger.ONE };
		int n = 2;

		for (;;) {

			BigInteger fib = m[0].add(m[1]);
			m[0] = m[1];
			m[1] = fib;

			n++;

			if (m[1].toString().length() >= 1000) {
				System.out.println(n);
				return;
			}
		}
	}
}
