#include<iostream>
using namespace std;

int siz=0;
int* allInd(int arr[],int n,int sp,int csf,int key)
{
    if(sp==n)
    {
        int* arr=new int[csf];
        siz=csf;
        return arr;
    }

    int* res=NULL;
    if(arr[sp]==key)
    {
        res=allInd(arr,n,sp+1,csf+1,key);
        res[csf]=sp;
    }
    else
    {
        res=allInd(arr,n,sp+1,csf,key);
    }
    return res;
}
int main(int argc,char** argv)
{
    int arr[] ={2,4,5,8,1,8,6,8,7,9};
    int* res=allInd(arr,10,0,0,8);
    for(int i=0;i<siz;i++)
        cout << res[i] << " ";
}