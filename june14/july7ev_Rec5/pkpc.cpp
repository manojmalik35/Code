#include<iostream>
#include<string>

using namespace std;

string codes[10]={".","abc","def","ghi","jkln","no","pqrs","tu","vwxy","z"};

void pkpc(string q,string a)
{
    if(q.length()==0)
    {
        cout << a << endl;
        return;
    }
    char ch=q[0];
    string roq=q.substr(1);
    string code=codes[ch-'0'];
    for(int i=0;i<code.length();i++)
        pkpc(roq,a+code[i]);
}
int main(int argc,char** argv)
{
    pkpc("835","");
}