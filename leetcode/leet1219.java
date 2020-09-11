public class leet1219 {
    
    static int dfs(int[][] grid, int i, int j, int[][] dir){
        
        int g = grid[i][j];
        grid[i][j] = 0;
        
        int ansgold = 0;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            
            if(r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] != 0)
                ansgold = Math.max(ansgold, dfs(grid, r, c, dir));
        }
        
        grid[i][j] = g;
        return ansgold + g;
    }
    
    public int getMaximumGold(int[][] grid) {
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int maxGold = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] != 0)
                    maxGold = Math.max(maxGold, dfs(grid, i, j, dir));
            }
        }
        
        return maxGold;
    }
}