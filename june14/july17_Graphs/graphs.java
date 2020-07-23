import java.util.*;
public class graphs {

    static class Edge {
        public int nbr;
        public int wt;

        Edge(int nbr,int wt){
            this.nbr = nbr;
            this.wt = wt;
        }

        void display(){
            System.out.print("[" + this.nbr + "-" + this.wt + "]");
        }
    }

    static ArrayList<ArrayList<Edge>> graph;

    static void addEdge(int v1,int v2,int wt){
        Edge e1 = new Edge(v2,wt);
        graph.get(v1).add(e1);

        Edge e2 = new Edge(v1,wt);
        graph.get(v2).add(e2);
    }

    static void display(){
        for(int v = 0;v < graph.size(); v++){
            System.out.print(v+" -> ");
            for(int n = 0;n < graph.get(v).size(); n++){
                Edge ne = graph.get(v).get(n);
                ne.display();
            }
            System.out.println();
        }
    }

    static boolean hasPath(int src,int des,boolean[] visited){

        if(src == des){
            return true;
        }
        visited[src] = true;
        for(int n = 0; n < graph.get(src).size(); n++){
            Edge ne = graph.get(src).get(n);
            if(visited[ne.nbr] == false){
                boolean rres = hasPath(ne.nbr, des, visited);
                if(rres)
                    return true;
            }
        }

        return false;
    }

    static void printAllPaths(int src,int des,boolean[] visited,String asf,int dsf){
        if(src == des){
            System.out.println(asf + des + " @ " + dsf);
            return;
        }

        visited[src] = true;
        for(int n = 0; n < graph.get(src).size(); n++){
            Edge ne = graph.get(src).get(n);
            if(visited[ne.nbr] == false)
                printAllPaths(ne.nbr, des, visited,asf + src,dsf + ne.wt);
        }
        visited[src] = false;

    }

    static void shortestPath(int src, int des, boolean[] visited, String asf, int dsf,int minpath){
        if(src == des){
            if(dsf < minpath){
                minpath = dsf;
                System.out.println(asf + des + " " + minpath);
            }
            
            return;
        }
        visited[src] = true;
        for(int n = 0; n < graph.get(src).size(); n++){
            Edge ne = graph.get(src).get(n);
            if(visited[ne.nbr] == false){
                shortestPath(ne.nbr, des, visited, asf + src, dsf + ne.wt, minpath);
            }
        }
        visited[src] = false;
    }
    public static void main(String[] args) {
        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());

        addEdge(0,1,10);
        addEdge(0,3,40);
        addEdge(1,2,10);
        addEdge(2,3,10);
        addEdge(3,4,2);
        addEdge(4,5,3);
        addEdge(5,6,3);
        addEdge(4,6,8);

        // display();
        boolean[] visited = new boolean[graph.size()];
        // System.out.println(hasPath(0, 6, visited));
        // printAllPaths(0, 6, visited, "", 0);
        shortestPath(0, 6, visited, "", "", 0, Integer.MAX_VALUE);
    }
}