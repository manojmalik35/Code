import java.util.*;
public class astronauts{

    static class Edge{
        int nbr;
        int wt;

        Edge(int nbr, int wt){
            this.nbr = nbr;
            this.wt = wt;
        }

        void display(){
            System.out.print("[" + this.nbr + " - " + this.wt + "]");
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
        for(int v = 0; v < graph.size(); v++){
            System.out.print(v + " -> ");
            for(int e = 0; e < graph.get(v).size(); e++){
                Edge ne = graph.get(v).get(e);
                ne.display();
            }
            System.out.println();
        }
    }

    static String gscc(int src, boolean[] visited){
        String comp = "";
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(src);

        while(queue.size() > 0){
            int rem = queue.removeFirst();

            if(visited[rem])
                continue;
            visited[rem] = true;

            comp += rem;

            for(int i = 0; i < graph.get(rem).size(); i++){
                Edge ne = graph.get(rem).get(i);
                if(visited[ne.nbr] == false)
                    queue.addLast(ne.nbr);
            }
        }
        return comp;
    }

    static ArrayList<String> gcc(){
        ArrayList<String> ans = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        for(int i = 0; i < graph.size(); i++){
            if(visited[i] == false){
                String comp = gscc(i, visited);
                ans.add(comp);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 10;
        for(int v = 0; v < n; v++)
            graph.add(new ArrayList<>());

        int[] v1 = {9, 5, 2, 3, 6, 1};
        int[] v2 = {4, 3, 0, 7, 8, 4};

        for(int e = 0; e < v1.length; e++)
            addEdge(v1[e], v2[e], 1);

        ArrayList<String> teams = gcc();
        
        int now = 0;
        for(int a = 0; a < teams.size(); a++){
            for(int b = a + 1; b < teams.size(); b++)
                now += teams.get(a).length() * teams.get(b).length();
        }

        System.out.println(now);
    }
}