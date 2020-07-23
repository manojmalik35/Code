#include<iostream>
#include<string>
#include<vector>

using namespace std;

void tarSum(int* arr,int n,int sp,int tar,string asf)
{
    if(sp == n)
    {
        if(tar == 0)
            cout << asf << endl;
        return;
    }
    tarSum(arr,n,sp+1,tar-arr[sp],asf+to_string(arr[sp])+" ");
    tarSum(arr,n,sp+1,tar,asf);
}

void tarSum2(int* arr,int n,int sp,int tar,vector<int>& asf)
{
    if(sp == n)
    {
        if(tar == 0)
        {
            for(auto itr : asf)
                cout << itr << " ";
            cout << endl;
        }
        return;
    }

    asf.push_back(arr[sp]);
    tarSum2(arr,n,sp+1,tar-arr[sp],asf);
    asf.pop_back();
    tarSum2(arr,n,sp+1,tar,asf);
}

int main(int argc,char** argv)
{
    int arr[5] = {10,20,30,40,50};
    // tarSum(arr, 5, 0, 60, "");
    vector<int> asf;
    tarSum2(arr,5,0,60,asf);
}