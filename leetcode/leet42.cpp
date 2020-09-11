#include <vector>
#include <stack>

using namespace std;

int trap(vector<int> &arr)
{
    vector<int> lmax(arr.size(), 0);
    vector<int> rmax(arr.size(), 0);

    int prev = -1;
    for (int i = 0; i < lmax.size(); i++)
    {
        lmax[i] = max(arr[i], prev);
        prev = lmax[i];
    }

    prev = -1;
    for (int i = rmax.size() - 1; i >= 0; i--)
    {
        rmax[i] = max(arr[i], prev);
        prev = rmax[i];
    }

    int water = 0;
    for (int i = 0; i < arr.size(); i++)
    {
        int ew = min(lmax[i], rmax[i]) - arr[i]; //eligible water
        water += ew;
    }

    return water;
}

int trap2(vector<int> &arr)
{
    stack<int> st;
    int water = 0;
    for(int i = 0; i < arr.size(); i++){
        while(st.size() != 0 && arr[st.top()] < arr[i]){
            int h = arr[st.top()]; st.pop();
            if(st.size() == 0)
                break;

            int oh = min(arr[i], arr[st.top()]) - h;//overall height
            int width = i - st.top() - 1;
            water += oh * width;
        }
        st.push(i);
    }

    return water;
}