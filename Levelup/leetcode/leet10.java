package leetcode;

public class leet10 {
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
                    else
                        dp[i][j] = false;
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
}