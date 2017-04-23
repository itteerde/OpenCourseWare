package de.itteerde.euler;

import java.util.Arrays;

public class Problem0090 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] a = { 0, 5, 6, 7, 8, 9 };
		int[] b = { 1, 2, 3, 4, 8, 9 };

		int[] c = { 0, 5, 6, 7, 8, 9 };
		int[] d = { 1, 2, 3, 4, 6, 7 };

		if (!(isValid(a, b) && isValid(c, d))) {
			System.out.println("invalid validity test");
			return;
		}

		int noOfValid = 0;

		for (int ca1 = 0; ca1 <= 9; ca1++) {
			for (int ca2 = 0; ca2 < ca1; ca2++) {
				for (int ca3 = 0; ca3 < ca2; ca3++) {
					for (int ca4 = 0; ca4 < ca3; ca4++) {
						for (int ca5 = 0; ca5 < ca4; ca5++) {
							for (int ca6 = 0; ca6 < ca5; ca6++) {
								for (int cb1 = 0; cb1 <= 9; cb1++) {
									for (int cb2 = 0; cb2 < cb1; cb2++) {
										for (int cb3 = 0; cb3 < cb2; cb3++) {
											for (int cb4 = 0; cb4 < cb3; cb4++) {
												for (int cb5 = 0; cb5 < cb4; cb5++) {
													for (int cb6 = 0; cb6 < cb5; cb6++) {
														a[0] = ca1;
														a[1] = ca2;
														a[2] = ca3;
														a[3] = ca4;
														a[4] = ca5;
														a[5] = ca6;
														b[0] = cb1;
														b[1] = cb2;
														b[2] = cb3;
														b[3] = cb4;
														b[4] = cb5;
														b[5] = cb6;

														if (isValid(a, b)) {
															noOfValid++;
															System.out.println("a:" + Arrays.toString(a) + ", b:"
																	+ Arrays.toString(b) + ": 1");
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

			}

		}

		System.out.println(noOfValid / 2);
	}

	private static boolean isValid(int[] a, int[] b) {

		if (a[5] == a[4] || b[5] == b[4]) {
			return false;
		}

		if (!(has(a, 0) && has(b, 1) || has(b, 0) && has(a, 1))) {// 1
			return false;
		}
		if (!(has(a, 0) && has(b, 4) || has(b, 0) && has(a, 4))) {// 4
			return false;
		}
		if (!(has(a, 0) && (has(b, 6) || has(b, 9)) || has(b, 0) && (has(a, 6) || has(a, 9)))) {// 9
			return false;
		}
		if (!(has(a, 1) && (has(b, 6) || has(b, 9)) || has(b, 1) && (has(a, 6) || has(a, 9)))) {// 16
			return false;
		}
		if (!(has(a, 2) && has(b, 5) || has(b, 2) && has(a, 5))) {// 25
			return false;
		}
		if (!(has(a, 3) && (has(b, 6) || has(b, 9)) || has(b, 3) && (has(a, 6) || has(a, 9)))) {// 36
			return false;
		}
		if (!(has(a, 4) && (has(b, 6) || has(b, 9)) || has(b, 4) && (has(a, 6) || has(a, 9)))) {// 49,64
			return false;
		}
		if (!(has(a, 1) && has(b, 8) || has(b, 1) && has(a, 8))) {// 81
			return false;
		}
		return true;
	}

	private static boolean has(int[] a, int n) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == n) {
				return true;
			}
		}

		return false;
	}

}
