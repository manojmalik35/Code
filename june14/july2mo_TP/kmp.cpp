#include<iostream>
#include<string>
using namespace std;

int* lpsCalc(string pat)
{
    int* arr=new int[pat.length()]{0};//agr default value 0 ni denge to vo garbage value daal dega
    arr[0]=0;
    int i=1,l=0;
    while(i<pat.length())
    {
        if(pat[i]==pat[l])
        {
            arr[i]=l+1;
            i++;
            l++;
        }
        else
        {
            if(l>0)
                l=arr[l-1];
            else
                i++;
        }
    }
    return arr;
}
void kmp(string src,string pat)
{
    int i=0,j=0;
    int* lps=lpsCalc(pat);
    while(i<src.length())
    {
        if(src[i]==pat[j])
        {
            i++;
            j++;
            if(j==pat.length())
            {
                cout << i-pat.length() << " ";
                j=lps[j-1];
            }
        }
        else
        {
            if(j>0)
                j=lps[j-1];
            else
                i++;
        }
    }
}
int main(int argc,char** argv)
{
    string src="abcabcababcabcab";
    string pat="bc";
    kmp(src,pat);
}