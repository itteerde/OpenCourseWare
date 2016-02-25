package de.itter.euler;

import java.util.Iterator;
import java.util.Vector;

public class Problem0003 {

	static long theNumber = 600851475143L;
	
	public static void main(String[] args){
		int primeMaximum = (int) Math.sqrt(theNumber);
		Vector<Integer> primeFactors = new Vector<Integer>();
		
		new SiebErastosthenes(primeMaximum);
		
		for(int i=0;;i++){
			if(theNumber % SiebErastosthenes.primes.elementAt(i) == 0){
				primeFactors.addElement(SiebErastosthenes.primes.elementAt(i));
				theNumber /= SiebErastosthenes.primes.elementAt(i);
			}
			
			if(theNumber == 1){
				break;
			}
		}
		
		
		for(Iterator<Integer> it = primeFactors.iterator();it.hasNext();){
			System.out.println(it.next());
		}
	}
}
