#include<iostream>
#include<string>
#include<unordered_set>

using namespace std;

// bool isthewordinvocab(unordered_set<string>& vocab,string word)
// {
//     if(vocab.count(word) == 1)
//         return true;
//     return false;
// }
void mango(unordered_set<string>& vocab,string query,string ans)
{
    if(query.length() == 0)
    {
        cout << ans << endl;
        return;
    }
    for(int i=0;i<query.length();i++)
    {
        string qs=query.substr(0,i+1);
        string ros=query.substr(i+1);
        if(vocab.count(qs) == 1)
        {
            mango(vocab,ros,ans+qs+" ");
        }
    }
}
int main(int argc,char** argv)
{
    // vector<string> dictionary {"man","go","mango","ice","cream","icecream","air","plane","airplane"};
    // vector<string> dictionary {"like","sung","go","man","mango","sam","samsung","i"};
    // mango(dictionary,"gomanicecreamairplane","");
    // mango(dictionary,"ilikesamsungmango","");
    unordered_set<string> dictionary;
    dictionary.insert("man");
    dictionary.insert("go");
    dictionary.insert("mango");
    dictionary.insert("ice");
    dictionary.insert("cream");
    dictionary.insert("icecream");
    dictionary.insert("sam");
    dictionary.insert("sung");
    dictionary.insert("samsung");
    mango(dictionary,"mangoicecreamsamsung","");

}