package de.itter.euler;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class Problem24 {

	public static void main(String[] args) {
		int[] res = new int[10];
		int[] objects = new int[10];
		
		for(int i=0; i<10;i++){
			objects[i] = i;
		}
		
		int index = 1000000-1;
		for(int i=objects.length-1;i>0;i--){
			res[objects.length-1-i] = (int) (index / CombinatoricsUtils.factorial(i));
			index -= res[objects.length-1-i] * CombinatoricsUtils.factorial(i);
		}

		for(int i=0;i<res.length;i++){
			System.out.print(nth(objects,res[i]+1));
		}
		
	}
	
	public static int nth(int[] objects, int i){
		int count = 0;
		for(int j=0;;j++){
			if(objects[j]!= -1){
				count++;
				if(count == i){
					int res = objects[j];
					objects[j] = -1;
					return res;
				}
			}
		}
	}
	
}
