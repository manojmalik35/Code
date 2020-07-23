#include<iostream>
using namespace std;

int get(int* a,int* b,int n,int m,int i)
{
    if(i<n)
        return a[i];
    else
        return b[i-n];
}

void set(int* a,int* b,int n,int m,int i,int val)
{
    if(i<n)
        a[i] = val;
    else
        b[i-n] = val;
}

void myswap(int* a,int* b,int n,int m,int i,int j)
{
    int ith=get(a,b,n,m,i);
    int jth=get(a,b,n,m,j);
    set(a,b,n,m,i,jth);
    set(a,b,n,m,j,ith);
}
void downheapify(int* a,int* b,int n,int m,int idx,int last)
{   
    int li = 2 * idx + 1;
    int ri = 2 * idx + 2;

    int mi = idx;
    if(li <= last && get(a,b,n,m,li) > get(a,b,n,m,mi))
        mi = li;

    if(ri <= last && get(a,b,n,m,ri) > get(a,b,n,m,mi))
        mi = ri;
    
    if(mi!=idx)
    {
        myswap(a,b,n,m,idx,mi);
        downheapify(a,b,n,m,mi,last);
    }
}
void heapsort(int* a,int* b,int n,int m)
{
    int os=n+m;
    for(int i=os/2-1; i>=0 ; i--)
        downheapify(a,b,n,m,i,os-1); //construction

    for(int i=1;i<=os-1;i++)
    {
        myswap(a,b,n,m,0,os-i);
        downheapify(a,b,n,m,0,os-i-1);
    }
}
int main(int argc,char** argv)
{
    int a[] = {5,2,4,9,1};
    int b[] = {9,8,5,6,1,12,36,7,-5};
    heapsort(a,b,5,9);

    for(int i=0;i<5;i++)
        cout << a[i] << " ";
    cout << endl;

    for(int i=0;i<9;i++)
        cout << b[i] << " ";
    cout << endl;
}