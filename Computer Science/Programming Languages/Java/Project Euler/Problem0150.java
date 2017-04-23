package de.itteerde.euler;

public class Problem0150 {

	static int[] s = new int[500500];

	public static void main(String[] args) {
		int t = 0;
		int mod = 2 << 19;
		int minuend = 2 << 18;

		for (int k = 1; k <= 500500; k++) {
			t = (615949 * t + 797807) % mod;
			s[k - 1] = t - minuend;
		}

		long sum = 0;
		for (int i = 0; i < s.length; i++) {
			sum += s[i];
		}
		System.out.println(sum);
	}

}
