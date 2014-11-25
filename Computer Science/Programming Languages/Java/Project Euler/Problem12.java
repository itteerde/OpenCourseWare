package de.itter.euler;

public class Problem12 {
	
	public static void main(String[] args) {
		
		long i = 1;
		long triNum = 1;
		
		for(;;){
			int numberOfDivisors = numberOfDivisors(triNum);
			if(numberOfDivisors > 500){
				System.out.println(triNum);
				return;
			}
			System.out.println("triNum: "+triNum+" ("+numberOfDivisors+" divisors)");
			triNum += i;
			i++;
		}
	}
	
	public static int numberOfDivisors(long x){
		int res = 0;
		
		long i = 1;
		for(;;){
			if(i * 2 > x){
				return res+1;
			}
			if(x % i == 0){
				res++;
			}
			i++;
		}
	}

}
