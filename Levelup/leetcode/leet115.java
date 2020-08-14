public class leet115 {
    static int numDistinct_Rec(String s, String t, int i, int j, int[][] dp) {
        if (j == t.length()) return dp[i][j] = 1;

        if (i == s.length()) return dp[i][j] = 0;

        if (dp[i][j] != -1) return dp[i][j];

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
        //     Arrays.fill(ar, -1);
        // int ans = numDistinct_Rec(s, t, 0, 0, dp);
        int ans = numDistinct_Tab(s, t, dp);
        return ans;
    }
}