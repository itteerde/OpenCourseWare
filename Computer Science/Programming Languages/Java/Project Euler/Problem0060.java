package de.itter.euler;

import java.util.Vector;
import java.util.logging.Logger;

import de.itter.math.SiebErastosthenes;

public class Problem0060 {

	private static final Logger LOGGER = Logger.getLogger(Problem0060.class.getName());

	public static void main(String[] args) {
		Vector<Integer> primeVector = SiebErastosthenes.primes(10000000);
		int[] primes = new int[primeVector.size()];
		for (int i = 0; i < primes.length; i++) {
			primes[i] = primeVector.elementAt(i);
		}
		primeVector = null;

		System.out.println("biggest prime in data: " + primes[primes.length - 1]);
		System.out.println("#primes in data: " + primes.length);

		int maxPrime = 1200;// 4000
		int minSum4 = Integer.MAX_VALUE;
		int minSum5 = Integer.MAX_VALUE;

		for (int i = 1; i < maxPrime; i++) {

			for (int j = 1; j < maxPrime; j++) {
				if (i == j || !SiebErastosthenes.isPrime((int) concatenate(primes[i], primes[j]))
						|| !SiebErastosthenes.isPrime((int) concatenate(primes[j], primes[i]))) {
					continue;
				}
				for (int k = j + 1; k < maxPrime; k++) {
					if (i == k || j == k || !SiebErastosthenes.isPrime((int) concatenate(primes[i], primes[k]))
							|| !SiebErastosthenes.isPrime((int) concatenate(primes[k], primes[i]))) {
						continue;

					}
					if (!SiebErastosthenes.isPrime((int) concatenate(primes[j], primes[k]))
							|| !SiebErastosthenes.isPrime((int) concatenate(primes[k], primes[j]))) {
						continue;

					}
					for (int l = k + 1; l < maxPrime; l++) {
						if (i == l || j == l || k == l
								|| !SiebErastosthenes.isPrime((int) concatenate(primes[i], primes[l]))
								|| !SiebErastosthenes.isPrime((int) concatenate(primes[l], primes[i]))) {
							continue;

						}
						if (!SiebErastosthenes.isPrime((int) concatenate(primes[j], primes[l]))
								|| !SiebErastosthenes.isPrime((int) concatenate(primes[l], primes[j]))) {
							continue;

						}
						if (!SiebErastosthenes.isPrime((int) concatenate(primes[k], primes[l]))
								|| !SiebErastosthenes.isPrime((int) concatenate(primes[l], primes[k]))) {
							continue;

						}

						int sum = primes[i] + primes[j] + primes[k] + primes[l];
						if (sum < minSum4) {
							minSum4 = sum;
							System.out.println(primes[i] + "+" + primes[j] + "+" + primes[k] + "+" + primes[l] + "+"
									+ " = " + sum);

						}

						for (int m = l + 1; m < maxPrime; m++) {

							if (i == m || j == m || k == m || l == m
									|| !SiebErastosthenes.isPrime((int) concatenate(primes[i], primes[m]))
									|| !SiebErastosthenes.isPrime((int) concatenate(primes[m], primes[i]))) {
								continue;
							}
							if (!SiebErastosthenes.isPrime((int) concatenate(primes[j], primes[m]))
									|| !SiebErastosthenes.isPrime((int) concatenate(primes[m], primes[j]))) {
								continue;
							}
							if (!SiebErastosthenes.isPrime((int) concatenate(primes[k], primes[m]))
									|| !SiebErastosthenes.isPrime((int) concatenate(primes[m], primes[k]))) {
								continue;
							}
							if (!SiebErastosthenes.isPrime((int) concatenate(primes[l], primes[m]))
									|| !SiebErastosthenes.isPrime((int) concatenate(primes[m], primes[l]))) {
								continue;
							}

							sum = primes[i] + primes[j] + primes[k] + primes[l] + primes[m];
							if (sum < minSum5) {
								minSum5 = sum;
								System.out.println(primes[i] + "+" + primes[j] + "+" + primes[k] + "+" + primes[l] + "+"
										+ primes[m] + " = " + sum);
							}

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
