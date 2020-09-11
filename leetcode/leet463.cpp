#include<iostream>
#include<vector>

using namespace std;

int islandPerimeter(vector<vector<int>>& grid) {
    int m = grid.size();
    if(m == 0)
        return 0;
    int n = grid[0].size();
    int ones = 0, nbrs = 0;
    for(int i = 0; i < m; i++)
    {
        for(int j = 0; j < n; j++)
        {
            if(grid[i][j] == 1)
            {
                ones++;
                if(j + 1 < n && grid[i][j + 1] == 1)
                    nbrs++;
                if(i + 1 < m && grid[i + 1][j] == 1)
                    nbrs++;
            }
        }
    }
    
    return ones * 4 - 2 * nbrs;
}

int islandPerimeter2(vector<vector<int>>& grid) {
    int m = grid.size();
    if(m == 0)
        return 0;
    int n = grid[0].size();
    int ones = 0, nbrs = 0;
    for(int i = 0; i < m; i++)
    {
        for(int j = 0; j < n; j++)
        {
            if(grid[i][j] == 1)
            {
                ones++;
                if(j + 1 < n && grid[i][j + 1] == 1)
                    nbrs++;
                if(i + 1 < m && grid[i + 1][j] == 1)
                    nbrs++;
                if(j - 1 >= 0 && grid[i][j - 1] == 1)
                    nbrs++;
                if(i - 1 >= 0 && grid[i - 1][j] == 1)
                    nbrs++;
            }
        }
    }
    
    return ones * 4 - nbrs;
}

int islandPerimeter3(vector<vector<int>>& grid) {
    int m = grid.size();
    if(m == 0)
        return 0;
    int n = grid[0].size();
    int ans = 0;
    for(int i = 0; i < m; i++)
    {
        for(int j = 0; j < n; j++)
        {
            if(grid[i][j] == 1)
            {
                int nbrs = 0;
                if(j + 1 < n && grid[i][j + 1] == 1)
                    nbrs++;
                if(i + 1 < m && grid[i + 1][j] == 1)
                    nbrs++;
                if(j - 1 >= 0 && grid[i][j - 1] == 1)
                    nbrs++;
                if(i - 1 >= 0 && grid[i - 1][j] == 1)
                    nbrs++;
                
                ans += 4;
                ans -=nbrs;
            }
        }
    }

    return ans;
}