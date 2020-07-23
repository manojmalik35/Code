#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int bs = 0; //block size
int height = 0;
vector<vector<int>> Tree;
vector<int> depth, parent, blockParent;

void dfs1(int src, int level, int par)
{
    depth[src] = level;
    parent[src] = par;
    height = max(height, level + 1);

    for (int ch : Tree[src])
        dfs1(ch, level + 1, src);
}

void dfs2(int src)
{
    int d1 = depth[src];
    int d2 = depth[parent[src]];

    if (d1 / bs == d2 / bs) //same block
        blockParent[src] = blockParent[parent[src]];
    else
        blockParent[src] = parent[src];
}

int LCA(int u, int v)
{
    while (blockParent[u] != blockParent[v])
    {
        if (depth[u] > depth[v])
            swap(u, v);

        v = blockParent[v];
    }

    while(u != v)
    {
        if (depth[u] > depth[v])
            swap(u, v);

        v = parent[v];
    }

    return v;
}

void solve()
{
    int n;
    cin >> n;
    Tree.resize(n + 1);
    depth.resize(n + 1);
    parent.resize(n + 1);
    blockParent.resize(n + 1);

    for (int i = 0; i < n - 1; i++)
    {
        int u, v;
        cin >> u >> v;
        Tree[u].push_back(v);
    }

    dfs1(1, 0, 0); //setting depth and parent and height
    bs = ceil(sqrt(height));
    dfs2(1); //setting block parent

    int q;
    cin >> q;
    while (q-- > 0)
    {
        int u, v;
        cin >> u >> v;
        cout << LCA(u, v) << "\n";
    }
}

int main()
{
    int t = 1;
    // cin >> t;
    while (t-- > 0)
    {
        solve();
    }
    return 0;
}