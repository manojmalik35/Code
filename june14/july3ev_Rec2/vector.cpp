#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

void printRotations(vector<int> list,int k,int pti)
{
    // int pti=0;
    // for(int i=0;i<k;i++)
    // {
    //     int t=list.back();
    //     list.pop_back();
    //     list.insert(list.begin()+pti,t);
    //     pti++;
    //     for(auto itr:list)
    //         cout << itr << " ";
    //     cout<<endl;
    // }
    if(k==0)
            return;
    int t=list.back();
    list.pop_back();
    list.insert(list.begin()+pti,t);
    for(auto itr:list)
        cout << itr << " ";
    cout << endl;
    printRotations(list, k-1,pti+1);
}

void removeDuplicates(vector<int>& list,int ep)
{
    if(ep<0)
        return;
    if(list[ep]==list[ep+1])
        list.erase(list.begin()+ep+1);
    removeDuplicates(list, ep-1);
}
void sumofDuplicates(vector<int>& list)
{
    int sum=0;
    for(int i=list.size()-2;i>=0;i--)
    {
        if(list[i]==list[i+1])
        {
            int val=list[i+1];
            sum += val;
            list.erase(list.begin()+i+1);
        }
        else
        {
            int val=list[i+1];
            val += sum;
            list[i+1] = val;
            sum=0;
        }
    }
    int val=list[0];
    val += sum;
    list[0] = val;
}
int main(int argc,char** argv)
{
    vector<int> list {10,20,30,40,50,60,70,80};
    printRotations(list,3,0);
    // vector<int> list {2,2,2,3,3,2,2,2,5,6,6,7,7,7,8,8};
    // removeDuplicates(list,list.size()-2);
    // sumofDuplicates(list);
    // for(auto itr: list)
    //     cout << itr << " ";
    // cout << endl;
}