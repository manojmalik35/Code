public class leet980 {
    static int dfs(int[][] grid, int sr, int sc, int[][] dir, int count){
        if(grid[sr][sc] == 2)
            return count == 0 ? 1 : 0;

        if(grid[sr][sc] == 0)
            count--;

        grid[sr][sc] = -1;
        int ans = 0;
        for(int d = 0; d < 4; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if(r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] != -1)
                ans += dfs(grid, r, c, dir, count);
        }

        grid[sr][sc] = 0;
        return ans;
    }

    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        int sr = 0, sc = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0)
                    count++;
                else if(grid[i][j] == 1){
                    sr = i; 
                    sc = j;
                }
            }
        }

        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        return dfs(grid, sr, sc, dir, count);
    }
}