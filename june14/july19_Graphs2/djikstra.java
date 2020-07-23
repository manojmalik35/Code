import java.util.*;

public class djikstra{

    static class Edge{
        int nbr;
        int wt;

        Edge(int nbr, int wt){
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    static void addEdge(int v1, int v2, int wt){
        Edge e1 = new Edge(v2, wt);
        graph.get(v1).add(e1);

        Edge e2 = new Edge(v1, wt);
        graph.get(v2).add(e2);
    }

    static class DHelp implements Comparable<DHelp>{
        int v;
        int c;
        String p;

        DHelp(int v, int c, String p){
            this.v = v;
            this.c = c;
            this.p = p;
        }

        public int compareTo(DHelp other){
            return this.c - other.c;
        }
    }
    static void dj(int s){
        PriorityQueue<DHelp> pq = new PriorityQueue<>();
        pq.add(new DHelp(s, 0, s + ""));
        boolean[] visited = new boolean[graph.size()];

        while(pq.size() > 0){
            DHelp rem = pq.remove();

            if(visited[rem.v])
                continue;
            visited[rem.v] = true;

            System.out.println(rem.v + " @ " + rem.c + " via " + rem.p);

            for(int n = 0; n < graph.get(rem.v).size(); n++){
                Edge ne = graph.get(rem.v).get(n);
                if(visited[ne.nbr] == false)
                    pq.add(new DHelp(ne.nbr, rem.c + ne.wt, rem.p + ne.nbr));
            }
        }
    }
    public static void main(String[] args) {
        int n = 7;
        for(int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        addEdge(0,1,10);
        addEdge(0,3,40);
        addEdge(1,2,10);
        addEdge(2,3,10);
        addEdge(3,4,2);
        addEdge(4,5,3);
        addEdge(5,6,3);
        addEdge(4,6,8);
        // addEdge(2,5,5);
        
        dj(0);
    }
}