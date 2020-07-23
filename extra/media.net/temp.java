import java.util.*;
import java.io.*;


public class temp {

	static int sizeOfGreatestSortedSubMatrix(int n, int[][] mat) {
		int[][] strg = new int[n][n];
		for(int i = strg.length - 1; i >= 0; i--){
			for(int j = strg[0].length - 1; j >= 0; j--){
				if(i == strg.length - 1 && j == strg[0].length - 1)
					strg[i][j] = 1;
				else if(i == strg.length - 1){
					if(mat[i][j] > mat[i][j+ 1])
						strg[i][j] = 1;
					else
						strg[i][j] = strg[i][j + 1] + 1;
				}else if(j == strg[0].length - 1){
					if(mat[i][j] > mat[i + 1][j])
						strg[i][j] = 1;
					else
						strg[i][j] = strg[i + 1][j] + 1;
				}else{
					if(mat[i][j] > mat[i][j+ 1] && mat[i][j] > mat[i + 1][j])
						strg[i][j] = 1;
					else if(mat[i][j] > mat[i][j+ 1])
						strg[i][j] = strg[i + 1][j] + 1;
					else if(mat[i][j] > mat[i + 1][j])
						strg[i][j] = strg[i][j + 1] + 1;
					else
						strg[i][j] = strg[i][j + 1] + strg[i + 1][j] - (strg[i + 1][j + 1] - 1);
					}
				}
		}
		int mymax = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++)
				mymax = Math.max(mymax, strg[i][j]);
		}
		return mymax;	
	}

	public static void main(String args[]) throws IOException{
		int n = 5;
		int[][] arr = {{2, 5, 3, 8, 3},
						{1, 4, 6, 8, 4},
						{3, 6, 7, 9, 5},
						{1, 3, 6, 4, 2},
						{2, 6, 4, 3, 1}};
		
		System.out.println(sizeOfGreatestSortedSubMatrix(n, arr));
	}
}
