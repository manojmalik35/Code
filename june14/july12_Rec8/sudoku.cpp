#include<iostream>
#include<vector>

using namespace std;

bool isthenumbersafe(vector<vector<int>>& board,int i,int j,int k)
{
    //horizontally
    for(int jj=0;jj<board.size();jj++)
    {
        if(board[i][jj] == k)
            return false;
    }

    //vertically
    for(int ii=0;ii<board.size();ii++)
    {
        if(board[ii][j] == k)
            return false;
    }

    //submatrix
    for(int ii=i/3*3;ii<i/3*3+3;ii++)
    {
        for(int jj=j/3*3;jj<j/3*3+3;jj++)
        {
            if(board[ii][jj] == k)
                return false;
        }
    }

    return true;
}

void sudoku(vector<vector<int>>& board,int bno)
{
    if(bno == board.size()*board.size())
    {
        for(int i=0;i<board.size();i++)
        {
            for(int j=0;j<board.size();j++)
                cout << board[i][j] << " ";
            cout << endl;
        }
        return;
    }

    int i=bno/board.size();
    int j=bno%board.size();
    if(board[i][j] == 0)
    {
        for(int m=1;m<=9;m++)
        {
            if(isthenumbersafe(board,i,j,m))
            {
                board[i][j] = m;
                sudoku(board,bno+1);
                board[i][j] = 0;
            }
        }
    }
    else
        sudoku(board,bno+1);
}
int main(int argc,char** argv)
{
    vector<vector<int>> board { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                                { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
    sudoku(board,0);
}