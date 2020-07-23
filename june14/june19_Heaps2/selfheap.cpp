#include<iostream>
#include<vector>
using namespace std;

class heap{
    private:
        vector<int> list;
        bool type;//true for min
        void swap(int i,int j)
        {
            int ith=list[i];
            int jth=list[j];
            list[i]=jth;
            list[j]=ith;
        }
        bool ishp(int i,int j)
        {
            if(this->type==true)
                return list[i]<list[j];
            else
                return list[i]>list[j];
        }
        void upheapify(int i)
        {
            if(i==0)
                return;
            int pi=(i-1)/2;
            if(ishp(i,pi)){
                swap(i,pi);
                upheapify(pi);
            }
        }
        void downheapify(int i)
        {
            int li=2*i+1;
            int ri=2*i+2;
            int hpi=i;
            if(li<list.size() && ishp(li,hpi))
                hpi=li;
            if(ri<list.size() && ishp(ri,hpi))
                hpi=ri;

            if(hpi!=i)
            {
                swap(i,hpi);
                downheapify(hpi);
            }
        }
    public:
        heap(bool type)
        {
            this->type=type;
        }
        void push(int val)
        {
            list.push_back(val);
            upheapify(list.size()-1);
        }
        void pop()
        {
            swap(0,list.size()-1);
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
int main(int argc,char** argv)
{
    heap hp(false);
    hp.push(10);
    hp.push(2);
    hp.push(50);
    hp.push(15);
    hp.push(70);

    while(hp.size()>0)
    {
        int val=hp.top(); hp.pop();
        cout<< val<<" ";
    }
}