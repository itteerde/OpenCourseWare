package de.itteerde.euler;

import java.util.Vector;

import de.itter.maths.Primes;
import de.itter.maths.SiebEratosthenes;

public class Problem0088 {

	private static int[] nOfK = new int[12000];
	private static Vector<Integer> primes = null;

	public static void main(String[] args) {

		SiebEratosthenes s = new SiebEratosthenes(1000);
		primes = s.primes(1000);

		long start = System.currentTimeMillis();

		for (int n = 2; n <= 12000; n++) {
			nOfK(n);
		}

		// System.out.println(primes.elementAt(1));
		System.out.println(System.currentTimeMillis() - start + "ms");

	}

	private static void nOfK(int n) {
		int[] primeFactors = Primes.factorInteger(n);

		Vector<Vector<Integer>> sets = new Vector<Vector<Integer>>();

		for (int i = 0; i < Math.pow(2, primeFactors.length); i++) {

		}

	}

}
