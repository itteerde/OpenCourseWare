package de.itter.euler;

import java.math.BigInteger;

import org.apache.commons.math3.fraction.BigFraction;

public class Problem100 {

	public static void main(String[] args) {
		BigInteger n = new BigInteger("1000000000001");
		BigInteger b = new BigInteger("707106781187");
		//BigInteger n = new BigInteger("10");
		//BigInteger b = new BigInteger("4");
		
		BigFraction p = null;
		
		for(int i = 0;;i++){
			p = p(b,n);
			if(i % 1000 == 0){
				System.out.println(p+" ("+p.compareTo(BigFraction.ONE_HALF)+") ("+i+")");
			}
			if(p.compareTo(BigFraction.ONE_HALF) == 0){
				System.out.println(b+" / "+n+" : ("+p.compareTo(BigFraction.ONE_HALF)+")");
				return;
			}
			if(p.compareTo(BigFraction.ONE_HALF) < 0){
				b = b.add(BigInteger.ONE);
			}else{
				n = n.add(BigInteger.ONE);
			}
		}
	}
	
	public static BigFraction p(BigInteger b, BigInteger n){
		BigFraction res = new BigFraction(b,n).multiply(
				new BigFraction(b.subtract(BigInteger.ONE), n.subtract(BigInteger.ONE)));
		
		return res;
	}

}
