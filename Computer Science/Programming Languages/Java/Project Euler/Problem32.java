package de.itter.euler;

import java.util.Vector;

public class Problem32 {

	public static void main(String[] args) {
		int sum = 0;
		int limit = 9999;
		
		for(int i = 1; i<limit;i++){
			boolean hit = false;
			int[] divisors = divisors(i);
			for(int j=0;j<divisors.length-1;j++){
				for(int k=1;k<divisors.length;k++){
					if(divisors[j]*divisors[k] != i){
						continue;
					}
					if(isPandigitalIdentity(divisors[j], divisors[k],i)){
						System.out.println(i+" ("+divisors[j]+","+divisors[k]+")");
						sum += i;
						hit = true;
						break;
					}
				}
				if(hit){
					break;
				}
			}
		}
		System.out.println(sum);
	}
	
	public static boolean isPandigitalIdentity(int x, int y, int z){
		int[] digits = {1,2,3,4,5,6,7,8,9};
		if(!deleteDigits(digits, x)){
			return false;
		}
		if(! deleteDigits(digits, y)){
			return false;
		}
		if(! deleteDigits(digits, z)){
			return false;
		}
		
		for(int i=0;i<digits.length;i++){
			if(digits[i]!=-1){
				return false;
			}
		}
		
		return true;
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

	public static int[] divisors(int x){
		Vector<Integer> divisors = new Vector<Integer>();
		divisors.add(new Integer(1));
		
		int divisor = 1;
		int limit = (int) Math.sqrt(x); 
		while(divisor++ < limit){
			if(x % divisor == 0){
				divisors.add(new Integer(divisor));
			}
		}
		if( divisor * divisor == x){
			divisors.add(new Integer(divisor));
		}
		
		int composites = divisors.size()-1;
		for(int i=composites; i>0;i--){
			if(divisors.elementAt(i) * divisors.elementAt(i) == x){
				continue;
			}
			divisors.add(new Integer(x / divisors.elementAt(i).intValue()));
		}
		
		int[] res = new int[divisors.size()];
		for(int i = 0; i< res.length;i++){
			res[i] = divisors.elementAt(i);
		}
		return res;
	}
	
}
