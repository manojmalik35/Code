#include<iostream>
#include<vector>

using namespace std;

bool isthewordinDic(vector<string>& dictionary, string word)
{
    for(string s : dictionary)
    {
        if(s == word)
            return true;
    }
    return false;
}

void solve(vector<string>& dictionary, string q, string ans)
{
    if(q.length() == 0)
    {
        cout << ans << endl;
        return;
    }

    for(int i = 0; i < q.length(); i++)
    {
        string c = q.substr(0, i + 1);
        string roq = q.substr(i + 1);
        if(isthewordinDic(dictionary, c))
            solve(dictionary, roq, ans + c + "-");
    }
}

int main(int argc, char** argv)
{
    vector<string> dictionary {"man", "go", "mango", "ice", "cream", "icecream", "air",
                               "plane", "airplane"};

    solve(dictionary, "gomanicecreamairplane", "");
}