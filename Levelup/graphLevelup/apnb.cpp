#include<iostream>
#include<vector>

using namespace std;

vector<vector<int>> graph(10, vector<int>());

void addEdge(int u, int v)
{
    graph[u].push_back(v);
    graph[v].push_back(u);
}

void display()
{
    for(int u = 0; u < graph.size(); u++)
    {
        cout << u << " -> ";
        for(int v : graph[u])
            cout << v << ", ";
        cout << "\n";
    }
}

void createGraph()
{
    addEdge(0, 1);
    addEdge(1, 2);
    addEdge(1, 4);
    addEdge(3, 4);
    addEdge(4, 5);
    addEdge(5, 6);
    addEdge(5, 7);
    addEdge(5, 8);
    addEdge(5, 9);
    addEdge(8, 9);
}

int timec = 0;
void apnbdfs(int u, vector<int> &par, vector<bool> &vis, vector<int> &disc, vector<int> &low, vector<int> &ap)
{
    disc[u] = low[u] = timec++;
    vis[u] = true;
    for(int v : graph[u])
    {
        if(!vis[v])//unvisited
        {
            par[v] = u;
            apnbdfs(v, par, vis, disc, low, ap);
            if(disc[u] <= low[v])
                ap[u]++;
            if(disc[u] < low[v])
                cout << "Articulation Bridge : " << u << " - " << v << " \n";
                
            if(par[u] == -1)
                ap[u]--;

            low[u] = min(low[u], low[v]);
        }
        else if(v != par[u])//visited
        {
            low[u] = min(low[u], disc[v]);
        }
    } 
}

void apnb()
{
    vector<int> par(graph.size(), -1);
    vector<bool> vis(graph.size(), false);
    vector<int> disc(graph.size(), 0);
    vector<int> low(graph.size(), 0);
    vector<int> ap(graph.size(), 0);
    apnbdfs(0, par, vis, disc, low, ap);
    int apc = 0;
    cout << "Articulation points are : ";
    for(int i = 0; i < ap.size(); i++)
    {
        if(ap[i] != 0)
        {
            apc++;
            cout << i << " ";
        }
    }
    cout << "\n" << apc << "\n";
}

int main()
{
    createGraph();
    // display();
    apnb();
    return 0;
}