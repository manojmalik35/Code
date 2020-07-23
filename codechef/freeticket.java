import java.util.*;
public class freeticket {

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

    static void addEdge(int v1, int v2, int wt){
        Edge ne1 = new Edge(v2, wt);
        graph.get(v1).add(ne1);

        Edge ne2 = new Edge(v1, wt);
        graph.get(v2).add(ne2);
    }
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();


    static void display(){
        for(int v = 0; v < graph.size(); v++){
            System.out.print(v + " -> ");
            for(int e = 0; e < graph.get(v).size(); e++){
                Edge ne = graph.get(v).get(e);
                ne.display();
            }
            System.out.println();
        }
    }

    static class DHelp implements Comparable<DHelp>{
        int v;
        int c;
        DHelp(int v, int c){
            this.v = v;
            this.c = c;
        }

        public int compareTo(DHelp other){
            return this.c - other.c;
        }

    }
    static int djikstra(int s){
        int max = Integer.MIN_VALUE;
        DHelp r = new DHelp(s, 0);
        PriorityQueue<DHelp> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.size()];
        pq.add(r);

        while(pq.size() > 0){
            DHelp rem = pq.remove();

            if(visited[rem.v])
                continue;
            visited[rem.v] = true;

            max = Integer.max(max, rem.c);

            for(int i = 0; i < graph.get(rem.v).size(); i++){
                Edge ne = graph.get(rem.v).get(i);
                pq.add(new DHelp(ne.nbr, rem.c + ne.wt));
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int v = 4, e = 5;
        // 1 2 10
        // 1 3 24
        // 2 3 2
        // 2 4 15
        // 3 4 7
        for(int i = 0; i <= v; i++)
            graph.add(new ArrayList<>());

        addEdge(1, 2, 10);
        addEdge(1, 3, 24);
        addEdge(2, 3, 2);
        addEdge(2, 4, 15);
        addEdge(3, 4, 7);
        // display();
        int ans = djikstra(1);
        System.out.println(ans);
    }
}