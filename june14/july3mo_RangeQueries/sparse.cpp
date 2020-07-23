#include<iostream>
#include<vector>
#include<climits>
using namespace std;

vector<vector<int>> sparse;
vector<int> logs;
int getLog(int n)//gives a floor of log n
{
    int x=0;
    while((1<<x) <=n)
        x++;

    return x-1;
}
void build(vector<int>& arr)
{
    int r=arr.size();
    logs=vector<int>(r+1);//bcoz we also want the log of 16 bcoz
    //when we query 0 to 15 then n=16 so we need to find log16
    for(int i=1;i<logs.size();i++)
        logs[i]=getLog(i);
    int c=logs[r]+1;
    sparse=vector<vector<int>>(r,vector<int>(c,0));
    for(int j=0;j<c;j++)
    {
        for(int i=0;i<r;i++)
        {
            if(j==0)
                sparse[i][j]=arr[i];
            else
            {
                if(i+(1<<j-1) < r)
                    sparse[i][j]=min(sparse[i][j-1],sparse[i+(1<<j-1)][j-1]);   
            }
        }
    }
}
int query(int l,int r)
{
    int n=r-l+1;
    int log=logs[n];
    int segment = (1<<log);
    return min(sparse[l][log],sparse[r-segment+1][log]);
}
int trivialQuery(vector<int>& arr,int l,int r)
{
    int mymin=INT_MAX;
    for(int i=l;i<=r;i++)
        mymin=min(mymin,arr[i]);
    return mymin;
}
int main(int argc,char** argv)
{
    vector<int> vec{2,4,-1,6,8,-4,7,12,5,4,-4,3,20,-16,4,11};
    build(vec);
    cout << query(6,9) << " " << trivialQuery(vec,6,9) << endl;
    cout << query(2,9) << " " << trivialQuery(vec,2,9) << endl;
    cout << query(7,15) << " " << trivialQuery(vec,7,15) << endl;
    cout << query(3,12) << " " << trivialQuery(vec,3,12) << endl;
}