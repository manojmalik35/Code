#include<iostream>
#include<vector>
#include<algorithm>

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
    if(size[p2] > size[p1])
    {
        par[p1] = p2;
        size[p2] += size[p1];
    }else
    {
        par[p2] = p1;
        size[p1] += size[p2];
    }
}

int minCostToSupplyWater_leet1168(int n, vector<int> &wells, vector<vector<int>> &pipes)
{

    for(int i = 0; i < wells.size(); i++)
    {
        int cost = wells[i];
        pipes.push_back({0, i + 1, cost});
    }
    
    sort(pipes.begin(), pipes.end(), [](vector<int> &a, vector<int> &b){
        return a[2] < b[2];
    });

    vector<int> size(n + 1, 0);
    vector<int> par(n + 1);
    for(int i = 0; i < par.size(); i++)
        par[i] = i;

    int ans = 0;
    for(int i = 0; i < pipes.size(); i++)
    {
        vector<int> pipe = pipes[i];

        int p1 = findParent(pipe[0], par);
        int p2 = findParent(pipe[1], par);

        if(p1 != p2)
        {
            merge(p1, p2, par, size);
            ans += pipe[2];
        }
    }

    return ans;
}

int main()
{
    
    int n = 5;
    vector<int> wells {1, 2, 2, 3, 2};
    vector<vector<int>> pipes {{1, 2, 1}, {2, 3, 1}, {4, 5, 7}};
    cout << minCostToSupplyWater_leet1168(n, wells, pipes) << endl;
    return 0;
}