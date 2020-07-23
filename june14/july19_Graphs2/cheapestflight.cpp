#include<iostream>
#include<vector>
#include<string>
#include<queue>
#include<climits>

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

//bonus
int mp = INT_MAX;
void cheapestflightkstop(int src, int des, int k, vector<bool>& visited, int price)
{
    if(src == des)
    {
        if(price < mp && k + 1 >= 0)
            mp = price;
        return;
    }

    visited[src] = true;
    for(int n = 0;n < graph[src].size(); n++)
    {
        Edge ne = graph[src][n];
        if(visited[ne.nbr] == false)
            cheapestflightkstop(ne.nbr, des, k - 1, visited, price + ne.wt);
    }
    visited[src] = false;
}


int main(int argc, char** argv)
{
    int n =3;
    for(int i = 0; i < n; i++)
        graph.push_back(vector<Edge>());

    // addEdge(0, 1, 100);
    // addEdge(1, 2, 100);
    // addEdge(0, 2, 500);

    addEdge(0, 1, 90);
    addEdge(1, 2, 20);
    addEdge(3, 2, 110);
    addEdge(3, 4, 70);
    addEdge(4, 5, 10);
    addEdge(5, 0, 50);

    vector<bool> visited (graph.size(), false);
    cheapestflightkstop(0, 3, 1, visited, 0);
    cout << mp << endl;

    
}