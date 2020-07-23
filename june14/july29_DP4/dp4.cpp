#include<iostream>
#include<vector>

using namespace std;

void printSubsets(vector<int>& arr, vector<vector<bool>>& strg, int i, int j, string asf)
{
    if(j == 0)
    {
        cout << asf << endl;
        return;
    }
    if(i == 0)
    {
        asf += to_string(arr[i]) + " ";
        cout << asf << endl;
        return;
    }
    
    if(strg[i - 1][j] && strg[i - 1][j - arr[i]])
    {
        printSubsets(arr, strg, i - 1, j, asf);
        printSubsets(arr, strg, i - 1, j - arr[i], asf + to_string(arr[i]) + " ");
    }
    else if(strg[i - 1][j])
        printSubsets(arr, strg, i - 1, j, asf);
    else
        printSubsets(arr, strg, i - 1, j - arr[i], asf + to_string(arr[i]) + " ");
        
}

bool targetSumSubsets(vector<int>& arr, int tar)
{
    vector<vector<bool>> strg (arr.size(), vector<bool> (tar + 1, false));
    for(int i = 0; i < strg.size(); i++)
    {
        for(int j = 0; j < strg[0].size(); j++)
        {
            if(j == 0)
                strg[i][j] = true;
            else if(i == 0)
                strg[i][j] = j == arr[0];
            else if(arr[i] > j)
                strg[i][j] = strg[i - 1][j];
            else
               strg[i][j] = strg[i - 1][j] || strg[i - 1][j - arr[i]];
        }
    }
    
    printSubsets(arr, strg, strg.size() - 1, strg[0].size() - 1, "");
    return strg[strg.size() - 1][tar];
}

int countCoinChangePerm(vector<int>& coins, int amt)
{
    vector<int> strg (amt + 1);
    strg[0] = 1;
    for(int i = 1; i < strg.size(); i++)
    {
        for(int j = 0; j < coins.size(); j++)
        {
            if(i >= coins[j])
                strg[i] += strg[i - coins[j]];
        }
    }

    return strg[amt];
}

int countCoinChangeComb(vector<int>& coins, int amt)
{
    vector<int> strg (amt + 1);
    strg[0] = 1;
    for(int j = 0; j < coins.size(); j++)
    {
        for(int i = 1; i < strg.size(); i++)
        {
            if(i >= coins[j])
                strg[i] += strg[i - coins[j]];
        }
    }   
    
    return strg[amt];
}

int main(int argc, char** argv)
{
    vector<int> arr {3, 2, 5, 1, 8};
    int tar = 9;
    cout << targetSumSubsets(arr, tar) << endl;
    // vector<int> coins {2, 3, 5, 6};
    // cout << countCoinChangePerm(coins, 7) << endl;
    // cout << countCoinChangeComb(coins, 7) << endl;
}