#include <vector>
#include <stack>
#include <unordered_map>

using namespace std;

void ngor(vector<int> &arr, unordered_map<int, int> &ngr)
{
    stack<int> st;
    for (int i = 0; i < arr.size(); i++)
    {
        while (st.size() != 0 && st.top() < arr[i])
        {
            ngr[st.top()] = arr[i];
            st.pop();
        }

        st.push(arr[i]);
    }
}

vector<int> nextGreaterElement(vector<int>& nums1, vector<int>& nums2) {
    unordered_map<int, int> ngr;
    ngor(nums2, ngr);
    vector<int> ans;
    for(int ele : nums1){
        int a = ngr[ele];
        a = a == 0 ? -1 : a;
        ans.push_back(a);
    }
    
    return ans;
}