#include<iostream>
#include<string>
#include<vector>

using namespace std;

vector<string> allpaths(int* arr,int n,int idx)
{
    if(idx==n-1)
    {
        vector<string> bres;
        // bres.push_back(to_string(idx)); // to print the last index as well
        bres.push_back("");
        return bres;
    }
    if(idx>n-1)
    {
        vector<string> bres;
        return bres;
    }
    int noj=arr[idx];
    vector<string> mres;
    for(int i=1;i<=noj;i++)
    {
        vector<string> rres=allpaths(arr,n,idx+i);
        for(int j=0;j<rres.size();j++)
            // mres.push_back(to_string(i)+" "+ rres[j]); //to print the jumps
            mres.push_back(to_string(idx)+" "+rres[j]); //to print the indexes
    }
    return mres;   
}


int main(int argc,char** argv)
{
    int arr[11] ={3,2,0,4,1,2,0,2,3,0,1};
    vector<string> res=allpaths(arr,11,0);
    for(auto itr:res)
        cout << itr << endl;
}