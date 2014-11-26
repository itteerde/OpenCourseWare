package de.itter.euler;

import java.util.Iterator;
import java.util.Vector;

public class Problem21 {

	public static void main(String[] args) {
		long res = 0;
		for(int i=0; i<10000;i++){
			if(isAmicable(i)){
				res += i;
			}
		}
		System.out.println(res);
	}

	public static boolean isAmicable(long x){
		long dA = sum(properDivisors(x));
		if(dA == x){
			return false;
		}
		long dB = sum(properDivisors(dA));
		if(x == dB){
			return true;
		}
		return false;
	}
	
	public static long sum(long[] x){
		long res = 0;
		for(int i=0;i<x.length;i++){
			res += x[i];
		}
		return res;
	}
	
	public static long[] properDivisors(long n){
		Vector<Long> properDivisors = new Vector<Long>();
		
		for(long i = 1; i <= n/2;i++){
			if(n % i == 0){
				properDivisors.add(new Long(i));
			}
		}
		long[] res = new long[properDivisors.size()];
		Iterator<Long> it = properDivisors.iterator();
		for(int i = 0; i< res.length;i++){
			res[i]=((Long)it.next()).longValue();
		}
		
		return res;
	}

}
