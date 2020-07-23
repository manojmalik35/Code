#include<iostream>
#include<vector>
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

bool hasPath(int src,int des,vector<bool>& visited)
{
    if(src == des)
        return true;
    visited[src] = true;
    for(int n = 0;n < graph[src].size(); n++)
    {
        Edge ne = graph[src][n];
        if(visited[ne.nbr] == false)
        {
            bool rres = hasPath(ne.nbr,des,visited);
            if(rres)
                return true;
        }
    }
    // visited[v1] = false;
    return false;
}

void printAllPaths(int src,int des,string asf,vector<bool>& visited,int dsf)
{
    if(src == des)
    {
        asf += to_string(des);
        cout << asf << " @ " << dsf <<endl;
        return;
    }
    visited[src] = true;
    for(int n = 0;n < graph[src].size(); n++)
    {
        Edge ne = graph[src][n];
        if(visited[ne.nbr] == false)
            printAllPaths(ne.nbr,des,asf+to_string(src),visited,dsf+ne.wt);
    }
    visited[src] = false;
}


int minpath = INT_MAX;
string minp;
int maxpath = INT_MIN;
string maxp;
void printShortestandLongestPath(int src,int des,string asf,vector<bool>& visited,int dsf)
{
    if(src == des)
    {
        asf += to_string(des);
        if(dsf < minpath)
        {
            minpath = dsf;
            minp = asf;
        }

        if(dsf > maxpath)
        {
            maxpath = dsf;
            maxp = asf;
        }
        return;
    }
    visited[src] = true;
    for(int n = 0;n < graph[src].size(); n++)
    {
        Edge ne = graph[src][n];
        if(visited[ne.nbr] == false)
            printShortestandLongestPath(ne.nbr,des,asf+to_string(src),visited,dsf + ne.wt);
    }
    visited[src] = false;

}

int ceil = INT_MAX;
string cp;
int floor = INT_MIN;
string fp;
void printcfpath(int src, int des, string asf, vector<bool>& visited, int dsf, int factor)
{
    if(src == des)
    {
        asf += to_string(des);
        if(dsf < factor && dsf > floor)
        {
            floor = dsf;
            fp = asf;
        }

        if(dsf > factor && dsf < ceil)
        {
            ceil = dsf;
            cp = asf;
        }
        return;
    }
    visited[src] = true;
    for(int n = 0;n < graph[src].size(); n++)
    {
        Edge ne = graph[src][n];
        if(visited[ne.nbr] == false)
            printcfpath(ne.nbr,des,asf+to_string(src),visited,dsf + ne.wt, factor);
    }
    visited[src] = false;
}

void hamiltonianPnC(int src, string asf, vector<bool>& visited, int count, int ors)//ors = original source
{
    if(count == graph.size() - 1)
    {
        asf += to_string(src);
        cout << asf;

        for(int n = 0; n < graph[src].size(); n++)
        {
            Edge ne = graph[src][n];
            if(ne.nbr == ors)
            {
                cout << "*" << endl; // represents hamiltonian cycle
                return;
            }
        }

        cout << endl;
        return;
    }
    visited[src] = true;
    for(int n = 0; n < graph[src].size(); n++)
    {
        Edge ne = graph[src][n];
        if(visited[ne.nbr] == false)
        {
            hamiltonianPnC(ne.nbr, asf + to_string(src), visited, count + 1, ors);
        }
    }
    visited[src] = false;

}

int main(int argc,char** argv)
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
    addEdge(5,6,3);
    addEdge(4,6,8);
    addEdge(2,5,5);

    // display();
    vector<bool> visited (graph.size(),false);
    // cout << hasPath(0, 6, visited) << endl;
    // printAllPaths(0,6,"",visited,0);
    // printShortestandLongestPath(0, 6, "", visited, 0);
    // cout << minp << " @ " << minpath << endl;
    // cout << maxp << " @ " << maxpath << endl;
    // printcfpath(0, 6, "", visited, 0, 45);
    // cout << fp << " @ " << floor << endl;
    // cout << cp << " @ " << ceil << endl;

    hamiltonianPnC(0, "", visited, 0, 0);

}