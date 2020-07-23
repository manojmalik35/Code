#include<iostream>
#include<vector>

using namespace std;

int cps(string s)//count palindromic subsequence
{
    vector<vector<int>> strg (s.length(), vector<int> (s.length(), 0));
    for(int gap = 0; gap < strg[0].size(); gap++)
    {
        for(int i = 0, j = i + gap; j < strg[0].size(); i++, j++)
        {
            if(gap == 0)
                strg[i][j] = 1;
            else if(gap == 1)
                strg[i][j] = s[i] == s[j] ? 3 : 2;
            else
            {
                if(s[i] == s[j])
                    strg[i][j] = strg[i][j - 1] + strg[i + 1][j] + 1;
                else
                    strg[i][j] = strg[i][j - 1] + strg[i + 1][j] - strg[i + 1][j - 1];
            }
        }
    }

    return strg[0][strg[0].size() - 1];
}

void clpss(string s)
//count of palindromic substrings and length of longest palindromic substring
{
    vector<vector<bool>> strg (s.length(), vector<bool> (s.length(), false));
    int count = 0;
    int length = 0;
    for(int gap = 0; gap < strg[0].size(); gap++)
    {
        for(int i = 0, j = i + gap; j < strg[0].size(); i++, j++)
        {
            if(gap == 0)
                strg[i][j] = true;
            else if(gap == 1)
                strg[i][j] = s[i] == s[j];
            else
                strg[i][j] = s[i] == s[j] && strg[i + 1][j - 1];

            if(strg[i][j])
            {
                // cout << s.substr(i, j - i + 1) << endl;
                count++;
                length = gap + 1;
            }
        }
    }
    cout << count << " " << length << endl;
}

int cosubdbn(string s, int n)//count of subsequences divisible by n
{
    vector<vector<int>> strg (s.length(), vector<int> (n, 0));
    strg[0][0] = 1;
    int ij = s[0] - '0';
    strg[0][ij % n] += 1;
    for(int i = 1; i < strg.size(); i++)
    {
        int t = s[i] - '0';
        for(int j = 0; j < strg[0].size(); j++)
        {
            strg[i][j] += strg[i - 1][j];
            int temp = j * 10 + t;
            temp = temp % n;
            strg[i][temp] += strg[i - 1][j];
        }
    }

    return strg[strg.size() - 1][0];
}

int main(int argc, char** argv)
{
    // cout << cps("abckycbc") << endl;
    // clpss("abccbc");
    cout << cosubdbn("235168", 6) << endl;
}