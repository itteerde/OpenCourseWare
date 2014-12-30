package de.itter.euler;

public class Problem38 {

	public static void main(String[] args) {
		long res = 0;
		
		for(int i =9123;i<=9876;i++){
			if(isPandigitalMultiple(i)){
				if((((long)i)*100000+i*2)>res){
					System.out.println(i);
					res = (((long)i)*100000+i*2);
				}
			}
		}
		System.out.println(res);
	}

	public static boolean deleteDigits(int[] digits, int x){
		for(int i=1;;i*=10){
			if(i>x){
				break;
			}
			int digit = (x % (i*10)) / i;
			if(digit < 1 || digits[digit-1]==-1){
				return false;
			} else{
				digits[digit-1] = -1;
			}
		}
		return true;
	}

	public static boolean isPandigitalMultiple(int x){
		int[] digits = {1,2,3,4,5,6,7,8,9};
		
		int i = 1;
		while(!isEmpty(digits)){
			if(!deleteDigits(digits,x*i++)){
				return false;
			}
		}
		return true;
	}

	private static boolean isEmpty(int[] digits) {
		for(int i:digits){
			if(i != -1){
				return false;
			}
		}
		return true;
	}
}
