package de.itter.euler;

import java.math.BigInteger;

public class Problem0020 {

	public static void main(String[] args) {
		BigInteger facn = BigInteger.ONE;

		for (int i = 2; i <= 100; i++) {
			facn = facn.multiply(new BigInteger(Integer.toString(i)));
		}

		byte[] digits = facn.toString().getBytes();

		int sum = 0;
		for (int i = 0; i < digits.length; i++) {
			sum += digits[i] - '0';
		}

		System.out.println(sum);
	}

}
