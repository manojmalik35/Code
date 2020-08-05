import java.util.*;

public class dp {

    static void display(int[] dp) {
        for (int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    static void display(int[][] dp) {
        for (int[] ar : dp) {
            for (int ele : ar)
                System.out.print(ele + " ");
            System.out.println();
        }
    }

    static int minCostClimbingStairs_M(int[] cost, int idx, int[] dp) {
        if (idx <= 1)
            return dp[idx] = cost[idx];

        if (dp[idx] != 0)
            return dp[idx];

        int nm1 = minCostClimbingStairs_M(cost, idx - 1, dp);
        int nm2 = minCostClimbingStairs_M(cost, idx - 2, dp);

        dp[idx] = Math.min(nm1, nm2) + (idx == cost.length ? 0 : cost[idx]);
        return dp[idx];
    }

    public static int minCostClimbingStairs_T(int[] cost, int[] dp) {
        for (int idx = 0; idx <= cost.length; idx++) {
            if (idx <= 1) {
                dp[idx] = cost[idx];
                continue;
            }

            int nm1 = dp[idx - 1];
            int nm2 = dp[idx - 2];

            dp[idx] = Math.min(nm1, nm2) + (idx == cost.length ? 0 : cost[idx]);
        }

        // display(dp);
        return dp[cost.length];
    }

    public static int minCostClimbingStairs_B(int[] cost) {// Best
        int a = cost[0];
        int b = cost[1];
        int sum = Math.min(a, b);
        for (int i = 2; i <= cost.length; i++) {
            sum = Math.min(a, b);
            sum += (i == cost.length ? 0 : cost[i]);
            a = b;
            b = sum;
        }
        return sum;
    }

    public static int minCostClimbingStairs(int[] cost) {// leetcode 746
        int[] dp = new int[cost.length + 1];
        // int ans = minCostClimbingStairs_M(cost, cost.length, dp);
        // int ans = minCostClimbingStairs_T(cost, dp);
        int ans = minCostClimbingStairs_B(cost);
        return ans;
    }

    static int minPathSum_Rec(int[][] grid, int i, int j, int[][] dp) {
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return dp[i][j] = grid[i][j];

        if (dp[i][j] != 0)
            return dp[i][j];

        int rsum = j + 1 < grid[0].length ? minPathSum_Rec(grid, i, j + 1, dp) : (int) 1e7;
        int dsum = i + 1 < grid.length ? minPathSum_Rec(grid, i + 1, j, dp) : (int) 1e7;

        return dp[i][j] = Math.min(rsum, dsum) + grid[i][j];
    }

    static int minPathSum_Tab(int[][] grid, int[][] dp) {
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length - 1 && j == grid[0].length - 1) {
                    dp[i][j] = grid[i][j];
                    continue;
                }

                int rsum = j + 1 < grid[0].length ? dp[i][j + 1] : (int) 1e7;
                int dsum = i + 1 < grid.length ? dp[i + 1][j] : (int) 1e7;

                dp[i][j] = Math.min(rsum, dsum) + grid[i][j];
            }
        }
        return dp[0][0];
    }

    public static int minPathSum(int[][] grid) {// leetcode 64
        int[][] dp = new int[grid.length][grid[0].length];
        // int ans = minPathSum_Rec(grid, 0, 0, dp);
        int ans = minPathSum_Tab(grid, dp);
        display(dp);
        return ans;
    }

    static int goldMine_Rec(int[][] grid, int i, int j, int[][] dp) {
        if (j == grid[0].length - 1)
            return dp[i][j] = grid[i][j];

        if (dp[i][j] != 0)
            return dp[i][j];

        int up = i - 1 >= 0 ? goldMine_Rec(grid, i - 1, j + 1, dp) : (int) -1e7;
        int st = goldMine_Rec(grid, i, j + 1, dp);
        int dw = i + 1 < grid.length ? goldMine_Rec(grid, i + 1, j + 1, dp) : (int) -1e7;

        return dp[i][j] = Math.max(up, Math.max(st, dw)) + grid[i][j];
    }

    static int goldMine_Tab(int[][] grid, int[][] dp) {
        for (int j = grid[0].length - 1; j >= 0; j--) {
            for (int i = 0; i < grid.length; i++) {
                if (j == grid[0].length - 1) {
                    dp[i][j] = grid[i][j];
                    continue;
                }

                int up = i - 1 >= 0 ? dp[i - 1][j + 1] : (int) -1e7;
                int st = dp[i][j + 1];
                int dw = i + 1 < grid.length ? dp[i + 1][j + 1] : (int) -1e7;

                dp[i][j] = Math.max(up, Math.max(st, dw)) + grid[i][j];
            }
        }

        int max = 0;
        for (int i = 0; i < dp.length; i++)
            max = Math.max(max, dp[i][0]);

        return max;
    }

    static int goldMine(int[][] grid) {// GFG
        int[][] dp = new int[grid.length][grid[0].length];
        int maxGold = 0;
        for (int i = 0; i < grid.length; i++)
            maxGold = Math.max(maxGold, goldMine_Rec(grid, i, 0, dp));

        // int ans = maxGold;
        int ans = goldMine_Tab(grid, dp);
        return ans;
    }

    static int nowtpp_Rec(int n, int[] dp) {
        if (n <= 2)
            return dp[n] = n;

        if (dp[n] != 0)
            return dp[n];

        int nm1 = nowtpp_Rec(n - 1, dp);
        int nm2 = nowtpp_Rec(n - 2, dp);

        return dp[n] = nm1 + (n - 1) * nm2;
    }

    static int nowtpp_Tab(int n, int[] dp) {
        for (int i = 0; i <= n; i++) {
            if (i <= 2) {
                dp[i] = i;
                continue;
            }

            dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
        }

        return dp[n];
    }

    static int nowtpp_B(int n) {
        if (n <= 2)
            return n;

        int a = 1;
        int b = 2;
        int s = b + 2 * a;
        for (int i = 3; i <= n; i++) {
            s = b + (i - 1) * a;
            a = b;
            b = s;
        }
        return s;
    }

    static int nowtpp(int n) {// No of ways to pair people GFG
        // int[] dp = new int[n + 1];
        // int ans = nowtpp_Rec(n, dp);
        // int ans = nowtpp_Tab(n, dp);
        int ans = nowtpp_B(n);
        return ans;
    }

    static int partitionNEleKSets_Rec(int n, int k, int[][] dp) {
        if (k == 1 || n == k)
            return dp[n][k] = 1;

        if (dp[n][k] != 0)
            return dp[n][k];

        int newSet = partitionNEleKSets_Rec(n - 1, k - 1, dp);
        int partOfExistingSets = partitionNEleKSets_Rec(n - 1, k, dp) * k;

        return dp[n][k] = newSet + partOfExistingSets;
    }

    static int partitionNEleKSets_Tab(int n, int k, int[][] dp) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i == 0 || j == 0 || j > i)
                    dp[i][j] = 0;
                else if (j == 1 || i == j)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] * j;
            }
        }

        return dp[n][k];
    }

    static int partitionNEleKSets(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        // int ans = partitionNEleKSets_Rec(n, k, dp);
        int ans = partitionNEleKSets_Tab(n, k, dp);
        display(dp);
        return ans;
    }

    static int longestPalindromeSubseq_Rec(String s, int i, int j, int[][] dp) {
        if (i > j)
            return 0;

        if (i == j)
            return dp[i][j] = 1;

        if (dp[i][j] != 0)
            return dp[i][j];

        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = longestPalindromeSubseq_Rec(s, i + 1, j - 1, dp) + 2;

        int withoutJ = longestPalindromeSubseq_Rec(s, i, j - 1, dp);
        int withoutI = longestPalindromeSubseq_Rec(s, i + 1, j, dp);

        return dp[i][j] = Math.max(withoutI, withoutJ);
    }

    static int longestPalindromeSubseq_Tab(String s, int[][] dp) {
        for (int gap = 0; gap < dp[0].length; gap++) {
            for (int i = 0, j = gap; j < dp[0].length; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
            }
        }

        return dp[0][dp.length - 1];
    }

    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        // int ans = longestPalindromeSubseq_Rec(s, 0, s.length() - 1, dp);
        int ans = longestPalindromeSubseq_Tab(s, dp);
        return ans;
    }

    static int countPalindromicSubseq_Rec(String s, int i, int j, int[][] dp) {
        if (i > j)
            return 0;

        if (i == j)
            return dp[i][j] = 1;

        if (dp[i][j] != 0)
            return dp[i][j];

        int withoutI = countPalindromicSubseq_Rec(s, i + 1, j, dp);
        int withoutJ = countPalindromicSubseq_Rec(s, i, j - 1, dp);
        int w = withoutI + withoutJ;
        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = w + 1;
        else
            return dp[i][j] = w - countPalindromicSubseq_Rec(s, i + 1, j - 1, dp);
    }

    static int countPalindromicSubseq_Tab(String s, int[][] dp) {
        for (int gap = 0; gap < dp[0].length; gap++) {
            for (int i = 0, j = gap; j < dp[0].length; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1;
                else if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] + 1;
                else
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
            }
        }

        return dp[0][dp.length - 1];
    }

    static int countPalindromicSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int ans = countPalindromicSubseq_Rec(s, 0, s.length() - 1, dp);
        // int ans = countPalindromicSubseq_Tab(s, dp);
        return ans;
    }

    static int count = 0;
    static int longestLen = 1;

    static boolean countAndLongestPalindromicSubstring_Rec(String s, int i, int j, boolean[][] dp) {
        if (i > j)
            return false;

        if (i == j)
            return dp[i][j] = true;

        if (i + 1 == j) {
            if (dp[i][j] == false && s.charAt(i) == s.charAt(j)) {
                count++;
                longestLen = 2;
            }

            return dp[i][j] = s.charAt(i) == s.charAt(j);
        }

        if (dp[i][j])
            return dp[i][j];

        boolean rres = false;
        if (s.charAt(i) == s.charAt(j)) {
            rres = countAndLongestPalindromicSubstring_Rec(s, i + 1, j - 1, dp);
            count = rres ? count + 1 : count;
            longestLen = Math.max(longestLen, j - i + 1);
        }

        countAndLongestPalindromicSubstring_Rec(s, i, j - 1, dp);
        countAndLongestPalindromicSubstring_Rec(s, i + 1, j, dp);
        return dp[i][j] = rres;
    }

    static int countPalindromicSubstring_Tab(String s, boolean[][] dp) {
        int count = 0;
        for (int gap = 0; gap < dp[0].length; gap++) {
            for (int i = 0, j = gap; j < dp[0].length; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];

                if (dp[i][j])
                    count++;
            }
        }

        return count;
    }

    static int countPalindromicSubstring(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        // int ans = countPalindromicSubstring_Tab(s, dp);
        count = s.length();
        countAndLongestPalindromicSubstring_Rec(s, 0, s.length() - 1, dp);
        int ans = count;// static vala
        return ans;
    }

    static int longestPalindromicSubstring_Tab(String s, boolean[][] dp) {
        int maxLen = 0;
        for (int gap = 0; gap < dp[0].length; gap++) {
            for (int i = 0, j = gap; j < dp[0].length; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];

                if (dp[i][j] && gap + 1 > maxLen)
                    maxLen = Math.max(maxLen, gap + 1);
            }
        }

        return maxLen;
    }

    static String longestPalindromicSubstring_Best(String s) {// O(1) space n2 time
        if (s.length() == 0 || s.length() == 1)
            return s;
        int maxLen = 1;
        int si = 0;

        // for even lengths
        int mid = 1;
        while (mid < s.length()) {
            if (s.charAt(mid) == s.charAt(mid - 1)) {
                int l = mid - 1;
                int r = mid;
                int templen = 0;
                while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                    templen += 2;
                    l--;
                    r++;
                }

                if (templen > maxLen) {
                    maxLen = templen;
                    si = l + 1;
                }
            }
            mid++;
        }

        // for odd lengths
        mid = 1;
        while (mid < s.length() - 1) {
            if (s.charAt(mid - 1) == s.charAt(mid + 1)) {
                int l = mid - 1;
                int r = mid + 1;
                int templen = 1;
                while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                    templen += 2;
                    l--;
                    r++;
                }

                if (templen > maxLen) {
                    maxLen = templen;
                    si = l + 1;
                }
            }
            mid++;
        }

        String ans = s.substring(si, si + maxLen);
        return ans;
    }

    static int longestPalindromicSubstring(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        // int ans = longestPalindromicSubstring_Tab(s, dp);
        // countAndLongestPalindromicSubstring_Rec(s, 0, s.length() - 1, dp);
        // int ans = longestLen;
        String ans = longestPalindromicSubstring_Best(s);
        return ans.length();
    }

    static int longestCommonSubseq_Rec(String s1, String s2, int i, int j, int[][] dp) {
        if (i == s1.length() || j == s2.length())
            return 0;

        if (dp[i][j] != 0)
            return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j))
            return dp[i][j] = longestCommonSubseq_Rec(s1, s2, i + 1, j + 1, dp) + 1;

        int one = longestCommonSubseq_Rec(s1, s2, i, j + 1, dp);
        int two = longestCommonSubseq_Rec(s1, s2, i + 1, j, dp);

        return dp[i][j] = Math.max(one, two);

    }

    static int longestCommonSubseq_Tab(String s1, String s2, int[][] dp) {
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                if (i == s1.length() || j == s2.length()) {
                    dp[i][j] = 0;
                    continue;
                }

                if (s1.charAt(i) == s2.charAt(j))
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }

        return dp[0][0];
    }

    static int longestCommonSubseq(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        // int ans = longestCommonSubseq_Rec(s1, s2, 0, 0, dp);
        int ans = longestCommonSubseq_Tab(s1, s2, dp);
        return ans;
    }

    static int lcst = 0;

    static int longestCommonSubstring_Rec(String s1, String s2, int i, int j, int[][] dp) {

        if (i == s1.length() || j == s2.length())
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        int ans = 0;
        if (s1.charAt(i) == s2.charAt(j)) {
            ans = longestCommonSubstring_Rec(s1, s2, i + 1, j + 1, dp) + 1;
            lcst = Math.max(lcst, ans);
        }

        longestCommonSubstring_Rec(s1, s2, i, j + 1, dp);
        longestCommonSubstring_Rec(s1, s2, i + 1, j, dp);

        return dp[i][j] = ans;
    }

    static int longestCommonSubstring_Tab(String s1, String s2, int[][] dp) {
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i - 1 >= 0 && j - 1 >= 0)
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    else
                        dp[i][j] = 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max;
    }

    static int longestCommonSubstring(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        for (int[] ar : dp)
            Arrays.fill(ar, -1);
        lcst = 0;
        longestCommonSubstring_Rec(s1, s2, 0, 0, dp);
        int ans = lcst;
        // int ans = longestCommonSubstring_Tab(s1, s2, dp);
        return ans;
    }

    static int minDistance_Rec(String s1, String s2, int i, int j, int[][] dp) {
        if (i == s1.length() && j == s2.length())
            return dp[i][j] = 0;

        if (i == s1.length())
            return dp[i][j] = s2.length() - j;

        if (j == s2.length())
            return dp[i][j] = s1.length() - i;

        if (dp[i][j] != 0)
            return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j))
            return dp[i][j] = minDistance_Rec(s1, s2, i + 1, j + 1, dp);

        int one = minDistance_Rec(s1, s2, i, j + 1, dp);
        int two = minDistance_Rec(s1, s2, i + 1, j, dp);

        return dp[i][j] = Math.min(one, two) + 1;
    }

    static int minDistance_Tab(String s1, String s2, int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public static int minDistance(String s1, String s2) {// leet583
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int ans = minDistance_Rec(s1, s2, 0, 0, dp);
        // int ans = minDistance_Tab(s1, s2, dp);
        // display(dp);
        return ans;
    }

    static int editDistance_Rec(String s1, String s2, int i, int j, int[][] dp) {
        if (i == 0 && j == 0)
            return dp[i][j] = 0;

        if (i == 0)
            return dp[i][j] = j;

        if (j == 0)
            return dp[i][j] = i;

        if (dp[i][j] != 0)
            return dp[i][j];

        if (s1.charAt(i - 1) == s2.charAt(j - 1))
            return dp[i][j] = editDistance_Rec(s1, s2, i - 1, j - 1, dp);

        int one = editDistance_Rec(s1, s2, i - 1, j - 1, dp);
        int two = editDistance_Rec(s1, s2, i, j - 1, dp);
        int three = editDistance_Rec(s1, s2, i - 1, j, dp);

        return dp[i][j] = Math.min(one, Math.min(two, three)) + 1;
    }

    static int editDistance_Tab(String s1, String s2, int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public static int editDistance(String s1, String s2) {// leet72
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        // int ans = editDistance_Tab(s1, s2, dp);
        int ans = editDistance_Rec(s1, s2, s1.length(), s2.length(), dp);
        display(dp);
        return ans;
    }

    static int numDistinct_Rec(String s, String t, int i, int j, int[][] dp) {
        if (j == t.length())
            return dp[i][j] = 1;

        if (i == s.length())
            return dp[i][j] = 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        int count = 0;
        if (s.charAt(i) != t.charAt(j))
            return dp[i][j] = numDistinct_Rec(s, t, i + 1, j, dp);

        count += numDistinct_Rec(s, t, i + 1, j + 1, dp);
        count += numDistinct_Rec(s, t, i + 1, j, dp);

        return dp[i][j] = count;
    }

    static int numDistinct_Tab(String s, String t, int[][] dp) {
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                if (j == t.length())
                    dp[i][j] = 1;
                else if (i == s.length())
                    dp[i][j] = 0;
                else if (s.charAt(i) != t.charAt(j))
                    dp[i][j] = dp[i + 1][j];
                else
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
            }
        }

        return dp[0][0];
    }

    public static int numDistinct(String s, String t) {// leet 115
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        // for (int[] ar : dp)
        // Arrays.fill(ar, -1);
        // int ans = numDistinct_Rec(s, t, 0, 0, dp);
        int ans = numDistinct_Tab(s, t, dp);
        return ans;
    }

    static int coinChangePerm(int[] coins, int tar) {
        int[] dp = new int[tar + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j])
                    dp[i] += dp[i - coins[j]];
            }
        }

        return dp[tar];
    }

    static int coinChangeComb(int[] coins, int tar) {
        int[] dp = new int[tar + 1];
        dp[0] = 1;
        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i < dp.length; i++) {
                if (i >= coins[j])
                    dp[i] += dp[i - coins[j]];
            }
        }

        return dp[tar];
    }

    static int linearEquationNVariables(int[] coeff, int rhs) {// GFG
        int[] dp = new int[rhs + 1];
        dp[0] = 1;
        for (int j = 0; j < coeff.length; j++) {
            for (int i = 1; i < dp.length; i++) {
                if (i >= coeff[j])
                    dp[i] += dp[i - coeff[j]];
            }
        }

        return dp[rhs];
    }

    static int minHeightCoinChange_Rec(int[] coins, int tar, int[] dp) {// permutation h... combination vle se saare
                                                                        // cases pass ni hore the
        if (tar == 0)
            return dp[tar] = 0;

        if (dp[tar] != 0)
            return dp[tar];

        int h = (int) 1e8;
        for (int c : coins) {
            if (tar - c >= 0) {
                int rres = minHeightCoinChange_Rec(coins, tar - c, dp);
                h = Math.min(h, rres);
            }
        }

        return dp[tar] = h + 1;
    }

    static int minHeightCoinChange_Tab(int[] coins, int tar, int[] dp) {
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int h = (int) 1e8;
            for (int c : coins) {
                if (i - c >= 0)
                    h = Math.min(h, dp[i - c]);
            }

            dp[i] = h + 1;
        }

        return dp[tar];
    }

    static int minHeightCoinChange_TabComb(int[] coins, int tar, int[] dp) {
        Arrays.fill(dp, (int) 1e8);
        dp[0] = 0;
        for (int c : coins) {
            for (int i = 1; i < dp.length; i++) {
                if (i - c >= 0)
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
            }
        }

        return dp[tar];
    }

    public static int minHeightCoinChange(int[] coins, int amount) {// leet 322
        int[] dp = new int[amount + 1];
        // int ans = minHeightCoinChange_Rec(coins, amount, dp);
        int ans = minHeightCoinChange_Tab(coins, amount, dp);
        display(dp);
        return ans >= (int) 1e8 ? -1 : ans;
    }

    public int combinationSum4(int[] nums, int target) {// leet 377
        return coinChangePerm(nums, target);
    }

    static boolean targetSumSubsets(int[] arr, int tar) {
        boolean[][] dp = new boolean[arr.length + 1][tar + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j == 0)
                    dp[i][j] = true;
                else if (i == 0)
                    dp[i][j] = false;
                else {
                    dp[i][j] = dp[i - 1][j];
                    if (arr[i - 1] <= j)
                        dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        return dp[arr.length][tar];
    }

    static int targetSumSubsets2(int[] arr, int tar) {
        int[][] dp = new int[arr.length + 1][tar + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j == 0)
                    dp[i][j] = 1;
                else if (i == 0)
                    dp[i][j] = 0;
                else {
                    dp[i][j] = dp[i - 1][j];
                    if (arr[i - 1] <= j)
                        dp[i][j] = dp[i][j] + dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        return dp[arr.length][tar];
    }

    static int zeroOneKnapsack(int[] weights, int[] values, int cap) {
        int[][] dp = new int[weights.length + 1][cap + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= weights[i - 1])
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }

        return dp[weights.length][cap];
    }

    static int unboundedKnapsack(int[] wts, int[] prices, int cap) {
        int[] dp = new int[cap + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < wts.length; j++) {
                if (i >= wts[j])
                    dp[i] = Math.max(dp[i], dp[i - wts[j]] + prices[j]);
            }
        }

        return dp[cap];
    }

    // static int findTargetSumWays_Rec(int[] nums, int idx, int tar, int[][] dp) {
    // if(idx == nums.length){
    // if(tar == 0)
    // return 1;
    // return 0;
    // }

    // if(dp[idx][tar] != -1)
    // return dp[idx][tar];

    // int count = 0;
    // count += findTargetSumWays_Rec(nums, idx + 1, tar - nums[idx], dp);
    // if()
    // count += findTargetSumWays_Rec(nums, idx + 1, tar + nums[idx], dp);

    // return dp[idx][tar] = count;
    // }

    // public static int findTargetSumWays(int[] nums, int S) {//leet 494
    // int[][] dp = new int[nums.length][S + 1];
    // }

    // LIS Type
    static int LIS_l2r(int[] arr, int[] dp) {// figure -> /
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    static int LIS_rtl(int[] arr, int[] dp) {// figure -> \
        dp[dp.length - 1] = 1;
        int ans = 1;
        for (int i = dp.length - 2; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < dp.length; j++) {
                if (arr[i] > arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    static int LBS(int[] arr) {// figure -> /\
        int[] lis = new int[arr.length];
        int[] lds = new int[arr.length];
        LIS_l2r(arr, lis);
        LIS_rtl(arr, lds);
        int ans = 0;
        for (int i = 0; i < lis.length; i++)
            ans = Math.max(ans, lis[i] + lds[i] - 1);

        return ans;
    }

    static int LDS_l2r(int[] arr, int[] dp) {// figure -> \
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    static int LDS_r2l(int[] arr, int[] dp) {// figure -> /
        dp[dp.length - 1] = 1;
        int ans = 1;
        for (int i = dp.length - 2; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < dp.length; j++) {
                if (arr[i] < arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    static int reverseLBS(int[] arr) {// figure -> \/
        int[] l2r = new int[arr.length];
        int[] r2l = new int[arr.length];
        LDS_l2r(arr, l2r);
        LDS_r2l(arr, r2l);
        int ans = 0;
        for (int i = 0; i < arr.length; i++)
            ans = Math.max(ans, l2r[i] + r2l[i] - 1);

        return ans;
    }

    static int binarySearch(int[] arr, int lo, int hi, int data) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (arr[mid] < data)
                lo = mid + 1;
            else
                hi = mid - 1;
        }

        return lo;
    }

    static int LIS_Eff(int[] arr) {// O(nlogn)
        int[] dp = new int[arr.length];
        int lo = 0, hi = 0;
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {

            if (dp[hi] < arr[i]) {
                dp[hi + 1] = arr[i];
                hi++;
            } else {
                int ceilIndex = binarySearch(dp, lo, hi, arr[i]);
                dp[ceilIndex] = arr[i];
            }
        }

        return hi + 1;
    }

    static int maxSumIncreasingSubseq(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int ans = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    static int minNoOfDeletionsToMakeArraySorted(int[] arr) {
        // arr.length - LIS.... Lis me modification krni padegi ki strictly increasing
        // na ho equal vale elements b lene h
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] >= arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return arr.length - maxLen;
    }

    public static int findNumberOfLIS(int[] nums) {// Leet 673
        if (nums.length == 0)
            return 0;

        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];

        int maxLen = 0;
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i])
                        count[i] += count[j];
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = count[i];
            } else if (dp[i] == maxLen)
                maxCount += count[i];
        }

        return maxCount;
    }

    public int maxEnvelopes(int[][] envelopes) {// leet 354
        if (envelopes.length == 0)
            return 0;

        Arrays.sort(envelopes, (int[] a, int[] b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return b[1] - a[1];
        });

        // return LIS_Eff(envelopes);

        int lis = 1;
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        for (int i = 1; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            lis = Math.max(lis, dp[i]);
        }

        return lis;
    }

    public int jobScheduling(int[] st, int[] et, int[] pr) {// leet 1235
        int[][] jobs = new int[st.length][3];
        for (int i = 0; i < st.length; i++)
            jobs[i] = new int[] { st[i], et[i], pr[i] };

        Arrays.sort(jobs, (a, b) -> {
            return a[1] - b[1]; // sort on end times
        });

        int[] dp = new int[st.length];
        int maxPro = jobs[0][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = jobs[i][2];
            for (int j = 0; j < i; j++) {
                if (jobs[i][0] >= jobs[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + jobs[i][2]);
            }
            maxPro = Math.max(maxPro, dp[i]);
        }

        return maxPro;
    }

    static int numDecodings_Rec(String s, int idx, int[] dp) {
        if (idx == s.length())
            return 1;

        if (dp[idx] != 0)
            return dp[idx];

        int num0 = s.charAt(idx) - '0';
        if (num0 == 0)
            return dp[idx] = 0;

        int ans = 0;
        ans += numDecodings_Rec(s, idx + 1, dp);

        if (idx + 1 < s.length()) {
            int num1 = s.charAt(idx + 1) - '0';
            int num01 = num0 * 10 + num1;

            if (num01 <= 26)
                ans += numDecodings_Rec(s, idx + 2, dp);
        }

        return dp[idx] = ans;
    }

    static int numDecodings_RecII(String s, int idx, int[] dp) {
        if (idx < 0)
            return 1;

        if (dp[idx] != 0)
            return dp[idx];

        int num0 = s.charAt(idx) - '0';

        int ans = 0;
        if (num0 != 0)
            ans += numDecodings_RecII(s, idx - 1, dp);

        if (idx - 1 >= 0) {
            int num1 = s.charAt(idx - 1) - '0';
            int num10 = num1 * 10 + num0;

            if (num10 >= 10 && num10 <= 26)
                ans += numDecodings_RecII(s, idx - 2, dp);
        }

        return dp[idx] = ans;
    }

    static int numDecodings_Tab(String s, int[] dp) {
        if (s.charAt(0) == '0')
            return 0;

        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            int numm1 = s.charAt(i - 1) - '0';

            int n = numm1 * 10 + num;
            if (n >= 10 && n <= 26)
                dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
            if (num != 0)
                dp[i] += dp[i - 1];
        }

        return dp[s.length() - 1];
    }

    public int numDecodings(String s) {// leet 91
        int[] dp = new int[s.length()];
        // int ans = numDecodings_Rec(s, 0, dp);
        // int ans = numDecodings_RecII(s, s.length() - 1, dp);
        int ans = numDecodings_Tab(s, dp);
        return ans;
    }

    static int mod = (int) 1e9 + 7;

    static long numDecodingsII_Rec(String s, int idx, long[] dp) {
        if (idx == s.length())
            return 1;

        if (dp[idx] != 0)
            return dp[idx];

        if (s.charAt(idx) == '0')
            return dp[idx] = 0;

        long ans = 0;
        char ch = s.charAt(idx);
        if (ch == '*') {
            ans = (ans + numDecodingsII_Rec(s, idx + 1, dp) * 9) % mod;
            if (idx + 1 < s.length()) {
                char ch1 = s.charAt(idx + 1);
                if (ch1 == '*')
                    ans = (ans + numDecodingsII_Rec(s, idx + 2, dp) * 15) % mod;
                else if (ch1 >= '0' && ch1 <= '6')
                    ans = (ans + numDecodingsII_Rec(s, idx + 2, dp) * 2) % mod;
                else
                    ans = (ans + numDecodingsII_Rec(s, idx + 2, dp)) % mod;
            }
        } else {
            ans = (ans + numDecodingsII_Rec(s, idx + 1, dp)) % mod;
            if (idx + 1 < s.length()) {
                char ch1 = s.charAt(idx + 1);
                if (ch1 == '*') {
                    if (ch == '1')
                        ans = (ans + numDecodingsII_Rec(s, idx + 2, dp) * 9) % mod;
                    else if (ch == '2')
                        ans = (ans + numDecodingsII_Rec(s, idx + 2, dp) * 6) % mod;
                } else {
                    int num0 = ch - '0';
                    int num1 = ch1 - '0';
                    int num01 = num0 * 10 + num1;
                    if (num01 <= 26)
                        ans = (ans + numDecodingsII_Rec(s, idx + 2, dp)) % mod;
                }
            }
        }

        return dp[idx] = ans;
    }

    static long numDecodingsII_Tab(String s, long[] dp) {
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '*') {
                dp[i] = (dp[i] + dp[i + 1] * 9) % mod;
                if (i + 1 < s.length()) {
                    char ch1 = s.charAt(i + 1);
                    if (ch1 == '*')
                        dp[i] = (dp[i] + dp[i + 2] * 15) % mod;
                    else if (ch1 >= '0' && ch1 <= '6')
                        dp[i] = (dp[i] + dp[i + 2] * 2) % mod;
                    else
                        dp[i] = dp[i] + dp[i + 2] % mod;
                }
            } else if (ch != '0') {
                dp[i] = (dp[i] + dp[i + 1]) % mod;
                if (i + 1 < s.length()) {
                    char ch1 = s.charAt(i + 1);
                    if (ch1 == '*') {
                        if (ch == '1')
                            dp[i] = (dp[i] + dp[i + 2] * 9) % mod;
                        else if (ch == '2')
                            dp[i] = (dp[i] + dp[i + 2] * 6) % mod;
                    } else {
                        int num = (ch - '0') * 10 + (ch1 - '0');
                        if (num <= 26)
                            dp[i] = (dp[i] + dp[i + 2]) % mod;
                    }
                }
            }
        }

        return dp[0];
    }

    public int numDecodingsII(String s) {// leet 639
        long[] dp = new long[s.length() + 1];
        dp[dp.length - 1] = 1;
        int ans = (int) numDecodingsII_Tab(s, dp);
        return ans;
    }

    static int[][] permutationsII(int[][] arr) {
        int[][] boxes = new int[arr.length * 3][4];
        // 0-> height 1->width 2->length 3->area
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            // 1st permutation
            boxes[k][0] = arr[i][0];
            if (arr[i][1] > arr[i][2]) {
                boxes[k][1] = arr[i][1];
                boxes[k][2] = arr[i][2];
            } else {
                boxes[k][1] = arr[i][2];
                boxes[k][2] = arr[i][1];
            }
            boxes[k][3] = boxes[k][1] * boxes[k][2];
            k++;

            // 2nd permutation
            boxes[k][0] = arr[i][1];
            if (arr[i][0] > arr[i][2]) {
                boxes[k][1] = arr[i][0];
                boxes[k][2] = arr[i][2];
            } else {
                boxes[k][1] = arr[i][2];
                boxes[k][2] = arr[i][0];
            }
            boxes[k][3] = boxes[k][1] * boxes[k][2];
            k++;

            // 3rd permutaion
            boxes[k][0] = arr[i][2];
            if (arr[i][1] > arr[i][0]) {
                boxes[k][1] = arr[i][1];
                boxes[k][2] = arr[i][0];
            } else {
                boxes[k][1] = arr[i][0];
                boxes[k][2] = arr[i][1];
            }
            boxes[k][3] = boxes[k][1] * boxes[k][2];
            k++;
        }

        return boxes;
    }

    static int[][] permutations(int h[], int w[], int l[]) {
        int[][] boxes = new int[h.length * 3][4];
        // 0-> height 1->width 2->length 3->area
        int k = 0;
        for (int i = 0; i < h.length; i++) {
            // 1st permutation
            boxes[k][0] = h[i];
            if (w[i] > l[i]) {
                boxes[k][1] = w[i];
                boxes[k][2] = l[i];
            } else {
                boxes[k][1] = l[i];
                boxes[k][2] = w[i];
            }
            boxes[k][3] = boxes[k][1] * boxes[k][2];
            k++;

            // 2nd permutation
            boxes[k][0] = w[i];
            if (h[i] > l[i]) {
                boxes[k][1] = h[i];
                boxes[k][2] = l[i];
            } else {
                boxes[k][1] = l[i];
                boxes[k][2] = h[i];
            }
            boxes[k][3] = boxes[k][1] * boxes[k][2];
            k++;

            // 3rd permutaion
            boxes[k][0] = l[i];
            if (w[i] > h[i]) {
                boxes[k][1] = w[i];
                boxes[k][2] = h[i];
            } else {
                boxes[k][1] = h[i];
                boxes[k][2] = w[i];
            }
            boxes[k][3] = boxes[k][1] * boxes[k][2];
            k++;
        }

        return boxes;
    }

    static int boxStackingDP1(int[] h, int[] w, int[] l) {
        int[][] boxes = permutations(h, w, l);
        Arrays.sort(boxes, (a, b) -> {
            return a[3] - b[3];
        });

        int maxH = 0;
        int[] dp = new int[boxes.length];
        dp[0] = boxes[0][0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = boxes[i][0];
            for (int j = 0; j < i; j++) {
                if (boxes[i][1] > boxes[j][1] && boxes[i][2] > boxes[j][2])
                    dp[i] = Math.max(dp[i], dp[j] + boxes[i][0]);
            }
            maxH = Math.max(maxH, dp[i]);
        }

        return maxH;
    }

    static int boxStackingDP2(int[] h, int[] w, int[] l) {
        int[][] boxes = permutations(h, w, l);
        Arrays.sort(boxes, (a, b) -> {
            if (a[1] != b[1])
                return a[1] - b[1];
            return b[2] - a[2];
        });

        int maxH = 0;
        int[] dp = new int[boxes.length];
        dp[0] = boxes[0][0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = boxes[i][0];
            for (int j = 0; j < i; j++) {
                if (boxes[i][2] > boxes[j][2])
                    dp[i] = Math.max(dp[i], dp[j] + boxes[i][0]);
            }
            maxH = Math.max(maxH, dp[i]);
        }

        return maxH;
    }

    static int boxStacking(int height[], int width[], int length[], int n) {// GFG & Interview Bit
        // return boxStackingDP1(height, width, length);
        return boxStackingDP2(height, width, length);

    }

    static boolean[][] palindromicSubstring(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int gap = 0; gap < dp[0].length; gap++) {
            for (int i = 0, j = gap; j < dp[0].length; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }

        return dp;
    }

    static int minCut_Rec(String s, int i, int j, int[][] dp) {// all test cases passed
        if (i == j)
            return dp[i][j] = 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (i + 1 == j)
            return dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;

        if (s.charAt(i) == s.charAt(j)) {
            int temp = minCut_Rec(s, i + 1, j - 1, dp);
            if (temp == 0)
                return dp[i][j] = 0;
        }

        int min = (int) 1e8;
        for (int cut = i; cut <= j - 1; cut++) {
            int a = minCut_Rec(s, i, cut, dp);
            int b = minCut_Rec(s, cut + 1, j, dp);

            min = Math.min(min, a + b);
        }

        return dp[i][j] = min + 1;
    }

    static int minCut_RecII(String s, int i, int j, int[][] dp, boolean[][] sub) {// saare test case pass ni hote
        if (i == j || sub[i][j])
            return dp[i][j] = 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (i + 1 == j)
            return dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;

        int min = (int) 1e8;
        for (int cut = i; cut <= j - 1; cut++) {
            int a = minCut_RecII(s, i, cut, dp, sub);
            int b = minCut_RecII(s, cut + 1, j, dp, sub);

            min = Math.min(min, a + b);
        }

        return dp[i][j] = min + 1;
    }

    static int minCut_Tab(String s, int[][] dp) {
        for (int gap = 1; gap < dp[0].length; gap++) {
            for (int i = 0, j = gap; j < dp[0].length; i++, j++) {
                if (gap == 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 0)
                    dp[i][j] = 0;
                else {
                    int min = (int) 1e8;
                    for (int k = i; k < j; k++)
                        min = Math.min(min, dp[i][k] + dp[k + 1][j]);

                    dp[i][j] = min + 1;
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    static int minCut_RecIII(int i, int j, int[] dp, boolean[][] isPalindrome) {// O(n2)
        if (i > j)
            return -1;

        if (dp[i] != -1)
            return dp[i];

        int min = (int) 1e8;
        for (int cut = i; cut <= j; cut++) {
            if (isPalindrome[i][cut]) {
                int rres = minCut_RecIII(cut + 1, j, dp, isPalindrome);
                min = Math.min(min, rres);
            }
        }

        return dp[i] = min + 1;
    }

    static int minCut_TabII(int[] dp, boolean[][] isPalindrome) {
        int j = dp.length - 1;
        dp[j] = 0;
        for (int i = dp.length - 2; i >= 0; i--) {
            int min = (int) 1e8;
            for (int cut = i; cut <= j; cut++) {
                if (isPalindrome[i][cut])
                    min = Math.min(min, cut + 1 < dp.length ? dp[cut + 1] : -1);
            }

            dp[i] = min + 1;
        }

        return dp[0];
    }

    public static int minCut(String s) {// leet 132
        // int[][] dp = new int[s.length()][s.length()];
        // for(int[] ar : dp)
        // Arrays.fill(ar, -1);
        // int ans = minCut_Rec(s, 0, s.length() - 1, dp);
        boolean[][] sub = palindromicSubstring(s);
        // int ans = minCut_RecII(s, 0, s.length() - 1, dp, sub);
        // int ans = minCut_Tab(s, dp);

        int[] dp = new int[s.length()];
        // Arrays.fill(dp, -1);
        // int ans = minCut_RecIII(0, s.length() - 1, dp, sub);
        int ans = minCut_TabII(dp, sub);
        // display(dp);
        return ans;
    }

    static int minInsertions_Rec(String s, int i, int j, int[][] dp) {
        if (i == j)
            return dp[i][j] = 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (i + 1 == j)
            return dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;

        if (s.charAt(i) == s.charAt(j))
            return dp[i][j] = minInsertions_Rec(s, i + 1, j - 1, dp);

        int a = minInsertions_Rec(s, i, j - 1, dp);
        int b = minInsertions_Rec(s, i + 1, j, dp);

        return dp[i][j] = Math.min(a, b) + 1;
    }

    static int minInsertions_Tab(String s, int[][] dp) {
        for (int gap = 1; gap < dp.length; gap++) {
            for (int i = 0, j = gap; j < dp.length; i++, j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
            }
        }

        return dp[0][dp.length - 1];
    }

    public int minInsertions(String s) {// leet 1312
        int[][] dp = new int[s.length()][s.length()];
        // for(int i = 0; i < dp.length; i++) for(int j = 0; j < dp.length; j++)
        // dp[i][j] = -1;
        // int ans = minInsertions_Rec(s, 0, s.length() - 1, dp);
        int ans = minInsertions_Tab(s, dp);
        // int ans = s.length() - longestPalindromeSubseq(s);
        return ans;
    }

    static long MCM_Rec(int[] mat, int i, int j, long[][] dp) {
        if (i + 1 == j)
            return dp[i][j] = 0;

        if (dp[i][j] != 0)
            return dp[i][j];

        long min = (long) 1e17;
        for (int cut = i + 1; cut <= j - 1; cut++) {
            long lp = MCM_Rec(mat, i, cut, dp);// left part
            long rp = MCM_Rec(mat, cut, j, dp);// right part
            long myMultCost = mat[i] * mat[cut] * mat[j];

            min = Math.min(min, lp + rp + myMultCost);
        }

        return dp[i][j] = min;
    }

    static long MCM_Tab(int[] mat, long[][] dp) {
        int n = dp.length;
        for (int gap = 2; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                long min = (long) 1e16;
                for (int k = i + 1; k < j; k++) {
                    int multCost = mat[i] * mat[k] * mat[j];
                    min = Math.min(min, dp[i][k] + dp[k][j] + multCost);
                }
                dp[i][j] = min;
            }
        }

        return dp[0][n - 1];
    }

    static long MCM(int[] mat) {
        int n = mat.length;
        long[][] dp = new long[n][n];
        // long ans = MCM_Rec(mat, 0, n - 1, dp);
        long ans = MCM_Tab(mat, dp);
        return ans;
    }

    static int optimalBST_Rec(int[] freq, int i, int j, int[][] dp, int[] psa) {
        if (i > j)
            return 0;

        if (dp[i][j] != 0)
            return dp[i][j];

        if (i == j)
            return dp[i][j] = freq[i];

        int min = (int) 1e8;
        int levelCost = psa[j] - (i >= 1 ? psa[i - 1] : 0);
        for (int cut = i; cut <= j; cut++) {
            int lcost = optimalBST_Rec(freq, i, cut - 1, dp, psa);
            int rcost = optimalBST_Rec(freq, cut + 1, j, dp, psa);

            min = Math.min(min, lcost + rcost + levelCost);
        }

        return dp[i][j] = min;
    }

    static int optimalBST_Tab(int[] freq, int[][] dp, int[] psa) {
        int n = freq.length;
        for (int i = 0; i < n; i++)
            dp[i][i] = freq[i];

        for (int gap = 1; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                int min = (int) 1e8;
                int myCost = psa[j] - (i >= 1 ? psa[i - 1] : 0);
                for (int k = i; k <= j; k++) {
                    int lcost = k - 1 >= 0 ? dp[i][k - 1] : 0;
                    int rcost = k + 1 < n ? dp[k + 1][j] : 0;
                    min = Math.min(min, lcost + rcost + myCost);
                }

                dp[i][j] = min;
            }
        }

        return dp[0][n - 1];
    }

    static int optimalBST(int[] arr, int[] freq) {
        int n = arr.length;
        int[] psa = new int[n];
        psa[0] = freq[0];
        for (int i = 1; i < n; i++)
            psa[i] = freq[i] + psa[i - 1];
        int[][] dp = new int[n][n];
        // int ans = optimalBST_Rec(freq, 0, n - 1, dp, psa);
        int ans = optimalBST_Tab(freq, dp, psa);
        return ans;
    }

    static int burstBalloons_Rec(int[] nums, int i, int j, int[][] dp) {
        if (i > j)
            return 0;

        if (dp[i][j] != 0)
            return dp[i][j];

        int l = i - 1, r = j + 1;
        int leftMultiplier = l >= 0 ? nums[l] : 1;
        int rightMultiplier = r < nums.length ? nums[r] : 1;

        if (i == j)// iske bina b ho jayga pr y tabulation me help krta h
            return dp[i][j] = leftMultiplier * nums[i] * rightMultiplier;

        int max = 0;
        for (int cut = i; cut <= j; cut++) {
            int lcost = burstBalloons_Rec(nums, i, cut - 1, dp);
            int rcost = burstBalloons_Rec(nums, cut + 1, j, dp);

            int myCost = lcost + rcost + (leftMultiplier * nums[cut] * rightMultiplier);
            max = Math.max(max, myCost);
        }

        return dp[i][j] = max;
    }

    static int burstBalloons_Tab(int[] nums, int[][] dp) {
        int n = nums.length;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                int l = i - 1, r = j + 1;
                int leftMultiplier = l >= 0 ? nums[l] : 1;
                int rightMultiplier = r < n ? nums[r] : 1;
                if (gap == 0)
                    dp[i][j] = leftMultiplier * nums[i] * rightMultiplier;
                else {
                    int max = 0;
                    for (int cut = i; cut <= j; cut++) {
                        int lcost = cut - 1 >= 0 ? dp[i][cut - 1] : 0;
                        int rcost = cut + 1 < n ? dp[cut + 1][j] : 0;
                        int myCost = lcost + rcost + (leftMultiplier * nums[cut] * rightMultiplier);

                        max = Math.max(max, myCost);
                    }
                    dp[i][j] = max;
                }
            }
        }

        return dp[0][n - 1];
    }

    public static int burstBalloons(int[] nums) {// leet 312
        int n = nums.length;
        int[][] dp = new int[n][n];
        // int ans = burstBalloons_Rec(nums, 0, n - 1, dp);
        int ans = burstBalloons_Tab(nums, dp);
        display(dp);
        return ans;
    }

    static boolean wordBreak_Rec(String s, int si, HashSet<String> dic, boolean[] dp) {
        if (si == s.length())
            return true;

        if (dp[si])
            return true;

        boolean res = false;
        for (int i = si + 1; i <= s.length() && !res; i++) {
            String q = s.substring(si, i);
            if (dic.contains(q)) {
                res = res || wordBreak_Rec(s, i, dic, dp);
            }
        }

        return dp[si] = res;
    }

    static boolean wordBreak_Tab(String s, HashSet<String> dic) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        int len = 0;
        for (String word : dic)
            len = Math.max(len, word.length());

        for (int i = 0; i < n; i++) {
            if (!dp[i])
                continue;

            for (int l = 1; i + l <= n && l <= len; l++) {
                if (dic.contains(s.substring(i, i + l)))
                    dp[i + l] = true;
            }
        }

        return dp[n];
    }

    public boolean wordBreak(String s, List<String> wordDict) {// leet 139
        HashSet<String> dic = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        // return wordBreak_Rec(s, 0, dic, dp);
        return wordBreak_Tab(s, dic);
    }

    static int wildcardMatching_Rec(String s, String p, int i, int j, int[][] dp) {
        // 1 = true, 2 = false
        if (i == s.length() && j == p.length())
            return dp[i][j] = 1;// true

        if (i == s.length() || j == p.length()) {
            if (j == p.length())
                return dp[i][j] = 2;// false

            return dp[i][j] = (p.charAt(j) == '*' && j == p.length() - 1) ? 1 : 2;
        }

        if (dp[i][j] != 0)
            return dp[i][j];

        char sc = s.charAt(i);
        char pc = p.charAt(j);
        boolean res = false;
        if (pc == '?' || sc == pc)
            res = wildcardMatching_Rec(s, p, i + 1, j + 1, dp) == 1;
        else if (pc == '*') {
            res = res || wildcardMatching_Rec(s, p, i, j + 1, dp) == 1; // as empty string
            res = res || wildcardMatching_Rec(s, p, i + 1, j, dp) == 1; // as a substring
        }

        return dp[i][j] = res ? 1 : 2;
    }

    static boolean wildcardMatching_Tab(String s, String p, boolean[][] dp) {
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length(); j >= 0; j--) {
                if (i == s.length() && j == p.length())
                    dp[i][j] = true;
                else if (j == p.length())
                    dp[i][j] = false;
                else if (i == s.length())
                    dp[i][j] = p.charAt(j) == '*' && j == p.length() - 1;
                else {
                    char sc = s.charAt(i);
                    char pc = p.charAt(j);
                    if (pc == '?' || sc == pc)
                        dp[i][j] = dp[i + 1][j + 1];
                    else if (pc == '*')
                        dp[i][j] = dp[i][j + 1] || dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    static boolean wildcardMatching_TabII(String s, String p, boolean[][] dp) {
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = true;
                else if (j == 0)
                    dp[i][j] = false;
                else if (i == 0)
                    dp[i][j] = p.charAt(j - 1) == '*' && j == 1;
                else {
                    char sc = s.charAt(i - 1);
                    char pc = p.charAt(j - 1);
                    if (pc == '?' || sc == pc)
                        dp[i][j] = dp[i - 1][j - 1];
                    else if (pc == '*')
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    static String removeStars(String p) {
        StringBuilder sb = new StringBuilder();
        boolean star = false;
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            if (ch == '*') {
                if (!star)
                    sb.append(ch);
                star = true;
            } else {
                sb.append(ch);
                star = false;
            }
        }
        return sb.toString();
    }

    public static boolean wildcarMatching(String s, String p) {// leet 44
        p = removeStars(p);
        // int[][] dp = new int[s.length() + 1][p.length() + 1];
        // return wildcardMatching_Rec(s, p, 0, 0, dp) == 1;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // return wildcardMatching_Tab(s, p, dp);
        return wildcardMatching_TabII(s, p, dp);
    }

    static int regularExpressionMatching_Rec(String s, String p, int i, int j, int[][] dp) {
        if (i == s.length() && j == p.length())
            return dp[i][j] = 1;// true

        if (j == p.length())
            return dp[i][j] = 2;// false

        if (i == s.length()) {
            if (j + 1 < p.length() && p.charAt(j + 1) == '*')
                return dp[i][j] = regularExpressionMatching_Rec(s, p, i, j + 2, dp);
            return dp[i][j] = 2;
        }

        if (dp[i][j] != 0)
            return dp[i][j];

        char sc = s.charAt(i);
        char pc = p.charAt(j);
        boolean res = false;
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            if (pc == '.' || sc == pc) {
                res = res || regularExpressionMatching_Rec(s, p, i, j + 2, dp) == 1;
                res = res || regularExpressionMatching_Rec(s, p, i + 1, j, dp) == 1;
            } else
                res = regularExpressionMatching_Rec(s, p, i, j + 2, dp) == 1;
        } else if (sc == pc || pc == '.')
            res = regularExpressionMatching_Rec(s, p, i + 1, j + 1, dp) == 1;

        return dp[i][j] = res ? 1 : 2;
    }

    static boolean regularExpressionMatching_Tab(String s, String p, boolean[][] dp) {
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length(); j >= 0; j--) {
                if (i == s.length() && j == p.length())
                    dp[i][j] = true;
                else if (j == p.length())
                    dp[i][j] = false;
                else if (i == s.length()) {
                    if (j + 1 < p.length() && p.charAt(j + 1) == '*')
                        dp[i][j] = dp[i][j + 2];
                } else {
                    char sc = s.charAt(i);
                    char pc = p.charAt(j);
                    if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                        if (pc == '.' || sc == pc)
                            dp[i][j] = dp[i][j + 2] || dp[i + 1][j];
                        else
                            dp[i][j] = dp[i][j + 2];
                    } else if (sc == pc || pc == '.')
                        dp[i][j] = dp[i + 1][j + 1];
                }
            }
        }

        return dp[0][0];
    }

    static boolean regularExpressionMatching_TabII(String s, String p, boolean[][] dp) {
        for (int i = 0; i <= p.length(); i++) {
            for (int j = 0; j <= s.length(); j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = true;
                else if (i == 0)
                    dp[i][j] = false;
                else if (j == 0) {
                    if (p.charAt(i - 1) == '*')
                        dp[i][j] = dp[i - 2][j];
                } else {
                    char sc = s.charAt(j - 1);
                    char pc = p.charAt(i - 1);
                    if (pc == '*') {
                        dp[i][j] = dp[i - 2][j];
                        dp[i][j] = dp[i][j] || ((p.charAt(i - 2) == sc || p.charAt(i - 2) == '.') && dp[i][j - 1]);
                    } else if (sc == pc || pc == '.')
                        dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[p.length()][s.length()];
    }

    public static boolean regularExpressionMatching(String s, String p) {// leet 10
        // int[][] dp = new int[s.length() + 1][p.length() + 1];
        // return regularExpressionMatching_Rec(s, p, 0, 0, dp) == 1;
        // boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // return regularExpressionMatching_Tab(s, p, dp);
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        return regularExpressionMatching_TabII(s, p, dp);
    }

    public int distinctSubseqII(String S) {// leet 940
        int mod = (int) 1e9 + 7;
        int n = S.length();
        int[] loc = new int[26];
        Arrays.fill(loc, -1);
        int[] dp = new int[n];
        dp[0] = 2;
        loc[S.charAt(0) - 'a'] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = (dp[i - 1] * 2) % mod;
            char ch = S.charAt(i);
            if (loc[ch - 'a'] != -1) {
                int prevIndex = loc[ch - 'a'];
                dp[i] -= prevIndex == 0 ? 1 : dp[prevIndex - 1];
            }
            loc[ch - 'a'] = i;
            dp[i] %= mod;
        }

        int ans = --dp[n - 1];
        if (ans < 0)
            ans += mod;
        return ans;
    }

    public int[] countBits(int num) {// leet 338
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++)
            dp[i] = dp[i & (i - 1)] + 1;

        return dp;
    }

    static long mobileNumericKeypad_Rec(int n, int idx, int[][] keypad, int[][] dir, long[][] dp) {
        if (n == 1)
            return dp[n][idx] = 1;

        if (dp[n][idx] != 0)
            return dp[n][idx];

        int r = idx / 3;
        int c = idx % 3;
        long count = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if (x >= 0 && y >= 0 && x < 4 && y < 3 && keypad[x][y] != -1)
                count += mobileNumericKeypad_Rec(n - 1, x * 3 + y, keypad, dir, dp);

        }

        return dp[n][idx] = count;
    }

    static long mobileNumericKeypad_RecII(int n, int key, ArrayList<Integer>[] moves, long[][] dp) {
        if (n == 1)
            return dp[n][key] = 1;

        if (dp[n][key] != 0)
            return dp[n][key];

        long count = 0;
        for (int i = 0; i < moves[key].size(); i++) {
            int dest = moves[key].get(i);
            count += mobileNumericKeypad_RecII(n - 1, dest, moves, dp);
        }

        return dp[n][key] = count;
    }

    static long mobileNumericKeypad_Tab(int[][] keypad, int[][] dir, long[][] dp) {
        int n = dp.length - 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 12; j++) {
                if (i == 1)
                    dp[i][j] = 1;
                else {
                    int r = j / 3;
                    int c = j % 3;
                    for (int d = 0; d < dir.length; d++) {
                        int x = r + dir[d][0];
                        int y = c + dir[d][1];

                        if (x >= 0 && y >= 0 && x < 4 && y < 3 && keypad[x][y] != -1)
                            dp[i][j] += dp[i - 1][x * 3 + y];
                    }
                }
            }
        }

        long sum = 0;
        for (int d = 0; d < 12; d++)
            sum += (d == 9 || d == 11) ? 0 :dp[n][d];
        return sum;
    }

    static long mobileNumericKeypad_TabII(ArrayList<Integer>[] moves, long[][] dp) {
        for (int n = 1; n < dp.length; n++) {
            for (int d = 0; d < 10; d++) {
                if (n == 1)
                    dp[n][d] = 1;
                else {
                    for (int i = 0; i < moves[d].size(); i++)
                        dp[n][d] += dp[n - 1][moves[d].get(i)];
                }
            }
        }

        long sum = 0;
        for (int d = 0; d < 10; d++)
            sum += dp[dp.length - 1][d];
        return sum;
    }

    static long mobileNumericKeypad(int n) {// GFG
        int[][] dir = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int[][] keypad = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { -1, 0, -1 } };
        long[][] dp = new long[n + 1][12];

        // long ans = 0;
        // for (int i = 0; i < 4; i++) {
        //     for (int j = 0; j < 3; j++) {
        //         if (keypad[i][j] != -1)
        //             ans += mobileNumericKeypad_Rec(n, i * 3 + j, keypad, dir, dp);
        //     }
        // }
        
        long ans = mobileNumericKeypad_Tab(keypad, dir, dp);
        
        // ArrayList<Integer>[] moves = new ArrayList[10];
        // moves[0] = new ArrayList<>(Arrays.asList(0, 8));
        // moves[1] = new ArrayList<>(Arrays.asList(1, 2, 4));
        // moves[2] = new ArrayList<>(Arrays.asList(2, 1, 3, 5));
        // moves[3] = new ArrayList<>(Arrays.asList(3, 2, 6));
        // moves[4] = new ArrayList<>(Arrays.asList(4, 1, 5, 7));
        // moves[5] = new ArrayList<>(Arrays.asList(5, 2, 6, 8, 4));
        // moves[6] = new ArrayList<>(Arrays.asList(6, 3, 5, 9));
        // moves[7] = new ArrayList<>(Arrays.asList(7, 4, 8));
        // moves[8] = new ArrayList<>(Arrays.asList(8, 5, 9, 0, 7));
        // moves[9] = new ArrayList<>(Arrays.asList(9, 6, 8));
        // long[][] dp = new long[n + 1][10];
        // // long ans = 0;
        // // for(int i = 0; i < 10; i++)
        // // ans += mobileNumericKeypad_RecII(n, i, moves, dp);
        // long ans = mobileNumericKeypad_TabII(moves, dp);

        return ans;
    }

    static int[] evaluate(int[] lp, int[] rp, char op){
        int[] mp = new int[2];
        if(op == '^'){
            mp[0] = (lp[0] * rp[1]) + (lp[1] * rp[0]);
            mp[1] = (lp[0] * rp[0]) + (lp[1] * rp[1]);
        }else if(op == '&'){
            mp[0] = lp[0] * rp[0];
            mp[1] = (lp[0] * rp[1]) + (lp[1] * rp[0]) + (lp[1] * rp[1]);
        }else{
            mp[0] = (lp[0] * rp[0]) + (lp[0] * rp[1]) + (lp[1] * rp[0]);
            mp[1] = lp[1] * rp[1];
        }
        mp[0] %= 1003;
        mp[1] %= 1003;
        return mp;
    }

    static int[] booleanParenthesization_Rec(String s, int i, int j, int[][][] dp){
        if(i == j){
            int[] base = new int[2];
            char ch = s.charAt(i);
            if(ch == 'T') base[0]++;
            else base[1]++;
            return dp[i][j] = base;
        }

        if(dp[i][j][0] != -1)
            return dp[i][j];

        int[] mp = new int[2];
        for(int cut = i + 1; cut < j; cut += 2){
            int[] lp = booleanParenthesization_Rec(s, i, cut - 1, dp);
            int[] rp = booleanParenthesization_Rec(s, cut + 1, j, dp);

            char op = s.charAt(cut);
            int[] temp = evaluate(lp, rp, op);
            mp[0] = (mp[0] + temp[0]) % 1003;
            mp[1] = (mp[1] + temp[1]) % 1003;
        }

        return dp[i][j] = mp;
    }

    static int booleanParenthesization(String s){//GFG
        int[][][] dp = new int[s.length()][s.length()][2];
        for(int[][] col : dp){
            for(int[] cell : col)
                Arrays.fill(cell, -1);
        }
        int[] ans = booleanParenthesization_Rec(s, 0, s.length() - 1, dp);
        return ans[0];
    }

    public static void main(String[] args) {
        // int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        // System.out.println(minPathSum(grid));
        // int[][] grid = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2
        // } };
        // System.out.println(goldMine(grid));

        // System.out.println(nowtpp(3));
        // System.out.println(partitionNEleKSets(5, 3));
        // System.out.println(countPalindromicSubseq("abccbc"));
        // System.out.println(countPalindromicSubstring("abccbc"));
        // System.out.println(longestPalindromicSubstring("abccbc"));
        // System.out.println(longestCommonSubstring("axmnp", "txmnop"));
        // System.out.println(minDistance("sea", "eat"));
        // System.out.println(editDistance("horse", "ros"));
        // System.err.println(numDistinct("rabbbit", "rabbit"));

        // int[] coins = { 1, 2, 5 };
        // System.out.println(minHeightCoinChange(coins, 11));

        // int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        // System.out.println(reverseLBS(arr));
        // System.out.println(LBS(arr));
        // System.out.println(LIS_Eff(arr));
        // System.out.println(findNumberOfLIS(arr));
        // int[] height = {1, 4, 3}, width = {2, 5, 4}, length = {3, 6, 1};
        // int[] height = {4, 1, 4, 10}, width = {6, 2, 5, 12}, length = {7, 3, 6, 32};
        // System.out.println(boxStacking(height, width, length, height.length));

        // System.out.println(minCut("abccbddeddff"));

        // int[] arr = {10, 20, 30, 40, 50, 60, 70};
        // int[] freq = {2, 1, 4, 1, 1, 3, 5};
        // System.out.println(optimalBST(arr, freq));

        // int[] arr = { 3, 1, 5, 8 };
        // System.out.println(burstBalloons(arr));

        // System.out.println(wildcarMatching("abccdez", "ab*d??"));
        // System.out.println(regularExpressionMatching("mississippi", "mis*is*p*."));

        // System.out.println(mobileNumericKeypad(2));
        System.out.println(booleanParenthesization("T|F^F&T|F^F^F^T|T&T^T|F^T^F&F^T|T^F"));
    }
}