#include<iostream>
#include<vector>
#include<climits>

using namespace std;

int matrixChainMult(vector<int>& mat)
{
    vector<vector<int>> strg (mat.size() - 1, vector<int> (mat.size() - 1, 0));
    for(int gap = 1; gap < strg[0].size(); gap++)
    {
        for(int i = 0, j = i + gap; j < strg[0].size(); i++, j++)
        {
            if(gap == 1)
                strg[i][j] = mat[i] * mat[j] * mat[j + 1];
            else
            {
                int mymin = INT_MAX;
                for(int k = i; k < j; k++)
                {
                    int multcost = mat[i] * mat[k + 1] * mat[j + 1];
                    mymin = min(mymin, strg[i][k] + strg[k + 1][j] + multcost);
                }
                strg[i][j] = mymin;
            }
        }
    }
    return strg[0][strg[0].size() - 1];
}

int maxAmount(vector<int>& arr)
{
    //alternate turns to pick from corners of the pile of coins
    //first turn is ours
    vector<vector<int>> strg (arr.size(), vector<int> (arr.size() , 0));
    for(int gap = 1; gap < strg[0].size(); gap += 2)
    {
        for(int i = 0, j = i + gap; j < strg[0].size(); i++, j++)
        {
            if(gap == 1)
                strg[i][j] = max(arr[i], arr[j]);
            else
            {
                int f1 = min(strg[i][j - 2], strg[i + 1][j - 1]) + arr[j];
                int f2 = min(strg[i + 2][j], strg[i + 1][j - 1]) + arr[i];
                strg[i][j] = max(f1, f2);
            }
        }
    }
    return strg[0][strg[0].size() - 1];
}

int main(int argc, char** argv)
{
    vector<int> mat {10, 20, 30, 40, 50, 60};
    cout << matrixChainMult(mat) << endl;
    vector<int> arr {20, 30, 2, 2, 2, 10};
    cout << maxAmount(arr) << endl;
}