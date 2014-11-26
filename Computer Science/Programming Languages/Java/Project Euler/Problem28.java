package de.itter.euler;

public class Problem28 {

	public static void main(String[] args) {
		int sideLength = 1;
		int sum = 1;
		int pos = 1;
		
		for(int i = 0; i<500;i++){
			sideLength += 2;
			for(int j = 0; j<4;j++){
				pos += (sideLength-1);
				sum += pos;
			}
		}
		
		System.out.println(sum);
	}

}
