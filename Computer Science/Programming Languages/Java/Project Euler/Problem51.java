package de.itter.euler;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Vector;

public class Problem51 {

	private static long[] primes = primes(5000000);
	private static boolean[][] patterns;

	public static void main(String[] args) {
		init();
		solve();
		// test();
	}

	private static void solve() {
		boolean[] pattern = null;
		for (int i = 0; i < primes.length; i++) {
			Vector<Long> family = new Vector<Long>();
			pattern = isFamily(primes[i], 8, family);
			if (pattern != null) {
				System.out.println(primes[i]);
				printPattern(pattern);
				System.out.println(family);
				return;
			}
		}
	}

	private static void printPattern(boolean[] pattern) {
		System.out.println();
		System.out.print("( ");
		for (int i = 0; i < pattern.length; i++) {
			if (i == 0) {
				System.out.print(pattern[i]);
				continue;
			}
			System.out.print(", " + pattern[i]);
			if (i == pattern.length - 1) {
				System.out.println(" )");
			}
		}
	}

	private static void init() {
		patterns = new boolean[128][8];
		int pattern = 0;
		for (int b1 = 0; b1 <= 1; b1++) {
			for (int b2 = 0; b2 <= 1; b2++) {
				for (int b3 = 0; b3 <= 1; b3++) {
					for (int b4 = 0; b4 <= 1; b4++) {
						for (int b5 = 0; b5 <= 1; b5++) {
							for (int b6 = 0; b6 <= 1; b6++) {
								for (int b7 = 0; b7 <= 1; b7++) {
									patterns[pattern][0] = b1 == 0 ? false
											: true;
									patterns[pattern][1] = b2 == 0 ? false
											: true;
									patterns[pattern][2] = b3 == 0 ? false
											: true;
									patterns[pattern][3] = b4 == 0 ? false
											: true;
									patterns[pattern][4] = b5 == 0 ? false
											: true;
									patterns[pattern][5] = b6 == 0 ? false
											: true;
									patterns[pattern][6] = b7 == 0 ? false
											: true;
									patterns[pattern][7] = false;
									pattern++;
								}
							}
						}
					}
				}
			}
		}
	}

	private static void test() {

		boolean[] pattern = { false, false, false, false, false, false, false,
				true, false };
		// System.out.println(isFamily(13, pattern, 6));
		// System.out.println(isFamily(13, pattern, 7));
		// System.out.println(isFamily(56003, 7));
	}

	private static boolean[] isFamily(long prime, int minSize,
			Vector<Long> family) {
		for (int p = 0; p < patterns.length; p++) {
			family.clear();
			if (isFamily(prime, patterns[p], minSize, family)) {
				return patterns[p];
			}
		}
		return null;
	}

	private static boolean isFamily(long prime, boolean[] pattern,
			int minimumFamilySize, Vector<Long> family) {
		int members = 0;

		for (int i = 0; i <= 9; i++) {
			Long variant = replace(prime, pattern, i);
			if (variant == -1) {
				continue;
			}
			if (isPrime(variant)) {
				if (!family.contains(variant)) {
					family.add(variant);
					members++;
				}
			}
		}
		return members >= minimumFamilySize ? true : false;
	}

	private static long replace(long number, boolean[] pattern, int digit) {

		char[] digits = BigInteger.valueOf(number).toString().toCharArray();

		if (digit == 0 && pattern[pattern.length - digits.length]) {
			return -1;
		}

		for (int i = 0; i < digits.length; i++) {
			if (pattern[pattern.length - 1 - i]) {
				digits[digits.length - 1 - i] = (char) (digit + '0');
			}
		}

		BigInteger res = new BigInteger(new String(digits));

		return res.longValue();
	}

	private static boolean isPrime(long sum) {
		return Arrays.binarySearch(primes, sum) >= 0;
	}

	public static long[] primes(int limit) {
		boolean[] calc = calcPrimes(limit);
		int count = 0;
		for (int i = 0; i < calc.length; i++) {
			if (calc[i]) {
				count++;
			}
		}
		long[] res = new long[count];
		int idx = 0;
		for (int i = 0; i < calc.length; i++) {
			if (calc[i]) {
				res[idx++] = i;
			}
		}
		return res;
	}

	public static boolean[] calcPrimes(int limit) {
		boolean[] prime = new boolean[limit + 1];
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;
		int root = (int) Math.ceil(Math.sqrt(limit));

		for (int n = 2; n < root; n++) {
			for (int i = n + n; i <= limit; i += n) {
				prime[i] = false;
			}
		}

		if (root * root == limit) {
			prime[limit] = false;
		}

		return prime;
	}
}
