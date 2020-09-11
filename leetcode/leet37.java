import java.util.*;
public class leet37 {
    static int[] row;
    static int[] col;
    static int[][] sub;
       
    static boolean dfs(char[][] board, int idx, ArrayList<Integer> locOfZeroes){
        if(idx == locOfZeroes.size())
            return true;

        int loc = locOfZeroes.get(idx);
        int r = loc / 9;
        int c = loc % 9;
        for(int i = 1; i <= 9; i++){
            char ch = (char)(i + '0');
            int mask = (1 << i);
            if((row[c] & mask) == 0 && (col[r] & mask) == 0 && (sub[r / 3][c / 3] & mask) == 0){
                
                row[c] ^= mask;
                col[r] ^= mask;
                sub[r / 3][c / 3] ^= mask;
                
                board[r][c] = ch;
                if(dfs(board, idx + 1, locOfZeroes)) return true;
                board[r][c] = '.';
                
                row[c] ^= mask;
                col[r] ^= mask;
                sub[r / 3][c / 3] ^= mask;
            }
        }
        return false;
    }
    
    public void solveSudoku(char[][] board) {
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
        
        dfs(board, 0, locOfZeroes);
    }
}