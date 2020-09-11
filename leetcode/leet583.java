public class leet583 {
    static int minDistance_Rec(String s1, String s2, int i, int j, int[][] dp){
        if(i == s1.length() && j == s2.length()) return dp[i][j] = 0;

        if(i == s1.length()) return dp[i][j] = s2.length() - j;

        if(j == s2.length()) return dp[i][j] = s1.length() - i;

        if(dp[i][j] != 0) return dp[i][j];

        if(s1.charAt(i) == s2.charAt(j))
            return dp[i][j] = minDistance_Rec(s1, s2, i + 1, j + 1, dp);

        int one = minDistance_Rec(s1, s2, i, j + 1, dp);
        int two = minDistance_Rec(s1, s2, i + 1, j, dp);

        return dp[i][j] = Math.min(one, two) + 1;
    }

    static int minDistance_Tab(String s1, String s2, int[][] dp){
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                if(i == 0)
                    dp[i][j] = j;
                else if(j == 0)
                    dp[i][j] = i;
                else if(s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public static int minDistance(String s1, String s2) {//leet583
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int ans = minDistance_Rec(s1, s2, 0, 0, dp);
        // int ans = minDistance_Tab(s1, s2, dp);
        // display(dp);
        return ans;
    }
}