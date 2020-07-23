#include <iostream>
#include<stack>

using namespace std;

bool isValid(string s) {
    stack<char> st;
    for(int i = 0; i < s.length(); i++){
        char ch = s[i];
        if(ch == ')'){
            if(st.size() == 0 || st.top() != '(')
                return false;
            st.pop();
        }else if(ch == '}'){
            if(st.size() == 0 || st.top() != '{')
                return false;
            st.pop();
        }else if(ch == ']'){
            if(st.size() == 0 || st.top() != '[')
                return false;
            st.pop();
        }else
            st.push(ch);
    }

    return st.size() == 0;
}