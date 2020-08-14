public class leet130 {
    
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static void dfs(char[][] board, int i, int j){
        if(board[i][j] == 'X')
            return;
        
        if(board[i][j] == 'O')
            board[i][j] = '#';
        for(int d = 0; d < dir.length; d++){
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            if(x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == 'O')
                dfs(board, x, y);
        }
           
    }
    
    public void solve(char[][] board) {
        int r = board.length;
        if(r == 0) return;
        int c = board[0].length;
        for(int i = 0; i < r; i++){
            if(board[i][0] == 'O')
                dfs(board, i, 0);
            if(board[i][c - 1] == 'O')
                dfs(board, i, c - 1);
        }
        
        for(int j = 0; j < c; j++){
            if(board[0][j] == 'O')
                dfs(board, 0, j);
            if(board[r - 1][j] == 'O')
                dfs(board, r - 1, j);
        }
        
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(board[i][j] == '#')
                    board[i][j] = 'O';
                else if(board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }

    public static void main(String[] args) {
        
    }
}