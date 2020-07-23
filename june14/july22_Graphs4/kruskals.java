import java.util.*;

public class kruskals{

    static class Edge{
        int nbr;
        int wt;

        Edge(int nbr, int wt){
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    static void addEdge(ArrayList<ArrayList<Edge>> g, int v1, int v2, int wt){
        Edge e1 = new Edge(v2, wt);
        g.get(v1).add(e1);

        Edge e2 = new Edge(v1, wt);
        g.get(v2).add(e2);
    }

    static void display(ArrayList<ArrayList<Edge>> g){
        for(int v = 0; v < g.size(); v++){
            System.out.print(v + " -> ");

            for(int n = 0; n < g.get(v).size(); n++){
                Edge ne = g.get(v).get(n);
                System.out.print("[" + ne.nbr + "-" + ne.wt + "] ");
            }

            System.out.println();
        }
    }

    static class KEdge implements Comparable<KEdge>{
        int v1;
        int v2;
        int wt;

        KEdge(int v1, int v2, int wt){
            this.v1 = v1;
            this.v2 = v2;
            this.wt = wt;
        }

        public int compareTo(KEdge other){
            return this.wt - other.wt;
        }
    }

    static int find(int v, int[] pa){
        if(v == pa[v])
            return pa[v];
        return find(pa[v], pa);
    }

    static void merge(int sl1, int sl2, int[] pa, int[] ra){
        if(ra[sl1] < ra[sl2])
            pa[sl1] = sl2;
        else if(ra[sl2] < ra[sl1])
            pa[sl2] = sl1;
        else{
            ra[sl1]++;
            pa[sl2] = sl1;
        }
    }
    static void kruskal(ArrayList<ArrayList<Edge>> g){
        ArrayList<ArrayList<Edge>> mst = new ArrayList<>();
        int[] pa = new int[g.size()];
        int[] ra = new int[g.size()];
        for(int v = 0; v < g.size(); v++){
            pa[v] = v;
            ra[v] = 1;
            mst.add(new ArrayList<>());
        }

        PriorityQueue<KEdge> pq = new PriorityQueue<>();
        for(int v = 0; v < g.size(); v++){
            for(int n = 0; n < g.get(v).size(); n++){
                Edge ne = g.get(v).get(n);
                if(v < ne.nbr)
                    pq.add(new KEdge(v, ne.nbr, ne.wt));
            }
        }

        while(pq.size() > 0){
            KEdge ke = pq.remove();

            int v1 = ke.v1;
            int v2 = ke.v2;
            int v1sl = find(v1, pa); // v1 set leader
            int v2sl = find(v2, pa);

            if(v1sl != v2sl){
                addEdge(mst, v1, v2, ke.wt);
                merge(v1sl, v2sl, pa, ra);
            }
        }

        display(mst);
    }
    
    public static void main(String[] args) {
        int n = 7;
        for(int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        addEdge(graph, 0, 1, 20);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 1,2,10);
        addEdge(graph, 2,3,20);
        addEdge(graph, 3,4,2);
        addEdge(graph, 4,5,3);
        addEdge(graph, 5,6,3);
        addEdge(graph, 4,6,8);
        addEdge(graph, 2,5,5);
        
        // display(graph);
        kruskal(graph);
    }
}