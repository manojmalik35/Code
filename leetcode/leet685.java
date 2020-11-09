public class leet685 {
    int findPar(int u, int[] par){
        if(u == par[u]) return u;
        return par[u] = findPar(par[u], par);
    }
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        
        int n = edges.length;
        int[] par = new int[n + 1];
        for(int i = 0; i <= n; i++) par[i] = i;

        int[] ans = null;
        for(int[] edge : edges){
            int p1 = findPar(edge[0], par);
            int p2 = findPar(edge[1], par);

            if(p1 != p2)
                par[p1] = p2;
            else{
                ans = edge;
                break;
            }
        }
        
        return ans;
    }
}
