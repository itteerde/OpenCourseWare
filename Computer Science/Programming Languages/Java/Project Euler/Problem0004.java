package de.itter.euler;

public class Problem0004 {

	public static void main(String[] args) {

		int max = -1;
		
		
		for(int a = 999; a > 0; a--){
			for(int b = 999; b > 0; b--){
				if(isPalindromic(a*b)){
					if(a*b > max){
						max = a*b;
					}
				}
			}
		}
		
		
		System.out.println(max);
	}
	
	private static boolean isPalindromic(long n){
		int log = (int) Math.log10(n);
		long magnitudeR = 10;
		long magnitudeL = (long) Math.pow(10, log);
		
		for(int i=0;i<=log/2;i++){
			if((n % (magnitudeR))/(magnitudeR/10)!=(n/magnitudeL)%10){
				return false;
			}
			
			magnitudeR *= 10;
			magnitudeL /= 10;
		}
		
		return true;
	}

}
