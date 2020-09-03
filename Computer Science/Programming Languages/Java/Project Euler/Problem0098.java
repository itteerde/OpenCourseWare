package de.itteerde.euler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class Problem0098 {

	public static void main(String[] args) {
		Vector<String> words = importWords();
		HashMap<String, Vector<String>> keyGroups = new HashMap<String, Vector<String>>();

		for (int i = 0; i < words.size(); i++) {
			String w = words.elementAt(i);
			String key = toKey(w);
			if (!keyGroups.containsKey(key)) {
				keyGroups.put(key, new Vector<String>());
			}
			keyGroups.get(key).add(w);
		}

		Vector<Vector<String>> anagrams = new Vector<Vector<String>>();
		Iterator<String> it = keyGroups.keySet().iterator();
		while (it.hasNext()) {
			Vector<String> group = keyGroups.get(it.next());
			if (group.size() > 1) {
				anagrams.add(group);
			}
		}

		int maxIdx = 0;
		for (int i = 0; i < anagrams.size(); i++) {
			if (anagrams.elementAt(i).elementAt(0).length() >= anagrams.elementAt(maxIdx).elementAt(0).length()) {
				System.out.println(anagrams.elementAt(i) + ">=" + anagrams.elementAt(maxIdx));
				maxIdx = i;
			}
		}

		System.out.println(anagrams.elementAt(maxIdx));

		char[] a = { 'i', 'n', 't', 'r', 'o', 'd', 'u', 'c', 'e' };
		int ai = 123456789;
		System.out.println(applyAnagram(ai));

		for (int i = 987654321;; i--) {

			int sqrt = (int) Math.sqrt(i);
			if (sqrt * sqrt == i) {
				int sqrtA = (int) Math.sqrt(applyAnagram(i));
				if (sqrtA * sqrtA == applyAnagram(i)) {
					System.out.println(i + " (" + applyAnagram(i) + ")");
				}
			}
		}
	}

	private static String toKey(String s) {

		char[] key = s.toCharArray();
		Arrays.sort(key);
		return new String(key);
	}

	private static Vector<String> importWords() {
		BufferedReader in = null;
		Vector<String> words = new Vector<String>();
		try {
			in = new BufferedReader(new FileReader("p098_words.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String txt = in.readLine();
			String word = null;
			StringTokenizer st = new StringTokenizer(txt, ",");
			while (st.hasMoreTokens()) {
				word = st.nextToken();
				words.addElement(word.substring(1, word.length() - 1));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return words;
	}

	private static int applyAnagram(int a) {
		int i = a;
		int res = 0;

		res += (i % 10) * 10000000;// e
		i /= 10;

		res += (i % 10) * 10000;// c
		i /= 10;

		res += (i % 10) * 100000;// u
		i /= 10;

		res += (i % 10) * 1000000;// d
		i /= 10;

		res += (i % 10) * 10;// o
		i /= 10;

		res += (i % 10) * 100000000;// r
		i /= 10;

		res += (i % 10) * 1000;// t
		i /= 10;

		res += (i % 10) * 1;// n
		i /= 10;

		res += (i % 10) * 100;// i
		i /= 10;

		return res;
	}

}
