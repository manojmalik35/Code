#include<iostream>
#include<vector>
#include<string>

using namespace std;

void printComb(vector<bool>& boxes,int co,int to,int lowph,string asf)//objects on levels
//lowph = last object was placed here
{
    if(co == to)
    {
        cout << asf << endl;
        return;
    }
    for(int i=lowph+1;i<boxes.size();i++)
    {
        if(boxes[i] == false)
        {
            boxes[i] = true;
            printComb(boxes,co+1,to,i,asf+"O"+to_string(co)+"B"+to_string(i)+" ");
            boxes[i] = false;
        }
    }
}


void printComb2(int bno,int co,int to,int tb,string ans)//boxes on levels
{
    if(bno == tb)
    {
        if(co == to)
            cout << ans << endl;
        return;
    }

    printComb2(bno+1,co,to,tb,ans);//box ne aane se mana kr diya
    printComb2(bno+1,co+1,to,tb,ans+"O"+to_string(co)+"B"+to_string(bno)+" ");
}

//proactive
// void printComb3(int bno,int co,int to,int tb,string ans)//boxes on levels
// {
//     if(bno == tb)
//     {
//         if(co == to)
//             cout << ans << endl;
//         return;
//     }
//     if(bno+1 <= tb)
//     {
//         printComb3(bno+1,co,to,tb,ans);//box ne aane se mana kr diya
//         printComb3(bno+1,co+1,to,tb,ans+"O"+to_string(co)+"B"+to_string(bno)+" ");
//     }
// }
int main(int argc,char** argv)
{
    vector<bool> boxes (4,false); 
    // printComb(boxes,0,2,-1,"");
    printComb2(0,0,2,4,"");
}