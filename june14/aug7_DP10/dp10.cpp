#include<iostream>
#include<vector>
#include<climits>

using namespace std;

int optimalBST(vector<int>& arr, vector<int>& freq)
{
    //another method to find sumf
    // vector<int> fpsa (freq.size(), 0);
    // fpsa[0] = freq[0];
    // for(int i = 1; i < freq.size(); i++)//frequency prefix sum array
    //     fpsa[i] = fpsa[i - 1] + freq[i];

    vector<vector<int>> strg (arr.size(), vector<int> (arr.size(), 0));
    for(int gap = 0; gap < strg.size(); gap++)
    {
        for(int i = 0, j = i + gap; j < strg.size(); i++, j++)
        {
            if(gap == 0)
                strg[i][j] = freq[i];
            else if(gap == 1)
                strg[i][j] = min(freq[i] + (2 * freq[j]), 2 * freq[i] + freq[j]);
            else
            {
                int mymin = INT_MAX;
                int sumf = 0;
                for(int k = i - 1; k < j; k++)
                {
                    int left = k < 0 ? 0 :strg[i][k];
                    int right = k == j - 1 ? 0 : strg[k + 2][j];
                    mymin = min(mymin, left + right);
                    sumf += k == i - 1 ? 0 : freq[k];
                }
                // strg[i][j] = mymin + fpsa[j];
                // if(i > 0)
                //     strg[i][j] -= fpsa[i - 1];
                strg[i][j] = mymin + sumf + freq[j];
            }
        }
    }
    return strg[0][strg.size() - 1];
}

int minSquareCut(int l, int w, vector<vector<int>>& qb)
{
    if(l == 0 || w == 0)
        return 0;
    else if(l == w)
        return 1;

    if(qb[l][w] != 0)
        return qb[l][w];

    int res = INT_MAX;
    for(int x = 1; x <= min(l,w); x++)
    {
        int vp1 = minSquareCut(l- x, x, qb);//vertical part
        int vp2 = minSquareCut(l, w- x, qb);
        int vscore = vp1 + vp2;
    
        int hp1 = minSquareCut(x, w -x, qb);//horizontal
        int hp2 = minSquareCut(l - x, w, qb);
        int hscore = hp1 + hp2;
        
        res = min(res, min(hscore, vscore));
    }
    res++; //1 for our square of size x
    qb[l][w] = res;
    return res;
}

int main(int argc, char** argv)
{
    // vector<int> arr {10, 20, 30, 40, 50, 60, 70};
    // vector<int> freq {2, 1, 4, 1, 1, 3, 5};
    // cout << optimalBST(arr, freq) << endl;
    int l = 30, w = 36;
    vector<vector<int>> qb (l + 1, vector<int> (w + 1, 0));
    cout << minSquareCut(l, w, qb) << endl;
}