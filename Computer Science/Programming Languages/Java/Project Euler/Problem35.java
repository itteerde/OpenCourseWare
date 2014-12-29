package de.itter.euler;

import java.util.Vector;

public class Problem35 {

	public static void main(String[] args) {
		
		int res = 0;
		
		for(int i=2;i<1000000;i++){
			if(isPrime(i)){
				int[] rotations = rotations(i);
				boolean areRotationsPrime = true;
				for(int j: rotations){
					if(!isPrime(j)){
						areRotationsPrime = false;
						break;
					}
				}
				if(areRotationsPrime){
					System.out.println(i);
					res++;
				}
			}
		}
		
		System.out.println("count: "+res);
	}

	private static int[] rotations(int i) {
		Vector<Integer> rotations = new Vector<Integer>();
		String iStr = Integer.toString(i);
		for(int j=0;j<iStr.length();j++){
			StringBuffer s = new StringBuffer();
			s.append(iStr.substring(iStr.length()-j, iStr.length()));
			s.append(iStr.substring(0, iStr.length()-j));
			rotations.add(Integer.parseInt(s.toString()));
		}
		
		int[] res = new int[rotations.size()];
		for(int j=0;j<res.length;j++){
			res[j] = rotations.elementAt(j);
		}
		
		return res;
	}

	public static boolean isPrime(int x){
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
