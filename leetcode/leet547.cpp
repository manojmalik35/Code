#include<iostream>
#include<vector>

using namespace std;

int findParent(int u, vector<int>& par)
{
    if(par[u] == u)
        return u;
    else{
        int p = findParent(par[u], par);
        par[u] = p;
        return p;
    }
}

void merge(int p1, int p2, vector<int>& par, vector<int>& size)
{
    if(size[p1] > size[p2])
    {
        par[p2] = p1;
        size[p1] += size[p2];
    }else
    {
        par[p1] = p2;
        size[p2] += size[p1];
    }
}

int friendCircleNum(vector<vector<int>>& M) {

    int nop = M.size();
    vector<int> size(nop, 1);
    vector<int> par(nop);
    for(int i = 0; i < nop; i++)
        par[i] = i;

    for(int i = 0; i < M.size(); i++)
    {
        for(int j = 0; j < M[0].size(); j++)
        {
            if(i != j && M[i][j] == 1)
            {
                int p1 = findParent(i, par);
                int p2 = findParent(j, par);
                
                if(p1 != p2){
                    nop--;
                    merge(p1, p2, par, size);
                }
            }
        }
    }

    return nop;
}

int main()
{
    return 0;
}