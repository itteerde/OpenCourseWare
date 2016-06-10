package de.itter.euler;

import de.itter.maths.Algorithms;

public class Problem0091 {

	private static final int limit = 50;

	public static void main(String[] args) {

		int rightTriangles = 0;

		for (int qx = 1; qx <= limit; qx++) {
			for (int qy = 1; qy <= limit; qy++) {

				//System.out.println("(" + qx + "," + qy + "): " + rightTriangles(qx, qy));
				rightTriangles += rightTriangles(qx, qy);
			}
		}

		System.out.println(rightTriangles + limit * limit * 2);
	}

	private static int rightTriangles(int qx, int qy) {
		int sum = 0;

		int[] dyOdx = new int[2];
		dyOdx[1] = qx / Algorithms.gcd(qx, qy);
		dyOdx[0] = qy / Algorithms.gcd(qx, qy);

		for (int i = 1;; i++) {
			if (qy + dyOdx[1] * i <= limit && qx - (dyOdx[0] * i) >= 0) {
				sum++;
			} else {
				break;
			}
		}

		for (int i = 1;; i++) {
			if (qy - dyOdx[1] * i >= 0 && qx + dyOdx[0] * i <= limit) {
				sum++;
			} else {
				break;
			}
		}

		return sum + 1;
	}

}
