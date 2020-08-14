public class leet63 {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1)
            return 0;

        //Using the grid as dp
        grid[0][0] = 1;
        for (int i = 1; i < m; i++)// 1st col
            grid[i][0] = grid[i][0] == 0 && grid[i - 1][0] == 1 ? 1 : 0;

        for (int j = 1; j < n; j++)// 1st row
            grid[0][j] = grid[0][j] == 0 && grid[0][j - 1] == 1 ? 1 : 0;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0)
                    grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
                else
                    grid[i][j] = 0;
            }
        }

        return grid[m - 1][n - 1];
    }
}