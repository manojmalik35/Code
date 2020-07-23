#include<iostream>
#include<vector>
#include<string>
#include<queue>

using namespace std;

class Edge
{
    public:
        int nbr;
        int wt;

        Edge(int nbr,int wt)
        {
            this-> nbr = nbr;
            this-> wt = wt;
        }

        void display()
        {
            cout << "[" << this->nbr << "-" << this->wt << "]";
        }
};

vector<vector<Edge>> graph;

void addEdge(int v1,int v2,int wt)
{
    Edge e1(v2,wt);
    graph[v1].push_back(e1);
    
    Edge e2(v1,wt);
    graph[v2].push_back(e2);
}

void addEdge(vector<vector<Edge>>& graph,int v1,int v2,int wt)
{
    Edge e1(v2,wt);
    graph[v1].push_back(e1);
    
    Edge e2(v1,wt);
    graph[v2].push_back(e2);
}

void display()
{
    for(int v = 0;v < graph.size(); v++)
    {
        cout << v << " -> ";
        for(int n = 0;n < graph[v].size(); n++)
        {
            Edge ne = graph[v][n];
            ne.display();
        }
        cout << endl;
    }
}

void display(vector<vector<Edge>>& graph)
{
    for(int v = 0;v < graph.size(); v++)
    {
        cout << v << " -> ";
        for(int n = 0;n < graph[v].size(); n++)
        {
            Edge ne = graph[v][n];
            ne.display();
        }
        cout << endl;
    }
}

class PPair
{
    public:
        int v;
        int av;
        int c;

        PPair(int v, int av, int c)
        {
            this->v = v;
            this->av = av;
            this->c = c;
        }

        bool operator>(const PPair& other) const
        {
            return this->c > other.c;
        }
};


void prims()
{
    vector<vector<Edge>> mst (graph.size(), vector<Edge>());
    vector<bool> visited (graph.size(), false);
    priority_queue<PPair, vector<PPair>, greater<PPair>> pq;
    pq.push(PPair(0, -1, 0));

    while(pq.size() > 0)
    {
        PPair rem = pq.top();
        pq.pop();

        if(visited[rem.v])
            continue;
        visited[rem.v] = true;

        if(rem.av != -1)
            addEdge(mst, rem.v, rem.av, rem.c);

        for(int n = 0; n < graph[rem.v].size(); n++)
        {
            Edge ne = graph[rem.v][n];
            if(visited[ne.nbr] == false)
                pq.push(PPair(ne.nbr, rem.v, ne.wt));
        }
    }

    display(mst);
}
int main(int argc, char** argv)
{
    int n = 7;
    for(int i = 0; i < n; i++)
        graph.push_back(vector<Edge>());

    addEdge(0,1,20);
    addEdge(0,3,40);
    addEdge(1,2,10);
    addEdge(2,3,20);
    addEdge(3,4,2);
    addEdge(4,5,3);
    addEdge(5,6,3);
    addEdge(4,6,8);
    // addEdge(2,5,5);

    prims();
}