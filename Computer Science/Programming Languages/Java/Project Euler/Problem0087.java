package de.itter.euler;

import java.util.Vector;

import de.itter.math.SiebErastosthenes;

public class Problem0087 {

	public static void main(String[] args) {

		Vector<Integer> primes = SiebErastosthenes.primes(8000);
		boolean[] sieve = new boolean[50000000];

		System.out.println(primes.elementAt(1006));
		System.out.println(primes.elementAt(75));
		System.out.println(primes.elementAt(23));

		for (long i = 0; i < 1006; i++) {
			for (long j = 0; j < 75; j++) {
				for (long k = 0; k < 23; k++) {
					long n = primes.elementAt((int) i) * primes.elementAt((int) i)
							+ primes.elementAt((int) j) * primes.elementAt((int) j) * primes.elementAt((int) j)
							+ primes.elementAt((int) k) * primes.elementAt((int) k) * primes.elementAt((int) k)
									* primes.elementAt((int) k);
					if (n < 50000000) {
						sieve[(int) (n - 1)] = true;
					}
				}
			}
		}

		int result = 0;

		for (int i = 0; i < sieve.length; i++) {
			if (sieve[i]) {
				result++;
			}
		}
		System.out.println(result);
	}

}
