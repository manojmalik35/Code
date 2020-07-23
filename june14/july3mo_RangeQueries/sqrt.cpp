#include<iostream>
#include<bits/stdc++.h>
using namespace std;


int* oa;
int* sa;
int sob;
int nob;

void build(int* arr,int n)
{
    oa=arr;
    sob=(int)(sqrt(n));
    nob=n/sob;
    sa=new int[nob]{0};

    for(int i=0;i<sob*nob;i++)
    {
        int bi=i/sob;
        sa[bi]+=oa[i];
    }
}
int query(int l,int r)
{
    int sum=0;
    int lbi=l/sob;
    int rbi=r/sob;
    //loop1
    for(int i=l;i/sob==lbi;i++)
        sum += oa[i];

    //loop2
    for(int bi=lbi+1;bi<=rbi-1;bi++)
        sum += sa[bi];

    //loop3
    for(int i=r;i/sob==rbi;i--)
        sum += oa[i];

    return sum;
}
void update(int idx,int val)
{
    int old=oa[idx];
    int delta=val-old;

    int bi=idx/sob;
    sa[bi]+=delta;

    oa[idx]=val;
}
int trivialQuery(int l,int r)
{
    int sum=0;
    for(int i=l;i<=r;i++)
        sum+=oa[i];
    return sum;
}
int main(int argc,char** argv)
{
    int* arr=new int[58]{0};
    for(int i=0;i<58;i++)
        arr[i]=rand()%100;
    // for(int i=0;i<58;i++)
    //     cout << arr[i] << " ";
    build(arr,58);
    cout << query(20, 45) << " " << trivialQuery(20, 45) << endl;
    cout << query(2, 40) << " " << trivialQuery(2, 40) << endl;
    cout << query(18, 57) << " " << trivialQuery(18, 57) << endl;
    cout << query(30, 42) << " " << trivialQuery(30, 42) << endl;
    update(25, 100);
    cout << "--------------" << endl;
    cout << query(20, 45) << " " << trivialQuery(20, 45) << endl;
    cout << query(2, 40) << " " << trivialQuery(2, 40) << endl;
    cout << query(18, 34) << " " << trivialQuery(18, 34) << endl;
    cout << query(30, 42) << " " << trivialQuery(30, 42) << endl;
}