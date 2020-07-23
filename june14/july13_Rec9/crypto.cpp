#include<iostream>
#include<string>
#include<vector>

using namespace std;

string s1="send";
string s2="more";
string s3="money";
int counter=0;

int getnumber(string s,vector<int>& amap)
{
    int ans=0,p=1;
    for(int i=s.length()-1;i>=0;i--)
    {
        char ch=s[i];
        int t = amap[ch -'a'];
        ans += t * p;
        p = p * 10;
    }
    return ans;
}
void crypto(string ques,vector<int>& amap,vector<bool>& dused)
{
    if(ques.length() == 0)
    {
        int n1=getnumber(s1,amap);
        int n2=getnumber(s2,amap);
        int n3=getnumber(s3,amap);
        // if(n1 + n2 == n3)
        // {   
        //     counter++;
        //     cout << "************" << counter << "*************" << endl;
        //     for(auto itr : amap)
        //         cout << itr << " ";
        //     cout << endl;
        // }
        if(n1 + n2 == n3)
        {
            counter++;
            cout << "***********" << counter << "***********" << endl;
            cout << n1 << endl;
            cout << "+" << n2 << endl;
            cout << "=" << n3 << endl;
        }
        return;
    }
    char ch=ques[0];
    string ros=ques.substr(1);
    for(int i=0;i<10;i++)
    {
        if(dused[i] == false)
        {
            dused[i] = true;
            amap[ch - 'a'] = i;
            crypto(ros,amap,dused);
            amap[ch - 'a'] = -1;
            dused[i] = false;
        }
    }
}
int main(int argc,char** argv)
{
    bool cused[26] {false};
    string qs="";
    for(int i=0;i<s1.length();i++)
    {
        char ch = s1[i];
        if(cused[ch - 'a'] == false)
        {
            cused[ch - 'a'] = true;
            qs += ch;
        }
    }

    for(int i=0;i<s2.length();i++)
    {
        char ch = s2[i];
        if(cused[ch - 'a'] == false)
        {
            cused[ch - 'a'] = true;
            qs += ch;
        }
    }

    for(int i=0;i<s3.length();i++)
    {
        char ch = s3[i];
        if(cused[ch - 'a'] == false)
        {
            cused[ch - 'a'] = true;
            qs += ch;
        }
    }

    vector<int> amap (26,-1);
    vector<bool> dused (10,false);
    crypto(qs,amap,dused);
}