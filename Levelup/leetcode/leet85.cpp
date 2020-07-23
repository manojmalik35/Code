#include <vector>
#include <stack>

using namespace std;

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