package de.itteerde.euler;

import java.math.BigInteger;

public class Problem0303 {

	public static void main(String[] args) {
		long sum = 0;

		long start = System.currentTimeMillis();
		int min = 1;
		int max = 1997;
		int inc = 1;

		for (int n = min; n <= max; n += inc) {
			if (n == 8 || n == 98 || n == 998 || n == 9998 || n == 9 || n == 99 || n == 999 || n == 9999) {
				continue;
			}

			long t0 = System.currentTimeMillis();
			long f = f(n, true);
			long t1 = System.currentTimeMillis();

			if (t1 - t0 > 100) {
				System.out.println(
						"f(" + Long.toString(n) + ")=" + Long.toString(f) + " (" + Long.toString(t1 - t0) + "ms)");
			}

			sum += f / n;
		}
		System.out.println("\\sum_{" + min + "}^{" + max + "} \\frac{f(n)}{n} ="
				+ (new BigInteger(Long.toString(sum)).add(correctionTerm(min, max))) + " \\text{("
				+ Long.toString(System.currentTimeMillis() - start) + "ms)}");
	}

	private static long f(long n) {
		return f(n, false);
	}

	private static long f(long n, boolean verbose) {

		boolean valid = true;
		long p = n;

		for (;; p += n) {
			String s = Long.toString(p);
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) > '2' || s.charAt(i) < '0') {
					valid = false;
					break;
				}
			}
			if (valid) {
				if (verbose) {
					System.out.println("f(" + n + ") &=& " + p + ", \\frac{f(n)}{n}=" + p / n);
				}
				return p;
			}
			valid = true;
		}
	}

	private static BigInteger correctionTerm(int min, int max) {

		BigInteger sum = BigInteger.ZERO;

		BigInteger minBI = BigInteger.valueOf(min);
		BigInteger maxBI = BigInteger.valueOf(max);

		if (minBI.compareTo(BigInteger.valueOf(8)) <= 0 && maxBI.compareTo(BigInteger.valueOf(8)) >= 0) {

			sum = sum.add(new BigInteger("14"));
		}
		if (minBI.compareTo(BigInteger.valueOf(98)) <= 0 && maxBI.compareTo(BigInteger.valueOf(98)) >= 0) {

			sum = sum.add(new BigInteger("1144"));
		}
		if (minBI.compareTo(BigInteger.valueOf(998)) <= 0 && maxBI.compareTo(BigInteger.valueOf(998)) >= 0) {

			sum = sum.add(new BigInteger("111444"));
		}
		if (minBI.compareTo(BigInteger.valueOf(9998)) <= 0 && maxBI.compareTo(BigInteger.valueOf(9998)) >= 0) {

			sum = sum.add(new BigInteger("11114444"));
		}
		if (minBI.compareTo(BigInteger.valueOf(9)) <= 0 && maxBI.compareTo(BigInteger.valueOf(9)) >= 0) {

			sum = sum.add(new BigInteger("12222"));
		}
		if (minBI.compareTo(BigInteger.valueOf(99)) <= 0 && maxBI.compareTo(BigInteger.valueOf(99)) >= 0) {

			sum = sum.add(new BigInteger("1122222222"));
		}
		if (minBI.compareTo(BigInteger.valueOf(999)) <= 0 && maxBI.compareTo(BigInteger.valueOf(999)) >= 0) {

			sum = sum.add(new BigInteger("111222222222222"));
		}
		if (minBI.compareTo(BigInteger.valueOf(9999)) <= 0 && maxBI.compareTo(BigInteger.valueOf(9999)) >= 0) {

			sum = sum.add(new BigInteger("11112222222222222222"));
		}

		return sum;
	}
}
