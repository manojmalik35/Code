#include <iostream>
#include <vector>
#include <stack>

using namespace std;

void nsor(vector<int>& heights, vector<int>& nsr){
    stack<int> st;
    for(int i = 0; i < heights.size(); i++){
        while(st.size() != 0 && heights[st.top()] > heights[i]){
            nsr[st.top()] = i;
            st.pop();
        }
        
        st.push(i);
    }
}

void nsol(vector<int>& heights, vector<int>& nsl){
    stack<int> st;
    for(int i = heights.size() - 1; i >= 0; i--){
        while(st.size() != 0 && heights[st.top()] > heights[i]){
            nsl[st.top()] = i;
            st.pop();
        }
        
        st.push(i);
    }
}

int largestRectangleArea(vector<int>& heights) {
    vector<int> nsr(heights.size(), heights.size());
    vector<int> nsl(heights.size(), -1);
    nsor(heights, nsr);
    nsol(heights, nsl);
    
    int maxArea = 0;
    for(int i = 0; i < heights.size(); i++){
        int h = heights[i];
        int w = nsr[i] - nsl[i] - 1;
        int ar = h * w;
        maxArea = max(maxArea, ar);
    }
    
    return maxArea;
}