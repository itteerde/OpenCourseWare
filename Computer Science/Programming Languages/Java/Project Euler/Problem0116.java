package de.itter.euler;

public class Problem0116 {

	public static void main(String[] args) {
		int n = 50;
		System.out.println(fillingsRed(n) + fillingsGreen(n) + fillingsBlue(n));
	}

	private static long fillingsRed(int n) {
		long[] fillings = { 0, 1, 1 };
		for (int i = 3; i <= n; i++) {
			fillings[2] = fillings[1] + 1 + fillings[0];
			fillings[0] = fillings[1];
			fillings[1] = fillings[2];
		}

		return fillings[2];
	}

	private static long fillingsGreen(int n) {
		long[] fillings = { 0, 0, 1, 1 };
		for (int i = 4; i <= n; i++) {
			fillings[3] = fillings[2] + 1 + fillings[0];
			fillings[0] = fillings[1];
			fillings[1] = fillings[2];
			fillings[2] = fillings[3];
		}

		return fillings[3];
	}

	private static long fillingsBlue(int n) {
		long[] fillings = { 0, 0, 0, 1, 1 };
		for (int i = 5; i <= n; i++) {
			fillings[4] = fillings[3] + 1 + fillings[0];
			fillings[0] = fillings[1];
			fillings[1] = fillings[2];
			fillings[2] = fillings[3];
			fillings[3] = fillings[4];
		}

		return fillings[4];
	}

}
