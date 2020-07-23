import java.util.*;
public class magnets{

    static int[] up;
    static int[] left;
    static int[] down;
    static int[] right;


    static boolean canPlaceH(char[][] grid, String mag, int i, int j){
        if(j - 1 >= 0 && grid[i][j - 1] == mag.charAt(0))
            return false;

        if(i - 1 >= 0 && grid[i - 1][j] == mag.charAt(0))
            return false;

        if(i - 1 >= 0 && j + 1 < grid[0].length && grid[i - 1][j + 1] == mag.charAt(1))
            return false;

        if(j + 2 < grid[0].length && grid[i][j + 2] == mag.charAt(1))
            return false;

        return true;
    }

    static boolean canPlaceV(char[][] grid, String mag, int i, int j){
        if(j - 1 >= 0 && grid[i][j - 1] == mag.charAt(0))
            return false;

        if(i - 1 >= 0 && grid[i - 1][j] == mag.charAt(0))
            return false;

        if(j + 1 < grid[0].length && grid[i][j + 1] == mag.charAt(1))
            return false;

        return true;
    }

    static void magnetssolve(char[][] grid, int bno){
        if(bno == grid.length * grid[0].length){

            //for each row
            for(int i = 0; i < grid.length; i++){
                int po = 0;
                int ne = 0;
                int reqp = left[i];
                int reqn = right[i];
                for(int j = 0; j < grid[0].length; j++){
                    if(grid[i][j] == '+')
                        po++;
                    else if(grid[i][j] == '-')
                        ne++;
                }
                if(reqp != -1 && reqp != po)
                    return;
                if(reqn != -1 && reqn != ne)
                    return;
            }

            //for each column
            for(int j = 0; j < grid[0].length; j++){
                int po = 0;
                int ne = 0;
                int reqp = up[j];
                int reqn = down[j];
                for(int i = 0; i < grid.length; i++){
                    if(grid[i][j] == '+')
                        po++;
                    else if(grid[i][j] == '-')
                        ne++;
                }
                if(reqp != -1 && reqp != po)
                    return;
                if(reqn != -1 && reqn != ne)
                    return;
            }

            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++)
                    System.out.print(grid[i][j] + " ");
                System.out.println();
            }

            return;
        }

        int i = bno / grid[0].length;
        int j = bno % grid[0].length;
        if(grid[i][j] == 'L'){
            //for +-
            if(canPlaceH(grid, "+-", i, j)){
                grid[i][j] = '+';
                grid[i][j + 1] = '-';
                magnetssolve(grid, bno + 1);
                grid[i][j] = 'L';
                grid[i][j + 1] = 'R';
            }

            //for -+
            if(canPlaceH(grid, "-+", i, j)){
                grid[i][j] = '-';
                grid[i][j + 1] = '+';
                magnetssolve(grid, bno + 1);
                grid[i][j] = 'L';
                grid[i][j + 1] = 'R';
            }

            //for xx
            grid[i][j] = 'X';
            grid[i][j + 1] = 'X';
            magnetssolve(grid, bno + 1);
            grid[i][j] = 'L';
            grid[i][j + 1] = 'R';

        }else if(grid[i][j] == 'T'){
            //for +-
            if(canPlaceV(grid, "+-", i, j)){
                grid[i][j] = '+';
                grid[i + 1][j] = '-';
                magnetssolve(grid, bno + 1);
                grid[i][j] = 'T';
                grid[i + 1][j] = 'D';
            }

            //for -+
            if(canPlaceV(grid, "-+", i, j)){
                grid[i][j] = '-';
                grid[i + 1][j] = '+';
                magnetssolve(grid, bno + 1);
                grid[i][j] = 'T';
                grid[i + 1][j] = 'D';
            }

            //for xx
            grid[i][j] = 'X';
            grid[i + 1][j] = 'X';
            magnetssolve(grid, bno + 1);
            grid[i][j] = 'T';
            grid[i + 1][j] = 'D';

        }else
            magnetssolve(grid, bno + 1);
        
    }

    public static void main(String[] args) {
        char[][] grid = {"LRLRTT".toCharArray(),
                         "LRLRDD".toCharArray(),
                         "TTTTLR".toCharArray(),
                         "DDDDTT".toCharArray(),
                         "LRLRDD".toCharArray()};

        // char[][] grid = {"TTT".toCharArray(),
        //                  "BBB".toCharArray(),
        //                  "TLR".toCharArray(),
        //                  "BLR".toCharArray()};
        
        // up = new int[]{2, -1, -1};
        // left = new int[]{-1, -1, 2, -1};
        // down = new int[]{-1, -1, 2};
        // right = new int[]{0, -1, -1, -1};
        
        up = new int[]{1, -1, -1, 2, 1, -1};
        left = new int[]{2, 3, -1, -1, -1};
        down = new int[]{2, -1, -1, 2, -1, 3};
        right = new int[]{-1, -1, -1, 1, -1};
        
        magnetssolve(grid, 0);

    }
}