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

string gscc(int src, vector<bool>& visited)//get single connected component
{
    string comp = "";
    queue<int> q;
    q.push(src);
    while(q.size() > 0)
    {
        int rem = q.front();
        q.pop();
        if(visited[rem])
            continue;
        visited[rem] = true;
        comp += to_string(rem);

        for(int n = 0; n < graph[rem].size(); n++)
        {
            Edge ne = graph[rem][n];
            if(visited[ne.nbr] == false)
                q.push(ne.nbr);
        }
    }

    return comp;
}

vector<string> gcc()
{
    vector<bool> visited (graph.size(), false);
    vector<string> ans;

    for(int v = 0; v < graph.size(); v++)
    {
        if(visited[v] == false)
        {
            string comp = gscc(v, visited);
            ans.push_back(comp);
        }
    }

    return ans;
}


int main(int argc, char** argv)
{
    int n = 10;
    for(int v = 0; v < n; v++)
        graph.push_back(vector<Edge>());

    vector<int> v1 {9, 5, 2, 3, 6, 1};
    vector<int> v2 {4, 3, 0, 7, 8, 4};

    for(int e = 0; e < v1.size(); e++)
        addEdge(v1[e], v2[e], 1);

    vector<string> nations = gcc();
    int teams = 0;
    for(int n1 = 0; n1 < nations.size(); n1++)
    {
        for(int n2 = n1 + 1; n2 < nations.size(); n2++)
            teams += nations[n1].length() * nations[n2].length();
    }
    cout << teams << endl;
}