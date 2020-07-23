import java.util.*;

public class djikstra {
    
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
        String path;

        DHelp(int v, int c, String path){
            this.v = v;
            this.c = c;
            this.path = path;
        }

        public int compareTo(DHelp other){
            return this.c - other.c;
        }
    }


    static void solve(int src){
        PriorityQueue<DHelp> q = new PriorityQueue<>();
        q.add(new DHelp(src, 0, src + ""));
        boolean[] visited = new boolean[graph.size()];
        while(q.size() > 0){
            DHelp rem = q.remove();

            if(visited[rem.v])
                continue;
            visited[rem.v] = true;

            System.out.println(rem.path + " @ " + rem.c);

            for(int c = 0; c < graph.get(rem.v).size(); c++){
                Edge ne = graph.get(rem.v).get(c);
                if(visited[ne.nbr] == false)
                    q.add(new DHelp(ne.nbr, rem.c + ne.wt, rem.path + ne.nbr));
            }
        }
    }

    public static void main(String[] args) {
        int v = 7;
        for(int i = 0; i < v; i++)
            graph.add(new ArrayList<>());

        addEdge(0, 1, 20);
        addEdge(1, 2, 10);
        addEdge(2, 3, 20);
        addEdge(0, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 3);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);

        solve(0);
    }
}