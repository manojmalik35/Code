#include<iostream>
#include<vector>
#include<queue>

using namespace std;

int n = 11;
vector<vector<int>> graph (n, vector<int>());

void addEdge(int u, int v)
{
    graph[u].push_back(v);
}

void display(vector<vector<int>> &graph)
{
    for(int i = 0; i < graph.size(); i++)
    {
        cout << i << " -> ";
        for(int n : graph[i])
            cout << n << ", "; 
        cout << endl;
    }
}

void createGraph()
{
    addEdge(0, 2);
    addEdge(2, 1);
    addEdge(1, 0);
    addEdge(2, 3);
    addEdge(3, 4);
    addEdge(4, 5);
    addEdge(5, 3);
    addEdge(6, 5);
    addEdge(6, 7);
    addEdge(7, 8);
    addEdge(8, 9);
    addEdge(9, 6);
    addEdge(9, 10);
    
    // display(graph);
}

void topoDFS(int src, vector<bool> &vis, vector<int> &st)
{
    vis[src] = true;
    for(int n : graph[src])
    {
        if(!vis[n])
            topoDFS(n, vis, st);
    }

    st.push_back(src);
}

void topoSortDFS()
{
    vector<bool> vis(graph.size(), false);
    vector<int> st;
    for(int i = 0; i < graph.size(); i++)
    {
        if(!vis[i])
            topoDFS(i, vis, st);
    }

    for(int i = st.size() - 1; i >= 0; i--)
        cout << st[i] << " ";
    cout << endl;
}

void khansAlgo()
{
    vector<int> indeg(graph.size(), 0);
    for(int i = 0; i < graph.size(); i++)
    {
        for(int n : graph[i])
            indeg[n]++;
    }

    queue<int> q;
    for(int i = 0; i < indeg.size(); i++)
    {
        if(indeg[i] == 0)
            q.push(i);
    }

    if(q.size() == 0)
    {
        cout << "There is a cycle" << endl;
        return;
    }

    vector<int> ans;
    while(q.size() > 0)
    {
        int n = q.front(); q.pop();
        ans.push_back(n);

        for(int nbr : graph[n])
        {
            if(--indeg[nbr] == 0)
                q.push(nbr);
        }
    }

    for(int i : ans)
        cout << i << " ";
    cout << endl;
}

bool topoDFS2(int src, vector<bool> &vis, vector<bool> &cycle, vector<int> &st)//iska maksad y maan k chalo ki isne cycle find krni h
{
    vis[src] = cycle[src] = true;
    bool res = false;
    for(int n : graph[src])
    {
        if(!vis[n] && !res)
            res = res || topoDFS2(n, vis, cycle, st);
        else if(cycle[n])
            return true;//cycle detected
    }   

    st.push_back(src);
    cycle[src] = false;
    return res;
}

void topoSortDFS2()
{
    vector<bool> vis(graph.size(), false);
    vector<bool> cycle(graph.size(), false);
    vector<int> st;

    bool res = false;
    for(int i = 0; i < graph.size(); i++)
    {
        if(!vis[i])
            res = res || topoDFS2(i, vis, cycle, st);
    }

    if(res)
        cout << "Topological sort not possible." << endl;
    else
    {
        //print st
    }
}

bool topoDFS3(int src, vector<int> &vis, vector<int> &st)
{
    vis[src] = 2;
    bool res = false;
    for(int n : graph[src])
    {
        if(vis[n] == -1 && !res)
            res = res || topoDFS3(n, vis, st);
        else if(vis[n] == 2)
            return true;//cycle detected
    }   

    st.push_back(src);
    vis[src] = 1;
    return res;
}

void topoSortDFS3() {

    vector<int> vis(graph.size(), -1);
    vector<int> st;

    bool res = false;
    for(int i = 0; i < graph.size(); i++)
    {
        if(vis[i] == -1)
            res = res || topoDFS3(i, vis, st);
    }

    if(res)
        cout << "Topological sort not possible." << endl;
    else
    {
        //print st
    }
}

string kosaDFS(vector<vector<int>> &newGraph, int src, vector<bool> &vis)
{
    string rres = "";
    vis[src] = true;
    for(int v : newGraph[src]){
        if(!vis[v])
            rres = kosaDFS(newGraph, v, vis);
    }
    
    return rres + to_string(src);
}

void kosaraju()
{
    vector<bool> vis(graph.size(), false);
    vector<int> st;
    for(int i = 0; i < graph.size(); i++)
    {
        if(!vis[i])
            topoDFS(i, vis, st);
    }

    vector<vector<int>> newGraph(graph.size(), vector<int>());
    for(int u = 0; u < graph.size(); u++)
    {
        for(int v : graph[u])
            newGraph[v].push_back(u);
    }

    // display(newGraph);
    fill(vis.begin(), vis.end(), false);
    vector<string> comp;
    int noc = 0;
    while(st.size() > 0)
    {
        int t = st.back(); st.pop_back();
        if(!vis[t]){
            noc++;
            string c = kosaDFS(newGraph, t, vis);
            comp.push_back(c);
        }
    }

    cout << noc << "\n";
    for(string c : comp)
        cout << c << " ";
    cout << "\n";

}

int main()
{
    createGraph();
    // topoSortDFS();
    // khansAlgo();
    kosaraju();
    return 0;
}