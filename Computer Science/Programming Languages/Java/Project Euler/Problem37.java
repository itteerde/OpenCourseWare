package de.itter.euler;

import java.util.Iterator;
import java.util.Vector;

public class Problem37 {

	public static void main(String[] args) {
		int sum = 0;
		int count = 0;
		
		for(int i=9;;i++){
			if(isPrime(truncates(i))){
				sum += i;
				count++;
				System.out.println(i);
			}
			if(count == 11){
				System.out.println(sum);
				return;
			}
		}
	}
	
	public static boolean isPrime(int[] xs){
		for(int i=0; i< xs.length;i++){
			if(!isPrime(xs[i])){
				return false;
			}
		}
		return true;
	}
	
	public static int[] truncates(int x){
		Vector<Integer> truncates = new Vector<Integer>();
		
		int t = x;
		for(;;){
			if(t==0){
				break;
			}
			truncates.add(new Integer(t));
			t /= 10;
		}
		
		t=0;
		for(int i=10;;i*=10){
			if(i>x){
				break;
			}
			t = x % i;
			truncates.add(new Integer(t));
		}
		
		int[] res = new int[truncates.size()];
		Iterator<Integer> it = truncates.iterator();
		for(int i=0;i<res.length;i++){
			res[i]= it.next().intValue();
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
