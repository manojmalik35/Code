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

    void display()
    {
        cout << "[" << this->nbr << "-" << this->wt << "]";
    }
};

vector<vector<Edge*>> graph;

void addEdge(int v1, int v2, int wt)
{
    Edge* e1 = new Edge(v2, wt);
    graph[v1].push_back(e1);

    Edge* e2 = new Edge(v1, wt);
    graph[v2].push_back(e2);
}

void display()
{
    for(int n = 0; n < graph.size(); n++)
    {
        cout << n << " -> ";
        for(int e = 0; e < graph[n].size(); e++)
        {
            Edge* ne = graph[n][e];
            ne->display();
        }
        cout << endl;
    }
}

bool hasPathbfs(int src, int des)
{
    vector<bool> visited(graph.size(), false);
    queue<int> q;
    q.push(src);

    while(q.size() > 0)
    {
        int rem = q.front();
        q.pop();

        if(visited[rem] == true)
            continue;
        visited[rem] = true;

        if(rem == des)
            return true;

        for(int e = 0; e < graph[rem].size(); e++)
        {
            Edge* ne = graph[rem][e];
            if(visited[ne->nbr] == false)
                q.push(ne->nbr);
        }
    }

    return false;
}

string gscc(int src, vector<bool>& visited)
{
    string ans = "";
    queue<int> q;
    q.push(src);

    while(q.size() > 0)
    {
        int rem = q.front();
        q.pop();

        if(visited[rem] == true)
            continue;
        visited[rem] = true;

        ans += to_string(rem);

        for(int e = 0; e < graph[rem].size(); e++)
        {
            Edge* ne = graph[rem][e];
            if(visited[ne->nbr] == false)
                q.push(ne->nbr);
        }
    }

    return ans;
}

vector<string>* gcc()
{
    vector<bool> visited(graph.size(), false);
    vector<string>* comps = new vector<string>();
    for(int v = 0; v < graph.size(); v++)
    {
        if(visited[v] == false)
        {
            string s = gscc(v, visited);
            comps->push_back(s);
        }
    }
    return comps;
}

bool isConnected()
{
    vector<bool> visited(graph.size(), false);
    int cc = 0;
    for(int v = 0; v < graph.size(); v++)
    {
        if(visited[v] == false)
        {
            gscc(v, visited);
            cc++;
            if(cc > 1)
                return false;
        }
    }
    return true;
}

bool isCompCyclic(int src, vector<bool>& visited)
{
    queue<int> q;
    q.push(src);
    while(q.size() > 0)
    {
        int rem = q.front();
        q.pop();

        if(visited[rem])
            return true;
        visited[rem] = true;

        for(int e = 0; e < graph[rem].size(); e++)
        {
            Edge* ne = graph[rem][e];
            if(visited[ne->nbr] == false)
                q.push(ne->nbr);
        }
    }
    
    return false;
}

bool isCyclic()
{
    vector<bool> visited(graph.size(), false);
    for(int v = 0; v < graph.size(); v++)
    {
        if(visited[v] == false)
        {
            bool tres = isCompCyclic(v, visited);
            if(tres)
                return true;
        }
    }
    return false;
}

class BiPair
{
    public:
        int v;
        int l;

        BiPair(int v, int l)
        {
            this->v = v;
            this->l = l;
        }
};

bool isCompBipartite(int src, vector<int>& visited)
{
    queue<BiPair*> q;
    q.push(new BiPair(src, 0));
    while(q.size() > 0)
    {
        BiPair* rem = q.front();
        q.pop();

        if(visited[rem->v] != 0)
        {
            int ol = visited[rem->v];
            int nl = rem->l;
            if(ol % 2 != nl % 2)
                return false;
        }
        visited[rem->v] = rem->l;

        for(int e = 0; e < graph[rem->v].size(); e++)
        {
            Edge* ne = graph[rem->v][e];
            if(visited[ne->nbr] == 0)
                q.push(new BiPair(ne->nbr, rem->l + 1));
        }
    }

    return true;
}

bool isBipartite()
{
    vector<int> visited(graph.size(), 0);
    for(int v = 0; v < graph.size(); v++)
    {
        if(visited[v] == 0)
        {
            bool compres = isCompBipartite(v, visited);
            if(compres == false)
                return false;
        }
    }
    return true;
}

class Cstate
{
    public:
        int i;
        int j;
        int t;

        Cstate(int i, int j, int t)
        {
            this->i = i;
            this->j = j;
            this->t = t;
        }
};

void firestorm(vector<vector<int>>& city, int t)
{
    queue<Cstate*> q;
    for(int i = 0; i < city.size(); i++)
    {
        for(int j = 0; j < city[0].size(); j++)
        {
            if(city[i][j] == 1)
                q.push(new Cstate(i, j, 1));
        }
    }

    while(q.size() > 0)
    {
        Cstate* rem = q.front();
        q.pop();

        if(rem->i < 0 || rem->j < 0 || rem->i >= city.size() || rem->j >= city[0].size() ||
           city[rem->i][rem->j] == 2 || city[rem->i][rem->j] < 0)
             continue;

        // city[rem->i][rem->j] = 1;
        city[rem->i][rem->j] = -rem->t;

        if(rem->t == t + 1)
        {
            for(int i = 0; i < city.size(); i++)
            {
                for(int j = 0; j < city[0].size(); j++)
                    cout << city[i][j] << " ";
                cout << endl;
            }
            break;   
        }

        q.push(new Cstate(rem->i - 1, rem->j, rem->t + 1));
        q.push(new Cstate(rem->i + 1, rem->j, rem->t + 1));
        q.push(new Cstate(rem->i, rem->j - 1, rem->t + 1));
        q.push(new Cstate(rem->i, rem->j + 1, rem->t + 1));
    }
}


int main(int argc, char** argv)
{
    // int n = 7;
    // for(int i = 0; i < n; i++)
    //     graph.push_back(vector<Edge*>());

    // addEdge(0, 1, 10);
    // addEdge(1, 2, 10);
    // addEdge(2, 3, 10);
    // addEdge(0, 3, 40);
    // addEdge(3, 4, 2);
    // addEdge(4, 5, 3);
    // addEdge(5, 6, 3);
    // addEdge(4, 6, 8);
    // addEdge(2, 5, 5);

    // display();
    // cout << hasPathbfs(0, 6) << endl;
    // cout << gscc(0) << endl;
    // vector<string>* comps = gcc();
    // for(auto itr : *comps)
    //     cout << itr << endl;

    // cout << isConnected() << endl;
    // cout << isCyclic() << endl;
    // cout << isBipartite() << endl;

    vector<vector<int>> city {{1, 2, 0, 2, 0, 0, 0},
                              {0, 2, 0, 2, 0, 1, 0},
                              {0, 0, 0, 2, 0, 0, 0},
                              {2, 2, 2, 2, 2, 2, 0},
                              {0, 2, 1, 0, 0, 0, 0},
                              {0, 2, 0, 0, 0, 0, 0},
                              {0, 2, 0, 0, 0, 0, 0}};

    firestorm(city, 3);

}