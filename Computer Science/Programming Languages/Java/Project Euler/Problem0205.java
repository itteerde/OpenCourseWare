package de.itteerde.euler;

import java.util.Arrays;

public class Problem0205 {

	public static void main(String[] args) {

		long t = System.currentTimeMillis();

		int[] w4s = new int[36 - 8];
		int[] w6s = new int[36 - 5];

		for (int w1 = 1; w1 <= 4; w1++) {
			for (int w2 = 1; w2 <= 4; w2++) {
				for (int w3 = 1; w3 <= 4; w3++) {
					for (int w4 = 1; w4 <= 4; w4++) {
						for (int w5 = 1; w5 <= 4; w5++) {
							for (int w6 = 1; w6 <= 4; w6++) {
								for (int w7 = 1; w7 <= 4; w7++) {
									for (int w8 = 1; w8 <= 4; w8++) {
										for (int w9 = 1; w9 <= 4; w9++) {
											w4s[w1 + w2 + w3 + w4 + w5 + w6 + w7 + w8 + w9 - 9]++;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		System.out.println(Arrays.toString(w4s));

		for (int w1 = 1; w1 <= 6; w1++) {
			for (int w2 = 1; w2 <= 6; w2++) {
				for (int w3 = 1; w3 <= 6; w3++) {
					for (int w4 = 1; w4 <= 6; w4++) {
						for (int w5 = 1; w5 <= 6; w5++) {
							for (int w6 = 1; w6 <= 6; w6++) {
								w6s[w1 + w2 + w3 + w4 + w5 + w6 - 6]++;
							}
						}
					}
				}
			}
		}
		System.out.println(Arrays.toString(w6s));

		long successes = 0;
		for (int i = 0; i < w4s.length; i++) {
			for (int j = 0; j <= i + 2; j++) {

				successes += w4s[i] * w6s[j];
			}
		}

		System.out.println(successes);
		System.out.println(System.currentTimeMillis() - t);
	}

}
