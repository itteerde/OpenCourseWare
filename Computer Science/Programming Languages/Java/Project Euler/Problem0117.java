package de.itter.euler;

import java.util.Arrays;

public class Problem0117 {

	private static final int N = 50;
	private static long[] fillings = new long[N + 1];

	public static void main(String[] args) {
		fillings[1] = 1;
		fillings[2] = 2;
		fillings[3] = 4;
		fillings[4] = 8;

		for (int n = 5; n < fillings.length; n++) {
			fillings[n] = fillings[n - 1] + fillings[n - 2] + fillings[n - 3] + fillings[n - 4];
		}

		System.out.println(Arrays.toString(fillings));
		System.out.println(fillings[fillings.length - 1]);
	}

}
