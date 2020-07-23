#include<iostream>
#include<string>
#include<vector>

using namespace std;

vector<string> gmp(int sr,int sc,int dr,int dc)//get maze path
{
    if(sr == dr && sc == dc)
    {
        vector<string> bres;
        bres.push_back("");
        return bres;
    }
    else if(sr>dr || sc>dc)
    {
        vector<string> bres;
        return bres;
    }
    vector<string> mres;
    vector<string> rresv=gmp(sr+1,sc,dr,dc);
    vector<string> rresh=gmp(sr,sc+1,dr,dc);
    for(int i=0;i<rresv.size();i++)
        mres.push_back("V"+rresv[i]);
    
    for(int i=0;i<rresh.size();i++)
        mres.push_back("H"+rresh[i]);

    return mres;
}

vector<string> gmp2(int sr,int sc,int dr,int dc)//multimove
{
    if(sr == dr && sc == dc)
    {
        vector<string> bres;
        bres.push_back("");
        return bres;
    }
    // if(sr>dr || sc>dc)
    // {
    //     vector<string> bres;
    //     return bres;
    // }
    vector<string> mres;
    for(int j=1;sr+j<=dr;j++)//proactive
    // for(int j=1;j<=dr;j++)//reactive
    {
        vector<string> rresv=gmp2(sr+j,sc,dr,dc);
        for(int i=0;i<rresv.size();i++)
            mres.push_back("V"+to_string(j)+rresv[i]);
    }
    for(int j=1;sc+j<=dc;j++)
    {
        vector<string> rresv=gmp2(sr,sc+j,dr,dc);
        for(int i=0;i<rresv.size();i++)
            mres.push_back("H"+to_string(j)+rresv[i]);
    }
    for(int j=1;sr+j<=dr && sc+j<=dc;j++)
    {
        vector<string> rresv=gmp2(sr+j,sc+j,dr,dc);
        for(int i=0;i<rresv.size();i++)
            mres.push_back("D"+to_string(j)+rresv[i]);
    }
    return mres;
}
int main(int argc,char** argv)
{
    int count=0;
    vector<string> res=gmp2(0,0,2,2);
    for(auto itr:res)
    {
        cout << itr << endl;
        count++;
    }
    cout << count;
}