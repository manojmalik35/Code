#include<iostream>
#include<vector>
#include<climits>

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
    for(int v = 0; v < graph.size(); v++)
    {
        cout << v <<  " -> ";
        for(int n = 0; n < graph[v].size(); n++)
        {
            Edge* ne = graph[v][n];
            ne->display();
        }
        cout << endl;
    }    
}

bool hasPath(int src, int des, vector<bool>& visited)
{
    if(src == des)
        return true;

    visited[src] = true;
    for(int n = 0; n < graph[src].size(); n++)
    {
        Edge* ne = graph[src][n];
        if(visited[ne->nbr] == false)
        {
            bool rres = hasPath(ne->nbr, des, visited);
            if(rres)
                return true;
        }
    }

    return false;
}

void printallPaths(int src, int des, string psf, vector<bool>& visited, int dsf)
{
    if(src == des)
    {
        psf += to_string(des);
        cout << psf << " @ " << dsf << endl;
        return;
    }

    visited[src] = true;
    for(int n = 0; n < graph[src].size(); n++)
    {
        Edge* ne = graph[src][n];
        if(visited[ne->nbr] == false)
            printallPaths(ne->nbr, des, psf + to_string(src), visited, dsf + ne->wt);
    }
    visited[src] = false; 
}

int mind = INT_MAX;
string minp = "";
int maxd = INT_MIN;
string maxp = "";

void printShortestnLongestPath(int src, int des, string asf, vector<bool>& visited, int dsf)
{   
    if(src == des)
    {
        asf += to_string(des);
        if(dsf < mind)
        {
            mind = dsf;
            minp = asf;
        }
        if(dsf > maxd)
        {
            maxd = dsf;
            maxp = asf;
        }
        return;
    }

    visited[src] = true;
    for(int n = 0; n < graph[src].size(); n++)
    {
        Edge* ne = graph[src][n];
        if(visited[ne->nbr] == false)
            printShortestnLongestPath(ne->nbr, des, asf + to_string(src), visited, dsf + ne->wt);
    }
    visited[src] = false;
}


int floor = INT_MIN;
string fpath = " No path";
int ceil = INT_MAX;
string cpath = " No path";
void printceilnfloorPath(int src, int des, vector<bool>& visited, string psf, int dsf, int factor)
{
    if(src == des)
    {
        psf += to_string(des);
        if(dsf < factor && dsf > floor)
        {
            floor = dsf;
            fpath = psf;
        }
        if(dsf > factor && dsf < ceil)
        {
            ceil = dsf;
            cpath = psf;
        }
        return;
    }

    visited[src] = true;

    for(int n = 0; n < graph[src].size(); n++)
    {
        Edge* ne = graph[src][n];
        if(visited[ne->nbr] == false)
            printceilnfloorPath(ne->nbr, des, visited, psf + to_string(src), dsf + ne->wt, factor);
    }

    visited[src] = false;
}

void hamiltonianPnC(int src, int osrc, vector<bool>& visited, string psf, int vc)
{
    if(vc == graph.size() - 1)
    {
        psf += to_string(src);
        for(int i = 0; i < graph[src].size(); i++)
        {
            Edge* ne = graph[src][i];
            if(ne->nbr == osrc)
            {
                cout << psf << " cycle" << endl;
                return;
            }
        }
        cout << psf << endl; 
        return;
    }

    visited[src] = true;

    for(int n = 0; n < graph[src].size(); n++)
    {
        Edge* ne = graph[src][n];
        if(visited[ne->nbr] == false)
            hamiltonianPnC(ne->nbr, osrc, visited, psf + to_string(src), vc + 1);
    }

    visited[src] = false;
}

int c = 0;
void knightsTour(vector<vector<int>>& board, int i, int j, int count)
{
    if(i < 0 || j < 0 || i >= board.size() || j >= board[0].size() || board[i][j] != 0)
        return;

    if(count == board.size() * board[0].size())
    {
        c++;
        board[i][j] = count;
        cout << "=========" << c << "=============" << endl;
        for(int r = 0; r < board.size(); r++)
        {
            for(int c = 0; c < board[0].size(); c++)
                cout << board[r][c] << " ";
            cout << endl;
        }
        cout << "=========" << c << "=============" << endl;
        board[i][j] = 0;
        return;
    }

    board[i][j] = count;
    knightsTour(board, i - 2, j + 1, count + 1);
    knightsTour(board, i - 1, j + 2, count + 1);
    knightsTour(board, i + 1, j + 2, count + 1);
    knightsTour(board, i + 2, j + 1, count + 1);
    knightsTour(board, i + 2, j - 1, count + 1);
    knightsTour(board, i + 1, j - 2, count + 1);
    knightsTour(board, i - 1, j - 2, count + 1);
    knightsTour(board, i - 2, j - 1, count + 1);
    board[i][j] = 0;
}

int main(int argc, char** argv)
{
    // int v = 7;
    // for(int i = 0; i < v; i++)
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
    // vector<bool> visited(graph.size(), false);
    // cout << hasPath(0, 6, visited) << endl;
    // printallPaths(0, 6, "", visited, 0);
    // printShortestnLongestPath(0, 6, "", visited, 0);
    // cout << minp << " @ " << mind << endl;
    // cout << maxp << " @ " << maxd << endl;
    // printceilnfloorPath(0, 6, visited, "", 0, 45);
    // cout << fpath << " @ " << floor << endl;
    // cout << cpath << " @ " << ceil << endl;
    // hamiltonianPnC(0, 0, visited, "", 0);
    vector<vector<int>> board(5, vector<int> (5, 0));
    knightsTour(board, 1, 3, 1);
}