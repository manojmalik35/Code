#include<iostream>
#include<stack>
#include<vector>

using namespace std;

bool hasDuplicateBrackets(string& exp)
{
    stack<char> st;
    for(int i = 0; i < exp.length(); i++)
    {
        char ch = exp[i];
        if(ch != ')')
            st.push(ch);
        else
        {
            if(st.top() == '(')
                return true;

            while(st.top() != '(')
                st.pop();

            st.pop();
        }
    }

    return false;
}

bool hasBalancedBrackets(string& exp)
{
    stack<char> st;
    for(int i = 0; i < exp.length(); i++)
    {
        char ch = exp[i];
        if(ch == '(' || ch == '{' || ch == '[')
            st.push(ch);
        else if(ch == ')')
        {
            if(st.size() == 0 || st.top() != '(')
                return false;

            st.pop();
        }
        else if(ch == '}')
        {
            if(st.size() == 0 || st.top() != '{')
                return false;

            st.pop();
        }
        else if(ch == ']')
        {
            if(st.size() == 0 || st.top() != '[')
                return false;

            st.pop();
        }
    }

    if(st.size() > 0)
        return false;
        
    return true;
}

// class myPair
// {
//     public:
//         int ele;
//         int idx;

//         myPair(int ele, int idx)
//         {
//             this->ele = ele;
//             this->idx = idx;
//         }
// };

void closestGreaterElement(vector<int>& arr)
{
    vector<int> ans (arr.size(), 0);

    // using objects
    // stack<myPair> st;
    // for(int i = 0; i < arr.size(); i++)
    // {
    //     while(st.size() > 0 && arr[i] > st.top().ele)
    //     {
    //         myPair t = st.top();
    //         st.pop();

    //         ans[t.idx] = arr[i];
    //     }
    //     st.push(myPair(arr[i], i));
    // }

    // while(st.size() > 0){
    //     myPair t = st.top(); st.pop();
    //     ans[t.idx] = -1;
    // }

    stack<int> st;//isme sirf indexes store krvayenge
    for(int i = 0; i < arr.size(); i++)
    {
        while(st.size() > 0 && arr[i] > arr[st.top()])
        {
            ans[st.top()] = arr[i];
            st.pop();
        }

        st.push(i);
    }

    while(st.size() > 0){
        ans[st.top()] = -1;
        st.pop();
    }

    for(int val : ans)
        cout << val << " ";
    cout << endl;
}

void closestGreaterElement2(vector<int>& arr)//right to left processing
{
    vector<int> ans (arr.size(), 0);
    stack<int> st;

    ans[ans.size() - 1] = -1;
    st.push(arr[arr.size() - 1]);

    for(int i = arr.size() - 2; i >= 0; i--)
    {
        while(st.size() > 0 && st.top() < arr[i])
            st.pop();

        ans[i] = st.size() == 0 ? -1 : st.top();
        st.push(arr[i]);
    }

    for(int val : ans)
        cout << val << " ";
    cout << endl;
}

int largestAreaHistogram(vector<int>& bars)
{
    stack<int> st;

    //right boundary (next smaller on the right)
    vector<int> rb(bars.size(), 0);
    for(int i = 0; i < bars.size(); i++)
    {
        while(st.size() > 0 && bars[i] < bars[st.top()])
        {
            rb[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }

    while(st.size() > 0)
    {
        rb[st.top()] = rb.size();
        st.pop();
    }

    //left boundary (next smaller on the left)
    vector<int> lb(bars.size(), 0);
    for(int i = bars.size() - 1; i >= 0; i--)
    {
        while(st.size() > 0 && bars[i] < bars[st.top()])
        {
            lb[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }

    while(st.size() > 0)
    {
        lb[st.top()] = -1;
        st.pop();
    }

    int maxarea = 0;
    for(int i = 0; i < lb.size(); i++)
    {
        int width = rb[i] - lb[i] - 1;
        int area = bars[i] * width;
        maxarea = max(maxarea, area);
    }

    return maxarea;
}

vector<int> maxinSlidingWindow(vector<int>& arr, int k)
{
    vector<int> nge (arr.size());
    stack<int> st;
    for(int i = 0; i < arr.size(); i++)
    {
        while(st.size() > 0 && arr[st.top()] < arr[i])
        {
            nge[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }

    while(st.size() > 0)
    {
        nge[st.top()] = arr.size();
        st.pop();
    }

    vector<int> res (arr.size() - k + 1);
    int i = 0, j = 0;
    while(i < res.size())
    {
        if(i > j)
            j = i;
            
        while(nge[j] < i + k)
            j = nge[j];

        res[i] = arr[j];
        i++;
    }

    return res;
}

void psntfp(string pat)//print smallest number that follows the pattern
//every digit should be unique
{
    stack<int> st;
    int val = 1;
    for(int i = 0; i < pat.length(); i++)
    {
        if(pat[i] == 'd')
            st.push(val++);
        else
        {
            st.push(val++);
            while(st.size() > 0)
            {
                cout << st.top();
                st.pop();
            }
        }
    }

    st.push(val);
    while(st.size() > 0)
    {
        cout << st.top();
        st.pop();
    }
}

int main(int argc, char** argv)
{
    // string exp = "(a+((b+c+(d+e))))";
    // cout << hasDuplicateBrackets(exp) << endl;
    // string exp = "[a+{b+(d+e)}]";
    // cout << hasBalancedBrackets(exp) << endl;
    // vector<int> arr {5, 9, 8, 3, 2, 7, 16, 4, 14, 19, 3};
    // closestGreaterElement(arr);
    // vector<int> arr {6, 2, 5, 4, 5, 1, 6};
    // cout << largestAreaHistogram(arr) << endl;
    // vector<int> arr {5, 9, 3, 1, 8, 6, 7, 2, 11, 4, 17, 12};
    // vector<int> slidwin = maxinSlidingWindow(arr, 5);
    // for(int val : slidwin)
    //     cout << val << " ";
    // cout << endl;
    string pat = "didididi";
    psntfp(pat);
}