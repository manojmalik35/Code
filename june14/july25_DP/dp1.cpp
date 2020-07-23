#include<iostream>
#include<vector>
#include<climits>

using namespace std;

int fib(int n, int qb[])
{
    if(n == 0 || n == 1)
        return n;

    if(qb[n] != 0)
        return qb[n];
    int fnm1 = fib(n - 1, qb);
    int fnm2 = fib(n - 2, qb);
    int fn = fnm1 + fnm2;

    qb[n] = fn;
    return fn;
}

int climbStairs(int n, int qb[])
{
    if(n == 0)
        return 1;
    
    if(n < 0)
        return 0;

    if(qb[n] != 0)
        return qb[n];
    int snm1 = climbStairs(n - 1, qb);
    int snm2 = climbStairs(n - 2, qb);
    int snm3 = climbStairs(n - 3, qb);
    int sn = snm1 + snm2 + snm3;
    
    qb[n] = sn;
    return sn;
}

//memoization
int minPaths(vector<int>& arr, int idx, int qb[])
{
    if(idx == arr.size() - 1)
        return 1;

    if(idx >= arr.size())
        return 0;

    if(qb[idx] != 0)
        return qb[idx];
    int count = 0;
    int noj = arr[idx];
    for(int i = 1; i <= noj; i++)
    {
        int rres = minPaths(arr, idx + i, qb);
        count += rres;//paths
    }

    qb[idx] = count;
    return count;
}

//tabulation
int countPaths(vector<int>& arr)
{
    vector<int> strg (arr.size(), 0);
    strg[strg.size() - 1] = 1;
    for(int i = strg.size() - 2; i >= 0; i--)
    {
        for(int j = 1; j <= arr[i] && j < arr.size(); j++)
            strg[i] += strg[i + j];
    }

    return strg[0];
}

//minJumps with tabulation
int minJumps(vector<int>& arr)
{
    vector<int> strg (arr.size(), 0);
    strg[strg.size() - 1] = 0;
    for(int i = strg.size() - 2; i >= 0; i--)
    {
        int mymin = strg.size();// or INT_MAX
        for(int j = 1; j <= arr[i] && j < arr.size(); j++)
            mymin = min(mymin, strg[i + j]);

        strg[i] = mymin + 1;
    }

    return strg[0];
}


int main(int argc, char** argv)
{
    int n = 7;
    // int arr[n + 1] {0};
    // cout << climbStairs(n, arr) << endl;
    vector<int> arr {4, 2, 0, 0, 2, 4, 6, 3, 1, 0, 1, 2, 3, 1, 1};
    // int qb[arr.size()] {0};
    // cout << minPaths(arr, 0, qb) << endl;
    cout << countPaths(arr) << endl;
    cout << minJumps(arr) << endl;
}