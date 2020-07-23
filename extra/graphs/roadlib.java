import java.util.*;
public class roadlib{

    static void display(ArrayList<Integer>[] graph){
        for(int i = 1; i < graph.length; i++)
            System.out.println(i + " -> " + graph[i]);
    }


    static long gscc(ArrayList<Integer>[] graph, int src, boolean[] visited){
        String comp = "";
        long noe = 0;
        Queue<Integer> q = new LinkedList<>();       
        q.add(src);
        while(q.size() > 0){
            int rem = q.remove();
            
            if(visited[rem])
                continue;
            visited[rem] = true;

            noe++;
            comp += rem;
            for(int i = 0; i < graph[rem].size(); i++){
                int nbr = graph[rem].get(i);
                if(visited[nbr] == false)
                    q.add(nbr);
            }

        }
        return noe - 1;
    }

    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];

        for(int i = 1; i < graph.length; i++)
            graph[i] = new ArrayList<>();

        for(int i = 0; i < cities.length; i++){
            graph[cities[i][0]].add(cities[i][1]);
            graph[cities[i][1]].add(cities[i][0]);
        }
        
        long noc = 0, noe = 0;
        boolean[] visited = new boolean[graph.length];
        for(int i = 1; i < graph.length; i++){
            if(visited[i] == false){
                long noeisc = gscc(graph, i, visited);//no of edges in this component
                noc++;
                noe += noeisc;
            }
        }

        long libcost = noc * c_lib;
        long roadcost = noe * c_road;
        // System.out.println(libcost + " " + roadcost);
        long ans = libcost + roadcost;
        long factor = (long)(n * c_lib);
        ans = factor < ans ? factor : ans;

        return ans;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int c_lib = sc.nextInt();
            int c_road = sc.nextInt();
            int[][] cities = new int[m][2];

            for(int i = 0; i < m; i++){
                cities[i][0] = sc.nextInt();
                cities[i][1] = sc.nextInt();
            }

            System.out.println("===================================");
            System.out.println(roadsAndLibraries(n, c_lib, c_road, cities));
        }
        // 2
// 3 3 2 1
// 1 2
// 3 1
// 2 3
// 6 6 2 5
// 1 3
// 3 4
// 2 4
// 1 2
// 2 3
// 5 6
    }
}