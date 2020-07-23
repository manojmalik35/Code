#include<iostream>
#include<queue>
using namespace std;
class MedianPQ
{
    private:
        void rebalance()
        {
            if(left.size()-right.size() == 2)
            {
                int val=left.top(); left.pop();
                right.push(val);
            }
            else if(left.size()-right.size() == -2)
            {
                int val=right.top(); right.pop();
                left.push(val);
            }
        }
    public:
        priority_queue<int> left;
        priority_queue<int,vector<int>,greater<int>> right;

        void push(int val)
        {
            if(left.size()>0 && val<left.top())
                left.push(val);
            else
                right.push(val);
            rebalance();
        }
        int top()
        {
            if(left.size()>right.size())
                return left.top();
            else
                return right.top();
        }
        void pop()
        {
            if(left.size()>right.size())
                left.pop();
            else
                right.pop();
        }
        int size()
        {
            return left.size()+right.size();
        }
};
int main(int argc,char** argv)
{
    MedianPQ pq;
    vector<int> test {50,10,80,0,20,90,100,5,70,40};
    for(int i=0;i<test.size();i++)
    {
        pq.push(test[i]);
        cout << pq.top() <<" ";
    }
    cout<< endl;
    while(pq.size()>0)
    {
        int val=pq.top(); pq.pop();
        cout << val << " ";
    }
    
}