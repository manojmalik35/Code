#include<iostream>
#include<vector>
#include<string>
#include<queue>

using namespace std;

class State
{
    public:
        int j1;
        int j2;
        int level;
        string psf;

        State()
        {
        }
        State(int j1, int j2, int level, string psf)
        {
            this->j1 = j1;
            this->j2 = j2;
            this->level = level;
            this->psf = psf;
        }
};

void wj(int j1, int j2, int wr, vector<vector<bool>>& visited)//wr = water required
{
    queue<State> q;
    q.push(State(0, 0, 0, ""));
    while(q.size() > 0)
    {
        State rem = q.front();
        q.pop();

        if(visited[rem.j1][rem.j2])
            continue;

        visited[rem.j1][rem.j2] = true;

        if(rem.j1 == wr || rem.j2 == wr)
        {
            cout << rem.psf << " @ " << rem.level << endl;
            break;
        }

        if(rem.j1 < j1 && visited[j1][rem.j2] == false) //fill3
            q.push(State(j1, rem.j2, rem.level + 1, rem.psf + "f" + to_string(j1) + " "));

        if(rem.j2 < j2 && visited[rem.j1][j2] == false) //fill5
            q.push(State(rem.j1, j2, rem.level + 1, rem.psf + "f" + to_string(j2) + " "));

        if(rem.j1 > 0 && visited[0][rem.j2] == false) // empty3
            q.push(State(0, rem.j2, rem.level + 1, rem.psf + "e" + to_string(j1) + " "));

        if(rem.j2 > 0 && visited[rem.j1][0] == false) // empty5
            q.push(State(rem.j1, 0, rem.level + 1, rem.psf + "e" + to_string(j2) + " "));

        if(rem.j1 > 0 && rem.j2 < j2) // move 3 to 5
        {
            int space = j2 - rem.j2;
            State ns;
            if(space > rem.j1)
            {
                ns.j1 = 0;
                ns.j2 = rem.j2 + rem.j1;
            }
            else
            {
                ns.j1 = rem.j1 - space;
                ns.j2 = j2;
            }
            ns.level = rem.level + 1;
            ns.psf = rem.psf + "m" + to_string(j1) + to_string(j2) + " ";
            if(visited[ns.j1][ns.j2] == false)
                q.push(ns);
        }

        if(rem.j2 > 0 && rem.j1 < j1) // move 5 to 3 
        {
            int space = j1 - rem.j1;
            State ns;
            if(space > rem.j2)
            {
                ns.j2 = 0;
                ns.j1 = rem.j1 + rem.j2;
            }
            else
            {
                ns.j2 = rem.j2 - space;
                ns.j1 = j1;
            }
            ns.level = rem.level + 1;
            ns.psf = rem.psf + "m" + to_string(j2) + to_string(j1) + " ";
            if(visited[ns.j1][ns.j2] == false)
                q.push(ns);
        }
    }
}
int main(int argc, char** argv)
{
    int j1 = 3;
    int j2 = 5;
    vector<vector<bool>> visited (j1 + 1, vector<bool> (j2 + 1, false));
    wj(3, 5, 4, visited);
}