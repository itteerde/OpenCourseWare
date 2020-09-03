package de.itteerde.euler;

public class Problem0149 {

	static int[] t = new int[4000000];
	static boolean[] bitT = new boolean[4000000];

	public static void main(String[] args) {

		long t = System.currentTimeMillis();

		buildTable();
		int max = Integer.MIN_VALUE;
		for (int x = 1; x <= 2000; x++) {
			for (int y = 1; y <= 2000; y++) {
				int m = max(x, y);
				if (m > max) {
					max = m;
				}
			}
		}
		System.out.println(max);

		System.out.println(System.currentTimeMillis() - t + "ms");
	}

	private static void buildTable() {
		for (int i = 1; i <= 4000000; i++) {
			t[i - 1] = s(i);
		}
	}

	public static int s(int k) {
		if (k < 56) {
			if (bitT[k - 1]) {
				return t[k - 1];
			} else {
				long kl = k;
				t[k - 1] = (int) (((100003L - 200003L * kl + 300007L * kl * kl * kl) % 1000000L) - 500000);
				bitT[k - 1] = true;
				return t[k - 1];
			}
		}

		if (bitT[k - 1]) {
			return t[k - 1];
		}

		bitT[k - 1] = true;
		t[k - 1] = ((s(k - 24) + s(k - 55) + 1000000) % 1000000) - 500000;
		return t[k - 1];
	}

	public static int getCell(int x, int y) {
		return t[2000 * (y - 1) + x - 1];
	}

	public static int maxHorizontal(int x, int y) {
		int max = 0;
		int sum = 0;

		for (int i = x; i <= 2000; i++) {
			sum += getCell(i, y);
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}

	public static int maxVertical(int x, int y) {
		int max = 0;
		int sum = 0;

		for (int i = y; i <= 2000; i++) {
			sum += getCell(x, i);
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}

	public static int maxDiagonal(int x, int y) {
		int max = 0;
		int sum = 0;

		for (int i = 0; Math.max(x, y) + i <= 2000; i++) {
			sum += getCell(x + i, y + i);
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}

	public static int maxAntiDiagonal(int x, int y) {
		int max = 0;
		int sum = 0;

		for (int i = 0; i < Math.min(2000 - x, y); i++) {
			sum += getCell(x + i, y - i);
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}

	public static int max(int x, int y) {
		int max = maxAntiDiagonal(x, y);
		int m = maxDiagonal(x, y);
		if (m > max) {
			max = m;
		}
		m = maxHorizontal(x, y);
		if (m > max) {
			max = m;
		}

		m = maxVertical(x, y);
		if (m > max) {
			max = m;
		}

		return max;
	}

}
