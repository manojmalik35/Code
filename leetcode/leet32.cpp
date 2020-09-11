#include <iostream>
#include<stack>

using namespace std;

int longestValidParentheses(string s) {
    int maxLen = 0;
    stack<int> st;
    st.push(-1);
    for(int i = 0; i < s.length(); i++){
        if(st.top() != -1 && s[i] == ')' && s[st.top()] == '('){
            st.pop();
            maxLen = max(maxLen, i - st.top());
        }else
            st.push(i);
        
    }
    
    return maxLen;
}