#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>

using namespace std;

vector<int> intersection(vector<int> &nums1, vector<int> &nums2) //leet 349
{
    vector<int> ans;
    unordered_set<int> set;
    for (int i : nums1)
        set.insert(i);

    for (int i : nums2)
    {
        if (set.count(i) != 0)
        {
            ans.push_back(i);
            set.erase(i);
        }
    }

    return ans;
}

vector<int> intersect(vector<int> &nums1, vector<int> &nums2) //leet 350
{
    unordered_map<int, int> map;
    for (int i : nums1)
        map[i]++;

    vector<int> ans;
    for (int i : nums2)
    {
        if (map[i] > 0)
        {
            ans.push_back(i);
            map[i]--;
        }

        if (map[i] == 0)
            map.erase(i);
    }

    return ans;
}

int longestConsecutive(vector<int> &nums) //leet 128
{
    unordered_set<int> set;
    for (int ele : nums)
        set.insert(ele);

    int len = 0;
    for (int ele : nums)
    {
        if (set.count(ele) == 0)
            continue;

        set.erase(ele);
        int prev = ele - 1;
        int next = ele + 1;

        while (set.count(prev) != 0)
            set.erase(prev--);

        while (set.count(next) != 0)
            set.erase(next++);

        len = max(len, next - prev - 1);
    }

    return len;
}

int longestArithSeqLength(vector<int> &A) //leet 1027
{
    vector<unordered_map<int, int>> dp(A.size());

    int maxLen = 0;
    for (int i = 1; i < A.size(); i++)
    {
        for (int j = 0; j < i; j++)
        {
            int d = A[i] - A[j];

            dp[i][d] = dp[j].count(d) > 0 ? dp[j][d] + 1 : 2;
            maxLen = max(maxLen, dp[i][d]);
        }
    }

    return maxLen;
}

int main()
{
    

    return 0;
}