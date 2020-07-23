#include<iostream>
#include<vector>

using namespace std;

int dir[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
int dfs(vector<vector<int>>& grid, int i, int j)
{
    grid[i][j] = 0;
    int count = 0;
    for(int d = 0; d < 4; d++)
    {
        int x = i + dir[d][0];
        int y = j + dir[d][1];

        if(x >= 0 && y >= 0 && x < grid.size() && y < grid[0].size() && grid[x][y] == 1)
            count += dfs(grid, x, y);
    }

    return count + 1;
}

int maxAreaOfIsland(vector<vector<int>>& grid) {
    int maxArea = 0;
    for(int i = 0; i < grid.size(); i++)
    {
        for(int j = 0; j < grid[0].size(); j++)
        {
            if(grid[i][j] == 1)
            {
                int area = dfs(grid, i, j);
                maxArea = max(maxArea, area);
            }
        }
    }
    
    return maxArea;
}

int findParent(int v, vector<int>& par)
{
    if(par[v] == v)
        return v;
    else
    {
        int p = findParent(par[v], par);
        par[v] = p;
        return p;
    }
}

void merge(int p1, int p2, vector<int>& par, vector<int>& size)
{
    if(size[p2] > size[p1])
    {
        par[p1] = p2;
        size[p2] += size[p1];
    }else
    {
        par[p2] = p1;
        size[p1] += size[p2];
    }
}

int maxAreaOfIsland2(vector<vector<int>>& grid) {
    int maxArea = 0;
    int m = grid.size();
    if(m == 0)
        return 0;
    int n = grid[0].size();

    vector<int> par;
    vector<int> size;

    for(int i = 0; i < m; i++)
    {
        for(int j = 0; j < n; j++)
        {
            par.push_back(i * n + j);
            if(grid[i][j] == 1)
                size.push_back(1);
            else
                size.push_back(0);
        }
    }

    for(int i = 0; i < m; i++)
    {
        for(int j = 0; j < n; j++)
        {
            if(grid[i][j] == 1)
            {
                if(j + 1 < n && grid[i][j + 1] == 1)
                {
                    int p1 = findParent(i * n + j, par);
                    int p2 = findParent(i * n + j + 1, par);

                    if(p1 != p2)
                        merge(p1, p2, par, size);
                }

                if(i + 1 < m && grid[i + 1][j] == 1)
                {
                    int p1 = findParent(i * n + j, par);
                    int p2 = findParent((i + 1) * n + j, par);

                    if(p1 != p2)
                        merge(p1, p2, par, size);
                }
            }
        }
    }
    
    for(int i = 0; i < size.size(); i++)
        maxArea = max(maxArea, size[i]);
    
    return maxArea;
}