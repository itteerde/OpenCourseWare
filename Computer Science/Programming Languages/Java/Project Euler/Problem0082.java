package de.itter.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Problem0082 {

	public static void main(String[] args) {
		int[][] matrix = readMatrix();
		int[][] exploration = new int[matrix.length][];
		for (int i = 0; i < exploration.length; i++) {
			exploration[i] = new int[matrix[i].length];
			exploration[i][0] = matrix[i][0];
		}

		int pathCost = -1;
		int[] step = null;

		for (;;) {
			step = step(matrix, exploration);
			if (step[0] == 1) {
				pathCost = step[1];
				break;
			}
		}

		System.out.println(pathCost);
	}

	/**
	 * 
	 * @param matrix
	 * @param exploration
	 * @return <code>int[] result</code>, such that <code>result[0]==1</code> if
	 *         and only if target has been reached and <code>result[1]</code>
	 *         equal to the optimum path cost to (including) the step done.
	 */
	private static int[] step(int[][] matrix, int[][] exploration) {
		int[] result = new int[2];

		int optimumCost = Integer.MAX_VALUE;
		int[] bestStep = new int[2];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 1; j < matrix[i].length; j++) {
				if (exploration[i][j] != 0) {
					continue;
				}
				int cost = cost(i, j, matrix, exploration);
				if (cost < optimumCost) {
					optimumCost = cost;
					bestStep[0] = i;
					bestStep[1] = j;
				}
			}
		}

		exploration[bestStep[0]][bestStep[1]] = optimumCost;
		if (bestStep[1] == matrix[bestStep[0]].length - 1) {
			result[0] = 1;
		}
		result[1] = optimumCost;

		return result;
	}

	private static int cost(int i, int j, int[][] matrix, int[][] exploration) {
		int fromLeft = Integer.MAX_VALUE;
		int fromAbove = Integer.MAX_VALUE;
		int fromBelow = Integer.MAX_VALUE;

		if (j > 0 && exploration[i][j - 1] != 0) {
			fromLeft = exploration[i][j - 1] + matrix[i][j];
		}

		if (i > 0 && exploration[i - 1][j] != 0) {
			fromAbove = exploration[i - 1][j] + matrix[i][j];
		}

		if (i < matrix.length - 1 && exploration[i + 1][j] != 0) {
			fromBelow = exploration[i + 1][j] + matrix[i][j];
		}

		int min = 0;
		if (fromLeft < fromAbove) {
			min = fromLeft;
		} else {
			min = fromAbove;
		}

		if (fromBelow < min) {
			min = fromBelow;
		}

		return min;
	}

	private static int min(int[][] matrix) {
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < matrix[0].length; i++) {
			if (matrix[i][matrix[i].length - 1] < min) {
				min = matrix[i][matrix[i].length - 1];
			}
		}
		return min;
	}

	private static int[][] readMatrix() {

		int[][] matrix = new int[80][80];
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader("p082_matrix.txt"));
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
