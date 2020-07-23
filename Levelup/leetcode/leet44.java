package leetcode;

public class leet44 {
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

    public boolean isMatch(String s, String p) {// leet 44
        p = removeStars(p);
        // int[][] dp = new int[s.length() + 1][p.length() + 1];
        // return wildcardMatching_Rec(s, p, 0, 0, dp) == 1;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        return wildcardMatching_Tab(s, p, dp);
    }
}