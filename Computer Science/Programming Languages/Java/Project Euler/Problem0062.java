package de.itter.euler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class Problem0062 {

	public static void main(String[] args) {
		HashMap<String,Vector<Integer>> map = new HashMap<String,Vector<Integer>>();
		
		for(int i=1;;i++){
			String key = key((long) Math.pow(i, 3));
			if(!map.containsKey(key)){
				map.put(key, new Vector<Integer>());
			}
			map.get(key).add(new Integer(i));
			if(map.get(key).size() == 5){
				System.out.println(map.get(key));
				return;
			}
		}
		
	}
	
	private static String key(long number){
		char[] chars = Long.toString(number).toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
}
