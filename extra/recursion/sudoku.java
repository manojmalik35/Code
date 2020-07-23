import java.util.*;
public class sudoku{

    static boolean isthechoiceValid(int[][] grid, int i, int j, int o){
        for(int jj = 0; jj < grid.length; jj++){
            if(grid[i][jj] == o)
                return false;
        }

        for(int ii = 0; ii < grid.length; ii++){
            if(grid[ii][j] == o)
                return false;
        }

        int r = i / 3 * 3;
        int c = j / 3 * 3;
        for(int ii = 0; ii < 3; ii++){
            for(int jj = 0; jj < 3; jj++){
                if(grid[r + ii][c + jj] == o)
                    return false;
            }
        }

        return true;
    }

    static boolean isthechoiceValidO1(int[] ra, int[] ca, int[][] mat, int x, int y, int o){
        
        int rv = ra[x];
        int cv = ca[y];
        int mv = mat[x / 3][y / 3];

        if((rv & (1 << o)) != 0 || (cv & (1 << o)) != 0 || (mv & (1 << o)) != 0)
            return false;

        return true;
    }

    static int sudokusolve(int[][] grid, int bno){
        if(bno == grid.length * grid.length){
            System.out.println("=======================");
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid.length; j++)
                    System.out.print(grid[i][j] + " ");
                System.out.println();
            }
            System.out.println("=======================");
            return 1;
        }

        int i = bno / grid.length;
        int j = bno % grid.length;
        int count = 0;
        if(grid[i][j] == 0){
            for(int o = 1; o <= 9; o++){
                if(isthechoiceValid(grid, i, j, o)){
                    grid[i][j] = o;
                    count += sudokusolve(grid, bno + 1);
                    grid[i][j] = 0;
                }
            }
        }else
            count += sudokusolve(grid, bno + 1);

        return count;
    }

    static int sudokusolveBetter(int[][] grid, ArrayList<Integer> calls, int idx, int[] ra, int[] ca, int[][] mat){
        if(idx == calls.size()){
            System.out.println("=======================");
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid.length; j++)
                    System.out.print(grid[i][j] + " ");
                System.out.println();
            }
            System.out.println("=======================");
            return 1;
        }

        int i = calls.get(idx) / grid[0].length;
        int j = calls.get(idx) % grid[0].length;
        int count = 0;
        for(int o = 1; o <= 9; o++){
            if(isthechoiceValidO1(ra, ca, mat, i, j, o)){

                grid[i][j] = o;
                ra[i] ^= (1 << o);
                ca[j] ^= (1 << o);
                mat[i / 3][j / 3] ^= (1 << o);

                count += sudokusolveBetter(grid, calls, idx + 1, ra, ca, mat);

                grid[i][j] = 0;
                ra[i] ^= (1 << o);
                ca[j] ^= (1 << o);
                mat[i / 3][j / 3] ^= (1 << o);
            }
        }
    
        return count;
    }

    static boolean sudokusolveBetter2(int[][] grid, ArrayList<Integer> calls, int idx, int[] ra, int[] ca, int[][] mat){
        if(idx == calls.size()){
            System.out.println("=======================");
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid.length; j++)
                    System.out.print(grid[i][j] + " ");
                System.out.println();
            }
            System.out.println("=======================");
            return true;
        }

        int i = calls.get(idx) / grid[0].length;
        int j = calls.get(idx) % grid[0].length;
        boolean res = false;
        for(int o = 1; o <= 9 & !res; o++){
            if(isthechoiceValidO1(ra, ca, mat, i, j, o)){
                grid[i][j] = o;
                ra[i] ^= (1 << o);
                ca[j] ^= (1 << o);
                mat[i / 3][j / 3] ^= (1 << o);

                res = res || sudokusolveBetter2(grid, calls, idx + 1,ra, ca, mat);
                if(res) return res;

                grid[i][j] = 0;
                ra[i] ^= (1 << o);
                ca[j] ^= (1 << o);
                mat[i / 3][j / 3] ^= (1 << o);
            }
        }
    
        return res;
    }

    static void solve(int[][] grid){

        ArrayList<Integer> calls = new ArrayList<>();
        int[] ra = new int[9];
        int[] ca = new int[9];
        int[][] mat = new int[3][3];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0)
                    calls.add(i * 9 + j);
                else{
                    ra[i] ^= (1 << grid[i][j]);
                    ca[j] ^= (1 << grid[i][j]);
                    mat[i / 3][j / 3] ^= (1 << grid[i][j]);
                }
            }
        }
        sudokusolveBetter2(grid, calls, 0, ra, ca, mat);
    }

    public static void main(String[] args) {
        // int[][] grid = {{3, 0, 6, 5, 0, 8, 4, 0, 0},  
        //                 {5, 2, 0, 0, 0, 0, 0, 0, 0},  
        //                 {0, 8, 7, 0, 0, 0, 0, 3, 1},  
        //                 {0, 0, 3, 0, 1, 0, 0, 8, 0},  
        //                 {9, 0, 0, 8, 6, 3, 0, 0, 5},  
        //                 {0, 5, 0, 0, 9, 0, 6, 0, 0},  
        //                 {1, 3, 0, 0, 0, 0, 2, 5, 0},  
        //                 {0, 0, 0, 0, 0, 0, 0, 7, 4},  
        //                 {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        // sudokusolve(grid, 1, 0, -1);
    
        int[][] grid = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 8, 0, 0, 0, 0, 4, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 6, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {2, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 2, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        
        solve(grid);
        // Scanner sc = new Scanner(System.in);
        // int t = sc.nextInt();
        // int[][] grid = new int[9][9];
        // while(t-- > 0){
        //     for(int i = 0; i < grid.length; i++){
        //         for(int j = 0; j < grid.length; j++)
        //             grid[i][j] = sc.nextInt();
        //     }
            // sudokusolve(grid, 0);
  
        // if (solveSudoku(grid, 9)) 
        // { 
        //     print(grid, 9); // print solution 
        // }  
        // else
        // { 
        //     System.out.println("No solution"); 
        // }
        // }
        // sc.close(); 
    }
    
    static void sudokusolve2(int[][] grid, int n, int lnwphr, int lnwphc){
        if(n > 9){
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid.length; j++){
                    if(grid[i][j] == 0)
                        return;
                }
            }
            System.out.println("=======================");
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid.length; j++)
                    System.out.print(grid[i][j] + " ");
                System.out.println();
            }
            System.out.println("=======================");
            return;
        }

        for(int i = lnwphr; i < grid.length; i++){
            for(int j = i == lnwphr ? lnwphc + 1 : 0; j < grid.length; j++){
                if(grid[i][j] == 0 && isthechoiceValid(grid, i, j, n)){
                    grid[i][j] = n;
                    sudokusolve2(grid, n + 1, i, j);
                    grid[i][j] = 0;
                }
            }
        }
    }
  
}