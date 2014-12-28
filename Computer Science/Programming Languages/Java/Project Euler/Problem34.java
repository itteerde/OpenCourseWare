package de.itter.euler;

public class Problem34 {

	public static void main(String[] args) {
		int[] factorials = new int[10];
		factorials[0]=1;
		for(int i=1;i<10;i++){
			factorials[i]=factorials[i-1]*i;
		}
		
		int sum = 0;
		for(int i=3;;i++){
			if(isDigitalFactorialSum(i, factorials)){
				sum += i;
				System.out.println(i+", sum:"+sum);
			}
		}
	}
	
	public static boolean isDigitalFactorialSum(int x, int[] factorials){
		int sum = 0;
		for(int i=1;;i*=10){
			if(i>x){
				if(sum == x){
					return true;
				} else{
					return false;
				}
			}
			int digit = (x % (i*10))/i;
			sum += factorials[digit];
		}
	}

}
