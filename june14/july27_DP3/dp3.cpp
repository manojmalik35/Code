#include<iostream>
#include<vector>
#include<string>

using namespace std;

int countEncodings(vector<int>& arr)
{
    vector<int> strg (arr.size(), 0);
    if(arr[0] == 0)
        return 0;
    strg[0] = 1;
    strg[1] = arr[1] != 0 ? strg[0] : 0;

    int num = arr[0] * 10 + arr[1];
    if(num <= 26)
        strg[1] += 1;

    for(int i = 2; i < arr.size(); i++)
    {
        strg[i] = arr[i] != 0 ? strg[i - 1] : 0;
        int num2 = arr[i - 1] * 10 + arr[i];
        if(num2 >= 10 && num2 <= 26)
            strg[i] += strg[i - 2];
    }
    
    return strg[strg.size() - 1];
}

int countaibjcksubseq(string s)
{
    int ca = 0, cb = 0, cc = 0;
    for(int i = 0; i < s.length(); i++)
    {
        if(s[i] == 'a')
            ca = 2 * ca + 1;
        else if(s[i] == 'b')
            cb = ca + 2 * cb;
        else
            cc = cb + 2 * cc;
    }

    return cc;
}

int maxshareProfit(vector<int>& arr, int tc)
{
    int bp = 0;
    int sp = 0;
    bp = bp - arr[0];
    for(int i = 1; i < arr.size(); i++)
    {
        int osp = sp;
        int nsp = bp + arr[i] - tc;
        sp = max(sp, nsp);

        int nbp = osp - arr[i];
        bp = max(bp, nbp);
    }

    return sp;
}

int main(int argc, char** argv)
{

    // vector<int> arr {1, 2, 1, 3, 2, 6, 1, 2, 0, 1, 4};
    // cout << countEncodings(arr) << endl;
    // cout << countaibjcksubseq("abcabc") << endl;

    vector<int> share {9, 1, 3, 10, 1, 4, 6, 9};
    int tc = 2;
    cout << maxshareProfit(share, tc) << endl;
}