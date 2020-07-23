#include<iostream>
#include<vector>
#include<queue>

using namespace std;

class Helper
{
    public: 
        
};

void ludo(vector<int>& board)
{
    queue<int> q;
    q.push(0);
    vector<bool> visited (board.size(), false);
    while(q.size() > 0)
    {
        int rem = q.front();
        q.pop();
            
        if(visited[rem])
            continue;
        visited[rem] = true;
        cout << rem << endl;
        if(rem == board.size() - 1)
            break;

        for(int i = 1; i <= 6; i++)
        {
            if(rem + i < board.size())
            {
                if(board[rem + i] == -1)
                    q.push(rem + i);
                else
                    q.push(board[rem + i]);
            }
        }
        
    }
}

int main(int argc, char** argv)
{
    vector<int> board (30, -1);
    board[2] = 27;
    board[7] = 14;
    board[23] = 4;
    board[28] = 5;
    ludo(board);
}