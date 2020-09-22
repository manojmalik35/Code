import java.util.ArrayDeque;
public class leet542 {
    
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][] ans = new int[n][m];
        for(int[] ar : ans)
            Arrays.fill(ar, -1);
        
        ArrayDeque<Integer> que = new ArrayDeque<>();
        int countOnes = n * m;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 0){
                    que.addLast(i * m + j);
                    ans[i][j] = 0;
                    countOnes--;
                }
            }
        }
        
        int dist = 1;
        while(que.size() > 0){
            
            int size = que.size();
            while(size-- > 0){
                
                int point = que.removeFirst();
                int r = point / m;
                int c = point % m;
                
                for(int d = 0; d < 4; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    
                    if(x >= 0 && y >= 0 && x < n && y < m && ans[x][y] == -1){
                        ans[x][y] = dist;
                        countOnes--;
                        que.addLast(x * m + y);
                        if(countOnes == 0) return ans;
                    }
                }
            }
            dist++;
        }
        
        return ans;
    }
}
