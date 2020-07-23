#include<iostream>
using namespace std;

int fact(int n)
{
    if(n==0)
        return 1;
    int rres=fact(n-1);
    return rres*n;
}
int power(int x,int n)
{
    if(n==0)
        return 1;
    int rres=power(x,n/2);
    if(n%2==0)
        return rres*rres;
    else
        return rres*rres*x;
}
int main(int argc,char** argv)
{
    cout << fact(6) << endl;
    cout << power(2,5);
}