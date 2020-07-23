#include<iostream>
#include<vector>

using namespace std;

string s1 = "send";
string s2 = "more";
string s3 = "money";

int gnfs(string s, vector<int>& amap)
{
    int p = 1, ans = 0;
    for(int i = s.length() - 1; i >= 0; i--)
    {
        char c = s[i];
        int a = amap[c - 'a'];
        ans += a * p;
        p *= 10;
    }
    return ans;
}

int count = 1;
void crypto(string qs, vector<int>& amap, vector<bool>& dused)
{
    if(qs.length() == 0)
    {
        int nfs1 = gnfs(s1, amap);
        int nfs2 = gnfs(s2, amap);
        int nfs3 = gnfs(s3, amap);
        if(nfs1 + nfs2 == nfs3)
        {
            cout << "=========" << count << "============" << endl;
            cout << nfs1 << endl;
            cout << "+" << nfs2 << endl;
            cout << "--------" << endl;
            cout << nfs3 << endl;
            cout << "=========" << count << "============" << endl;
            count++;
        }
        return;
    }

    char ch = qs[0];
    string roq = qs.substr(1);
    for(int i = 0; i <= 9; i++)
    {
        if(amap[ch - 'a'] == -1 && dused[i] == false)
        {
            amap[ch - 'a'] = i;
            dused[i] = true;
            crypto(roq, amap, dused);
            amap[ch - 'a'] = -1;
            dused[i] = false;
        }
    }
}

int main(int argc, char** argv)
{
    string qs = "";
    bool cused[26] {false};
    for(int i = 0; i < s1.length(); i++)
    {
        char c = s1[i];
        if(cused[c - 'a'] == false)
        {
            cused[c - 'a'] = true;
            qs += c;
        }
    }

    for(int i = 0; i < s2.length(); i++)
    {
        char c = s2[i];
        if(cused[c - 'a'] == false)
        {
            cused[c - 'a'] = true;
            qs += c;
        }
    }

    for(int i = 0; i < s3.length(); i++)
    {
        char c = s3[i];
        if(cused[c - 'a'] == false)
        {
            cused[c - 'a'] = true;
            qs += c;
        }
    }

    vector<int> amap (26, -1);
    vector<bool> dused (10, false);
    crypto(qs, amap, dused);
    
}