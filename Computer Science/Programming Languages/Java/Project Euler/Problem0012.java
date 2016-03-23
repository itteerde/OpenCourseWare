package de.itter.euler;

import de.itter.math.Algorithms;

public class Problem0012 {

	public static void main(String[] args) {

		// System.out.println(Arrays.toString(Algorithms.properDivisors(28)));

		long triangleN = 0;
		for (long n = 1;; n++) {
			triangleN += n;
			if (Algorithms.properDivisors(triangleN).length > 499) {
				System.out.println(triangleN);
				return;
			}
		}

	}

}
