package de.itter.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

public class Problem22 {

	public static void main(String[] args) {
		Vector<String> names = readNames();
		Collections.sort(names);
		
		int score = 0;
		for(int i=1; i<= names.size();i++){
			score += i * score(names.elementAt(i-1));
		}
		
		System.out.println(score);
	}

	private static int score(String s) {
		int score = 0;
		for(int i=0;i<s.length();i++){
			score += s.charAt(i)-'A'+1;
		}
		return score;
	}

	private static Vector<String> readNames() {
		Vector<String> res = new Vector<String>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("p022_names.txt"));
			String names = in.readLine();
			StringTokenizer st = new StringTokenizer(names,",\"");
			while(st.hasMoreTokens()){
				res.add(st.nextToken());
			}
		} catch (IOException e) {
			try {
				in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return res;
	}

}
