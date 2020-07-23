#include<iostream>
#include<string>

using namespace std;

void psp1(int n,string a)
{
    if(n==0)
    {
        cout << a << endl;
        return;
    }
    // if(n<0) //reactive
    //     return;
    psp1(n-1,a+'1');
    if(n>=2) // proactive
        psp1(n-2,a+'2');
    if(n>=3)
        psp1(n-3,a+'3');
}

void psp2(int* arr,int n,int idx,string a)
{
    if(idx==n-1)
    {
        cout << a << endl;
        return;
    }
    // if(idx>n-1)
    //     return;
    int noj=arr[idx];
    for(int i=1;i<=noj;i++)
    {
        if(idx+i<=n-1)
            psp2(arr,n,idx+i,a+" "+to_string(i));
    }
}
int main(int argc,char** argv)
{
    // psp1(4,"");
    int arr[11] ={3,2,0,4,1,2,0,2,3,0,1};
    psp2(arr,11,0,"");
}