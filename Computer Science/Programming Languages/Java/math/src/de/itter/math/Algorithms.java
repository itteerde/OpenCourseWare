package de.itter.math;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class Algorithms {

	public static int gcd(int a, int b) {
		return (int) gcd((long) a, (long) b);
	}

	public static long gcd(long a, long b) {

		if (a < 0 || b < 0) {
			throw new ArithmeticException("GCD not defined for negative parameters");
		}

		while (b != 0) {
			long h = a % b;
			a = b;
			b = h;
		}

		return a;
	}

	public static BigInteger gcd(BigInteger a, BigInteger b) {

		if (a.signum() == -1 || b.signum() == -1) {
			throw new ArithmeticException("GCD not defined for negative parameters");
		}

		while (b.compareTo(BigInteger.ZERO) != 0) {
			BigInteger h = a.mod(b);
			a = b;
			b = h;
		}

		return a;
	}

	public static BigInteger[] factorize(BigInteger n) {
		Vector<BigInteger> factors = new Vector<BigInteger>();

		BigInteger i = n;
		for (BigInteger t = new BigInteger("2"); i.compareTo(BigInteger.ONE) > 0;) {
			if (i.mod(t).compareTo(BigInteger.ZERO) == 0) {
				factors.add(t);
				i = i.divide(t);
			} else {
				t = t.add(BigInteger.ONE);
			}
		}

		BigInteger[] result = new BigInteger[factors.size()];
		result = factors.toArray(result);

		return result;
	}

	public static long[] amicableChain(long n) {
		return null;
	}

	public static boolean isPerfectNumber(long n) {
		return false;
	}

	public static long[] properDivisors(long n) {
		LinkedList<Long> divisors = new LinkedList<Long>();

		for (long i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				divisors.add(i);
				divisors.add(n / i);
			}
		}

		long[] result = new long[divisors.size()];
		Iterator<Long> iterator = divisors.iterator();
		for (int i = 0; i < divisors.size(); i++) {
			result[i] = iterator.next();
		}

		return result;
	}

	public static double root(double num, double root) {
		return Math.pow(Math.E, Math.log(num) / root);
	}
}
