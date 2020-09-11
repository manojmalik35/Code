#include <vector>
#include<algorithm>

using namespace std;

int carFleet(int target, vector<int> &position, vector<int> &speed)
{
    vector<pair<int, double>> postime;
    for (int i = 0; i < position.size(); i++)
    {
        double time = (target - position[i]) * 1.0 / speed[i];
        postime.push_back({position[i], time});
    }

    sort(postime.begin(), postime.end());
    int fleets = position.size();
    int fl = postime.size() - 1;//fleet leader
    for (int i = fl; i > 0; i--)
    {
        if (postime[i - 1].second <= postime[fl].second)
            fleets--;
        else
            fl = i - 1;
    }

    return fleets;
}