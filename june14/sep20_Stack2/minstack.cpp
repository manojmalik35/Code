#include<iostream>
#include<stack>
#include<climits>

using namespace std;

class minstack
{
    private:
        stack<int> st;
        int min;

    public:
        minstack()
        {
            min = INT_MAX;
        }

        void push(int val)
        {
            if(st.size() == 0)
            {
                st.push(val);
                min = val;
            }
            else if(val < min)
            {
                st.push(val + (val - min));
                min = val;
            }
            else
            {
                st.push(val);
            }
        }

        void pop()
        {
            if(st.top() < min)
            {
                min = 2 * min - st.top();
                st.pop();
            }
            else
            {
                st.pop();   
            }
        }

        int top()
        {
            if(st.top() < min)
                return min;
            else
                return st.top();
        }

        int stmin()
        {
            return min;
        }

        int size()
        {
            return st.size();
        }

};

int main(int argc, char** argv)
{
    minstack st;
    st.push(10);
    st.push(5);
    st.push(7);
    st.push(1);
    st.push(5);

    while(st.size() > 0)
    {
        cout << st.top() << " " << st.stmin() << endl;
        st.pop();
    }
}