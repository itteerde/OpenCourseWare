package de.itter.euler;

import java.util.TreeSet;

import org.apache.commons.math3.primes.Primes;

public class Problem47 {

	public static void main(String[] args) {
		
		for(int i=2;;i++){
			if(primeFactors(i).size() == 4){
				if(primeFactors(i+1).size() == 4){
					if(primeFactors(i+2).size() == 4){
						if(primeFactors(i+3).size() == 4){
							System.out.println(i);
							return;
						}
					}
				}
			}
		}
		
	}
	
	public static TreeSet<Integer> primeFactors(int n){
		return new TreeSet<Integer>(Primes.primeFactors(n));
	}

}
