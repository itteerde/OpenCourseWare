package de.itter.euler;

import java.util.Vector;
import java.util.logging.Logger;

import de.itter.math.SiebErastosthenes;

public class Problem0060 {

	private static final Logger LOGGER = Logger.getLogger(Problem0060.class.getName());

	public static void main(String[] args) {
		Vector<Integer> primes = SiebErastosthenes.primes(100000000);

		System.out.println("biggest prime in data: " + primes.elementAt(primes.size() - 1));
		System.out.println("#primes in data: " + primes.size());

		int maxPrime = 10000;

		for (int i = 1; i < maxPrime; i++) {

			if (i % (maxPrime / 10) == 0) {
				LOGGER.info("i: " + i);
			}

			for (int j = 1; j < maxPrime; j++) {
				if (i == j || !SiebErastosthenes.isPrime((int) concatenate(primes.elementAt(i), primes.elementAt(j)))
						|| !SiebErastosthenes.isPrime((int) concatenate(primes.elementAt(j), primes.elementAt(i)))) {
					break;
				}
				for (int k = 1; k < maxPrime; k++) {
					if (i == k || j == k
							|| !SiebErastosthenes.isPrime((int) concatenate(primes.elementAt(i), primes.elementAt(k)))
							|| !SiebErastosthenes
									.isPrime((int) concatenate(primes.elementAt(k), primes.elementAt(i)))) {
						break;

					}
					if (!SiebErastosthenes.isPrime((int) concatenate(primes.elementAt(j), primes.elementAt(k)))
							|| !SiebErastosthenes
									.isPrime((int) concatenate(primes.elementAt(k), primes.elementAt(j)))) {
						break;

					}
					for (int l = 1; l < maxPrime; l++) {
						if (i == l || j == l || k == l
								|| !SiebErastosthenes
										.isPrime((int) concatenate(primes.elementAt(i), primes.elementAt(l)))
								|| !SiebErastosthenes
										.isPrime((int) concatenate(primes.elementAt(l), primes.elementAt(i)))) {
							break;

						}
						if (!SiebErastosthenes.isPrime((int) concatenate(primes.elementAt(j), primes.elementAt(l)))
								|| !SiebErastosthenes
										.isPrime((int) concatenate(primes.elementAt(l), primes.elementAt(j)))) {
							break;

						}
						if (!SiebErastosthenes.isPrime((int) concatenate(k, l)) || !SiebErastosthenes
								.isPrime((int) concatenate(primes.elementAt(l), primes.elementAt(k)))) {
							break;

						}
						for (int m = 1; m < maxPrime; m++) {
							if (i == m || j == m || k == m || l == m
									|| !SiebErastosthenes
											.isPrime((int) concatenate(primes.elementAt(i), primes.elementAt(m)))
									|| !SiebErastosthenes
											.isPrime((int) concatenate(primes.elementAt(m), primes.elementAt(i)))) {
								break;
							}
							if (!SiebErastosthenes.isPrime((int) concatenate(primes.elementAt(j), primes.elementAt(m)))
									|| !SiebErastosthenes
											.isPrime((int) concatenate(primes.elementAt(m), primes.elementAt(j)))) {
								break;
							}
							if (!SiebErastosthenes.isPrime((int) concatenate(primes.elementAt(k), primes.elementAt(m)))
									|| !SiebErastosthenes
											.isPrime((int) concatenate(primes.elementAt(m), primes.elementAt(k)))) {
								break;
							}
							if (!SiebErastosthenes.isPrime((int) concatenate(primes.elementAt(l), primes.elementAt(m)))
									|| !SiebErastosthenes
											.isPrime((int) concatenate(primes.elementAt(m), primes.elementAt(l)))) {
								break;
							}

							System.out.println(primes.elementAt(i) + "+" + primes.elementAt(j) + "+"
									+ primes.elementAt(k) + "+" + primes.elementAt(l) + "+" + primes.elementAt(m)
									+ " = " + (primes.elementAt(i) + primes.elementAt(j) + primes.elementAt(k)
											+ primes.elementAt(l) + primes.elementAt(m)));

						}
					}
				}
			}
		}
	}

	private static long concatenate(long a, long b) {

		int magnitude = (int) Math.log10(b);

		while (magnitude > -1) {
			a *= 10;
			a += b / power(10, magnitude);
			b -= b / power(10, magnitude) * power(10, magnitude);
			magnitude--;
		}

		return a;
	}

	private static long power(long base, int power) {

		long result = 1;

		for (int i = 0; i < power; i++) {
			result *= base;
		}

		return result;
	}

}
