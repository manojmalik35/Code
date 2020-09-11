#include <vector>
#include <stack>

using namespace std;

vector<int> nextGreaterElements(vector<int> &nums)
{
    vector<int> ans(nums.size(), -1);
    stack<int> st;
    for (int i = 0; i < 2 * nums.size(); i++)
    {
        int j = i % nums.size();
        while (st.size() != 0 && nums[st.top()] < nums[j])
        {
            ans[st.top()] = nums[j];
            st.pop();
        }
        st.push(j);
    }

    return ans;
}