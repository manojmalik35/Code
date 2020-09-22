import java.util.ArrayDeque;
public class leet994 {
    
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        if(n == 0) return 0;
        int m = grid[0].length;
        if(m == 0) return 0;
        ArrayDeque<Integer> que = new ArrayDeque<>();
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1)
                    count++;
                else if(grid[i][j] == 2)
                    que.addLast(i * m + j);
            }
        }
        
        if(count == 0) return 0;
        if(que.size() == 0) return -1;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int time = 1;
        while(que.size() > 0){
            
            int size = que.size();
            while(size-- > 0){
                
                int rot = que.removeFirst();
                int r = rot / m;
                int c = rot % m;
                for(int d = 0; d < 4; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    
                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1){
                        grid[x][y] = 2;
                        que.addLast(x * m + y);
                        count--;
                    }
                    
                    if(count == 0)
                        return time;
                }
                
            }
            time++;
        }
        
        return -1;
    }

    public int orangesRotting2(int[][] grid) {
        ArrayDeque<int[]> que = new ArrayDeque<>();
        int fresh = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 2)
                    que.addLast(new int[]{i, j, 0});
                else if(grid[i][j] == 1)
                    fresh++;
            }
        }
        
        if(fresh == 0)
            return 0;
        if(que.size() == 0) 
            return -1;
        // System.out.println(fresh);
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while(que.size() > 0){
            int[] cell = que.removeFirst();
            
            int i = cell[0], j = cell[1];
            if(grid[i][j] == 0)
                continue;
            
            if(grid[i][j] == 1)
                fresh--;
            
            grid[i][j] = 0;
            if(fresh == 0)
                return cell[2];
            
            for(int d = 0; d < 4; d++){
                int r = cell[0] + dir[d][0];
                int c = cell[1] + dir[d][1];
                
                if(r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1)
                    que.addLast(new int[]{r, c, cell[2] + 1});
            }
        }
        
        return -1;
    }
}
