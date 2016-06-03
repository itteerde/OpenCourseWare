package de.itter.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem0107 {

	public static void main(String[] args) {
		int[][] g = readMatrix();
		int[][] mst = new int[40][];
		for (int i = 0; i < mst.length; i++) {
			mst[i] = new int[40];
		}
		boolean[] nMST = new boolean[40];
		nMST[0] = true;
		while (!isMST(nMST)) {

			grow(g, mst, nMST);
		}

		System.out.println(weight(g) - weight(mst));
		System.out.println(Arrays.deepToString(mst));
	}

	private static void grow(int[][] g, int[][] mst, boolean[] nMST) {
		int min = Integer.MAX_VALUE;
		int bestU = -1;
		int bestV = -1;

		for (int v = 0; v < nMST.length; v++) {
			if (!nMST[v]) {
				continue;
			}

			for (int u = 0; u < g[v].length; u++) {
				if (nMST[u]) {
					continue;
				}

				if (g[v][u] != 0 && g[v][u] < min) {
					min = g[v][u];
					bestU = u;
					bestV = v;
				}
			}
		}

		nMST[bestU] = true;
		mst[bestV][bestU] = g[bestV][bestU];
		mst[bestU][bestV] = g[bestU][bestV];

	}

	private static boolean isMST(boolean[] nMST) {

		for (int i = 0; i < nMST.length; i++) {
			if (!nMST[i]) {
				return false;
			}
		}

		return true;
	}

	private static int weight(int[][] g) {
		int w = 0;
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				w += g[i][j];
			}
		}
		return w / 2;
	}

	@SuppressWarnings("resource")
	private static int[][] readMatrix() {

		int[][] g = new int[40][];
		int y = 0;

		try {
			BufferedReader in = new BufferedReader(new FileReader("p107_network.txt"));
			String line = null;
			while ((line = in.readLine()) != null) {
				g[y] = new int[40];

				StringTokenizer st = new StringTokenizer(line, ",");
				int x = 0;
				while (st.hasMoreTokens()) {
					String token = st.nextToken();
					if (!token.equals("-")) {
						g[y][x] = Integer.parseInt(token);
					}
					x++;
				}
				y++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return g;
	}

}
