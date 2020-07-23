#include <iostream>
#include <vector>

using namespace std;

int ra[9]{0};
int ca[9]{0};
int sba[3][3]{0};
bool isthenumbersafe(int i, int j, int m)
{
    if ((ra[i] & (1 << m)) != 0)
        return false;
    else if ((ca[j] & (1 << m)) != 0)
        return false;
    else if ((sba[i / 3][j / 3] & (1 << m)) != 0)
        return false;
    else
        return true;
}
void sudokubm(vector<vector<int>> &board, int bno)
{
    if (bno == board.size() * board.size())
    {
        for (int i = 0; i < board.size(); i++)
        {
            for (int j = 0; j < board[0].size(); j++)
                cout << board[i][j] << " ";
            cout << endl;
        }
        return;
    }
    int i = bno / board.size();
    int j = bno % board.size();
    if (board[i][j] == 0)
    {
        for (int op = 1; op <= 9; op++)
        {
            if (isthenumbersafe(i, j, op))
            {
                ra[i] ^= (1 << op);
                ca[j] ^= (1 << op);
                sba[i / 3][j / 3] ^= (1 << op);

                board[i][j] = op;
                sudokubm(board, bno + 1);
                board[i][j] = 0;

                ra[i] ^= (1 << op);
                ca[j] ^= (1 << op);
                sba[i / 3][j / 3] ^= (1 << op);
            }
        }
    }
    else
        sudokubm(board, bno + 1);
}
int main(int argc, char **argv)
{
    vector<vector<int>> board{{3, 0, 6, 5, 0, 8, 4, 0, 0},
                              {5, 2, 0, 0, 0, 0, 0, 0, 0},
                              {0, 8, 7, 0, 0, 0, 0, 3, 1},
                              {0, 0, 3, 0, 1, 0, 0, 8, 0},
                              {9, 0, 0, 8, 6, 3, 0, 0, 5},
                              {0, 5, 0, 0, 9, 0, 6, 0, 0},
                              {1, 3, 0, 0, 0, 0, 2, 5, 0},
                              {0, 0, 0, 0, 0, 0, 0, 7, 4},
                              {0, 0, 5, 2, 0, 6, 3, 0, 0}};

    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] != 0)
            {
                ra[i] |= (1 << board[i][j]); // turning the bit on
                ca[j] |= (1 << board[i][j]);
                sba[i / 3][j / 3] |= (1 << board[i][j]);
            }
        }
    }
    sudokubm(board, 0);
}