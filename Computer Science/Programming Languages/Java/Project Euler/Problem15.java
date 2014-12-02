package de.itter.euler;

import java.math.BigInteger;

public class Problem15 {

	public static void main(String[] args) {
		int gridSize = 20;
		BigInteger[][] grid = new BigInteger[gridSize+1][gridSize+1];
		
		for(int i = 0; i < grid[0].length;i++){
			for(int j = 0; j < grid[i].length; j++){
				grid[i][j] = new BigInteger("0");
			}
		}
		
		grid[0][0] = new BigInteger("1");
		
		for(int i = 0; i < grid[0].length; i++){
			for(int j = 0; j < grid[i].length; j++){
				if(i == 0 && j == 0){
					continue;
				}
				
				if(i == 0){
					grid[i][j] = grid[i][j-1];
					continue;
				}
				
				if(j == 0){
					grid[i][j] = grid[i-1][j];
					continue;
				}
				
				grid[i][j] = grid[i-1][j].add(grid[i][j-1]);
			}
		}
		System.out.println(grid[gridSize][gridSize]);
	}

}
