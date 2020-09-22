public class leet1020 {
    
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    void dfs(int[][] A, int i, int j){

        A[i][j] = 0;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r >= 0 && c >= 0 && r < A.length && c < A[0].length && A[r][c] == 1)
                dfs(A, r, c);
        }
    }

    public int numEnclaves(int[][] A) {
        int n = A.length;
        int m = A[0].length;

        for(int j = 0; j < m; j++){
            if(A[0][j] == 1)
                dfs(A, 0, j);
            if(A[n - 1][j] == 1)
                dfs(A, n - 1, j);
        }

        for(int i = 0; i < n; i++){
            if(A[i][0] == 1)
                dfs(A, i, 0);
            if(A[i][m - 1] == 1)
                dfs(A, i, m - 1);
        }

        int count = 0;
        for(int[] ar : A){
            for(int ele : ar){
                if(ele == 1) 
                    count++;
            }
        }

        return count;
    }
}
