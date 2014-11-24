package de.itter.euler;

public class Problem3 {

	public static void main(String[] args) {
		
		int maxPalindromic = 0;
		int limit = 1000;
		
		for(int i = 0; i < limit; i++){
			for(int j = 0; j < limit; j++){
				if(i*j > maxPalindromic && isPalindromic(i*j)){
					maxPalindromic = i * j;
				}
			}
		}
		
		System.out.println(maxPalindromic);
	}
	
	public static boolean isPalindromic(int i){
		String decimal = new Integer(i).toString();
		for(int n = 0; n < (decimal.length() / 2); n++){
			if(! (decimal.charAt(n) == decimal.charAt(decimal.length()-n-1))){
				return false;
			}
		}
		return true;
	}

}
