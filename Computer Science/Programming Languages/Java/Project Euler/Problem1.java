package de.itter.euler;

public class Problem1 {

	public static void main(String[] args) {
		long sum = 0;
		long start = System.currentTimeMillis();
		
		for(int i = 0; i< 10000000; i++){
			if(i % 3 == 0 || i % 5 == 0){
				sum += i;
			}
		}
		
		long duration = System.currentTimeMillis()-start;
		System.out.println(sum + "("+duration+"ms)");
	}

}
