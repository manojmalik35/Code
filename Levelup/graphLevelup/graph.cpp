#include<iostream>
#include<vector>
#include<queue>

using namespace std;

class Edge
{
    public:
        int nbr;
        int wt;

        Edge(int nbr, int wt)
        {
            this->nbr = nbr;
            this->wt = wt;
        }
};

int n = 7;
vector<vector<Edge*>> graph(n, vector<Edge*>());

void addEdge(int u, int v, int wt)
{
    graph[u].push_back(new Edge(v, wt));
    graph[v].push_back(new Edge(u, wt));
}

void display()
{
    for(int i = 0; i < graph.size(); i++)
    {
        cout << i << " -> ";
        for(Edge* e : graph[i])
            cout << "(" << e->nbr << "-" << e->wt << ")"; 
        cout << endl;
    }
}

void createGraph()
{
    addEdge(0, 1, 10);
    addEdge(0, 3, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(4, 6, 8);
    addEdge(5, 6, 3);
    display();
}


int BFS(int src, vector<bool> &vis)
{
    int cycles = 0;
    queue<int> q;
    q.push(src);
    while(q.size() > 0)
    {
        int v = q.front(); q.pop();

        if(vis[v])
        {
            // cout << "Cycle detected at " << v << endl;
            cycles++;
            continue;
        }

        vis[v] = true;
        for(Edge *e : graph[v])
        {
            if(!vis[e->nbr])
                q.push(e->nbr);
        }
    }

    return cycles;
}

int gcc()
{
    vector<bool> vis(graph.size(), false);
    int gcc = 0;
    int cycles = 0;
    for(int i = 0; i < graph.size(); i++)
    {
        if(!vis[i])
        {
            cycles += BFS(i, vis);
            gcc++;
        }
    }
    cout << cycles << " ";
    return gcc;
}



int main()
{
    createGraph();
    cout << gcc() << endl;
    return 0;
}