package de.itter.euler;

import java.math.BigInteger;

public class Problem55 {

	public static void main(String[] args) {
		int count = 0;
		for(int i=1;i<10000;i++){
			if(isLychrelNumber(i)){
				count++;
			}
		}
		System.out.println(count);
	}

	private static boolean isLychrelNumber(int n){
		BigInteger cur = new BigInteger(Integer.toString(n));
		for(int i=0; i<50;i++){
			cur = cur.add(new BigInteger(reverse(cur.toString())));
			if(isPalindromic(cur)){
				return false;
			}
		}
		return true;
	}

	private static boolean isPalindromic(BigInteger i) {
		return isPalindrom(i.toString());
	}
	
	private static String reverse(String s){
		StringBuffer res = new StringBuffer();
		for(int j=0; j<s.length();j++){
			res.append(s.charAt(s.length()-j-1));
		}
		return res.toString();
	}

	public static boolean isPalindrom(String s){
		for(int i=0; i<=s.length()/2;i++){
			if(s.charAt(i) != s.charAt(s.length()-i-1)){
				return false;
			}
		}
		return true;
	}
}
