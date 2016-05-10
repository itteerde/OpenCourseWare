package de.itter.euler;

import de.itter.maths.Algorithms;
import de.itter.maths.SiebEratosthenes;

public class Problem0072 {

	private static final int MD = 1000000;

	private static boolean[] sieb = SiebEratosthenes.field(MD);
	private static boolean[] field = new boolean[MD + 1];

	public static void main(String[] args) {

		long numberOfReducedProperFractions = 0;

		for (int n = 2; n <= MD; n++) {

			if (isPrime(n)) {

				numberOfReducedProperFractions += n - 1;
				continue;
			}

			long[][] primeFactors = Algorithms.factorize(n);
			numberOfReducedProperFractions += relativePrimes(n, primeFactors);
		}

		System.out.println(numberOfReducedProperFractions);

	}

	private static void resetField(int max) {
		for (int i = 0; i < max; i++) {
			field[i] = false;
		}
	}

	private static long relativePrimes(int n, long[][] primeFactors) {
		resetField(n);
		for (int i = 0; i < primeFactors.length; i++) {
			int p = (int) primeFactors[i][0];
			for (int j = p; j < n; j += p) {
				field[j] = true;
			}
		}

		int numberOfRelativePrimes = 0;
		for (int i = 1; i < n; i++) {
			if (!field[i]) {
				numberOfRelativePrimes++;
			}
		}
		return numberOfRelativePrimes;
	}

	private static boolean isPrime(int n) {
		return sieb[n];
	}

}
