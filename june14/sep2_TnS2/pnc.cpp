#include<iostream>
#include<vector>

using namespace std;

int count = 0;
int pncRecur(vector<vector<int>>& pcmap, int pno, vector<bool>& cused)
{
    if(pno == pcmap.size())
    {
        return 1;
    }

    int res = 0 ;
    cout << ++count << endl;
    for(int i = 0; i < pcmap[pno].size(); i++)
    {
        int cap = pcmap[pno][i];
        if(cused[cap] == false)
        {
            cused[cap] = true;
            int rres = pncRecur(pcmap, pno + 1, cused);
            res += rres;
            cused[cap] = false;
        }
    }
    return res;
}

int pncRecurMem(vector<vector<int>>& pcmap, int pno, int cused, vector<vector<int>>& strg)
{
    if(pno == pcmap.size())
    {
        return 1;
    }

    if(strg[pno][cused] != 0)
        return strg[pno][cused];

    int res = 0 ;
    cout << ++count << endl;
    for(int i = 0; i < pcmap[pno].size(); i++)
    {
        int cap = pcmap[pno][i];
        int bm = 1 << cap;
        if((cused & bm) == 0)
        {
            cused = cused ^ bm;
            int rres = pncRecurMem(pcmap, pno + 1, cused, strg);
            res += rres;
            cused = cused ^ bm;
        }
    }

    strg[pno][cused] = res;
    return res;
}

int main(int argc, char** argv)
{
    int caps = 5;
    vector<vector<int>> pcmap {{0, 2, 4},
                               {0, 1, 2, 3},
                               {1, 2, 3},
                               {0, 4}};

    // vector<bool> cused (caps, false);
    // int count = pncRecur(pcmap, 0, cused);
    int cused = 0;
    vector<vector<int>> strg(pcmap.size(), vector<int> (1 << caps));
    int count = pncRecurMem(pcmap, 0, cused, strg);
    cout << count << endl;
}