package de.itter.math;

import java.math.BigInteger;

public class Factorization {

	/**
	 * <b>TODO:</b>
	 * <ul>
	 * <li>Should it work for negative numbers as <code>FactorIneger</code> does
	 * in <i>Mathematica</i> giving one additional factor of -1?</li>
	 * </ul>
	 * 
	 * @param n
	 * @return
	 */
	public static BigInteger[] factorization(BigInteger n) {
		return null;
	}

	public static long[] factorization(long n) {
		return null;
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
