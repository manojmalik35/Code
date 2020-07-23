#include<iostream>
#include<vector>
#include<string>
using namespace std;

string codes[10]={".","abc","def","ghi","jkln","no","pqrs","tu","vwxy","z"};
vector<string> gkpc(string s)
{
    if(s.length()==0)
    {
        vector<string> bres;
        bres.push_back("");
        return bres;
    }
    char ch=s[0];
    string ros=s.substr(1);
    vector<string> rres=gkpc(ros);
    vector<string> mres;
    int t=ch-'0';
    string code=codes[t];
    for(int i=0;i<code.length();i++)
    {
        char c=code[i];
        for(int j=0;j<rres.size();j++)
            mres.push_back(c+rres[j]);
    }
    return mres;
}
int main(int argc,char** argv)
{
    vector<string> res=gkpc("835");
    for(auto itr:res)
        cout << itr << endl;
    
}