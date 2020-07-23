#include<iostream>
#include<vector>
#include<queue>

using namespace std;

class MedianPQ
{
    private:
        priority_queue<int> maxpq;
        priority_queue<int, vector<int>, greater<int>> minpq;

        void rebalance()
        {
            if(maxpq.size() - minpq.size() == 2)
            {
                int val = maxpq.top();
                maxpq.pop();
                minpq.push(val);
            }
            else if(minpq.size() - maxpq.size() == 2)
            {
                int val = minpq.top();
                minpq.pop();
                maxpq.push(val);
            }
        }

    public:
        void push(int val)
        {
            if(maxpq.size() > 0 && val < maxpq.top())
                maxpq.push(val);
            else
                minpq.push(val);

            rebalance();
        }

        void pop()
        {
            if(minpq.size() > maxpq.size())
                minpq.pop();
            else
                maxpq.pop();
        }

        int top()
        {
            if(minpq.size() > maxpq.size())
                return minpq.top();
            else
                return maxpq.top();
        }

        int size()
        {
            return maxpq.size() + minpq.size();
        }

        void statedisplay()
        {
            cout << "left -> ";
            while(maxpq.size() > 0)
            {
                int val = maxpq.top(); maxpq.pop();
                cout << val << " ";
            }
            cout << endl << "right -> ";
            while(minpq.size() > 0)
            {
                int val = minpq.top(); minpq.pop();
                cout << val << " ";
            }
            cout << endl;
        }
};

int main(int argc, char** argv)
{
    MedianPQ pq;
    pq.push(20);
    pq.push(5);
    pq.push(3);
    pq.push(17);
    pq.push(8);
    cout << pq.top() << endl;
    pq.push(9);
    pq.push(19);
    cout << pq.top() << endl;
    pq.push(11);
    cout << pq.top() << endl;
    pq.pop();
    pq.push(15);
    cout << pq.top() << endl;
    pq.pop();
    pq.push(77);
    pq.push(32);
    cout << pq.top() << endl;
    pq.pop();
    cout << pq.top() << endl;
    pq.pop();
    cout << pq.top() << endl;
    pq.pop();
    cout << pq.top() << endl;
    pq.pop();
    cout << pq.top() << endl;
    pq.pop();
    cout << pq.top() << endl;
    pq.pop();
    cout << pq.top() << endl;
    pq.pop();
    cout << pq.top() << endl;
    pq.pop();
    cout << pq.top() << endl;
    pq.pop();

}