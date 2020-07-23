#include<iostream>
#include<vector>
#include<queue>
using namespace std;

void demo1()
{
    priority_queue<int> pq;
    // priority_queue<int ,vector<int>,less<int>> pq;
}
void demo2()
{
    priority_queue<int ,vector<int>,greater<int>> pq;
}

void pkle(int arr[],int n,int k)//print k largest elements
{//space = n , time = klogn
    priority_queue<int> pq;
    for(int i=0;i<n;i++)
    {
        pq.push(arr[i]);
    }
    for(int i=0;i<k;i++)
    {
        int val=pq.top(); pq.pop();
        cout << val << " ";
    }
}
void pkle2(int arr[],int n,int k)
{//space =k, time=nlogk
    priority_queue<int,vector<int>,greater<int>> pq;
        for(int i=0;i<k;i++)
            pq.push(arr[i]);
        for(int i=k;i<n;i++){
            if(arr[i]>pq.top()){
                pq.pop();
                pq.push(arr[i]);
            }
        }
        while(pq.size()>0){
            int val=pq.top(); pq.pop();
            cout << val << " ";
        }
}
void ksorted(vector<int>& kar,int k)
{
    priority_queue<int,vector<int>,greater<int>> pq;
        for(int i=0;i<=k;i++)
            pq.push(kar[i]);
        for(int i=k+1;i<kar.size();i++){
            int val=pq.top(); pq.pop();
            cout << val << " ";
            pq.push(kar[i]);
        }
        while(pq.size()>0){
            int val=pq.top(); pq.pop();
            cout << val <<" ";
        }
}
class Node
{
    public:
        int li;
        int di;
        int val;
        Node(int li,int di,int val){
            this->li=li;
            this->di=di;
            this->val=val;
        }
        bool operator<(const Node& o) const
        {
            return this->val<o.val;
        }
        bool operator>(const Node& o) const
        {
            return this->val>o.val;
        }
};
void mergekSortedLists(vector<vector<int>>& lists)
{
    priority_queue<Node,vector<Node>,greater<Node>> pq;
    for(int i=0;i<lists.size();i++)
    {
        pq.push(Node(i,0,lists[i][0]));
    }
    while(pq.size()>0)
    {
        Node n=pq.top(); pq.pop();
        cout << n.val << " ";
        n.di++;
        if(n.di<lists[n.li].size()){
            n.val=lists[n.li][n.di];
            pq.push(n);
        }
    }
}
int main(int argc,char** argv)
{
    // int ar[] {2,8,14,5,6,1,9,4,20,3,11};
    // pkle2(ar,11,4); 
    // vector<int> kar {20,40,10,30,50,80,60,90,70,120,110,100};
    // ksorted(kar,2);
    vector<vector<int>> lists {
        {2,5,9,18,20,25},
        {3,7,8,15,22},
        {0,4,6,12},
        {1,10,16,21,28}
    };
    mergekSortedLists(lists);
}