package de.itter.math;

import java.math.BigInteger;

public class Factorization {

	/**
	 * <b>TODO:</b>
	 * <ul>
	 * <li>Should it work for negative numbers as <code>FactorInteger</code>
	 * does in <i>Mathematica</i> giving one additional factor of -1?</li>
	 * </ul>
	 * 
	 * @param n
	 * @return
	 */
	public static BigInteger[] factorization(BigInteger n) {
		return Algorithms.factorize(n);
	}

	/**
	 * Convenience wrapper for
	 * <code>BigInteger[] factorization(BigInteger n)</code>, therefore
	 * relatively slow.
	 * 
	 * @param n
	 * @return
	 */
	public static long[] factorization(long n) {

		BigInteger[] factors = factorization(new BigInteger(Long.toString(n)));

		long[] result = new long[factors.length];
		for (int i = 0; i < factors.length; i++) {
			result[i] = factors[i].longValue();
		}
		return result;
	}

	/**
	 * <a href=
	 * "https://en.wikipedia.org/wiki/Lenstra_elliptic_curve_factorization">
	 * Lenstra Elliptic Curve Factorization</a> for <code>n < 10^50</code>
	 * 
	 * @param n
	 * @return
	 */
	private static BigInteger[] lenstraEllipticCurveFactorization(BigInteger n) {
		return null;
	}

	private static BigInteger[] quadraticSieve(BigInteger n) {
		return null;
	}

	/**
	 * <code>n > 10^100</code>
	 * 
	 * @param n
	 * @return
	 */
	private static BigInteger[] generalNumberFieldSieve(BigInteger n) {
		return null;
	}
}
