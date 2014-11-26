package de.itter.euler;

import java.math.BigInteger;
import java.util.HashSet;

public class Problem29 {

	public static void main(String[] args) {
		HashSet<BigInteger> terms = new HashSet<BigInteger>();
		int limit = 100;
		
		for(int a=2;a<=limit;a++){
			for(int b=2;b<=limit;b++){
				terms.add(BigInteger.valueOf(a).pow(b));
			}
		}
		
		System.out.println(terms.size());
	}

}
