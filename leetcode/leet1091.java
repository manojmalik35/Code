import java.util.ArrayDeque;
public class leet1091 {
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;
        
        int[][] dir= {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.addLast(0);
        
        grid[0][0] = 1;
        int dist = 1;
        while(que.size() > 0){
            
            int size = que.size();
            while(size-- > 0){
                int point = que.removeFirst();
                if(point == n * n - 1)
                    return dist;
                int r = point / n;
                int c = point % n;
                
                for(int d = 0; d < 8; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    
                    if(x >= 0 && y >= 0 && x < n && y < n && grid[x][y] == 0){
                        grid[x][y] = 1;//visited mark
                        que.addLast(x * n + y);
                    }
                }
            }
            dist++;
        }
        
        return -1;
    }
}
