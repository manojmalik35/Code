#include<iostream>

using namespace std;

class kstack
{
    private:
        int* da;
        int* na;
        int* topa;
        int free;

    public:
        kstack(int k, int n)
        {
            da = new int[n];
            na = new int[n];
            topa = new int[k];
            free = 0;

            for(int i = 0; i < n - 1; i++)
                na[i] = i + 1;
            na[n - 1] = -1;

            for(int i = 0; i < k; i++)
                topa[i] = -1;
        }

        bool isFull()
        {
            return free == -1;
        }

        bool isEmpty(int k)
        {
            return topa[k] == -1;
        }

        void push(int k, int val)
        {
            if(isFull())
            {
                cout << "Stack is full" << endl;
                return;
            }

            int ofree = free;
            int nfree = na[free];
            free = nfree;
            da[ofree] = val;
            na[ofree] = topa[k];
            topa[k] = ofree;
        }

        void pop(int k)
        {
            if(isEmpty(k))
            {
                cout << "Stack is empty" << endl;
                return;
            }

            int top = topa[k];
            topa[k] = na[top];
            na[top] = free;
            free = top;
            da[free] = 0;
        }

        int top(int k)
        {
            if(isEmpty(k))
            {
                cout << "Stack is empty" << endl;
                return 0;
            }

            return da[topa[k]];
        }
};

int main(int argc, char** argv)
{
    kstack st(4, 10);
    st.push(0,10);
    st.push(1,20);
    st.push(0,30);
    st.push(0,40);
    st.push(2,50);
    st.push(2,60);
    st.push(0,70);
    st.push(3,80);
    st.push(0,90);
    st.push(3,100);
    st.push(1,110);
    for(int i=0;i<4;i++){
        cout << "s" << i << " -> ";
        while(st.isEmpty(i) == false){
            int val=st.top(i);
            st.pop(i);
            cout << val << " ";
        }
        cout << endl;
    }
}

