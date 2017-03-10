package de.itteerde.euler;

import java.math.BigInteger;

public class Problem0160 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(relevantDigits(fac(1000).toString()));
		System.out.println(System.currentTimeMillis() - start + "ms");
	}

	public static BigInteger fac(long n) {

		BigInteger fac = BigInteger.ONE;

		for (long i = 1; i <= n; i++) {
			fac = fac.multiply(new BigInteger(Long.toString(i)));
		}

		return fac;
	}

	public static String relevantDigits(String s) {
		int numberOfTrailingZeros = 0;
		for (int i = 0;; i++) {
			if (s.charAt(s.length() - 1 - i) != '0') {
				break;
			}
			numberOfTrailingZeros++;
		}

		return s.substring(s.length() - numberOfTrailingZeros - 5, s.length() - numberOfTrailingZeros);
	}

}
