#include<iostream>
#include<vector>
#include<string>

using namespace std;

void printPerm(vector<int>& coins,int amt,string asf)
{
    //proactive
    if(amt == 0)
    {
        cout << asf << endl;
        return;
    }

    for(int i=0;i<coins.size();i++)
    {
        if(coins[i] <= amt)
            printPerm(coins,amt-coins[i],asf+to_string(coins[i]));
    }
    //reactive
    // if(amt <= 0)
    // {
    //     if(amt == 0)
    //         cout << asf << endl;
    //     return;
    // }
    // for(int i=0;i<coins.size();i++)
    // {
    //     printPerm(coins,amt-coins[i],asf+to_string(coins[i]));
    // }
}


void printComb(vector<int>& coins,int amt,int lci,string asf)//lci = last coin used
{
    if(amt == 0)
    {
        cout << asf << endl;
        return;
    }
    for(int i=lci;i<coins.size();i++)
    {
        if(coins[i] <= amt)
            printComb(coins,amt-coins[i],i,asf+to_string(coins[i]));
    }
}
int main(int argc,char** argv)
{
    vector<int> coins {2,3,5};
    // printPerm(coins,7,"");
    printComb(coins,7,0,"");
}