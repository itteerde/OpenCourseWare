package de.itter.euler;

import java.util.Stack;

public class Problem0075 {

	private static int limit = 1500000;

	private static int[][] a = { { 1, -2, 2 }, { 2, -1, 2 }, { 2, -2, 3 } };
	private static int[][] b = { { 1, 2, 2 }, { 2, 1, 2 }, { 2, 2, 3 } };
	private static int[][] c = { { -1, 2, 2 }, { -2, 1, 2 }, { -2, 2, 3 } };

	private static int[] ls = new int[limit + 1];
	private static Stack<int[]> pythagoreanTriplets = new Stack<int[]>();

	public static void main(String[] args) {
		int[] start = { 3, 4, 5 };
		pythagoreanTriplets.push(start);

		for (;;) {
			step();

			if (pythagoreanTriplets.isEmpty()) {
				break;
			}
		}

		System.out.println(count());

	}

	private static int count() {
		int res = 0;

		for (int i = 0; i < ls.length; i++) {
			if (ls[i] == 1) {
				res++;
			}
		}
		return res;
	}

	private static void step() {
		int[] v = pythagoreanTriplets.pop();

		int[] va = dot(a, v);
		int[] vb = dot(b, v);
		int[] vc = dot(c, v);

		if (sum(va) <= limit) {
			pythagoreanTriplets.push(va);
		}
		if (sum(vb) <= limit) {
			pythagoreanTriplets.push(vb);
		}
		if (sum(vc) <= limit) {
			pythagoreanTriplets.push(vc);
		}

		scale(v);
	}

	private static void scale(int[] v) {
		for (int n = 0;; n++) {
			int l = n * sum(v);
			if (l <= limit) {
				ls[l]++;
			} else {
				return;
			}
		}
	}

	private static int[] dot(int[][] m, int[] v) {
		int[] p = new int[3];

		p[0] = m[0][0] * v[0] + m[0][1] * v[1] + m[0][2] * v[2];
		p[1] = m[1][0] * v[0] + m[1][1] * v[1] + m[1][2] * v[2];
		p[2] = m[2][0] * v[0] + m[2][1] * v[1] + m[2][2] * v[2];

		return p;
	}

	private static int sum(int[] v) {

		int sum = 0;

		for (int i = 0; i < v.length; i++) {
			sum += v[i];
		}

		return sum;
	}

}
