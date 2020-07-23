#include<iostream>
#include<string>
#include<vector>
using namespace std;

vector<string> stairs(int n)
{
    if(n==0)
    {
        vector<string> bres;
        bres.push_back("");
        return bres;
    }
    vector<string> mres;
    vector<string> rres1=stairs(n-1);
    for(int i=0;i<rres1.size();i++)
        mres.push_back(rres1[i]+'1');
    if(n>=2)
    {
        vector<string> rres2=stairs(n-2);
        for(int i=0;i<rres2.size();i++)
            mres.push_back(rres2[i]+'2');
    }
    if(n>=3)
    {
        vector<string> rres3=stairs(n-3);
        for(int i=0;i<rres3.size();i++)
            mres.push_back(rres3[i]+'3');
    }

    return mres;
}

vector<string> stairs2(int n)
{
    if(n==0)
    {
        vector<string> bres;
        bres.push_back("");
        return bres;
    }
    if(n<0)
    {
        vector<string> bres;
        return bres;
    }
    vector<string> mres;
    for(int i=1;i<=3;i++)
    {   
        if(n>=i)//proactive
        {
            vector<string> rresi=stairs2(n-i);
            for(int j=0;j<rresi.size();j++)
                mres.push_back(to_string(i) + rresi[j]);
        }

    }
    return mres;
}

int main(int argc,char** argv)
{
    vector<string> res=stairs2(4);
    for(auto itr:res)
        cout << itr << endl;
}