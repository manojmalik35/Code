#include<iostream>
#include<string>
#include<vector>
#include<sstream>

using namespace std;

bool isthechoicevalid(string s)
{
    if(s[0] == '0')
        return false;
    int n=stoi(s);
    if(n>26)
        return false;

    return true;
}

void encodings(string s,string asf)
{
    if(s.length() == 0)
    {
        cout << asf << endl;
        return;
    }
    for(int i=0;i<s.length();i++)
    {
        string t=s.substr(0,i+1);
        string ros=s.substr(i+1);
        if(isthechoicevalid(t))
        {
            int temp=stoi(t);
            char ch=temp+'a'-1;
            encodings(ros,asf+ch);
        }
    }
}
int main(int argc,char** argv)
{
    encodings("1123","");
    //conversion from string to integer
    //#include<sstream>
    // string s="1123";
    // stringstream sin(s);
    // int i=0;
    // sin >> i;
    // cout << i;
}