package de.itter.euler;

public class Problem4 {

	public static void main(String[] args) {
		int x = 0;
		
		for(;;){
			x++;
			if(isEvenlyDivisible(x,20)){
				System.out.println(x);
				return;
			}
		}
	}
	
	public static boolean isEvenlyDivisible(int x, int y){
		for(int i= y;i > 0; i--){
			if(x % i != 0){
				return false;
			}
		}
		return true;
	}

}
