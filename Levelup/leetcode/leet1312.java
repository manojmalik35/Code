package leetcode;

public class leet1312 {
    static int minInsertions_Rec(String s, int i, int j, int[][] dp){
        if(i == j)
            return dp[i][j] = 0;

        if(dp[i][j] != -1)
            return dp[i][j];
        
        if(i + 1 == j)
            return dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;

        if(s.charAt(i) == s.charAt(j))
            return dp[i][j] = minInsertions_Rec(s, i + 1, j - 1, dp);

        int a = minInsertions_Rec(s, i, j - 1, dp);
        int b = minInsertions_Rec(s, i + 1, j, dp);

        return dp[i][j] = Math.min(a, b) + 1;
    }

    static int minInsertions_Tab(String s, int[][] dp){
        for(int gap = 1; gap < dp.length; gap++){
            for(int i = 0, j = gap; j < dp.length; i++, j++){
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
            }
        }
        
        return dp[0][dp.length - 1];
    }

    public int minInsertions(String s) {//leet 1312
        int[][] dp = new int[s.length()][s.length()];
        // for(int i = 0; i < dp.length; i++) for(int j = 0; j < dp.length; j++) dp[i][j] = -1; 
        // int ans = minInsertions_Rec(s, 0, s.length() - 1, dp);
        int ans = minInsertions_Tab(s, dp);
        // int ans = s.length() - longestPalindromeSubseq(s);
        return ans;
    }
}