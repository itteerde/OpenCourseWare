package de.itter.euler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Problem67 {

	public static void main(String[] args) {
		int[][] rows = readTriangle();
		
		for(int i = 98; i>= 0; i--){
			for(int j = 0; j <= i; j++){
				rows[i][j] += rows[i+1][j] > rows[i+1][j+1] ? rows[i+1][j] : rows[i+1][j+1];
			}
			System.out.println();
			System.out.print("row "+i+":");
			for(int k = 0; k<= i;k++){
				System.out.print(" "+rows[i][k]);
			}
		}
	}
	
	public static int[][] readTriangle(){
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("p067_triangle.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int[][] rows = new int[100][100];
		for(int i = 0; i < 100; i++){
			try {
				String line = in.readLine();
				StringTokenizer st = new StringTokenizer(line);
				int j = 0;
				while(st.hasMoreTokens()){
					int n = Integer.parseInt(st.nextToken());
					rows[i][j] = n;
					j++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

}
