#include<iostream>

using namespace std;

bool isthechoiceValid(string c)
{
    if(c[0] == '0')
        return false;
    int i = stoi(c);
    if(i > 26)
        return false;
    return true;
}

void enco(string s, string ans)
{
    if(s.length() == 0)
    {
        cout << ans << endl;
        return;
    }

    for(int i = 0; i < s.length(); i++)
    {
        string c = s.substr(0, i + 1);
        string ros = s.substr(i + 1);
        if(isthechoiceValid(c))
        {
            int t = stoi(c);
            char ch = t + 'a' - 1;
            enco(ros, ans + ch);
        }
    }
}

int main(int argc, char** argv)
{
    enco("1023", "");
}