#include<iostream>
#include<vector>
#include<climits>

using namespace std;

int countdistinctSubseq(string s)
{
    vector<int> strg (s.length(), 0);
    strg[0] = 2;
    vector<int> hasCome (26, 27);
    hasCome[s[0] - 'a'] = 0;
    for(int i = 1; i < strg.size(); i++)
    {
        strg[i] = 2 * strg[i - 1];
        char c = s[i];
        if(hasCome[c - 'a'] != 27)
        {
            int loci = hasCome[c - 'a'];//last occuring index
            int nts = loci - 1 >=0 ? strg[loci - 1] : 1;//no. to subtract
            strg[i] -= nts;
            hasCome[c - 'a'] = i;
        }   
        else
            hasCome[c - 'a'] = i;
    }
    return strg[strg.size() - 1];
}

int minpalindromicCut(string s)
{
    //if s is already palindrome then return 0
    vector<vector<int>> strg (s.length(), vector<int> (s.length(), 0));
    for(int gap = 1; gap < strg[0].size(); gap++)
    {
        for(int i = 0, j = i + gap; j < strg[0].size(); i++, j++)
        {
            if(gap == 1)
                strg[i][j] = s[i] == s[j] ? 0 : 1;
            else if(s[i] == s[j] && strg[i + 1][j - 1] == 0)
                strg[i][j] = 0;
            else
            {
                int mymin = INT_MAX;
                for(int k = i; k < j; k++) 
                    mymin = min(mymin, strg[i][k] + strg[k + 1][j]);
                strg[i][j] = mymin + 1;
            }
        }
    }

    return strg[0][strg[0].size() - 1];
}

int eggdrop(int e, int f)//e = eggs, f = floors
{
    vector<vector<int>> strg (e + 1, vector<int> (f + 1, 0));
    for(int i = 1; i < strg.size(); i++)
    {
        for(int j = 0; j < strg[0].size(); j++)
        {
            if(i == 1 || j == 0 || j == 1)
                strg[i][j] = j;
            else
            {
                int mymin = INT_MAX;
                for(int k = 0; k < j; k++)
                    mymin = min(mymin, max(strg[i - 1][k], strg[i][j- 1- k]));
                strg[i][j] = mymin + 1;
            }
        }
    }

    return strg[e][f];
}

int main(int argc, char** argv)
{
    // cout << minpalindromicCut("abcbbc") << endl;
    // cout << eggdrop(2, 10) << endl;
    cout << countdistinctSubseq("abcbcb") << endl;
}