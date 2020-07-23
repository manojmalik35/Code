#include <bits/stdc++.h>
using namespace std;
int arr[1050][1050];
int n;
struct node
{
    int horizontal, vertical, combined;
};
struct node dp[1050][1050];
int solver(int i, int j)
{
    int x = i;
    int y = j;
    int left = dp[i][j].horizontal - 1;
    int up = dp[i][j].vertical - 1;
    int range = min(left, up);
    int count = 0;
    while (count <= range)
    {
        count++;
        x--;
        y--;
        if (dp[x][y].combined == 1)
            break;
    }
    int tempx = INT_MAX;
    int tempy = INT_MAX;
    for (int k = x; k <= i; k++)
    {
        tempx = min(tempx, dp[k][y].horizontal - 1);
    }
    for (int z = y; z <= j; z++)
    {
        tempy = min(tempy, dp[x][z].vertical - 1);
    }
    return ((count * count) + count + count + 1) + max((i - x + 1) * tempx, (j - y + 1) * tempy);
}
int solverr()
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            dp[i][j].horizontal = 1;
            dp[i][j].vertical = 1;
            dp[i][j].combined = 1;
        }
    }
    for (int i = 1; i < n; i++)
    {
        if (arr[i][0] > arr[i - 1][0])
        {
            dp[i][0].vertical += dp[i - 1][0].vertical;
        }
    }
    for (int j = 1; j < n; j++)
    {
        if (arr[0][j] > arr[0][j - 1])
        {
            dp[0][j].horizontal += dp[0][j - 1].horizontal;
        }
    }
    for (int i = 1; i < n; i++)
    {
        for (int j = 1; j < n; j++)
        {
            if (arr[i][j] > arr[i - 1][j])
            {
                dp[i][j].vertical = dp[i][j].vertical + dp[i - 1][j].vertical;
            }
            if (arr[i][j] > arr[i][j - 1])
            {
                dp[i][j].horizontal = dp[i][j].horizontal + dp[i][j - 1].horizontal;
            }
            if (arr[i][j] > arr[i - 1][j] && arr[i][j] > arr[i][j - 1] && arr[i - 1][j] > arr[i - 1][j - 1] && arr[i][j - 1] > arr[i - 1][j - 1])
            {
                dp[i][j].combined = solver(i, j);
            }
        }
    }

    int res = -1;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (res < dp[i][j].horizontal)
                res = dp[i][j].horizontal;

            if (res < dp[i][j].vertical)
                res = dp[i][j].vertical;

            if (res < dp[i][j].combined)
                res = dp[i][j].combined;
        }
    }

    return res;
}
int main()
{
    int t, ans;
    scanf("%d", &t);
    while (t--)
    {
        scanf("%d", &n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                scanf("%d", &arr[i][j]);
        ans = solverr();
        printf("%d\n", ans);
    }
    return 0;
}