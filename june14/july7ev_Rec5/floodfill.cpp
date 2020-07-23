#include<iostream>
#include<string>
#include<vector>

using namespace std;

//reactive
void floodfill(vector<vector<int>>& arr, int cr,int cc,int dr,int dc,string ans)
{
    if(cr==dr && cc==dc)
    {
        cout << ans << endl;
        return;
    }
    if(cr<0 || cc<0 || cr>dr || cc>dc || arr[cr][cc]==1)
        return;

    arr[cr][cc] = 1; //visit
    floodfill(arr,cr+1,cc,dr,dc,ans+"D");
    floodfill(arr,cr,cc+1,dr,dc,ans+"R");
    floodfill(arr,cr-1,cc,dr,dc,ans+"U");
    floodfill(arr,cr,cc-1,dr,dc,ans+"L");
    arr[cr][cc] = 0; //unvisit
}

//proactive
void floodfill2(vector<vector<int>>& arr, int cr,int cc,int dr,int dc,string ans)
{
    if(cr==dr && cc==dc)
    {
        cout << ans << endl;
        return;
    }

    arr[cr][cc] = 1; //visit
    if(cr+1<=dr && arr[cr+1][cc] != 1)
        floodfill2(arr,cr+1,cc,dr,dc,ans+"D");
    if(cc+1<=dc && arr[cr][cc+1] != 1)
        floodfill2(arr,cr,cc+1,dr,dc,ans+"R");
    if(cr-1>=0 && arr[cr-1][cc] != 1)
        floodfill2(arr,cr-1,cc,dr,dc,ans+"U");
    if(cc-1>=0 && arr[cr][cc-1] != 1)
        floodfill2(arr,cr,cc-1,dr,dc,ans+"L");
    arr[cr][cc] = 0; //unvisit
}

//vector or string dono same rahenge recursion me
void floodfill3(vector<vector<int>>& arr, int cr,int cc,int dr,int dc,string& ans)
{
    if(cr==dr && cc==dc)
    {
        cout << ans << endl;
        return;
    }

    arr[cr][cc] = 1; //visit
    if(cr+1<=dr && arr[cr+1][cc] != 1)
    {   
        ans += "D";
        floodfill3(arr,cr+1,cc,dr,dc,ans);
        ans.erase(ans.length()-1,1); // erase(index,how many characters to erase)
    }
    if(cc+1<=dc && arr[cr][cc+1] != 1)
    {
        ans += "R";
        floodfill3(arr,cr,cc+1,dr,dc,ans);
        ans.erase(ans.length()-1,1);
    }
    if(cr-1>=0 && arr[cr-1][cc] != 1)
    {
        ans += "U";
        floodfill3(arr,cr-1,cc,dr,dc,ans);
        ans.erase(ans.length()-1,1);
    }
    if(cc-1>=0 && arr[cr][cc-1] != 1)
    {
        ans += "L";
        floodfill3(arr,cr,cc-1,dr,dc,ans);
        ans.erase(ans.length()-1,1);
    }
    arr[cr][cc] = 0; //unvisit
}
int main(int argc,char** argv)
{
    vector<vector<int>> arr = {{0,1,0,0,0,0,0,0,0},
                               {0,1,0,1,1,1,1,1,0},
                               {0,1,0,1,1,0,0,0,0},
                               {0,0,0,0,0,0,1,1,1},
                               {0,1,0,1,1,0,0,0,0},
                               {0,1,0,1,1,1,1,1,0},
                               {0,1,0,0,0,0,0,0,0}};
    string ans="";
    floodfill3(arr,0,0,6,8,ans);
}