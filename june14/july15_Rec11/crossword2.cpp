#include<iostream>
#include<vector>
#include<string>

using namespace std;

bool canplacethewordhor(vector<vector<char>>& grid,string word,int i,int j)
{
    if(j+word.length() > grid[0].size())
        return false;
    if(j > 0 && grid[i][j-1] != '+')
        return false;
    if(j + word.length() < grid[0].size() && grid[i][j+word.length()] != '+')
        return false;
    for(int k=0;k<word.length();k++)
    {
        if(grid[i][j+k] != '-' && grid[i][j+k] != word[k])
            return false;
    }

    return true;
}

bool canplacethewordver(vector<vector<char>>& grid,string word,int i,int j)
{
    if(i+word.length() > grid.size())
        return false;
    if(i > 0 && grid[i-1][j] != '+')
        return false;
    if(i + word.length() < grid.size() && grid[i+word.length()][j] != '+')
        return false;
    for(int k=0;k<word.length();k++)
    {
        if(grid[i+k][j] != '-' && grid[i+k][j] != word[k])
            return false;
    }

    return true;
}

vector<bool> placethewordhor(vector<vector<char>>& grid,string word,int i,int j)
{
    vector<bool> mark (word.length(),false);
    for(int k=0;k<word.length();k++)
    {
        if(grid[i][j+k] == '-')
            grid[i][j+k] = word[k];
        else
            mark[k] = true;
    }
    return mark;
}

vector<bool> placethewordver(vector<vector<char>>& grid,string word,int i,int j)
{
    vector<bool> mark (word.length(),false);
    for(int k=0;k<word.length();k++)
    {
        if(grid[i+k][j] == '-')
            grid[i+k][j] = word[k];
        else
            mark[k] = true;
    }
    return mark;
}

void unplacethewordhor(vector<vector<char>>& grid,string word,int i,int j,vector<bool>& mark)
{
    for(int k=0;k<word.length();k++)
    {
        if(mark[k] == false)
            grid[i][j+k] = '-';
    }
}

void unplacethewordver(vector<vector<char>>& grid,string word,int i,int j,vector<bool>& mark)
{
    for(int k=0;k<word.length();k++)
    {
        if(mark[k] == false)
            grid[i+k][j] = '-';
    }
}


void cw(vector<vector<char>>& grid,vector<string>& words,int cwi)
{
    if(cwi == words.size())
    {
        for(int i=0;i<grid.size();i++)
        {
            for(int j=0;j<grid[0].size();j++)
                cout << grid[i][j] << " ";
            cout << endl;
        }
        return;
    }
    for(int i=0;i<grid.size();i++)
    {
        for(int j=0;j<grid[0].size();j++)
        {
            if(canplacethewordhor(grid,words[cwi],i,j))
            {
                vector<bool> mark = placethewordhor(grid,words[cwi],i,j);
                cw(grid,words,cwi + 1);
                unplacethewordhor(grid,words[cwi],i,j,mark);
            }
            if(canplacethewordver(grid,words[cwi],i,j))
            {
                vector<bool> mark = placethewordver(grid,words[cwi],i,j);
                cw(grid,words,cwi + 1);
                unplacethewordver(grid,words[cwi],i,j,mark);
            }
        }
    }
}
int main(int argc,char** argv)
{
    vector<vector<char>> grid {{'+','+','+','+','+','+','+','+','+','-'},
                               {'-','+','+','+','+','+','+','+','+','-'},
                               {'-','-','-','-','-','-','-','+','+','-'},
                               {'-','+','+','+','+','+','+','+','+','-'},
                               {'-','+','+','+','+','+','+','+','+','-'},
                               {'-','+','+','+','+','-','-','-','-','-'},
                               {'-','-','-','-','-','-','+','+','+','-'},
                               {'-','+','+','+','+','+','+','+','+','-'},
                               {'+','-','-','-','-','-','-','-','-','-'},
                               {'+','+','+','+','+','+','+','+','+','+'}};
    vector<string> words {"GEOGRAPHY","PHYSICS","HISTORY","CIVICS","MATHS","CHEMISTRY"};
    cw(grid,words,0);
}