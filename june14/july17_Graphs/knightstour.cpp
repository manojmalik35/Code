#include<iostream>
#include<vector>

using namespace std;

int cn = 0;
void kntour(vector<vector<int>>& board, int n, int r, int c,int count)
{
    if(count == n * n)
    {
        cn++;
        board[r][c] = count;
        cout << "===========" << cn << "==========" << endl;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
                cout << board[i][j] << " ";
            cout << endl;
        }
        cout << "===========" << cn << "==========" << endl;
        board[r][c] = 0;
        return;
    }
    board[r][c] = count;

    if(r - 2 >= 0 && c - 1 >= 0 && board[r - 2][c - 1] == 0)
        kntour(board, n, r - 2, c - 1, count + 1);

    if(r - 1 >= 0 && c - 2 >= 0 && board[r - 1][c - 2] == 0)
        kntour(board, n, r - 1, c - 2, count + 1);

    if(r + 1 < n && c - 2 >= 0 && board[r + 1][c - 2] == 0)
        kntour(board, n, r + 1, c - 2, count + 1);

    if(r + 2 < n && c - 1 >= 0 && board[r + 2][c - 1] == 0)
        kntour(board, n, r + 2, c - 1, count + 1);

    if(r + 2 < n && c + 1 < n && board[r + 2][c + 1] == 0)
        kntour(board, n, r + 2, c + 1, count + 1);

    if(r + 1 < n && c + 2 < n && board[r + 1][c + 2] == 0)
        kntour(board, n, r + 1, c + 2, count + 1);

    if(r - 1 >= 0 && c + 2 < n && board[r - 1][c + 2] == 0)
        kntour(board, n, r - 1, c + 2, count + 1);

    if(r - 2 >= 0 && c + 1 < n && board[r - 2][c + 1] == 0)
        kntour(board, n, r - 2, c + 1, count + 1);

    board[r][c] = 0;
}
int main(int argc, char** argv)
{
    int n = 5;
    vector<vector<int>> board (n, vector<int> (n, 0));
    kntour(board, n, 1, 3, 1);
}