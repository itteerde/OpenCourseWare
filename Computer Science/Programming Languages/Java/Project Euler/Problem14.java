package de.itter.euler;

public class Problem14 {

	public static void main(String[] args) {
		int maxLength = 0;
		long maxLengthStartingNumber = 1;
		for(int i=1;i<1000000;i++){
			int length = collatzLength(i);
			if (length > maxLength){
				maxLength = length;
				maxLengthStartingNumber = i;
			}
		}
		System.out.println(maxLengthStartingNumber);
	}
	
	public static int collatzLength(long x){
		int steps = 1;
		for(;;steps++){
			if(x == 1){
				return steps;
			}
			if(x % 2 == 0){
				x /= 2;
			}else{
				x = 3* x + 1;
			}
		}
	}

}
