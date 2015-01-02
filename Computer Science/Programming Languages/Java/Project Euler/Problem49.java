package de.itter.euler;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.math3.primes.Primes;

public class Problem49 {

	public static void main(String[] args) {
		for(int a=1;a<=9;a++){
			for(int b=1;b<=9;b++){
				for(int c=1;c<=9;c++){
					for(int d=1;d<=9;d++){
						
						int n1 = a*1000+b*100+c*10+d;
						if(!Primes.isPrime(n1)){
							continue;
						}
						
						ArrayList<Integer> primePermutations = new ArrayList<Integer>();
						
						int[] num = new int[4];
						num[0] = a;
						num[1] = b;
						num[2] = c;
						num[3] = d;
						
						ArrayList<ArrayList<Integer>>permutations = permute(num);
						
						Iterator<ArrayList<Integer>> permutationsIt = permutations.iterator();
						while(permutationsIt.hasNext()){
							ArrayList<Integer> permutation = permutationsIt.next();
							int n2 = permutation.get(0) * 1000 + permutation.get(1) * 100 
									+ permutation.get(2) * 10 + permutation.get(3);
							if(!Primes.isPrime(n2)){
								continue;
							}else{
								primePermutations.add(n2);
							}
						}
						
						int difference = 0;
						Iterator<Integer> pmIt = primePermutations.iterator();
						while(pmIt.hasNext()){
							int i = pmIt.next();
							if(i == n1){
								continue;
							}
							difference = Math.abs(n1-i);
							Iterator<Integer> pmItB = primePermutations.iterator();
							while(pmItB.hasNext()){
								int j = pmItB.next();
								if(j == i || j == n1){
									continue;
								}
								if(Math.abs(j-i) == difference || Math.abs(n1-j) == difference){
									System.out.println(n1+","+i+","+j);
								}
							}
						}
					}
				}
			}
		}
	}

	public static ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	 
		//start from an empty list
		result.add(new ArrayList<Integer>());
	 
		for (int i = 0; i < num.length; i++) {
			//list of list in current iteration of the array num
			ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
	 
			for (ArrayList<Integer> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size()+1; j++) {
					// + add num[i] to different locations
					l.add(j, num[i]);
	 
					ArrayList<Integer> temp = new ArrayList<Integer>(l);
					current.add(temp);
	 
					//System.out.println(temp);
	 
					// - remove num[i] add
					l.remove(j);
				}
			}
	 
			result = new ArrayList<ArrayList<Integer>>(current);
		}
	 
		return result;
	}
}
