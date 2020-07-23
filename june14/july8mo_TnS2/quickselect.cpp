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

int quickselect(int* arr,int k,int lo,int hi)
{
    int pi=partioning(arr,lo,hi);
    if(pi==k-1)
        return arr[pi];
    else if(k>pi+1)
        return quickselect(arr,k,pi+1,hi);
    else
        return quickselect(arr,k,lo,pi-1);
}

int main(int argc,char** argv)
{
    int arr[8] = {9,8,3,1,7,5,2,6};
    for(int i=1;i<=8;i++)
        cout << quickselect(arr,i,0,7) << endl;
}
