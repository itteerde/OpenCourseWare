package de.itter.euler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

public class Problem23 {

	public static void main(String[] args) {

		System.out.println(isAbundandNumber(4));

		HashSet<Long> abundandNumbers = new HashSet<Long>();
		for(int i=1;i<=28123;i++){
			if(isAbundandNumber(i)){
				abundandNumbers.add(new Long(i));
			}
		}
		
		
		long[] anA = new long[abundandNumbers.size()];
		Iterator<Long> it = abundandNumbers.iterator();
		for(int i=0;i<anA.length;i++){
			anA[i] = it.next();
		}
		Arrays.sort(anA);
		
		long sum = 0;
		int limit = abundandNumbers.size();
		boolean next = false;
		for(int k=1;k<=28123;k++){
			next = false;
			for(int i=0;i<limit && next==false;i++){
				if(abundandNumbers.contains(k-anA[i])){
					next=true;
					break;
				}
			}
			if(next){
				continue;
			}else{
				sum += k;
			}
		}
		System.out.println("Sum: "+sum);
	}

	public static void printLongArray(long[] x){
		for(int i = 0; i < x.length;i++){
			System.out.println(x[i]);
		}
	}
	
	public static boolean isPerfectNumber(long x){
		long[] divisors = divisors(x);
		long sum = 0;
		for(long l: divisors){
			sum+=l;
		}
		if(sum == x){
			return true;
		}
		return false;
	}
	
	public static boolean isAbundandNumber(long x){
		long[] divisors = divisors(x);
		long sum = 0;
		for(long l: divisors){
			sum+=l;
		}
		if(sum > x){
			return true;
		}
		return false;
	}
	
	public static long[] divisors(long x){
		Vector<Long> divisors = new Vector<Long>();
		divisors.add(new Long(1));
		
		long l = 1;
		long limit = (long) Math.sqrt(x); 
		while(l++ < limit){
			if(x % l == 0){
				divisors.add(new Long(l));
			}
		}
		if( l * l == x){
			divisors.add(new Long(l));
		}
		
		int composites = divisors.size()-1;
		for(int i=composites; i>0;i--){
			if(divisors.elementAt(i) * divisors.elementAt(i) == x){
				continue;
			}
			divisors.add(new Long(x / divisors.elementAt(i).longValue()));
		}
		
		long[] res = new long[divisors.size()];
		for(int i = 0; i< res.length;i++){
			res[i] = divisors.elementAt(i);
		}
		return res;
	}

}
