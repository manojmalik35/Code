#include <iostream>

using namespace std;

string removeOuterParentheses(string s) {
    string ans = "";
    int b = 0;
    for(int i = 0; i < s.length(); i++){
        char ch = s[i];
        if(ch == '(' && b++ > 0)
            ans += ch;
        else if(ch == ')' && b-- > 1)
            ans += ch;
    }
    
    return ans;
}