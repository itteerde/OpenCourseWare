package de.itter.euler;

import java.util.Vector;

import org.apache.commons.math3.primes.Primes;

public class Problem46 {

	public static void main(String[] args) {
		int limit = 1000000;
		int[] primes = primes(limit);
		for(int i=3;i<limit;i+=2){
			boolean goldbachHolds = false;
			if(Primes.isPrime(i)){
				continue;
			}
			for(int primeIdx = 0; primeIdx<primes.length;primeIdx++){
				if(primes[primeIdx]>=i){
					goldbachHolds = false;
					break;
				}
				for(int root=1;root<Math.sqrt(i);root++){
					if(primes[primeIdx]+2*root*root == i){
						goldbachHolds=true;
					}
				}
				if(goldbachHolds){
					break;
				}
			}
			if(!goldbachHolds){
				System.out.println(i);
				return;
			}
		}
	}
	
	public static int[] primes(int limit){
		Vector<Integer> primes = new Vector<Integer>();
		int lastPrime = 0;

		while(lastPrime < limit){
			lastPrime = Primes.nextPrime(lastPrime+1);
			if(lastPrime <= limit){
				primes.add(new Integer(lastPrime));
			}
		}
		
		int[] res = new int[primes.size()];
		for(int i=0;i<res.length;i++){
			res[i]=primes.elementAt(i);
		}
		return res;
	}

}
