#include<iostream>
using namespace std;

void pd(int n)
{
    if(n==0)
        return;
    cout<<n<<" ";
    pd(n-1);
}
void pi(int n)
{
    if(n==0)
        return;
    pi(n-1);
    cout<<n<<" ";
}
void pdi(int n)
{
    if(n==0)
        return;
    cout << n << " ";
    pdi(n-1);
    cout << n << " ";
}
int main(int argc,char** argv)
{
    pd(5);
    cout<<endl;
    pi(5);
    cout<<endl;
    pdi(5);
}