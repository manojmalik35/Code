#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int findParent(int u, vector<int> &par)
{
    if (par[u] == u)
        return u;
    else
    {
        int p = findParent(par[u], par);
        par[u] = p;
        return p;
    }
}

void merge(int p1, int p2, vector<int> &par, vector<int> &size)
{
    if (size[p2] > size[p1])
    {
        par[p1] = p2;
        size[p2] += size[p1];
    }
    else
    {
        par[p2] = p1;
        size[p1] += size[p2];
    }
}

vector<int> findRedundantConnection(vector<vector<int>> &edges)
{
    vector<int> par(edges.size() + 1);
    vector<int> size(edges.size() + 1, 1);

    for (int i = 1; i < par.size(); i++)
        par[i] = i;

    for (int i = 0; i < edges.size(); i++)
    {
        int u = edges[i][0];
        int v = edges[i][1];

        int p1 = findParent(u, par);
        int p2 = findParent(v, par);

        if (p1 == p2)
            return edges[i];
        else
            merge(p1, p2, par, size);
    }

    return {};
}

int friendCircleNum(vector<vector<int>> &M)
{

    int nop = M.size();
    vector<int> size(nop, 1);
    vector<int> par(nop);
    for (int i = 0; i < nop; i++)
        par[i] = i;

    for (int i = 0; i < M.size(); i++)
    {
        for (int j = 0; j < M[0].size(); j++)
        {
            if (i != j && M[i][j] == 1)
            {
                int p1 = findParent(i, par);
                int p2 = findParent(j, par);

                if (p1 != p2)
                {
                    nop--;
                    merge(p1, p2, par, size);
                }
            }
        }
    }

    return nop;
}

string lexicographicallySmallestEquivalentString(string A, string B, string S)
{
    vector<int> par(26, 0);
    for (int i = 0; i < 26; i++)
        par[i] = i;

    for (int i = 0; i < A.length(); i++)
    {
        int p1 = findParent(A[i] - 'a', par);
        int p2 = findParent(B[i] - 'a', par);
        int minParent = min(p1, p2);
        par[p1] = minParent;
        par[p2] = minParent;
    }

    string ans = "";
    for (int i = 0; i < S.length(); i++)
    {
        int parent = findParent(S[i] - 'a', par);
        ans += (char)(parent + 'a');
    }

    return ans;
}

int minCostToSupplyWater_leet1168(int n, vector<int> &wells, vector<vector<int>> &pipes)
{

    for (int i = 0; i < wells.size(); i++)
    {
        int cost = wells[i];
        pipes.push_back({0, i + 1, cost});
    }

    sort(pipes.begin(), pipes.end(), [](vector<int> &a, vector<int> &b) {
        return a[2] < b[2];
    });

    vector<int> size(n + 1, 1);
    vector<int> par(n + 1);
    for (int i = 0; i < par.size(); i++)
        par[i] = i;

    int ans = 0;
    for (int i = 0; i < pipes.size(); i++)
    {
        vector<int> pipe = pipes[i];

        int p1 = findParent(pipe[0], par);
        int p2 = findParent(pipe[1], par);

        if (p1 != p2)
        {
            merge(p1, p2, par, size);
            ans += pipe[2];
        }
    }

    return ans;
}

//hackerearth
int mr_president(int n, unsigned long long int k, vector<vector<int>> &roads)
{
    vector<int> size(n + 1, 1);
    vector<int> par(n + 1, 0);
    for (int i = 1; i < par.size(); i++)
        par[i] = i;

    sort(roads.begin(), roads.end(), [](vector<int> a, vector<int> b) {
        return a[2] < b[2];
    });

    long long int sum = 0;
    vector<int> myGraph;
    for (vector<int> road : roads)
    {
        int p1 = findParent(road[0], par);
        int p2 = findParent(road[1], par);

        if (p1 != p2)
        {
            merge(p1, p2, par, size);
            myGraph.push_back(road[2]);
            sum += road[2];
        }
    }

    int compCount = 0;
    for(int i = 1; i < par.size(); i++)
    {
        if(par[i] == i)
            compCount++;
    }
    
    if(compCount > 1)
        return -1;


    sort(myGraph.rbegin(), myGraph.rend());

    int countOfTransformations = 0;
    for (int cost : myGraph)
    {
        cout << sum << "\n";
        if (sum < k)
            break;
        sum -= cost + 1;
        countOfTransformations++;
    }

    myGraph.clear();
    return sum < k ? countOfTransformations : -1;
}

void mrpresident()
{
    int n, m;
    unsigned long long int k;
    cin >> n >> m >> k;
    vector<vector<int>> roads;
    for (int i = 0; i < m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        vector<int> ar{u, v, w};
        roads.push_back(ar);
    }

    cout << mr_president(n, k, roads) << "\n";
}

//hackerrank
long long int journeyToMoon()
{
    int n, p;
    cin >> n >> p;
    vector<vector<int>> astronaut;
    for(int i = 0; i < p; i++)
    {
        int a, b;
        cin >> a >> b;
        vector<int> ar {a, b};
        astronaut.push_back(ar);
    }
    vector<int> size(n, 1);
    vector<int> par(n);
    for(int i = 0; i < n; i++)
        par[i] = i;
    
    for(vector<int> p : astronaut)
    {
        int one = p[0];
        int two = p[1];

        int p1 = findParent(one, par);
        int p2 = findParent(two, par);

        if(p1 != p2)
            merge(p1, p2, par, size);
    }

    vector<int> cs;//component sizes
    int sum = 0;
    for(int i = 0; i < par.size(); i++)
    {
        if(par[i] == i)
        {
            cs.push_back(size[i]);
            sum += size[i];
        }
    }

    long long int ans = 0;
    for(int i = 0; i < cs.size(); i++){
        int ele = cs[i];
        ans += ele * (sum - ele);
        sum -= ele;
    }

    return ans;
}

int main()
{
    // vector<int> par;
    // vector<int> size;
    // cout << lexicographicallySmallestEquivalentString("parker", "morris", "parser") << endl;
    // cout << lexicographicallySmallestEquivalentString("leetcode", "programs", "sourcecode") << endl;

    // int n = 5;
    // vector<int> wells {1, 2, 2, 3, 2};
    // vector<vector<int>> pipes {{1, 2, 1}, {2, 3, 1}, {4, 5, 7}};
    // cout << minCostToSupplyWater_leet1168(n, wells, pipes) << endl;
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cout.tie(NULL);

    // mrpresident();
    cout << journeyToMoon() << "\n";
    return 0;
}