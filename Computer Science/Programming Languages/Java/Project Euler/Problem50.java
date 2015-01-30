package de.itter.euler;

import java.util.Arrays;

public class Problem50 {

	public static void main(String[] args) {
		long[] primes = primes(1000000);
		System.out.println("Primes generated.");
		int sequence = 0;
		long prime = 0;
		
		for(int start=0;start<primes.length-sequence;start++){
			long sum = 0;
			
			for(int idx=start;idx<primes.length && sum < 1000000 ;idx++){
				sum += primes[idx];
				if(isPrime(sum, primes)){
					if(idx-start+1 > sequence){
						sequence = idx-start+1;
						prime = sum;
						System.out.println("Prime:"+prime+", length:"+sequence);
					}
				}
			}
		}
		
		System.out.println(prime);
	}

	
	private static boolean isPrime(long sum, long[] primes) {
		return Arrays.binarySearch(primes, sum) >= 0;
	}


	public static long[] primes(int limit){
		boolean[] calc = calcPrimes(limit);
		int count = 0;
		for(int i=0;i<calc.length;i++){
			if(calc[i]){
				count++;
			}
		}
		long[] res = new long[count];
		int idx = 0;
		for(int i=0;i<calc.length;i++){
			if(calc[i]){
				res[idx++]=i;
			}
		}
		return res;
	}
	
	public static boolean[] calcPrimes(int limit){
		boolean[] prime = new boolean[limit+1];
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;
		int root = (int) Math.ceil(Math.sqrt(limit));
		
		for(int n=2; n < root; n++){
			for(int i=n+n;i<=limit;i+=n){
				prime[i] = false;
			}
		}
		
		if(root * root == limit){
			prime[limit] = false;
		}
		
		return prime;
	}
}
