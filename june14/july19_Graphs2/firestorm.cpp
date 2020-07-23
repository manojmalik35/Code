#include<iostream>
#include<vector>
#include<queue>

using namespace std;

class Point{
    public:
        int x;
        int y;
        int t;
        Point(int x, int y, int t){
            this->x = x;
            this->y = y;
            this->t = t;
        }
};
    
void solvefs(vector<vector<int>>& city, int time){
    queue<Point> q;
    for(int i =0; i < city.size(); i++){
        for(int j = 0; j < city[0].size(); j++){
            if(city[i][j] == 1)
                q.push(Point(i, j, 1));
        }
    }

    while(q.size() > 0){
        Point rem = q.front();
        q.pop();

        if(rem.t == time + 2)
            break;

        if(city[rem.x][rem.y] < 0)
            continue;
            
        city[rem.x][rem.y] = -rem.t;

        if(rem.x - 1 >= 0 && city[rem.x - 1][rem.y] == 0)
            q.push(Point(rem.x - 1, rem.y, rem.t + 1));
        if(rem.y - 1 >= 0 && city[rem.x][rem.y - 1] == 0)
            q.push(Point(rem.x, rem.y - 1, rem.t + 1));
        if(rem.x + 1 < city.size() && city[rem.x + 1][rem.y] == 0)
            q.push(Point(rem.x + 1, rem.y, rem.t + 1));
        if(rem.y + 1 < city[0].size() && city[rem.x][rem.y + 1] == 0)
            q.push(Point(rem.x, rem.y + 1, rem.t + 1));
    }
}

int main(int argc, char** argv)
{
    vector<vector<int>> city {{1, 2, 0, 2, 0, 0, 0},
                              {0, 2, 0, 2, 0, 1, 0},
                              {0, 0, 0, 2, 0, 0, 0},
                              {2, 2, 2, 2, 2, 2, 0},
                              {0, 2, 1, 0, 0, 0, 0},
                              {0, 2, 0, 0, 0, 0, 0},
                              {0, 2, 0, 0, 0, 0, 0}};
                            
    solvefs(city, 3);
    for(int i = 0; i < city.size(); i++)
    {
        for(int j = 0; j < city[0].size(); j++)
            cout << city[i][j] << " ";
        cout << endl;
    }
}