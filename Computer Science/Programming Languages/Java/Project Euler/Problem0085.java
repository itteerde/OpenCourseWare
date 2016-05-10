package de.itter.euler;

import java.util.Arrays;

public class Problem0085 {

	public static void main(String[] args) {

		int target = 2000000;
		int[] best = new int[3];

		for (int x = 1; x <= 2000; x++) {
			int bestX = 0;
			for (int y = 1; y <= 1415; y++) {
				int placings = placings(x, y);
				if (Math.abs(placings - target) < Math.abs(target - bestX)) {
					bestX = placings;
				} else {
					if (Math.abs(bestX - target) < Math.abs(best[2] - target)) {
						best[0] = x;
						best[1] = y - 1;
						best[2] = placings(best[0], best[1]);
					}
					break;
				}
			}
		}

		System.out.println(Arrays.toString(best));
		System.out.println(best[0] * best[1]);
	}

	private static int placings(int x, int y) {

		int placings = 0;

		for (int a = 1; a <= x; a++) {
			for (int b = 1; b <= y; b++) {
				placings += placings(a, b, x, y);
			}
		}
		return placings;
	}

	private static int placings(int a, int b, int x, int y) {
		return (x - a + 1) * (y - b + 1);
	}

}
