#include<iostream>
#include<vector>
#include<string>

using namespace std;

//objects on levels and boxes on options
int count=0;
void printPerm(vector<bool>& boxes,int co,int to,string ans)//current object and total object
{
    if(co == to)
    {
        cout << ans << endl;
        count++;
        return;
    }
    for(int i=0;i<boxes.size();i++)
    {
        if(boxes[i] == false)
        {
            boxes[i] = true;
            printPerm(boxes,co+1,to,ans+"O"+to_string(co+1)+"B"+to_string(i)+" ");
            boxes[i] = false;
        }
    }
}

int main(int argc,char** argv)
{
    vector<bool> boxes (4,0); // size , default value
    int to=2;
    printPerm(boxes,0,to,"");
    cout << count << endl;
}