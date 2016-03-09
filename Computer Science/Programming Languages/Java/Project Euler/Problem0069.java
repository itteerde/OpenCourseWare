package de.itter.euler;

import java.util.Vector;
import java.util.logging.Logger;

import de.itter.math.Algorithms;

public class Problem0069 {

	private final static Logger LOGGER = Logger.getLogger(Problem0069.class.getName());

	public static void main(String[] args) {
		Vector<Integer> primes = SiebErastosthenes.primes(30);

		int n = 1;
		for (int i = 0;; i++) {
			if (n * primes.elementAt(i) <= 1000000) {
				n *= primes.elementAt(i);
			} else {
				System.out.println(n);
				return;
			}
		}
	}

	public static void naiveSolution() {
		double maxNByPhi = -1;
		int best = -1;

		for (int n = 30000; n <= 1000000; n++) {
			double nByPhi = nDivByPhi(n);
			if (nByPhi > maxNByPhi) {
				maxNByPhi = nByPhi;
				best = n;
			}

			if (n % 10000 == 0) {
				LOGGER.info("n: " + n + " - best n=" + best + " (" + maxNByPhi + ")");
			}

		}

		System.out.println("n: " + best + " (" + maxNByPhi + ")");

	}

	private static double nDivByPhi(int n) {
		return ((double) n / phi(n));
	}

	private static int phi(int n) {

		int phi = 1;

		for (int i = 2; i < n; i++) {
			if (Algorithms.gcd(n, i) == 1) {
				phi++;
				if (phi > n / 5) {
					return -1;
				}
			}
		}

		return phi;
	}

}
