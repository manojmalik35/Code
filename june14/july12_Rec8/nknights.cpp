#include<iostream>
#include<string>
#include<vector>

using namespace std;

bool istheKnightSafe(vector<vector<bool>>& board,int i,int j)
{
    if(i-2>=0 && j-1>=0 && board[i-2][j-1])
        return false;

    else if(i-1>=0 && j-2>=0 && board[i-1][j-2])
        return false;

    else if(i+1<board.size() && j>=2 && board[i+1][j-2])
        return false;

    else if(i+2<board.size() && j>=1 && board[i+2][j-1])
        return false;

    else if(i+2<board.size() && j+1<board.size() && board[i+2][j+1])
        return false;

    else if(i+1<board.size() && j+2<board.size() && board[i+1][j+2])
        return false;

    else if(i>=1 && j+2<board.size() && board[i-1][j+2])
        return false;

    else if(i>=2 && j+1<board.size() && board[i-2][j+1])
        return false;
    else
        return true;
}
int count=0;
void nknights(vector<vector<bool>>& board,int bno,int kpsf,string asf)
//last queen was placed in this row
{
    if(bno == board.size()*board.size())
    {
        if(kpsf == board.size())
        {
            cout << asf << endl;
            count++;
        }
        return;
    }

    nknights(board,bno+1,kpsf,asf);
    int i=bno/board.size();
    int j=bno%board.size();
    if(istheKnightSafe(board,i,j)) // if(board[i][j] == false) vli condition optional h
    //kyuki b0 b1 ko call kr ra h or b1 b2 ko to mtlb kisi b box pe pehle se true ho hi ni skta 
    {
        board[i][j] = true;
        nknights(board,bno+1,kpsf+1,asf+to_string(i)+to_string(j)+"-");
        board[i][j] = false;
    }
}

int main(int argc,char** argv)
{   
    vector<vector<bool>> board (3,vector<bool>(3,false));
    nknights(board,0,0,"");
    cout << count << endl;
}