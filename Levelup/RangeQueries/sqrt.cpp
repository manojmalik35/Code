#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int bs = 0; //block size
vector<int> arr;
vector<int> blocks;

int query(int l, int r)
{
    int sum = 0;
    while(l % bs != 0 && l <= r)
        sum += arr[l++];
    
    while(l + bs  - 1 <= r){
        sum += blocks[l / bs];
        l += bs;
    }

    while(l <= r)
        sum += arr[l++];
    
    return sum;
}

void update(int i, int val)
{
    int bi = i / bs;
    int diff = val - arr[i];
    arr[i] = val;

    blocks[bi] += diff;
}

void solve()
{
    int n;
    cin >> n;
    arr.resize(n);

    bs = ceil(sqrt(n));
    blocks.resize(bs);
    
    for (int i = 0; i < n; i++){
        cin >> arr[i];
        blocks[i / bs] += arr[i];
    }
    
    int q;
    cin >> q;
    while (q-- > 0)
    {
        char c;
        cin >> c;
        if (c == 'u')
        {
            int idx, val;
            cin >> idx >> val;
            update(idx, val);
        }
        else
        {
            int l, r;
            cin >> l >> r;
            cout << query(l, r) << "\n";
        }
    }
}

int main()
{
    int t;
    cin >> t;
    while (t-- > 0)
    {
        solve();
    }
    return 0;
}