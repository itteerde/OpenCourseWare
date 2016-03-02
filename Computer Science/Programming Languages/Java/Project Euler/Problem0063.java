package de.itter.euler;

import java.math.BigInteger;

public class Problem0063 {

	public static void main(String[] args) {
		
		int result = 0;
		
		for(int exponent=1;exponent<30;exponent++){
			for(long base=1;;base++){
				BigInteger x = power(new BigInteger(Long.toString(base)),exponent);
				if(x.compareTo(power(new BigInteger("10"),exponent-1))>-1 && x.compareTo(power(new BigInteger("10"),exponent))==-1){
					result++;
					System.out.println(result+": power("+base+","+exponent+")="+x);
				}
				if(x.compareTo(power(new BigInteger("10"),exponent))>0){
					break;
				}
			}
		}
		
	}
	
	private static BigInteger power(BigInteger n, int pow){
		
		BigInteger result = new BigInteger("1");
		
		for(int i=0;i<pow;i++){
			result= result.multiply(n);
		}
		
		return result;
	}

}
