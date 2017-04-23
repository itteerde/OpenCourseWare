package de.itteerde.euler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

public class Problem0086 {

	public static void main(String[] args) {

		int[] countCuboids = new int[50000];
		HashSet<Vector<Integer>> tripels = new HashSet<Vector<Integer>>();

		for (int u = 2;; u++) {
			for (int v = 1; v < u; v++) {
				int a = u * u - v * v;
				int b = 2 * u * v;
				int c = u * u + v * v;

				if (c >= countCuboids.length) {
					System.out.println(Arrays.toString(countCuboids));
					return;

				}

				if (a > b) {
					int m = a;
					a = b;
					b = m;
				}
				if (b > c) {
					int m = b;
					b = c;
					c = m;
				}

				Vector<Integer> t = new Vector<Integer>();
				t.add(a);
				t.add(b);
				t.add(c);

				for (int multiplier = 1;; multiplier++) {
					if (c * multiplier >= countCuboids.length) {
						break;
					}
					Vector<Integer> m = new Vector<Integer>();
					m.add(t.get(0) * multiplier);
					m.add(t.get(1) * multiplier);
					m.add(t.get(2) * multiplier);

					if (tripels.contains(m)) {
						continue;
					}

					tripels.add(m);
					countCuboids(m, countCuboids);
				}
			}
		}

	}

	private static Integer countCuboids(Vector<Integer> t, int[] countCuboids) {
		int a = t.elementAt(0);
		int b = t.elementAt(1);

		int cuboids = 0;
		for (int i = 1; i <= a / 2; i++) {
			countCuboids[b]++;
			cuboids++;
		}

		if (b / 2 < a) {
			for (int i = 1; i <= b / 2; i++) {
				if (b - i > a) {
					continue;
				}
				countCuboids[a]++;
				cuboids++;
			}
		}
		return cuboids;
	}

}
