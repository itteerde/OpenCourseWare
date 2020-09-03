package de.itteerde.euler;

public class Problem0002 {

	public static void main(String[] args) {
		int[] m = { 1, 1 };
		int sum = 0;

		for (;;) {

			int fib = m[0] + m[1];
			if (fib > 4000000) {
				System.out.println(sum);
				return;
			}
			m[0] = m[1];
			m[1] = fib;
			if (fib % 2 == 0) {
				sum += fib;
			}
		}
	}

}
