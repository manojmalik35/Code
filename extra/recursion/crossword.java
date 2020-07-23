import java.util.*;
public class crossword{

    static boolean canplacethewordH(char[][] grid, String word, int i, int j){
        if(j + word.length() > grid[0].length)
            return false;

        if(j - 1 >= 0 && grid[i][j - 1] != '+')
            return false;

        if(j + word.length() < grid[0].length && grid[i][j + word.length()] != '+')
            return false;

        for(int k = 0; k < word.length(); k++){
            if(grid[i][j + k] != '-' && grid[i][j + k] != word.charAt(k))
                return false;
        }

        return true;
    }

    static boolean canplacethewordV(char[][] grid, String word, int i, int j){
        if(i + word.length() > grid.length)
            return false;

        if(i - 1 >= 0 && grid[i - 1][j] != '+')
            return false;

        if(i + word.length() < grid.length && grid[i + word.length()][j] != '+')
            return false;

        for(int k = 0; k < word.length(); k++){
            if(grid[i + k][j] != '-' && grid[i + k][j] != word.charAt(k))
                return false;
        }

        return true;
    }

    static boolean[] placethewordH(char[][] grid, String word, int i, int j){
        boolean[] weplaced = new boolean[word.length()];
        for(int k = 0; k < word.length(); k++){
            if(grid[i][j + k] == '-'){
                grid[i][j + k] = word.charAt(k);
                weplaced[k] = true;
            }
        }

        return weplaced;
    }

    static boolean[] placethewordV(char[][] grid, String word, int i, int j){
        boolean[] weplaced = new boolean[word.length()];
        for(int k = 0; k < word.length(); k++){
            if(grid[i + k][j] == '-'){
                grid[i + k][j] = word.charAt(k);
                weplaced[k] = true;
            }
        }

        return weplaced;
    }

    static void unplacethewordH(char[][] grid, String word, int i, int j, boolean[] weplaced){
        for(int k = 0; k < word.length(); k++){
            if(weplaced[k])
                grid[i][j + k] = '-';
        }
    }

    static void unplacethewordV(char[][] grid, String word, int i, int j, boolean[] weplaced){
        for(int k = 0; k < word.length(); k++){
            if(weplaced[k])
                grid[i + k][j] = '-';
        }
    }

    static void crosswordSolve(char[][] grid, String[] words, int wpsf){
        if(wpsf == words.length){
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++)
                    System.out.print(grid[i][j] + " ");
                System.out.println();
            }
            return; 
        }

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                String word = words[wpsf];
                if(grid[i][j] != '+'){
                    if(canplacethewordH(grid, word, i, j)){
                        boolean[] weplaced = placethewordH(grid, word, i, j);
                        crosswordSolve(grid, words, wpsf + 1);
                        unplacethewordH(grid, word, i, j, weplaced);
                    }

                    if(canplacethewordV(grid, word, i, j)){
                        boolean[] weplaced = placethewordV(grid, word, i, j);
                        crosswordSolve(grid, words, wpsf + 1);
                        unplacethewordV(grid, word, i, j, weplaced);
                    }
                }
            }
        }
    }

    static void crosswordSolve2(char[][] grid, String[] words, int bno, boolean[] wused){
        if(bno == grid.length * grid[0].length){
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++)
                    System.out.print(grid[i][j] + " ");
                System.out.println();
            }
            return;
        }

        int cc = 0;
        int i = bno / grid.length;
        int j = bno % grid[0].length;
        if(grid[i][j] != '+'){
            for(int w = 0; w < words.length; w++){
                String word = words[w];
                if(wused[w] == false && canplacethewordH(grid, word, i, j)){
                    boolean[] weplaced = placethewordH(grid, word, i, j);
                    wused[w] = true;
                    cc++;
                    crosswordSolve2(grid, words, bno + 1, wused);
                    wused[w] = false;
                    unplacethewordH(grid, word, i, j, weplaced);
                }

                if(wused[w] == false && canplacethewordV(grid, word, i, j)){
                    boolean[] weplaced = placethewordV(grid, word, i, j);
                    wused[w] = true;
                    cc++;
                    crosswordSolve2(grid, words, bno + 1, wused);
                    wused[w] = false;
                    unplacethewordV(grid, word, i, j, weplaced);
                }
            }

            if(cc == 0 && grid[i][j] != '-')
                crosswordSolve2(grid, words, bno + 1, wused);
        }else
            crosswordSolve2(grid, words, bno + 1, wused);
    }
    public static void main(String[] args) {
        // String[] grids = {"+-++++++++",
        //                   "+-++++++++",
        //                   "+-------++",
        //                   "+-++++++++",
        //                   "+-++++++++",
        //                   "+------+++",
        //                   "+-+++-++++",
        //                   "+++++-++++",
        //                   "+++++-++++",
        //                   "++++++++++"};
        // String word = "AGRA;NORWAY;ENGLAND;GWALIOR";
        Scanner sc = new Scanner(System.in);
        String[] grids = new String[10];
        for(int i = 0; i < grids.length; i++)
            grids[i] = sc.next();
        String word = sc.next();
        String[] words = word.split(";");
        char[][] grid = new char[grids.length][grids[0].length()];
        for(int i = 0; i < grids.length; i++){
            for(int j = 0; j < grids[0].length(); j++)
                grid[i][j] = grids[i].charAt(j);
        }

        boolean[] wused = new boolean[words.length];
        crosswordSolve2(grid, words, 0, wused);
    }
}