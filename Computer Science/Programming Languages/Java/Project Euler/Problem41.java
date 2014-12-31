package de.itter.euler;

public class Problem41 {

	public static void main(String[] args) {
		
		for(long l=987654321;;l-=2){
			if(isPrime(l)){
				System.out.println(l);
				if(isPandigital(l)){
					System.out.println(l);
					return;
				}
			}
		}

	}

	public static boolean deleteDigits(int[] digits, long x){
		for(int i=1;;i*=10){
			if(i>x){
				break;
			}
			int digit = (int) ((x % (i*10)) / i);
			if(digit < 1 || digits[digit-1]==-1){
				return false;
			} else{
				digits[digit-1] = -1;
			}
		}
		return true;
	}

	public static boolean isPandigital(long x){
		int[] digits = {1,2,3,4,5,6,7,8,9};
		
		if(!deleteDigits(digits, x)){
			return false;
		}
		
		boolean phase1 = true;
		for(int i=0;i<digits.length;i++){
			if(phase1){
				if(digits[i]!= -1){
					phase1 = false;
				}
			} else{
				if(digits[i]== -1){
					return false;
				}
			}
		}
		
		return true;
	}

	public static boolean isPrime(long x){
		int limit = ((int) Math.sqrt(x))+1;
		
		if(x < 2){
			return false;
		}
		
		if(x == 2){
			return true;
		}
		
		for(int i = 2; i<= limit;i++){
			if(x % i == 0){
				return false;
			}
		}
		return true;
	}
}
