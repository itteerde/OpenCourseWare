package de.itter.euler;

import java.math.BigInteger;

public class Problem16 {

	public static void main(String[] args) {
		String iS = new BigInteger("2").pow(1000).toString();
		byte[] i = iS.getBytes();
		
		System.out.println(crossSum(i));
	}
	
	public static long crossSum(byte[] n){
		long res = 0;
		for(int i=0;i<n.length;i++){
			res += n[i]-'0';
		}
		return res;
	}

}
