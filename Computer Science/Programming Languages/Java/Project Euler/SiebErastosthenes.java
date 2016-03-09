package de.itter.euler;

import java.util.Vector;

public class SiebErastosthenes {

	protected static Vector<Integer> primes = new Vector<Integer>();
	private static int maximum = 0;

	public static void main(String[] args) {

		new SiebErastosthenes(1000000);
		System.out.println(primes.elementAt(primes.size() - 1));

	}

	public SiebErastosthenes(int maximum) {

		if (maximum < SiebErastosthenes.maximum) {
			return;
		}

		SiebErastosthenes.maximum = maximum;

		// initially assume all integers are prime
		boolean[] isPrime = new boolean[maximum + 1];// waste a little for
														// readability
		for (int i = 2; i <= maximum; i++) {
			isPrime[i] = true;
		}

		// mark non-primes <= N using Sieve of Eratosthenes
		for (int i = 2; i * i <= maximum; i++) {

			// if i is prime, then mark multiples of i as nonprime
			// suffices to consider mutiples i, i+1, ..., N/i
			if (isPrime[i]) {
				for (int j = i; i * j <= maximum; j++) {
					isPrime[i * j] = false;
				}
			}
		}

		for (int i = 2; i < isPrime.length; i++) {
			if (isPrime[i]) {
				primes.add(new Integer(i));
			}
		}
	}

	public static int nth(int n) {
		return primes.elementAt(n - 1).intValue();
	}

	public static Vector<Integer> primes(int maximun) {
		if (primes.size() == 0 || primes.elementAt(primes.size() - 1) < maximum) {
			new SiebErastosthenes(maximun);
		}

		return SiebErastosthenes.primes;
	}

	public static Vector<Integer> primes() {
		return primes(100);
	}

	public static boolean isPrime(int n) {
		if (n > maximum) {
			new SiebErastosthenes(n);
		}

		return primes.contains(n);
	}

}
