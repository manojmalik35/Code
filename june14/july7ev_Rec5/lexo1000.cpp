#include<iostream>
#include<string>
#include<queue>

using namespace std;


void lexo(int sp,int n)
{
    if(sp>n)
        return;
    cout << sp << " ";
    if((sp*10)<=n)
        lexo(sp*10,n);
    if((sp+1)%10!=0)
        lexo(sp+1,n);
}

void lexo2(int n)
{
    priority_queue<string, vector<string>,greater<string>> pq;
    for(int i=1;i<=n;i++)
        pq.push(to_string(i));

    while(pq.size()>0)
    {
        string val=pq.top(); pq.pop();
        cout << val << " ";
    }
}
int main(int argc,char** argv)
{
    lexo(1,1000);
    // lexo2(1000);
}