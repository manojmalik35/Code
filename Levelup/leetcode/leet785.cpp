#include<iostream>
#include<vector>
#include<queue>

using namespace std;

bool bfs(int src, vector<vector<int>> &graph, vector<int> &vis)
{
    queue<pair<int, int>> q;
    q.push({src, 0});
    while(q.size() > 0)
    {
        pair<int, int> rpair = q.front();
        q.pop();
        
        int vtx = rpair.first;
        int color = rpair.second;
        
        if(vis[vtx] != -1)
        {
            if(vis[vtx] != color)
                return false;
            else
                continue;
        }
        vis[vtx] = color;
        
        for(int nbr : graph[vtx])
        {
            if(vis[nbr] == -1)
                q.push({nbr, (color + 1) % 2});
        }
    }
    
    return true;   
}

bool isBipartite(vector<vector<int>>& graph) {
    vector<int> vis(graph.size(), -1);
    
    bool res = true;
    for(int i = 0; i < graph.size(); i++)
    {
        if(vis[i] == -1)
            res = res && bfs(i, graph, vis);
    }
    
    return res;
}

int main()
{
    vector<vector<int>> graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
    cout << (boolalpha) << isBipartite(graph) << endl;
    return 0;
}