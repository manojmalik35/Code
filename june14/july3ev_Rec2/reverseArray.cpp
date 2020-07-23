#include<iostream>
using namespace std;

void reverseRecursively(int arr[],int l,int r)
{
    if(l>=r)
        return;
    int temp=arr[l];
    arr[l]=arr[r];
    arr[r]=temp;
    reverseRecursively(arr,l+1,r-1);
}
int main(int argc,char** argv)
{
    int arr[7]={2,5,6,7,8,9,4};
    reverseRecursively(arr,0,6);
    for(int i=0;i<7;i++)
        cout << arr[i] << " ";
}