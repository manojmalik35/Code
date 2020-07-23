#include<iostream>
#include<vector>

using namespace std;

int findParent(int u, vector<int>& par)
{
    if(par[u] == u)
        return u;
    else{
        int p = findParent(par[u], par);
        par[u] = p;
        return p;
    }
}

void merge(int p1, int p2, vector<int>& par, vector<int>& size)
{
    if(size[p1] > size[p2])
    {
        par[p2] = p1;
        size[p1] += size[p2];
    }else
    {
        par[p1] = p2;
        size[p2] += size[p1];
    }
}

vector<int> findRedundantConnection(vector<vector<int>>& edges) {
    vector<int> par(edges.size() + 1);
    vector<int> size(edges.size() + 1, 1);
    
    for(int i = 1; i < par.size(); i++)
        par[i] = i;
    
    for(int i = 0; i < edges.size(); i++)
    {
        int u = edges[i][0];
        int v = edges[i][1];
        
        int p1 = findParent(u, par);
        int p2 = findParent(v, par);
        
        if(p1 == p2)
            return edges[i];
        else
            merge(p1, p2, par, size);
            
    }
    
    return {};
}

int main()
{
    return 0;
}