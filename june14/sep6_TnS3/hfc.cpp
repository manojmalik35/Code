#include<iostream>
#include<vector>

using namespace std;

char hfc(string s)
{
    int maxi = 0;
    vector<int> fmap (26, 0);
    for(int i = 0; i < s.length(); i++)
    {
        char c = s[i];
        fmap[c - 'a'] += 1;
        if(fmap[c - 'a'] > fmap[s[maxi] - 'a'])
            maxi = c - 'a';
    }

    char ans = maxi + 'a';
    return ans;
}

int main(int argc, char** argv)
{
    string s = "baaabbcc";
    cout << hfc(s) << endl;
}