package de.itter.euler;

import java.math.BigInteger;

public class Problem12 {
	
	public static final BigInteger ZERO = new BigInteger("0");
	public static final BigInteger ONE = new BigInteger("1");
	public static final BigInteger TWO = new BigInteger("2");

	public static void main(String[] args) {
		BigInteger i = ONE;
		BigInteger triNum = ONE;
		
		for(;;){
			int numberOfDivisors = numberOfDivisors(triNum);
			if(numberOfDivisors > 500){
				System.out.println(triNum);
				return;
			}
			System.out.println("triNum: "+triNum+" ("+numberOfDivisors+" divisors)");
			triNum = triNum.add(i);
			i = i.add(ONE);
		}
	}
	
	public static int numberOfDivisors(BigInteger x){
		int res = 0;
		
		BigInteger i = new BigInteger("1");
		for(;;){
			if(i.multiply(TWO).compareTo(x) > 0){
				return res+1;
			}
			if(x.remainder(i).equals(ZERO)){
				res++;
			}
			i = i.add(ONE);
		}
	}

}
