#include<iostream>
#include<string>
#include<vector>

using namespace std;

bool isthechoiceValid(string word)
{
    if(word[0]=='0')
        return 0;
    int n=stoi(word);
    if(n==0)
        return 0;
    if(n>26)
        return 0;
    return 1;
}
vector<string> encodings(string q)
{
    if(q.length()==0)
    {
        vector<string> bres;
        bres.push_back("");
        return bres;
    }
    vector<string> mres;
    for(int d=0;d<q.length();d++)
    {
        string s=q.substr(0,d+1);
        string roq=q.substr(d+1);
        if(isthechoiceValid(s))
        {
            vector<string> rres=encodings(roq);
            int t=stoi(s);
            char ch=(char)(t+'a'-1);
            for(int i=0;i<rres.size();i++)
                mres.push_back(ch+rres[i]);
        }
    }
    return mres;
}

// void encodings2(string q,string a)
// {
//     if(q.length()==0)
//     {
//         cout << a << endl;
//         return;
//     }
//     for(int d=0;d<q.length();d++)
//     {
//         string s=q.substr(0,d+1);
//         string roq=q.substr(d+1);
//         if(isthechoiceValid(s))
//         {
//             int t=stoi(s);
//             char ch=(char)(t+'a'-1);
//             encodings2(roq,a+ch);
//         }
//     }
// }
int main(int argc,char** argv)
{
    vector<string> res=encodings("1023");
    for(auto itr : res)
        cout << itr << endl;

    // cout << "-------------------" << endl;
    // encodings2("1023","");
}