//RACETIME spoj

#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

int bs = 0; //block size
vector<int> arr;
vector<vector<pair<int, int>>> blocks;

int query(int l, int r, int x)
{
    int count = 0;
    while (l % bs != 0 && l <= r)
        count = arr[l++] <= x ? count + 1 : count;

    while (l + bs - 1 <= r)
    {
        count += lower_bound(blocks[l / bs].begin(), blocks[l / bs].end(), make_pair(x, (int)1e8)) - blocks[l / bs].begin();
        l += bs;
    }

    while (l <= r)
        count = arr[l++] <= x ? count + 1 : count;

    return count;
}

void update(int i, int val)
{
    int bi = i / bs;
    arr[i] = val;

    for (int j = 0; j < blocks[bi].size(); j++)
    {
        if (blocks[bi][j].second == i)
        {
            blocks[bi][j].first = val;
            break;
        }
    }

    sort(blocks[bi].begin(), blocks[bi].end());
}

void solve()
{
    int n, q;
    cin >> n >> q;
    arr.resize(n);

    bs = ceil(sqrt(n));
    blocks.resize(bs);

    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
        blocks[i / bs].push_back({arr[i], i});
    }

    for (int i = 0; i < blocks.size(); i++)
        sort(blocks[i].begin(), blocks[i].end());

    while (q-- > 0)
    {
        char c;
        cin >> c;
        if (c == 'M')
        {
            int idx, val;
            cin >> idx >> val;
            idx--;
            update(idx, val);
        }
        else
        {
            int l, r, x;
            cin >> l >> r >> x;
            l--;
            r--; //bcoz vo position dega or hume index chahiye
            cout << query(l, r, x) << "\n";
        }
    }
}

int main()
{
    int t = 1;
    // cin >> t;
    while (t-- > 0)
    {
        solve();
    }
    return 0;
}