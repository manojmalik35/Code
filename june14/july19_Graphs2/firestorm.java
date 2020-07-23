import java.util.*;

public class firestorm{

    static class Point{
        int x;
        int y;
        int t;
        Point(int x, int y, int t){
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
    
    static void solvefs(int[][] city, int time){
        ArrayDeque<Point> q = new ArrayDeque<>();
        for(int i =0; i < city.length; i++){
            for(int j = 0; j < city[0].length; j++){
                if(city[i][j] == 1)
                    q.addLast(new Point(i, j, 1));
            }
        }

        while(q.size() > 0){
            Point rem = q.getFirst();
            q.removeFirst();

            if(rem.t == time + 2)
                break;

            if(city[rem.x][rem.y] < 0)
                continue;
                
            city[rem.x][rem.y] = -rem.t;

            if(rem.x - 1 >= 0 && city[rem.x - 1][rem.y] == 0)
                q.addLast(new Point(rem.x - 1, rem.y, rem.t + 1));
            if(rem.y - 1 >= 0 && city[rem.x][rem.y - 1] == 0)
                q.addLast(new Point(rem.x, rem.y - 1, rem.t + 1));
            if(rem.x + 1 < city.length && city[rem.x + 1][rem.y] == 0)
                q.addLast(new Point(rem.x + 1, rem.y, rem.t + 1));
            if(rem.y + 1 < city[0].length && city[rem.x][rem.y + 1] == 0)
                q.addLast(new Point(rem.x, rem.y + 1, rem.t + 1));
        }
    }
    public static void main(String[] args) {
        int[][] city = {{1, 2, 0, 2, 0, 0, 0},
                        {0, 2, 0, 2, 0, 1, 0},
                        {0, 0, 0, 2, 0, 0, 0},
                        {2, 2, 2, 2, 2, 2, 0},
                        {0, 2, 1, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 0, 0}};

        solvefs(city, 3);
        for(int i =0; i < city.length; i++){
            for(int j = 0; j < city[0].length; j++)
                System.out.print(city[i][j] + " ");
            System.out.println();
        }
    }
}