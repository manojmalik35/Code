#include<iostream>
#include<vector>
#include<string>
#include<stack>

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

vector<vector<Edge>> dag;

void addEdge(int v1,int v2,int wt)
{
    Edge e1(v2,wt);
    dag[v1].push_back(e1);
}

void tsComp(int v, vector<bool>& visited, stack<int>& st)
{
    visited[v] = true;
    for(int n = 0; n < dag[v].size(); n++)
    {
        Edge ne = dag[v][n];
        if(visited[ne.nbr] == false)
            tsComp(ne.nbr, visited, st);
    }
    st.push(v);
}

void ts()
{
    stack<int> st;
    vector<bool> visited (dag.size(), false);

    for(int v = 0; v < dag.size(); v++)
    {
        if(visited[v] == false)
            tsComp(v, visited, st);
    }

    while(st.size() > 0)
    {
        int t = st.top();
        st.pop();
        cout << t << " ";
    }
    cout << endl;
}

int main(int argc, char** argv)
{
    int n = 7;
    for(int i = 0; i < n; i++)
        dag.push_back(vector<Edge>());

    addEdge(0, 1, 1);
    addEdge(1, 2, 1);
    addEdge(2, 3, 1);
    addEdge(0, 4, 1);
    addEdge(4, 3, 1);
    addEdge(5, 4, 1);
    addEdge(5, 6, 1);
    addEdge(6, 3, 1);

    ts();
}
