#include<iostream>
#include<vector>
#include<stack>

using namespace std;

vector<vector<int>> dag;

void display()
{
    for(int v = 0; v < dag.size(); v++)
    {
        cout << v << " -> ";
        for(int e = 0; e < dag[v].size(); e++)
            cout << dag[v][e] << " ";
        cout << endl;
    }
}

void tosoComp(int src, stack<int>& st, vector<bool>& visited)
{
    visited[src] = true;
    for(int i = 0; i < dag[src].size(); i++)
    {
        int nbr = dag[src][i];
        if(visited[nbr] == false)
            tosoComp(nbr, st, visited);
    }
    st.push(src);
}

void toso()
{
    stack<int> st;
    vector<bool> visited(dag.size(), false);
    for(int v = 0; v < dag.size(); v++)
    {
        if(visited[v] == false)
            tosoComp(v, st, visited);
    }

    while(st.size() > 0)
    {
        int t = st.top(); 
        st.pop();

        cout << t << " ";
    }
    cout << endl;
}




int main()
{
    int v = 7;
    for(int i = 0; i < v; i++)
        dag.push_back(vector<int>());
    dag[0].push_back(1);
    dag[0].push_back(4);
    dag[1].push_back(2);
    dag[2].push_back(3);
    dag[4].push_back(3);
    dag[5].push_back(4);
    dag[5].push_back(6);
    dag[6].push_back(3);

    // display();
    toso();

}