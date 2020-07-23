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
    if(j+word.length() < grid[0].size() && grid[i][j+word.length()] != '+')
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
    if(i+word.length() < grid.size() && grid[i+word.length()][j] != '+')
        return false;
    for(int k=0;k<word.length();k++)
    {
        if(grid[i+k][j] != '-' && grid[i+k][j] != word[k])
            return false;
    }

    return true;
}

void placethewordhor(vector<vector<char>>& grid,string word,int i,int j,vector<bool>& mark)
{
    for(int k=0;k<word.length();k++)
    {
        if(grid[i][j+k] == '-')
            grid[i][j+k] = word[k];
        else
            mark[k] = true; //ki y character humne nhi daala pehle se dala hua tha
    }
}

void placethewordver(vector<vector<char>>& grid,string word,int i,int j,vector<bool>& mark)
{
    for(int k=0;k<word.length();k++)
    {
        if(grid[i+k][j] == '-')
            grid[i+k][j] = word[k];
        else
            mark[k] = true;
    }
}

void unplacethewordhor(vector<vector<char>>& grid,string word,int i,int j,vector<bool>& mark)
{
    for(int k=0;k<word.length();k++)
    {
        if(mark[k] == false)
            grid[i][j+k] = '-';
        else
            mark[k] = false;   
    }
}

void unplacethewordver(vector<vector<char>>& grid,string word,int i,int j,vector<bool>& mark)
{
    for(int k=0;k<word.length();k++)
    {
        if(mark[k] == false)
            grid[i+k][j] = '-';
        else    
            mark[k] = false;
    }
}

//boxes on levels
void crossword(vector<vector<char>>& grid,vector<string>& words,int bno,vector<bool>& wused)
{
    if(bno == grid.size() * grid[0].size())
    {
        for(int i=0;i<grid.size();i++)
        {
            for(int j=0;j<grid[0].size();j++)
                cout << grid[i][j] << " ";
            cout << endl;
        }
        cout << "---------------------------" << endl;
        return;
    }
    int i = bno / grid.size();
    int j = bno % grid[0].size();
    int cc=0;
    if(grid[i][j] != '+')
    {
        for(int m=0;m<words.size();m++)
        {
            string word=words[m];
            if(canplacethewordhor(grid,word,i,j) && wused[m] == false)
            {
                vector<bool> mark (word.length(),false);
                placethewordhor(grid,word,i,j,mark);
                wused[m] = true;
                cc++;
                crossword(grid,words,bno+1,wused);
                wused[m] = false;
                unplacethewordhor(grid,word,i,j,mark);
            }
            if(canplacethewordver(grid,word,i,j) && wused[m] == false)
            {
                vector<bool> mark (word.length(),false);
                placethewordver(grid,word,i,j,mark);
                wused[m] = true;
                cc++;
                crossword(grid,words,bno+1,wused);
                wused[m] = false;
                unplacethewordver(grid,word,i,j,mark);
            }
        }

        if(cc == 0 && grid[i][j] != '-')
            crossword(grid,words,bno + 1,wused);
    }
    else
        crossword(grid,words,bno + 1,wused);
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
    vector<bool> wused (words.size(),false);
    crossword(grid,words,0,wused);

}