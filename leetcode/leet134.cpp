#include <vector>

using namespace std;

int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
    int ep = 0;
    int start = 0;
    int tp =0, td = 0;
    for(int i = 0; i < gas.size(); i++)
    {
        ep += gas[i] - cost[i];
        tp += gas[i];
        td += cost[i];
        if(ep < 0){
            start = i + 1;
            ep = 0;
        }
    }
    
    if(start == gas.size())
        return -1;
    else if(tp >= td)
        return start;
    
    return -1;
}