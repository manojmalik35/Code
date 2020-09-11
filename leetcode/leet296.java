import java.util.ArrayList;
public class leet296 {
    public int minTotalDistance(int[][] grid) {//lintcode 912 && leetcode 296
        int r = grid.length;
        int c = grid[0].length;
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(grid[i][j] == 1)
                    rows.add(i);
            }
        }

        int rowAns = rows.get((rows.size() >> 1));
        
        for(int j = 0; j < c; j++){
            for(int i = 0; i < r; i++){
                if(grid[i][j] == 1)
                cols.add(j);
            }
        }

        int colAns = cols.get((cols.size() >> 1));

        int totalDist = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(grid[i][j] == 1)
                    totalDist += Math.abs(rowAns - i) + Math.abs(colAns - j);
            }
        }

        return totalDist;
    }
}
