import java.util.*;
public class kruskals {

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

    static int getSetLeader(int v, int[] pa){
        if(pa[v] == v)
            return v;
        return getSetLeader(pa[v], pa);
    }

    static void mergeClouds(int sl1, int sl2, int[] pa, int[] ra){
        if(ra[sl1] > ra[sl2])
            pa[sl2] = sl1;
        else if(ra[sl1] < ra[sl2])
            pa[sl1] = sl2;
        else{
            pa[sl2] = sl1;
            ra[sl1]++;
        }
    }

    static ArrayList<ArrayList<Edge>> kruskal(ArrayList<ArrayList<Edge>> g){
        ArrayList<ArrayList<Edge>> mst = new ArrayList<>();

        int[] pa = new int[g.size()];
        int[] ra = new int[g.size()];
        for(int i = 0; i < pa.length; i++){
            pa[i] = i;
            ra[i] = 1;
            mst.add(new ArrayList<>());
        }

        PriorityQueue<KEdge> pq = new PriorityQueue<>();
        for(int v = 0; v < g.size(); v++){
            for(int e = 0; e < g.get(v).size(); e++){
                Edge ne = g.get(v).get(e);
                if(v < ne.nbr)
                    pq.add(new KEdge(v, ne.nbr, ne.wt));
            }
        }

        while(pq.size() > 0){
            KEdge rem = pq.remove();

            int sl1 = getSetLeader(rem.v1, pa);
            int sl2 = getSetLeader(rem.v2, pa);
            if(sl1 != sl2){
                addEdge(mst, rem.v1, rem.v2, rem.wt);
                mergeClouds(sl1, sl2, pa, ra);
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
        
        ArrayList<ArrayList<Edge>> mst = kruskal(graph);
        display(mst);
    }
}