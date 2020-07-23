#include<iostream>

using namespace std;

void mergetwosortedarrays(int* arr,int lo,int mid,int hi)
{
    int* aux=new int[hi-lo+1];
    int i=lo;
    int j=mid+1;
    int k=0;
    while(i<=mid && j<=hi)
    {
        if(arr[i] < arr[j])
            aux[k++] = arr[i++];
        else
            aux[k++] = arr[j++];
    }

    while(i<=mid)
        aux[k++] = arr[i++];

    while(j<=hi)
        aux[k++] = arr[j++];
    
    for(int i=0;i<hi-lo+1;i++)
        arr[lo+i] = aux[i];
}
void mergesort(int* arr,int lo,int hi)
{
    if(lo==hi)
        return;
    int mid=(lo+hi)/2;
    mergesort(arr,lo,mid);
    mergesort(arr,mid+1,hi);
    mergetwosortedarrays(arr,lo,mid,hi);
}

int main(int argc,char** argv)
{
    int arr[8]{2,9,6,1,4,3,7,5};
    mergesort(arr,0,7);
    for(int val:arr)
        cout << val << " ";
    cout << endl;
}