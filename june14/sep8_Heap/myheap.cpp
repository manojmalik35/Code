#include<iostream>
#include<vector>

using namespace std;

class myPQ
{
    private:
        vector<int> list;
        bool minormax;

        void swap(vector<int>& list, int i, int j)
        {
            int temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }

        bool isHigherPrio(int i, int j)
        {   
            if(minormax)//min
                return list[i] < list[j];
            else
                return list[i] > list[j];
        }

        void upheapify(int idx)
        {
            if(idx == 0)
                return;

            int pi = (idx - 1) / 2;
            if(isHigherPrio(idx, pi))
            {
                swap(list, idx, pi);
                upheapify(pi);
            }
        }

        void downheapify(int idx)
        {
            if(idx >= list.size())
                return;

            int lci = 2 * idx + 1;
            int rci = 2 * idx + 2;

            int hpi = idx;
            if(lci < list.size() && isHigherPrio(lci, hpi))
                hpi = lci;
            
            if(rci < list.size() && isHigherPrio(rci, hpi))
                hpi = rci;

            if(hpi != idx)
            {
                swap(list, idx, hpi);
                downheapify(hpi);
            }
        }

    public:
        myPQ(bool minormax)
        {
            this->minormax = minormax;
        }

        void push(int val)
        {
            list.push_back(val);
            upheapify(list.size() - 1);
        }

        void pop()
        {
            //mera swap
            swap(list, 0, list.size() - 1);

            //c++ vla swap
            // swap(list[0], list.back());
            list.pop_back();
            downheapify(0);
        }

        int top()
        {
            return list[0];
        }

        int size()
        {
            return list.size();
        }
};

int main(int argc, char** argv)
{
    myPQ pq(true);
    pq.push(25);
    pq.push(12);
    pq.push(2);
    pq.push(20);
    pq.push(35);
    while(pq.size() > 0)
    {
        cout << pq.top() << " ";
        pq.pop();
    }
}