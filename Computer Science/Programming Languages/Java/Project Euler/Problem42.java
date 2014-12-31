package de.itter.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Problem42 {

	public static void main(String[] args) {
		Vector<String> words = readWords(new File("p042_words.txt"));
		
		int count = 0;
		for(String s: words){
			if(isTriangularNumber(wordValue(s))){
				count++;
			}
		}
		
		System.out.println(count);
	}

	public static boolean isTriangularNumber(int x){
		for(int i=0;;i++){
			int tn = ((i+1) * i) / 2;
			if(tn == x){
				return true;
			}
			if(tn > x){
				return false;
			}
		}
	}
	
	public static int wordValue(String s){
		int wordValue = 0;
		for(int i=0;i<s.length();i++){
			wordValue += s.charAt(i) - 'A' +1;
		}
		
		return wordValue;
	}
	
	public static Vector<String> readWords(File f){
		Vector<String> words = new Vector<String>();
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(f));
			String wordsString = in.readLine();
			StringTokenizer st = new StringTokenizer(wordsString,",");
			while(st.hasMoreTokens()){
				String token = st.nextToken();
				words.add(token.substring(1, token.length()-1));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return words;
	}

}
