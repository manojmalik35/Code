package Recursion;
import java.util.*;
public class rec {
    
    static boolean isValidToPlaceNo(int[][] board, int r, int c, int num){

        for(int i = 0; i < 9; i++){
            if(board[r][i] == num) return false;
            if(board[i][c] == num) return false;
        }

        r = (r / 3) * 3;
        c = (c / 3) * 3;
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 3; j++)
                if(board[r + i][c + j] == num) return false;
        }

        return true;
    }

    static int dfs(int[][] board, int idx){
        if(idx == 81)
            return 1;

        int r = idx / 9;
        int c = idx % 9;
        if(board[r][c] != 0) return dfs(board, idx + 1);

        int count = 0;
        for(int i = 1; i <= 9; i++){
            if(isValidToPlaceNo(board, r, c, i)){
                board[r][c] = i;
                count += dfs(board, idx + 1);
                board[r][c] = 0;
            }
        }

        return count;
    }

    static int dfs2(int[][] board, int idx, ArrayList<Integer> locOfZeroes){
        if(idx == locOfZeroes.size())
            return 1;

        int loc = locOfZeroes.get(idx);
        int r = loc / 9;
        int c = loc % 9;
        int count = 0;
        for(int i = 1; i <= 9; i++){
            if(isValidToPlaceNo(board, r, c, i)){
                board[r][c] = i;
                count += dfs2(board, idx + 1, locOfZeroes);
                board[r][c] = 0;
            }
        }

        return count;
    }

    public boolean isValidSudoku(int[][] board) {//leet 36
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] != 0 && !isValidToPlaceNo(board, i, j, board[i][j]))
                    return false;
            }
        }

        return true;
    }

    static int[] row;
    static int[] col;
    static int[][] sub;
    
    static boolean isValidToPlaceNo(int num, int i, int j){
        int mask = (1 << num);
        
        if((row[j] & mask) != 0) return false;
        if((col[i] & mask) != 0) return false;
        if((sub[i / 3][j / 3] & mask) != 0) return false;
        
        return true;
    }
    
    static void dfs3(char[][] board, int idx, ArrayList<Integer> locOfZeroes, char[][] ans){
        if(idx == locOfZeroes.size()){
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++)
                    ans[i][j] = board[i][j];
            }
            return;
        }

        int loc = locOfZeroes.get(idx);
        int r = loc / 9;
        int c = loc % 9;
        for(int i = 1; i <= 9; i++){
            char ch = (char)(i + '0');
            if(isValidToPlaceNo(i, r, c)){
                int mask = (1 << i);
                
                row[c] ^= mask;
                col[r] ^= mask;
                sub[r / 3][c / 3] ^= mask;
                
                board[r][c] = ch;
                dfs3(board, idx + 1, locOfZeroes, ans);
                board[r][c] = '.';
                
                row[c] ^= mask;
                col[r] ^= mask;
                sub[r / 3][c / 3] ^= mask;
            }
        }
    }
    
    public void solveSudoku(char[][] board) {//leet 37
        row = new int[9];
        col = new int[9];
        sub = new int[3][3];
        ArrayList<Integer> locOfZeroes = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.')
                    locOfZeroes.add(i * 9 + j);
                else{
                    int num = board[i][j] - '0';
                    int mask = (1 << num);
                    row[j] |= mask;
                    col[i] |= mask;
                    sub[i / 3][j / 3] |= mask;
                }
            }
        }
        
        char[][] ans = new char[9][9];
        dfs3(board, 0, locOfZeroes, ans);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++)
                board[i][j] = ans[i][j];
        }
    }
}