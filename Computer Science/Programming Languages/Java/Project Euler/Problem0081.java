package de.itter.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Problem0081 {

	public static void main(String[] args) {
		int[][] matrix = readMatrix();

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (i > 0 && j > 0) {
					matrix[i][j] += matrix[i - 1][j] < matrix[i][j - 1] ? matrix[i - 1][j] : matrix[i][j - 1];
				} else {
					if (i > 0) {
						matrix[i][j] += matrix[i - 1][j];
					} else {
						if (j > 0) {
							matrix[i][j] += matrix[i][j - 1];
						}
					}
				}
			}
		}
		System.out.println(matrix[matrix.length - 1][matrix[matrix.length - 1].length - 1]);
	}

	private static int[][] readMatrix() {

		int[][] matrix = new int[80][80];
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader("p081_matrix.txt"));
			String line = null;
			int row = 0;

			while ((line = in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				int column = 0;

				while (st.hasMoreTokens()) {
					matrix[row][column] = Integer.parseInt(st.nextToken());
					column++;
				}

				row++;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return matrix;
	}

}
