//leetcode problem 909
import java.util.*;
public class snakeladder {

    static class Player{
        int i;
        int steps;
        String path;

        Player(int i, int steps, String path){
            this.i = i;
            this.steps = steps;
            this.path = path;
        }

    }

    static int snl(int[] lb){
        LinkedList<Player> q = new LinkedList<>();
        int ans = -1;
        boolean[] visited = new boolean[lb.length];
        q.push(new Player(0, 0, ""));
        while(q.size() > 0){
            Player rem = q.removeFirst();

            if(visited[rem.i])
                continue;
            visited[rem.i] = true;

            if(rem.i == lb.length - 1 && lb[lb.length - 1] == -1){
                ans = rem.steps;
                System.out.println(rem.path);
                break;
            }

            for(int i = 1; i <= 6; i++){
                if(rem.i + i < lb.length && lb[rem.i + i] == -1 && visited[rem.i + i] == false)
                    q.addLast(new Player(rem.i + i, rem.steps + 1, rem.path + i + " "));

                if(rem.i + i < lb.length && lb[rem.i + i] != -1 && visited[lb[rem.i + i]] == false)
                    q.addLast(new Player(lb[rem.i + i], rem.steps + 1, rem.path + i + " "));
            }


        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] board = {
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1}};

        // int[][] board = {{1, 1, -1},
        //                  {1, 1, 1},
        //                  {-1, 1, 1}};
        int k = 0;
        int[] lb = new int[board.length * board.length];
        Arrays.fill(lb, -1);
        int i = board.length - 1, j = 0;
        boolean flag = true;
        while(i >= 0){

            while(flag && j < board[0].length){
                if(board[i][j] != -1)
                    lb[k] = board[i][j] - 1;
                k++;
                j++;
            }


            while(!flag && j >= 0){
                if(board[i][j] != -1)
                    lb[k] = board[i][j] - 1;
                k++;
                j--;
            }
            j = flag ? j - 1 : j + 1;
            i--;
            flag = !flag;
        }

        // for(int h = 0; h < lb.length; h++){
        //     if(lb[h] != -1)
        //         System.out.println(h + " -> " + lb[h]);
        // }

        
        System.out.println(snl(lb));
    }
}