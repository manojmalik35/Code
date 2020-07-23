#include<iostream>
#include<vector>
#include<queue>

using namespace std;

void printKsmallest(vector<int>& arr, int k)
{
    priority_queue<int> pq;
    for(int i = 0; i < k; i++)
        pq.push(arr[i]);

    for(int i = k; i < arr.size(); i++)
    {
        int val = pq.top();
        if(arr[i] < val)
        {
            pq.pop();
            pq.push(arr[i]);
        }
    }

    while(pq.size() > 0)
    {
        int val = pq.top();
        pq.pop();
        cout << val << " ";
    }
    cout << endl;
}

void ksortedArray(vector<int>& arr, int k)
{
    priority_queue<int, vector<int>, greater<int>> pq;
    for(int i = 0; i <= k; i++)
        pq.push(arr[i]);

    for(int i = k + 1; i < arr.size(); i++)
    {
        cout << pq.top() << " ";
        pq.pop();
        pq.push(arr[i]);
    }

    while(pq.size() > 0)
    {
        cout << pq.top() << " ";
        pq.pop();
    }
    cout << endl;
}

class myPair
{
    public:
        int li;//list index
        int di;//data index
        int val;//value

        myPair(int li, int di, int val)
        {
            this->li = li;
            this->di = di;
            this->val = val;
        }

        bool operator>(const myPair& other) const
        {
            return this->val > other.val;
        }
};

void mergekSortedLists(vector<vector<int>>& lists)
{
    priority_queue<myPair, vector<myPair>, greater<myPair>> pq;
    for(int k = 0; k < lists.size(); k++)
        pq.push(myPair(k, 0, lists[k][0]));

    while(pq.size() > 0)
    {
        myPair t = pq.top();
        pq.pop();

        cout << t.val << " ";
        if(t.di + 1 < lists[t.li].size())
            pq.push(myPair(t.li, t.di + 1, lists[t.li][t.di + 1]));
    }
}

int main(int argc, char** argv)
{
    // vector<int> arr {2, 19, 3, 18, 17, 6, 4, 12, 7, 15, 10};
    // printKsmallest(arr, 5);
    vector<int> ksorted {20, 40, 10, 30, 50, 80, 60, 90, 70, 120, 110, 100};
    ksortedArray(ksorted, 2);

    // vector<vector<int>> lists {{2, 9, 19, 25, 35},
    //                            {1, 5, 7, 12},
    //                            {6, 14, 49, 54, 66, 77},
    //                            {4, 12, 20}};
    // mergekSortedLists(lists);

}