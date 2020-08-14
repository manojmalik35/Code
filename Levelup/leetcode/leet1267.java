public class leet1267 {

    static int[][] dir = { { 0, 1 }, { 1, 0 } };

    static int dfs(int[][] grid, int i, int j) {

        if (grid[i][j] == 2)
            return -1;
        grid[i][j] = 2;
        int count = 1;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < Math.max(grid.length, grid[0].length); rad++) {
                int x = i + rad * dir[d][0];
                int y = j + rad * dir[d][1];

                if (x < grid.length && y < grid[0].length && grid[x][y] != 0) {
                    int rres = dfs(grid, x, y);
                    count += rres;
                }
            }
        }

        return count;
    }

    static int countServers(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int res = dfs(grid, i, j);
                    ans += res;
                }
            }
        }
        System.out.println("=======================");
        return ans;
    }

    static int countServers2(int[][] grid) {

        int[] row = new int[grid.length];
        int[] col = new int[grid[0].length];
        int totalServer = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                row[i] += grid[i][j];
                col[j] += grid[i][j];
                totalServer += grid[i][j];
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] * row[i] * col[j] == 1)
                    totalServer--;
            }
        }

        return totalServer;
    }

    public static void main(String[] args) {
        int[][] grid = { { 0, 0, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 1, 1, 1, 0 }, { 1, 0, 0, 1, 1 },
                { 0, 0, 1, 1, 0 } };
        System.out.println(countServers(grid));
        // System.out.println(countServers2(grid));
        
    }

}