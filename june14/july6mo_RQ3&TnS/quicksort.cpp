#include<iostream>

using namespace std;

int partioning(int* arr,int lo,int hi)
{
    int i=lo;
    int j=lo;
    while(j<=hi)
    {
        if(arr[j]>arr[hi])
            j++;
        else
        {
            int temp = arr[i];
            arr[i] = arr[j];   
            arr[j] = temp;
            i++;
            j++;
        }
    }
    return i-1;//pivot index
}

void quicksort(int* arr,int lo,int hi)
{
    if(lo>=hi)
        return;
    int pivot=partioning(arr,lo,hi);
    quicksort(arr,lo,pivot-1);
    quicksort(arr,pivot+1,hi);
}
int main(int argc,char** argv)
{
    int arr[8] = {6,2,1,5,4,3,9,7};
    quicksort(arr,0,7);
    for(int val:arr)
        cout << val << " ";
    cout << endl;
}