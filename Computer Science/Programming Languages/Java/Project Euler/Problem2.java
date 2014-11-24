package de.itter.euler;

import java.math.BigInteger;

public class Problem2 {

	public static void main(String[] args) {
		BigInteger sum = new BigInteger("0");
		BigInteger [] fibs = {new BigInteger("0"), new BigInteger("1"), new BigInteger("2")};
		BigInteger two = new BigInteger("2");
		BigInteger max = new BigInteger("4000000");//two.shiftLeft(2<<14);
		BigInteger zero = new BigInteger("0");
		
		long start = System.currentTimeMillis();
		
		for(;;){
			fibs[2] = fibs[0].add(fibs[1]);
			if(fibs[2].compareTo(max) > 0){
				break;
			}
			if(fibs[2].remainder(two).equals(zero)){
				sum = sum.add(fibs[2]);
			}
			fibs[0] = fibs [1];
			fibs[1] = fibs [2];
		}
		
		long duration = System.currentTimeMillis()-start;
		
		int blockSize = 64;
		String sumString = sum.toString();
		for(int i = 0 ; i<= sumString.length() / blockSize; i++){
			System.out.println(sumString.substring(i * blockSize, Math.min(sumString.length(), i * blockSize + blockSize)));
		}
		System.out.println(sum.bitLength()+ "("+duration+"ms)");
	}

}
