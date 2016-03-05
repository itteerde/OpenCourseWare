package de.itter.math;

import java.math.BigInteger;
import java.util.Vector;

public class Algorithms {

	public static int gcd(int a, int b){
		return (int) gcd((long)a,(long)b);
	}
	
	public static long gcd(long a, long b){

		if(a < 0 || b < 0){
			throw new ArithmeticException("GCD not defined for negative parameters");
		}
		
		while(b != 0){
			long h = a % b;
			a = b;
			b = h;
		}
		
		return a;
	}
	
	public static BigInteger gcd(BigInteger a, BigInteger b){
		
		if(a.signum() == -1 || b.signum() == -1){
			throw new ArithmeticException("GCD not defined for negative parameters");
		}
		
		while(b.compareTo(BigInteger.ZERO) != 0){
			BigInteger h = a.mod(b);
			a = b;
			b = h;
		}
		
		return a;
	}
	
	public static BigInteger[] primeFactors(BigInteger n){
		Vector<BigInteger> factors = new Vector<BigInteger>();
		
		BigInteger i = n;
		for(BigInteger t = new BigInteger("2"); i.compareTo(BigInteger.ONE)>0;){
			if(i.mod(t).compareTo(BigInteger.ZERO)==0){
				factors.add(t);
				i = i.divide(t);
			}else{
				t = t.add(BigInteger.ONE);
			}
		}
		
		BigInteger[] result = new BigInteger[factors.size()];
		result = factors.toArray(result);
		
		return result;
	}
}
