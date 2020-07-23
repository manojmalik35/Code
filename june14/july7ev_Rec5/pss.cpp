#include<iostream>
#include<string>

using namespace std;

void pss(string q,string a)
{
    if(q.length()==0)
    {
        cout << a << endl;
        return;
    }
    char ch=q[0];
    string roq=q.substr(1);
    pss(roq,a+"-");
    pss(roq,a+ch);
}
int main(int argc,char** argv)
{
    pss("abc","");
}