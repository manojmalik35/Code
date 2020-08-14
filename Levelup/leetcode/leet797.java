import java.util.*;
public class leet797 {
    public List<List<Integer>> ans;
    public void dfs(int[][] graph, List<Integer> path, int src){
        if(src == graph.length - 1){
            ans.add(new ArrayList<>(path));
            return;
        }
        
        for(int v : graph[src]){
            path.add(v);
            dfs(graph, path, v);
            path.remove(path.size() - 1);
        }
    }
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph, path, 0);
        return ans;
    }
}