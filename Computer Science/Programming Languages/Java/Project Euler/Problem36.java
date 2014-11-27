package de.itter.euler;

public class Problem36 {

	public static void main(String[] args) {
		int sum = 0;
		for(int i=0;i<1000000;i++){
			if(isPalindromic(i)){
				sum += i;
			}
		}
		System.out.println(sum);
	}
	
	public static boolean isPalindromic(int x){
		String s10 = Integer.toString(x);
		
		if(!isPalindrom(s10)){
			return false;
		}
		
		String s2 = Integer.toBinaryString(x);
		
		if(!isPalindrom(s2)){
			return false;
		}
		
		return true;
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
