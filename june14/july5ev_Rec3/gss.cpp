#include<iostream>
#include<string>
#include<vector>
using namespace std;

vector<string> gss(string s)
{
    if(s.length()==0){
        vector<string> bres;
        bres.push_back("");
        return bres;
    }
    char ch=s[0];
    string ros=s.substr(1);
    vector<string> rres=gss(ros);
    vector<string> mres;
    for(int i=0;i<rres.size();i++)
    {
        mres.push_back("-"+rres[i]);
        mres.push_back(ch+rres[i]);
    }
    return mres;
}

int main(int argc,char** argv)
{
    vector<string> res=gss("abc");
    for(auto itr:res)
        cout << itr << endl;
    cout << endl;
}