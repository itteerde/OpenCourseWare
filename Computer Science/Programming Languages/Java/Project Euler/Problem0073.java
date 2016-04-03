package de.itter.euler;

import de.itter.math.Algorithms;

public class Problem0073 {

	public static void main(String[] args) {
		int counter = 0;

		for (int d = 5; d <= 12000; d++) {
			int lowerN = d / 3 + 1;
			int upperN = d % (d / 2) == 0 ? d / 2 - 1 : d / 2;

			for (int n = lowerN; n <= upperN; n++) {
				if (Algorithms.gcd(n, d) == 1) {
					// System.out.println(n + "/" + d);
					counter++;
				}
			}
		}

		System.out.println(counter);
	}

}
