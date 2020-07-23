import java.util.*;
public class waterjug{

    static class State{
        int j1;
        int j2;
        int level;
        String psf;

        State(){}
        State(int j1, int j2, int level, String psf){
            this.j1 = j1;
            this.j2 = j2;
            this.level = level;
            this.psf = psf;
        }
    }

    static void solve(int j1, int j2, int wr){
        LinkedList<State> q = new LinkedList<>();
        q.addLast(new State(0, 0, 0, ""));
        boolean[][] visited = new boolean[j1 + 1][j2 + 1];
        while(q.size() > 0){
            State rem = q.removeFirst();

            if(visited[rem.j1][rem.j2])
                continue;
            visited[rem.j1][rem.j2] = true;

            if(rem.j1 == wr || rem.j2 == wr){
                System.out.println(rem.psf + " @ " + rem.level);
                break;
            }

            if(rem.j1 < j1 && visited[j1][rem.j2] == false)//fill 3
                q.addLast(new State(j1, rem.j2, rem.level + 1, rem.psf + "f" + j1 + " "));

            if(rem.j2 < j2 && visited[rem.j1][j2] == false)//fill 5
                q.addLast(new State(rem.j1, j2, rem.level + 1, rem.psf + "f" + j2 + " "));

            if(rem.j1 > 0 && visited[0][rem.j2] == false)//empty 3
                q.addLast(new State(0, rem.j2, rem.level + 1, rem.psf + "e" + j1 + " "));

            if(rem.j2 > 0 && visited[rem.j1][0] == false)//empty 5
                q.addLast(new State(rem.j1, 0, rem.level + 1, rem.psf + "e" + j2 + " "));

            //move 3 to 5
            if(rem.j1 > 0 && rem.j2 < j2){
                State ns = new State();
                int space = j2 - rem.j2;
                if(rem.j1 > space){
                    ns.j1 = rem.j1 - space;
                    ns.j2 = j2;
                }else{
                    ns.j1 = 0;
                    ns.j2 = rem.j2 + rem.j1;
                }
                ns.level = rem.level + 1;
                ns.psf = rem.psf + "m" + j1 + j2 + " ";
                if(visited[ns.j1][ns.j2] == false)
                    q.addLast(ns);
            }

            //move 5 to 3
            if(rem.j2 > 0 && rem.j1 < j1){
                State ns = new State();
                int space = j1 - rem.j1;
                if(rem.j2 > space){
                    ns.j1 = j1;
                    ns.j2 = rem.j2 - space;
                }else{
                    ns.j1 = rem.j1 + rem.j2;
                    ns.j2 = 0;
                }
                ns.level = rem.level + 1;
                ns.psf = rem.psf + "m" + j2 + j1 + " ";
                if(visited[ns.j1][ns.j2] == false)
                    q.addLast(ns);
            }

        }
    }

    public static void main(String[] args) {
        int j1 = 3;
        int j2 = 5;
        int wr = 4;
        solve(j1, j2, wr);

    }
}