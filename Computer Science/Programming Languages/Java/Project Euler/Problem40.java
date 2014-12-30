package de.itter.euler;

import java.util.Vector;

public class Problem40 {

	public static void main(String[] args) {
		byte[] c = new byte[1000000];
		c[c.length-1] = -1;
		int idx = 0;
		
		int i=1;
		while(c[c.length-1]==-1){
			int[] ia = toIntArray(i++);
			append(ia,c,idx);
			idx+=ia.length;
		}
		
		int res = 1;
		for(int j=1;j<=1000000;j*=10){
			res *= c[j-1];
		}
		
		System.out.println(res);
	}
	
	public static int[] toIntArray(int i){
		Vector<Integer> digits = new Vector<Integer>();
		
		for(int j=1;j<=i;j*=10){
			digits.add(new Integer( (i%(j*10))/j ));
		}
		
		int[] res = new int[digits.size()];
		for(int j=0;j<res.length;j++){
			res[res.length-j-1] = digits.elementAt(j);
		}
		return res;
	}
	
	public static void printArray(byte[] a, int start, int end){
		for(int i=start;i<=end;i++){
			if((i-start) % 100 == 0){
				System.out.println();
			}
			System.out.print(a[i]);
		}
	}
	
	public static void append(int[] a, byte[] b, int idx){
		for(int i=0;i<a.length;i++){
			if(idx + i < b.length){
				b[idx+i] = (byte)a[i];
			}
		}
	}

}
