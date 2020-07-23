#include<iostream>
using namespace std;

void downheapify(int* arr,int idx,int last)
{   
    int li = 2 * idx + 1;
    int ri = 2 * idx + 2;

    int mi = idx;
    if(li <= last && arr[li] > arr[mi])
        mi = li;

    if(ri <= last && arr[ri] > arr[mi])
        mi = ri;
    
    if(mi!=idx)
    {
        swap(arr[idx] , arr[mi]);
        downheapify(arr,mi,last);
    }
}
void heapsort(int* arr,int n)
{
    for(int i=n/2-1; i>=0 ; i--)
        downheapify(arr,i,n-1); //construction

    for(int i=1;i<=n-1;i++)
    {
        swap(arr[0],arr[n-i]);
        downheapify(arr,0,n-i-1);
    }
}
int main(int argc,char** argv)
{
    int arr[10] = {25,36,89,12,0,2,3,-8,-15,-78};
    heapsort(arr,10);
    for(int i=0;i<10;i++)
        cout << arr[i] << " ";
    cout << endl;
}