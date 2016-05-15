package de.itter.euler;

import java.util.Arrays;

public class Problem0068 {

	public static void main(String[] args) {

		int[] best = new int[10];

		for (int n1 = 1; n1 <= 10; n1++) {
			for (int n2 = 1; n2 < 10; n2++) {
				if (n2 == n1) {
					continue;
				}
				for (int n3 = 1; n3 < 10; n3++) {
					if (n3 == n1 || n3 == n2) {
						continue;
					}
					for (int n4 = 1; n4 <= 10; n4++) {
						if (n4 == n1 || n4 == n2 || n4 == n3) {
							continue;
						}
						for (int n5 = 1; n5 < 10; n5++) {
							if (n5 == n1 || n5 == n2 || n5 == n3 || n5 == n4) {
								continue;
							}
							for (int n6 = 1; n6 <= 10; n6++) {
								if (n6 == n1 || n6 == n2 || n6 == n3 || n6 == n4 || n6 == n4 || n6 == n5) {
									continue;
								}
								for (int n7 = 1; n7 < 10; n7++) {
									if (n7 == n1 || n7 == n2 || n7 == n3 || n7 == n4 || n7 == n5 || n7 == n6) {
										continue;
									}
									for (int n8 = 1; n8 <= 10; n8++) {
										if (n8 == n1 || n8 == n2 || n8 == n3 || n8 == n4 || n8 == n5 || n8 == n6
												|| n8 == n7) {
											continue;
										}
										for (int n9 = 1; n9 < 10; n9++) {
											if (n9 == n1 || n9 == n2 || n9 == n2 || n9 == n3 || n9 == n4 || n9 == n4
													|| n9 == n5 || n9 == n6 || n9 == n7 || n9 == n8) {
												continue;
											}
											for (int n10 = 1; n10 <= 10; n10++) {
												if (n10 == n1 || n10 == n2 || n10 == n3 || n10 == n4 || n10 == n5
														|| n10 == n6 || n10 == n7 || n10 == n8 || n10 == n9) {
													continue;
												}

												if (test(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10)) {
													best(best, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10);
												}

											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		//System.out.println(Arrays.toString(best));
		System.out.println(best[0] + "" + best[1] + "" + best[2] + "" + best[3] + "" + best[2] + "" + best[4] + ""
				+ best[5] + "" + best[4] + "" + best[6] + "" + best[7] + "" + best[6] + "" + best[8] + "" + best[9] + ""
				+ best[8] + "" + best[1]);
	}

	private static void best(int[] best, int n1, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9,
			int n10) {

		int[] n = new int[10];
		n[0] = n1;
		n[1] = n2;
		n[2] = n3;
		n[3] = n4;
		n[4] = n5;
		n[5] = n6;
		n[6] = n7;
		n[7] = n8;
		n[8] = n9;
		n[9] = n10;

		unique(n);

		System.out.println(Arrays.toString(n));

		for (int i = 0; i < n.length; i++) {
			if (n[i] == best[i]) {
				continue;
			}

			if (n[i] > best[i]) {
				for (int j = 0; j < n.length; j++) {
					best[j] = n[j];
				}
				return;
			} else {
				return;
			}
		}
	}

	private static void unique(int[] n) {
		for (;;) {
			if (n[0] > n[3] || n[0] > n[5] || n[0] > n[7] || n[0] > n[9]) {
				rotate(n);
				continue;
			}

			return;
		}
	}

	private static void rotate(int[] n) {
		int[] memory = new int[n.length];
		for (int i = 0; i < memory.length; i++) {
			memory[i] = n[i];
		}

		n[0] = memory[9];
		n[1] = memory[8];
		n[2] = memory[1];
		n[3] = memory[0];
		n[4] = memory[2];
		n[5] = memory[3];
		n[6] = memory[4];
		n[7] = memory[5];
		n[8] = memory[6];
		n[9] = memory[7];
	}

	private static boolean test(int n1, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10) {
		int sum = n1 + n2 + n3;

		if (n4 + n3 + n5 != sum) {
			return false;
		}

		if (n6 + n5 + n7 != sum) {
			return false;
		}

		if (n8 + n7 + n9 != sum) {
			return false;
		}

		if (n10 + n9 + n2 != sum) {
			return false;
		}

		return true;
	}
}
