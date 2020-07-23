import java.util.*;
public class magnets{

    static int[] top;
    static int[] left;
    static int[] bottom;
    static int[] right;

    static boolean canplacethemagnethor(char[][] board,int i,int j,String pat){
        if(j-1 >= 0 && board[i][j-1] == pat.charAt(0))
            return false;
        if(i-1 >= 0 && board[i-1][j] == pat.charAt(0))
            return false;
        if(i-1 >= 0 && j+1 < board[0].length && board[i-1][j+1] == pat.charAt(1))
            return false;
        if(j+2 < board[0].length && board[i][j+2] == pat.charAt(1))
            return false;
        
        return true;
    }
    
    static boolean canplacethemagnetver(char[][] board,int i,int j,String pat){
        if(j-1 >= 0 && board[i][j-1] == pat.charAt(0))
            return false;
        if(i-1 >= 0 && board[i-1][j] == pat.charAt(0))
            return false;
        if(j+1 < board[0].length && board[i][j+1] == pat.charAt(0))
            return false;
        
        return true;
    }

    static void solve(char[][] board,int bno){

        if(bno == board.length * board[0].length){

            //for each row
            for(int i=0;i<board.length;i++){
                int pcount = 0;
                int ncount = 0;
                for(int j=0;j<board[0].length;j++){
                    if(board[i][j] == '+')
                        pcount++;
                    if(board[i][j] == '-')
                        ncount++;
                }

                if(left[i] != 0 && left[i] != pcount)
                    return;
                if(right[i] != 0 && right[i] != ncount)
                    return;
            }

            //for each column
            for(int j=0;j<board[0].length;j++){
                int pcount = 0;
                int ncount = 0;
                for(int i=0;i<board.length;i++){
                    if(board[i][j] == '+')
                        pcount++;
                    if(board[i][j] == '-')
                        ncount++;
                }

                if(top[j] != 0 && top[j] != pcount)
                    return;
                if(bottom[j] != 0 && bottom[j] != ncount)
                    return;
            }

            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[0].length;j++)
                    System.out.print(board[i][j] + " ");
                System.out.println();
            }

            return;
        }

        int n=Math.max(board.length,board[0].length);
        int i = bno / n;
        int j = bno % n;
        if(board[i][j] == 'h'){

            //op1 = +-
            if(canplacethemagnethor(board,i,j,"+-")){
                board[i][j] = '+';
                board[i][j+1] = '-';
                solve(board, bno + 1);
                board[i][j+1] = 'h';
                board[i][j] = 'h';
            }

            //op2 = -+
            if(canplacethemagnethor(board,i,j,"-+")){
                board[i][j] = '-';
                board[i][j+1] = '+';
                solve(board, bno + 1);
                board[i][j+1] = 'h';
                board[i][j] = 'h';
            }

            //op3 = xx
            board[i][j] = 'x';
            board[i][j+1] = 'x';
            solve(board, bno + 1);
            board[i][j+1] = 'h';
            board[i][j] = 'h';

        }else if(board[i][j] == 'v'){

            //op1 = +-
            if(canplacethemagnetver(board,i,j,"+-")){
                board[i][j] = '+';
                board[i+1][j] = '-';
                solve(board, bno + 1);
                board[i+1][j] = 'v';
                board[i][j] = 'v';
            }

            //op2 = -+
            if(canplacethemagnetver(board,i,j,"-+")){
                board[i][j] = '-';
                board[i+1][j] = '+';
                solve(board, bno + 1);
                board[i+1][j] = 'v';
                board[i][j] = 'v';
            }

            //op3 = xx
            board[i][j] = 'x';
            board[i+1][j] = 'x';
            solve(board, bno + 1);
            board[i+1][j] = 'v';
            board[i][j] = 'v';
        }else
            solve(board, bno + 1);
    }
    public static void main(String[] args) {
        
        char[][] board = {
            "hhhhvv".toCharArray(),
            "hhhhvv".toCharArray(),
            "vvvvhh".toCharArray(),
            "vvvvvv".toCharArray(),
            "hhhhvv".toCharArray()
        };

        top = new int[] {1,0,0,2,1,0};
        left = new int[] {2,3,0,0,0};
        bottom = new int[] {2,0,0,2,0,3};
        right = new int[] {0,0,0,1,0};

        solve(board,0);
    }
}