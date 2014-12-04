package de.itter.euler;

import java.util.Vector;

public class Problem26 {

	public static void main(String[] args) {
		int max = 0;
		int maxD = 0;
		for(int i=1;i<1000;i++){
			if(period(1,i).length() > max){
				max = period(1,i).length();
				maxD = i;
			}
		}
		System.out.println(maxD);
	}
	
	public static String period(long n, long d){
		StringBuffer period = new StringBuffer();
		Vector<Long> rests = new Vector<Long>();
		
		long rest = n;
		for(;;){
			period.append(rest / d);
			rest -= (rest / d) * d;
			if(rests.contains(new Long(rest))){
				break;
			}else{
				rests.add(new Long(rest));
			}
			rest *= 10;
		}
		
		return period.toString();
	}

}
