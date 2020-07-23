#include <vector>
#include <stack>
#include <string>

using namespace std;

vector<int> exclusiveTime(int n, vector<string>& logs) {
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