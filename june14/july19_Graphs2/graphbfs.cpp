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

bool bfs(int src, int des)
{
    vector<bool> visited (graph.size(), false);
    queue<int> q;
    q.push(src);
    while(q.size() > 0)
    {
        int rem = q.front();
        q.pop();
        visited[rem] = true;
        if(rem == des)
            return true;
        for(int n = 0; n < graph[rem].size(); n++)
        {
            Edge ne = graph[rem][n];
            if(visited[ne.nbr] == false)
                q.push(ne.nbr);
        }
    }

    return false;
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

bool isConnected()
{
    int counter = 0;
    vector<bool> visited (graph.size(), false);
    for(int v = 0; v < graph.size(); v++)
    {
        if(visited[v] == false)
        {
            gscc(v,visited);
            counter++;

            if(counter == 2)
                return false;
        }
    }

    return true;
}

bool isCompCyclic(int root, vector<bool>& visited)
{
    queue<int> q;
    q.push(root);
    while(q.size() > 0)
    {
        int rem = q.front();
        q.pop();

        if(visited[rem])
            return true;
        visited[rem] = true;

        for(int n = 0; n < graph[rem].size(); n++)
        {
            Edge ne = graph[rem][n];
            if(visited[ne.nbr] == false)
                q.push(ne.nbr);
        }
    }
    return false;
}

bool isCyclic()
{
    vector<bool> visited (graph.size(), false);
    for(int v = 0; v < graph.size(); v++)
    {
        if(visited[v] == false)
        {
            bool rres = isCompCyclic(v, visited);
            if(rres)
                return true;
        }
    }

    return false;
}

class BiPair
{
    public:
        int v; //vertex
        int l; //level

        BiPair(int v,int l)
        {
            this->v = v;
            this->l = l;
        }
};

bool isCompBipartite(int r,vector<int>& visited)
{
    queue<BiPair> q;
    q.push(BiPair(r,1));

    while(q.size() > 0)
    {
        BiPair rem = q.front();
        q.pop();

        if(visited[rem.v] != 0)
        {
            int ol = visited[rem.v];
            int nl = rem.l;
            if(ol % 2 != nl % 2)
                return false;
        }

        visited[rem.v] = rem.l;

        for(int n = 0; n < graph[rem.v].size(); n++)
        {
            Edge ne = graph[rem.v][n];
            if(visited[ne.nbr] == 0)
                q.push(BiPair(ne.nbr, rem.l + 1));
        }
    }
    return true;
}

bool isBipartite()
{
    vector<int> visited (graph.size(),0);
    for(int v = 0; v < graph.size(); v++)
    {
        if(visited[v] == 0)
        {
            bool rres = isCompBipartite(v, visited);
            if(rres == false)
                return false;
        }
    }

    return true;
}

int main(int argc, char** argv)
{
    graph.push_back(vector<Edge>()); //0
    graph.push_back(vector<Edge>()); //1
    graph.push_back(vector<Edge>()); //2
    graph.push_back(vector<Edge>()); //3
    graph.push_back(vector<Edge>()); //4
    graph.push_back(vector<Edge>()); //5
    graph.push_back(vector<Edge>()); //6

    addEdge(0,1,10);
    addEdge(0,3,40);
    addEdge(1,2,10);
    addEdge(2,3,10);
    addEdge(3,4,2);
    addEdge(4,5,3);
    // addEdge(5,6,3);
    addEdge(4,6,8);
    // addEdge(2,5,5);

    vector<bool> visited (graph.size(), false);
    // cout << bfs(0, 6) << endl;
    // cout << gscc(4, visited) << endl;
    // vector<string> comps = gcc();
    // for(auto itr : comps)
    //     cout << itr << endl;

    // cout << isConnected() << endl;
    // cout << isCyclic() << endl;
    cout << isBipartite() << endl;
}