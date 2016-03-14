package de.itter.euler;

public class Problem0092 {

	public static void main(String[] args) {
		int result = 0;

		for (long n = 1; n < 10000000; n++) {
			if (chainEnd(n) == 89) {
				result++;
			}
		}

		System.out.println(result);
	}

	private static int chainEnd(long n) {

		long res = 0;

		while (res != 1 && res != 89) {
			res = 0;
			while (n > 0) {
				res += (n % 10) * (n % 10);
				n /= 10;
			}
			n = res;
		}

		return (int) res;
	}

}
