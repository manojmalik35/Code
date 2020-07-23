#include<iostream>
#include<string>

using namespace std;

void pmzp1(int cr,int cc,int dr,int dc,string a)
{
    if(cr==dr && cc==dc)
    {
        cout << a << endl;
        return;
    }
    if(cr+1<=dr)
        pmzp1(cr+1,cc,dr,dc,a+"V");
    if(cc+1<=dc)
        pmzp1(cr,cc+1,dr,dc,a+"H");
}

void pmzp2(int cr,int cc,int dr,int dc,string a)
{
    if(cr==dr && cc==dc)
    {
        cout << a << endl;
        return;
    }

    for(int i=1;cr+i<=dr;i++)
        pmzp2(cr+i,cc,dr,dc,a+"V"+to_string(i));

    for(int i=1;cc+i<=dc;i++)
        pmzp2(cr,cc+i,dr,dc,a+"H"+to_string(i));

    for(int i=1;cr+i<=dr && cc+i<=dc;i++)
        pmzp2(cr+i,cc+i,dr,dc,a+"D"+to_string(i));
}
int main(int argc,char** argv)
{
    pmzp2(0,0,2,2,"");
}