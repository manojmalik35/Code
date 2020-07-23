#include<iostream>
#include<vector>

using namespace std;

int lcs(string s1, string s2)//longest common subsequence
{
    vector<vector<int>> strg (s1.length() + 1, vector<int> (s2.length() + 1, 0));
    for(int i = strg.size() - 1; i >= 0; i--)
    {
        for(int j = strg[0].size() - 1; j >= 0; j--)
        {
            if(i == strg.size() - 1 || j == strg[0].size() - 1)
                strg[i][j] = 0;
            else
            {
                if(s1[i] == s2[j])
                    strg[i][j] = strg[i + 1][j + 1] + 1;
                else
                    strg[i][j] = max(strg[i + 1][j], strg[i][j + 1]);
            }
        }
    }

    return strg[0][0];
}

int lps(string s)//longest palindromic subsequence
{
    vector<vector<int>> strg (s.length(), vector<int> (s.length(), 0));
    for(int gap = 0; gap < strg[0].size(); gap++)
    {
        int i = 0;
        int j = i + gap;
        while(j < strg[0].size())
        {
            if(gap == 0)
                strg[i][j] = 1;
            else if(gap == 1)
                strg[i][j] = s[i] == s[j] ? 2 : 1;
            else
            {
                if(s[i] == s[j])
                    strg[i][j] = strg[i + 1][j - 1] + 2;
                else
                    strg[i][j] = max(strg[i][j - 1], strg[i + 1][j]);
            }
            i++;
            j++;
        }
    }

    return strg[0][strg.size() - 1];
}

int main(int argc, char** argv)
{
    // cout << lcs("abcd", "aebd") << endl;
    cout << lps("abckycbc") << endl;
}