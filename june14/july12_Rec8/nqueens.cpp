#include<iostream>
#include<string>
#include<vector>

using namespace std;

bool istheQueenSafe(vector<vector<bool>>& board,int i,int j)
{
    //north
    for(int ii=i-1;ii>=0;ii--)
    {
        if(board[ii][j])
            return false;
    }

    //south
    for(int ii=i+1;ii<board.size();ii++)
    {
        if(board[ii][j])
            return false;
    }

    //east
    for(int jj=j+1;jj<board[0].size();jj++)
    {
        if(board[i][jj])
            return false;
    }

    //west
    for(int jj=j-1;jj>=0;jj--)
    {
        if(board[i][jj])
            return false;
    }

    //north west
    for(int ii=i-1,jj=j-1;ii>=0 && jj>=0;ii--,jj--)
    {
        if(board[ii][jj])
            return false;
    }

    //north east
    for(int ii=i-1,jj=j+1;ii>=0 && jj<board[0].size();ii--,jj++)
    {
        if(board[ii][jj])
            return false;
    }

    //south east
    for(int ii=i+1,jj=j+1;ii<board.size() && jj<board[0].size();ii++,jj++)
    {
        if(board[ii][jj])
            return false;
    }

    //south west
    for(int ii=i+1,jj=j-1;ii<board.size() && jj>=0;ii++,jj--)
    {
        if(board[ii][jj])
            return false;
    }
    return true;
}
int count=0;
void nqueensComb(vector<vector<bool>>& board,int cq,int lqwpitr,int lqwpitc,string asf)
//last queen was placed in this row
{
    if(cq == board.size())
    {
        cout << asf << endl;
        count++;
        return;
    }
    for(int i=lqwpitr;i<board.size();i++)
    {
        for(int j=(i == lqwpitr ? lqwpitc+1 : 0);j<board[0].size();j++)
        {
            if(board[i][j] == false && istheQueenSafe(board,i,j))
            {
                board[i][j] = true;
                nqueensComb(board,cq+1,i,j,asf+to_string(i)+to_string(j)+"-");
                board[i][j] = false;
            }
        }
    }
}
int main(int argc,char** argv)
{   
    vector<vector<bool>> board (4,vector<bool>(4,false));
    nqueensComb(board,0,0,-1,"");
    cout << count << endl;
}