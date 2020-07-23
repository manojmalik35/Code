import java.util.*;
public class cheapestflight{

    static class Edge{
        int nbr;
        int wt;

        Edge(int nbr, int wt){
            this.nbr = nbr;
            this.wt = wt;
        }

        void display(){
            System.out.print("[" + this.nbr + "-" + this.wt + "]");
        }
    }

    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    static void addEdge(int v1, int v2, int wt){
        Edge e1 = new Edge(v2, wt);
        graph.get(v1).add(e1);

        Edge e2 = new Edge(v1, wt);
        graph.get(v2).add(e2);
    }

    static void display(){
        for(int n = 0; n < graph.size(); n++){
            System.out.println(n + " -> ");
            for(int e = 0; e < graph.get(n).size(); e++){
                Edge ne = graph.get(n).get(e);
                ne.display();
            }
            System.out.println();
        }
    }

    static int mp = Integer.MAX_VALUE;

    static void cheapestFlight(int src, int des, boolean[] visited, int price, int k){
        if(src == des){
            if(price < mp && k + 1 >= 0)
                mp = price;
            return;
        }

        visited[src] = true;
        for(int n = 0; n < graph.get(src).size(); n++){
            Edge ne = graph.get(src).get(n);
            if(visited[ne.nbr] == false)
                cheapestFlight(ne.nbr, des, visited, price + ne.wt, k - 1);
        }
        visited[src] = false;
    }

    static class CHelp implements Comparable<CHelp>{
        int n;
        int p;
        int k;
        CHelp(int n, int p, int k){
            this.n = n;
            this.p = p;
            this.k = k;
        }

        public int compareTo(CHelp other){
            return this.p - other.p;
        }
    }

    static int cheapestFlightbfs(int src, int des, int k){
        int ans = Integer.MAX_VALUE;
        PriorityQueue<CHelp> q = new PriorityQueue<>();
        q.add(new CHelp(src, 0, k));
        boolean[] visited = new boolean[graph.size()];
        while(q.size() > 0){
            CHelp rem = q.remove();

            if(visited[rem.n])
                continue;
            visited[rem.n] = true;
            if(rem.n == des){
                if(rem.k + 1 >= 0){
                    ans = rem.p;
                    break;
                }else
                    visited[rem.n] = false;
            }
            

            for(int i = 0; i < graph.get(rem.n).size(); i++){
                Edge ne = graph.get(rem.n).get(i);
                if(visited[ne.nbr] == false)
                    q.add(new CHelp(ne.nbr, rem.p + ne.wt, rem.k - 1));
            }
        }



        return ans;
    }
    public static void main(String[] args) {
        int v = 3;
        for(int i = 0; i < v; i++)
            graph.add(new ArrayList<>());

        addEdge(0, 1, 100);
        addEdge(1, 2, 100);
        addEdge(0, 2, 500);

        int k = 1;
        // boolean[] visited = new boolean[graph.size()];
        // cheapestFlight(0, 2, visited, 0, k);
        // System.out.println(mp);
        System.out.println(cheapestFlightbfs(0, 2, k));
    }
}