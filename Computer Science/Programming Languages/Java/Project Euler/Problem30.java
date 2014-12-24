package de.itter.euler;

public class Problem30 {

	public static void main(String[] args) {
		int limit = 6 * 9 * 9 * 9 * 9 * 9;
		
		int sum = 0;
		for(int i=2;i<limit;i++){
			if(isSumOfDigitsPow(i, 5)){
				sum += i;
				System.out.println(i);
			}
		}
		System.out.println(sum);
	}
	
	public static boolean isSumOfDigitsPow(int x, int pow){
		int rest = x;
		int sum = 0;
		
		int i = 10;
		while(rest > 0){
			int digit = (rest % i) / (i/10);
			rest -= rest % i;
			i *= 10;
			
			int digitPow = 1;
			for(int j=0;j<pow;j++){
				digitPow *= digit;
			}
			
			sum += digitPow;
		}
		
		if(sum == x){
			return true;
		}
		return false;
	}

}
