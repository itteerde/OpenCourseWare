package de.itter.euler;

import java.util.Vector;

public class Problem74 {

	public static void main(String[] args) {

		int count = 0;
		
		for(int i = 1; i<1000000;i++){
			Vector<Integer> chain = new Vector<Integer>();
			chain.add(new Integer(i));
			for(;;){
				int next = facSum(chain.lastElement());
				if(chain.contains(next)){
					if(chain.size()==60){
						System.out.println(chain);
						count++;
					}
					break;
				}
				chain.add(next);
			}
		}
		
		System.out.println(count);
		
	}
	
	public static int facSum(int i){
		Vector<Integer> digits = digits(i);
		int sum = 0;
		for(int j: digits){
			sum += fac(j);
		}
		return sum;
	}
	
	public static Vector<Integer> digits(int i){
		Vector<Integer> digits = new Vector<Integer>();
		
		for(;;i/=10){
			int digit = i % 10;
			digits.add(new Integer(digit));
			if(i<10){
				return digits;
			}
		}
		
	}
	
	public static int fac(int n){
		int fac = 1;
		for(;n>1;n--){
			fac*=n;
		}
		return fac;
	}

}
