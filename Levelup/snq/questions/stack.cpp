#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

bool isValid(string s)
{
    stack<char> st;
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];
        if (ch == ')')
        {
            if (st.size() == 0 || st.top() != '(')
                return false;
            st.pop();
        }
        else if (ch == '}')
        {
            if (st.size() == 0 || st.top() != '{')
                return false;
            st.pop();
        }
        else if (ch == ']')
        {
            if (st.size() == 0 || st.top() != '[')
                return false;
            st.pop();
        }
        else
            st.push(ch);
    }

    return st.size() == 0;
}

string removeOuterParentheses(string s)
{
    string ans = "";
    int b = 0;
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];
        if (ch == '(' && b++ > 0)
            ans += ch;
        else if (ch == ')' && b-- > 1)
            ans += ch;
    }

    return ans;
}

int longestValidParentheses(string s)
{
    int maxLen = 0;
    stack<int> st;
    st.push(-1);
    for (int i = 0; i < s.length(); i++)
    {
        if (st.top() != -1 && s[i] == ')' && s[st.top()] == '(')
        {
            st.pop();
            maxLen = max(maxLen, i - st.top());
        }
        else
            st.push(i);
    }

    return maxLen;
}

void nsor(vector<int> &heights, vector<int> &nsr)
{
    stack<int> st;
    for (int i = 0; i < heights.size(); i++)
    {
        while (st.size() != 0 && heights[st.top()] > heights[i])
        {
            nsr[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }
}

void nsol(vector<int> &heights, vector<int> &nsl)
{
    stack<int> st;
    for (int i = heights.size() - 1; i >= 0; i--)
    {
        while (st.size() != 0 && heights[st.top()] > heights[i])
        {
            nsl[st.top()] = i;
            st.pop();
        }

        st.push(i);
    }
}

int largestRectangleArea(vector<int> &heights)
{
    vector<int> nsr(heights.size(), heights.size());
    vector<int> nsl(heights.size(), -1);
    nsor(heights, nsr);
    nsol(heights, nsl);

    int maxArea = 0;
    for (int i = 0; i < heights.size(); i++)
    {
        int h = heights[i];
        int w = nsr[i] - nsl[i] - 1;
        int ar = h * w;
        maxArea = max(maxArea, ar);
    }

    return maxArea;
}

int largestRectangleArea2(vector<int> &heights)
{ //Optimized

    stack<int> st;
    st.push(-1);
    int maxArea = 0;
    for (int i = 0; i < heights.size(); i++)
    {
        while (st.top() != -1 && heights[st.top()] >= heights[i])
        {
            int h = heights[st.top()];
            st.pop();

            int a = h * (i - st.top() - 1);
            maxArea = max(maxArea, a);
        }

        st.push(i);
    }

    while (st.size() > 1)
    {
        int h = heights[st.top()];
        st.pop();

        int a = h * (heights.size() - st.top() - 1);
        maxArea = max(maxArea, a);
    }

    return maxArea;
}

int maximalRectangle(vector<vector<char>> &matrix)
{
    if (matrix.size() == 0)
        return 0;
    int maxArea = 0;
    vector<int> rarr(matrix[0].size(), 0);
    for (int i = 0; i < matrix.size(); i++)
    {
        for (int j = 0; j < matrix[0].size(); j++)
        {
            if (matrix[i][j] == '1')
                rarr[j]++;
            else
                rarr[j] = 0;
        }

        maxArea = max(maxArea, largestRectangleArea2(rarr));
    }

    return maxArea;
}

vector<int> asteroidCollision(vector<int>& arr) {

    stack<int> st;
    for(int ele : arr){
        if(ele > 0)
            st.push(ele);
        else
        {
            while(st.size() > 0 && st.top() > 0 && st.top() < -ele)
                st.pop();

            if(st.size() > 0 && st.top() == -ele)
                st.pop();
            else if(st.size() == 0 || st.top() < 0)
                st.push(ele);
        }
    }   

    vector<int> ans(st.size(), 0);
    for(int i = ans.size() - 1; i >= 0; i--)
    {
        ans[i] = st.top();
        st.pop();
    }

    return ans;
}

int circularTour(vector<int>& gas, vector<int>& cost) {//gfg and leet134
    int ep = 0;
    int start = 0;
    int tp =0, td = 0;
    for(int i = 0; i < gas.size(); i++)
    {
        ep += gas[i] - cost[i];
        tp += gas[i];
        td += cost[i];
        if(ep < 0){
            start = i + 1;
            ep = 0;
        }
    }
    
    if(start == gas.size())
        return -1;
    else if(tp >= td)
        return start;
    
    return -1;
}

int carFleet(int target, vector<int>& position, vector<int>& speed) {
    vector<pair<int, double>> postime;
    for(int i = 0; i < position.size(); i++){
        double time = (target - position[i]) * 1.0 / speed[i];
        postime.push_back({position[i], time});
    }        

    sort(postime.begin(), postime.end());
    int fleets = position.size();
    int fl = postime.size() - 1;
    for(int i = fl; i > 0; i--){
        if(postime[i - 1].second <= postime[fl].second)
            fleets--;
        else
            fl = i - 1;
    }

    return fleets;
}

int trap(vector<int> &arr)//leet42
{
    vector<int> lmax(arr.size(), 0);
    vector<int> rmax(arr.size(), 0);

    int prev = -1;
    for (int i = 0; i < lmax.size(); i++)
    {
        lmax[i] = max(arr[i], prev);
        prev = lmax[i];
    }

    prev = -1;
    for (int i = rmax.size() - 1; i >= 0; i--)
    {
        rmax[i] = max(arr[i], prev);
        prev = rmax[i];
    }

    int water = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        int ew = min(lmax[i], rmax[i]) - arr[i]; //eligible water
        water += ew;
    }

    return water;
}

int trap2(vector<int> &arr)//leet42
{
    stack<int> st;
    int water = 0;
    for(int i = 0; i < arr.size(); i++){
        while(st.size() != 0 && arr[st.top()] < arr[i]){
            int h = arr[st.top()]; st.pop();
            if(st.size() == 0)
                break;

            int oh = min(arr[i], arr[st.top()]) - h;//overall height
            int width = i - st.top() - 1;
            water += oh * width;
        }
        st.push(i);
    }

    return water;
}

vector<int> exclusiveTime(int n, vector<string>& logs) {//leet636
    vector<int> ans(n, 0);
    stack<int> st;
    string s = logs[0];
    int prev = s[0] - '0';
    st.push(prev);
    for(int i = 1; i < logs.size(); i++){
        s = logs[i];
        int id = 0, k = 0;
        while(s[k] != ':'){
            id = id * 10 + (s[k] - '0');
            k++;
        }
        char type = s[k + 1];
        string t = type == 's' ? s.substr(k + 7) : s.substr(k + 5);
        int itime = stoi(t);
        if(type == 's'){
            if(st.size() != 0)
                ans[st.top()] += itime - prev;
            
            prev = itime;
            st.push(id);
        }else{
            ans[st.top()] += itime - prev + 1;
            prev = itime + 1;
            st.pop();
        }
    }

    return ans;   
}

int main()
{
    vector<vector<char>> matrix{{'0', '1'}, {'1', '0'}};
    cout << maximalRectangle(matrix) << endl;
}
