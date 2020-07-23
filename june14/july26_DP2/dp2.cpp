#include<iostream>
#include<vector>
#include<algorithm>
#include<climits>
#include<queue>

using namespace std;


void printminPaths(vector<vector<int>>& global, int i, int j, string asf)
{
    if(i == global.size() - 1 && j == global[0].size() - 1)
    {
        cout << asf << endl;
        return;
    }
    if(i == global.size() - 1)
        printminPaths(global, i, j + 1, asf + "H");
    else if(j == global[0].size() - 1)
        printminPaths(global, i + 1, j, asf + "V");
    else 
    // if(i + 1 < global.size() && j + 1 < global[0].size())
    {
        if(global[i][j + 1] == global[i + 1][j])
        {
            printminPaths(global, i + 1, j, asf + "V");
            printminPaths(global, i, j + 1, asf + "H");
        }
        else if(global[i][j + 1] < global[i + 1][j])
            printminPaths(global, i, j + 1, asf + "H");
        else
            printminPaths(global, i + 1, j, asf + "V");
    }
}

class PHelp
{
    public:
        int i;
        int j;
        string psf;

        PHelp(int i, int j, string psf)
        {
            this->i = i;
            this->j = j;
            this->psf = psf;
        }
};

void printminPathsbfs(vector<vector<int>>& g)
{   
    queue<PHelp> q;
    q.push(PHelp(0, 0, ""));
    // vector<vector<bool>> visited (g.size(), vector<bool> (g[0].size(), false));
    while(q.size() > 0)
    {
        PHelp rem = q.front();
        q.pop();

        //mark isliye ni kiya kyuki saare path chahiye

        if(rem.i == g.size() -1 && rem.j == g[0].size() - 1)
            cout << rem.psf << endl;
        else if(rem. i == g.size() - 1)
            q.push(PHelp(rem.i, rem.j + 1, rem.psf + "H"));
        else if(rem.j == g[0].size() - 1)
            q.push(PHelp(rem.i + 1, rem.j, rem.psf + "V"));
        else
        {
            if(g[rem.i][rem.j + 1] == g[rem.i + 1][rem.j])
            {
                q.push(PHelp(rem.i + 1, rem.j, rem.psf + "V"));
                q.push(PHelp(rem.i, rem.j + 1, rem.psf + "H"));
            }
            else if(g[rem.i][rem.j + 1] < g[rem.i + 1][rem.j])
                q.push(PHelp(rem.i, rem.j + 1, rem.psf + "H"));
            else
                q.push(PHelp(rem.i + 1, rem.j, rem.psf + "V"));
        }
    }
}

int minCost(vector<vector<int>>& local)
{
    vector<vector<int>> global (local.size(), vector<int> (local.size(), 0));
    for(int i = global.size() - 1; i >= 0; i--)
    {
        for(int j = global[0].size() - 1; j >=0; j--)
        {
            if(i == global.size() - 1 && j == global[0].size() - 1)
                global[i][j] = 0;
            else if(i == global.size() - 1)
                global[i][j] = global[i][j+1] + local[i][j];
            else if(j == global[0].size() - 1)
                global[i][j] = global[i + 1][j] + local[i][j];
            else
                global[i][j] = local[i][j] + min(global[i][j + 1], global[i + 1][j]);
        }
    }
    // printminPaths(global, 0, 0, "");
    printminPathsbfs(global);
    return global[0][0];
}

void printgoldPath(vector<vector<int>>& g, int mi, int j, string psf)
{
    if(j == g[0].size() - 1)
    {
        cout << psf << endl;
        return;
    }
    if(mi == 0)
    {
        if(g[mi][j + 1] > g[mi + 1][j + 1])
            printgoldPath(g, mi, j + 1, psf + "S");
        else if(g[mi + 1][j + 1] > g[mi][j + 1])
            printgoldPath(g, mi + 1, j + 1, psf + "D");
        else
        {
            printgoldPath(g, mi, j + 1, psf + "S");
            printgoldPath(g, mi + 1, j + 1, psf + "D");
        }
    }
    else if(mi == g.size() - 1)
    {
        if(g[mi][j + 1] > g[mi - 1][j + 1])
            printgoldPath(g, mi, j + 1, psf + "S");
        else if(g[mi - 1][j + 1] > g[mi][j + 1])
            printgoldPath(g, mi - 1, j + 1, psf + "U");
        else
        {
            printgoldPath(g, mi, j + 1, psf + "S");
            printgoldPath(g, mi - 1, j + 1, psf + "U");
        }
    }
    else
    {
        
    }
}

int goldMine(vector<vector<int>>& mine)
{
    vector<vector<int>> strg (mine.size(), vector<int> (mine.size(), 0));
    for(int j = mine[0].size() - 1; j >= 0; j--)
    {
        for(int i = 0; i < mine.size(); i++)
        {
            if(j == mine[0].size() - 1)
                strg[i][j] = mine[i][j];
            else if(i == 0)
                strg[i][j] = mine[i][j] + max(strg[i][j + 1], strg[i + 1][j + 1]);
            else if(i == mine.size() - 1)
                strg[i][j] = mine[i][j] + max(strg[i - 1][j + 1], strg[i][j + 1]);
            else
                strg[i][j] = mine[i][j] + max(strg[i - 1][j + 1],
                             max(strg[i][j + 1], strg[i + 1][j + 1]));
        }
    }

    int maxi = 0;
    for(int i = 1; i < strg.size(); i++)
    {
        if(strg[i][0] > strg[maxi][0])
            maxi = i;
    }

    return strg[maxi][0];
}

int bsolnwnc0(int n)//binary strings of length n with no consecutive 0s
{
    int c0 = 1;
    int c1 = 1;
    for(int j = 2; j <= n; j++)
    {
        int temp = c0;
        c0 = c1;
        c1 = temp + c1;
    }

    return c0 + c1;
}
int main(int argc, char** argv)
{
    // vector<vector<int>> local{
    //     {2,7,3,8,0,3,7},
    //     {4,0,1,2,4,3,4},
    //     {3,8,5,9,0,8,1},
    //     {1,6,0,4,5,5,2},
    //     {6,2,9,5,7,0,6},
    //     {0,8,7,9,6,3,0},
    //     {1,3,5,0,5,1,0}
    // };

    vector<vector<int>> local{
        {1, 5, 1, 0, 3},
        {1, 4, 0, 2, 3},
        {4, 0, 1, 3, 2},
        {2, 4, 0, 4, 1},
        {1, 2, 3, 2, 0},
    };
    // cout << minCost(local) << endl;
    // cout << goldMine(local) << endl;

    cout << bsolnwnc0(5) << endl;
}