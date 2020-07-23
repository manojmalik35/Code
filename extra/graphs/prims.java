import java.util.*;
public class prims{

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

    static void addEdge(ArrayList<ArrayList<Edge>> g, int v1, int v2, int wt){
        Edge e1 = new Edge(v2, wt);
        g.get(v1).add(e1);
        
        Edge e2 = new Edge(v1, wt);
        g.get(v2).add(e2);
    }

    static void display(ArrayList<ArrayList<Edge>> g){
        for(int v = 0; v < g.size(); v++){
            System.out.print(v + " => ");
            for(int e = 0; e < g.get(v).size(); e++){
                Edge ne = g.get(v).get(e);
                ne.display();
            }
            System.out.println();
        }
    }

    static class PHelp implements Comparable<PHelp>{
        int v;
        int av;
        int c;

        PHelp(int v, int av, int c){
            this.v = v;
            this.av = av;
            this.c = c;
        }

        public int compareTo(PHelp other){
            return this.c - other.c;
        }

    }


    static ArrayList<ArrayList<Edge>> primsolve(ArrayList<ArrayList<Edge>> graph){
        ArrayList<ArrayList<Edge>> mst = new ArrayList<>();
        for(int i = 0; i < graph.size(); i++)
            mst.add(new ArrayList<>());

        PriorityQueue<PHelp> q = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.size()];
        q.add(new PHelp(5, -1, 0));
        while(q.size() > 0){
            PHelp rem  = q.remove();

            if(visited[rem.v])
                continue;
            visited[rem.v] = true;

            if(rem.av != -1)
                addEdge(mst, rem.v, rem.av, rem.c);

            for(int e = 0; e < graph.get(rem.v).size(); e++){
                Edge ne = graph.get(rem.v).get(e);
                if(visited[ne.nbr] == false)
                    q.add(new PHelp(ne.nbr, rem.v, ne.wt));
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        int v = 7;
        for(int i = 0; i < v; i++)
            graph.add(new ArrayList<>());

        addEdge(graph, 0, 1, 20);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 20);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        // display(graph);

        ArrayList<ArrayList<Edge>> mst = primsolve(graph);
        display(mst);

    }
}