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

string lexicographicallySmallestEquivalentString(string A, string B, string S)
{
    vector<int> par(26, 0);
    for(int i = 0; i < 26; i++)
        par[i] = i;

    for(int i = 0; i < A.length(); i++)
    {
        int p1 = findParent(A[i] - 'a', par);
        int p2 = findParent(B[i] - 'a', par);
        int minParent = min(p1, p2);
        par[p1] = minParent;
        par[p2] = minParent;
    }

    string ans = "";
    for(int i = 0; i < S.length(); i++)
    {
        int parent = findParent(S[i] - 'a', par);
        ans += (char)(parent + 'a');
    }

    return ans;
}