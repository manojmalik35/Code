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

class Dhelp
{
    public:
        int v;
        int c;
        string p;

        Dhelp(int v, int c, string p)
        {
            this->v = v;
            this->c = c;
            this->p = p;
        }

        // bool operator<(const Dhelp& other) const
        // {
        //     return this->c < other.c;
        // }

        bool operator>(const Dhelp& other) const
        {
            return this->c > other.c;
        }
};

void djikstra(int s)
{
    priority_queue<Dhelp, vector<Dhelp>, greater<Dhelp>> pq;
    pq.push(Dhelp(s, 0, to_string(s)));
    vector<bool> visited (graph.size(), false);

    while(pq.size() > 0)
    {
        Dhelp rem = pq.top();
        pq.pop();

        if(visited[rem.v])
            continue;
        visited[rem.v] = true;

        cout << rem.v << " @ " << rem.c << " via " << rem.p << endl;

        for(int n = 0; n < graph[rem.v].size(); n++)
        {
            Edge ne = graph[rem.v][n];
            if(visited[ne.nbr] == false)
                pq.push(Dhelp(ne.nbr, rem.c + ne.wt, rem.p + to_string(ne.nbr)));
        }

    }
}

int main(int argc, char** argv)
{
    int n = 7;
    for(int i = 0; i < n; i++)
        graph.push_back(vector<Edge>());

    addEdge(0,1,10);
    addEdge(0,3,40);
    addEdge(1,2,10);
    addEdge(2,3,10);
    addEdge(3,4,2);
    addEdge(4,5,3);
    addEdge(5,6,3);
    addEdge(4,6,8);
    addEdge(2,5,5);

    djikstra(0);
}