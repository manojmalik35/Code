#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int dir[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

int BFS(vector<vector<int>> &mat, int i, int j)
{
    int len = 0;
    queue<pair<int, pair<int, int>>> q;
    q.push({1, {i, j}});
    while (q.size() > 0)
    {
        pair<int, pair<int, int>> p = q.front();
        q.pop();

        int t = p.first;

        len = max(len, t);

        int r = p.second.first;
        int c = p.second.second;

        // cout << r << c << endl;
        for (int d = 0; d < 4; d++)
        {
            int ni = r + dir[d][0];
            int nj = c + dir[d][1];

            if (ni >= 0 && nj >= 0 && ni < mat.size() && nj < mat[0].size() && mat[ni][nj] > mat[r][c])
                q.push({t + 1, {ni, nj}});
        }
    }

    // cout << len << endl;
    return len;
}

int DFS(vector<vector<int>> &mat, int i, int j)
{
    int count = 0;
    for (int d = 0; d < 4; d++)
    {
        int ni = i + dir[d][0];
        int nj = j + dir[d][1];

        if (ni >= 0 && nj >= 0 && ni < mat.size() && nj < mat[0].size() && mat[ni][nj] > mat[i][j])
            count = max(count, DFS(mat, ni, nj));
    }

    return count + 1;
}

int longestIncreasingPath(vector<vector<int>> &matrix)
{

    vector<vector<int>> indeg(matrix.size(), vector<int>(matrix[0].size(), 0));
    for (int i = 0; i < matrix.size(); i++)
    {
        for (int j = 0; j < matrix[0].size(); j++)
        {
            for (int d = 0; d < 4; d++)
            {
                int x = i + dir[d][0];
                int y = j + dir[d][1];

                if (x >= 0 && y >= 0 && x < matrix.size() && y < matrix[0].size() && matrix[x][y] < matrix[i][j])
                    indeg[i][j]++;
            }
        }
    }

    int maxLen = 0;
    for (int i = 0; i < indeg.size(); i++)
    {
        for (int j = 0; j < indeg[0].size(); j++)
        {
            if (indeg[i][j] == 0)
                maxLen = max(maxLen, DFS(matrix, i, j));
        }
    }

    return maxLen;
}

int main()
{
    vector<vector<int>> mat{{3, 4, 5},
                            {3, 2, 6},
                            {2, 2, 1}};

    cout << longestIncreasingPath(mat) << "\n";
    return 0;
}