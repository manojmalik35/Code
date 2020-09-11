public class leet72 {
    static int editDistance_Rec(String s1, String s2, int i, int j, int[][] dp){
        if(i == 0 && j == 0) return dp[i][j] = 0;

        if(i == 0) return dp[i][j] = j;

        if(j == 0) return dp[i][j] = i;

        if(dp[i][j] != 0) return dp[i][j];

        if(s1.charAt(i - 1) == s2.charAt(j - 1))
            return dp[i][j] = editDistance_Rec(s1, s2, i - 1, j - 1, dp);

        int one = editDistance_Rec(s1, s2, i - 1, j - 1, dp);
        int two = editDistance_Rec(s1, s2, i, j - 1, dp);
        int three = editDistance_Rec(s1, s2, i - 1, j, dp);

        return dp[i][j] = Math.min(one, Math.min(two, three)) + 1;
    }

    static int editDistance_Tab(String s1, String s2, int[][] dp){
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                if(i == 0)
                    dp[i][j] = j;
                else if(j == 0)
                    dp[i][j] = i;
                else if(s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public static int editDistance(String s1, String s2) {//leet72
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        // int ans = editDistance_Tab(s1, s2, dp);
        int ans = editDistance_Rec(s1, s2, s1.length(), s2.length(), dp);
        // display(dp);
        return ans;
    }
}