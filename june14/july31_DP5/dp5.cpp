#include<iostream>
#include<vector>

using namespace std;

void pathzoKnap(vector<vector<int>>& strg, vector<int>& wts, vector<int>& prices, int i, int j, string psf)
{
    if(j == 0)
    {
        cout << psf << endl;
        return;
    }
    else if(i == 0)
    {
        psf += wts[0];
        cout << psf << endl;
        return;
    }
    int f1 = strg[i - 1][j];
    int f2 = strg[i - 1][j - wts[i]] + prices[i];
    if(f1 == strg[i][j] && f2 == strg[i][j])
    {
        pathzoKnap(strg, wts, prices, i - 1, j, psf);
        pathzoKnap(strg, wts, prices, i - 1, j - wts[i], psf + to_string(wts[i]) + " ");
    }
    else if(f1 == strg[i][j])
        pathzoKnap(strg, wts, prices, i - 1, j, psf);
    else
        pathzoKnap(strg, wts, prices, i - 1, j - wts[i], psf + to_string(wts[i]) + " ");
}

int zoknapsack(vector<int>& wts, vector<int>& prices, int cap)
{
    vector<vector<int>> strg (wts.size(), vector<int> (cap + 1, 0));
    for(int i = 0; i < strg.size(); i++)
    {
        for(int j = 0; j < strg[0].size(); j++)
        {
            if(j == 0)
                strg[i][j] = 0;
            else if(i == 0){
                if(j >= wts[i])
                    strg[i][j] = prices[i];
            }
            else
            {
                if(j < wts[i])
                    strg[i][j] = strg[i - 1][j];
                else
                    strg[i][j] = max(strg[i - 1][j], strg[i - 1][j - wts[i]] + prices[i]);   
            }
        }
    }

    pathzoKnap(strg, wts, prices, strg.size() - 1, strg[0].size() - 1, "");
    return strg[strg.size() - 1][strg[0].size() - 1];
}

void pathunboKnap(vector<int>& strg, vector<int>& wts, vector<int>& prices, int i, string psf)
{
    if(i == 0)
    {
        cout << psf << endl;
        return;
    }

    if(i < 0)
        return;

    // int count = 0; //for only one path
    for(int j = 0; j < wts.size(); j++)
    {
        int factor = strg[i - wts[j]] + prices[j];
        if(factor == strg[i])
        {
            // if(count == 0)
            // {
            //     count ++;
                pathunboKnap(strg, wts, prices, i - wts[j], psf + to_string(wts[j]) + " ");
            // }
        }
    }
}

int unboundedKnapsack(vector<int>& wts, vector<int>& prices, int cap)
{
    vector<int> strg (cap + 1, 0);
    strg[0] = 0;
    for(int i = 0; i < wts.size(); i++)
    {
        for(int j = 1; j < strg.size(); j++)
        {
            if(j >= wts[i])
                strg[j] = max(strg[j], strg[j - wts[i]] + prices[i]);
        }
    }

    pathunboKnap(strg, wts, prices, cap, "");
    return strg[cap];
}

int friendsPair(int n)
{
    vector<int> strg (n + 1, 0);
    strg[0] = 1;
    strg[1] = 1;
    for(int i = 2; i <=n; i++)
        strg[i] = strg[i - 1] + (i- 1)*strg[i - 2];

    return strg[n];
}

int tileArea(int m, int n)
{
    vector<int> strg (n + 1, 0);
    strg[0] = 0;
    if(n < m)
        return 1;
    strg[1] = 1;
    for(int i = 2; i <= n; i++)
        strg[i] = strg[i - 1] + strg[i - m];

    return strg[n];
}

int main(int argc, char** argv)
{
    vector<int> wts {2, 5, 1, 3, 4};
    vector<int> prices {15, 14, 10, 45, 30};
    int cap = 7;
    // cout << zoknapsack(wts, prices, cap) << endl;
    // cout << unboundedKnapsack(wts, prices, cap) << endl;
    // cout << friendsPair(5) << endl;
    cout << tileArea(2, 10) << endl;
}