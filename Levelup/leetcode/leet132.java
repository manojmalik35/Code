package leetcode;

public class leet132 {
    static boolean[][] palindromicSubstring(String s){
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int gap = 0; gap < dp[0].length; gap++){
            for(int i = 0, j = gap; j < dp[0].length; i++, j++){
                if(gap == 0)
                    dp[i][j] = true;
                else if(gap == 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }

        return dp;
    }

    static int minCut_Rec(String s, int i, int j, int[][] dp){//all test cases passed
        if(i == j)
            return dp[i][j] = 0;

        if(dp[i][j] != -1)
            return dp[i][j];

        if(i + 1 == j)
            return dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
        
        if(s.charAt(i) == s.charAt(j)){
            int temp = minCut_Rec(s, i + 1, j - 1, dp);
            if(temp == 0)
                return dp[i][j] = 0;
        }

        int min = (int)1e8;
        for(int cut = i; cut <= j - 1; cut++){
            int a = minCut_Rec(s, i, cut, dp);
            int b = minCut_Rec(s, cut + 1, j, dp);

            min = Math.min(min, a + b);
        }

        return dp[i][j] = min + 1;
    }

    static int minCut_RecII(String s, int i, int j, int[][] dp, boolean[][] sub){//saare test case pass ni hote
        if(i == j || sub[i][j])
            return dp[i][j] = 0;

        if(dp[i][j] != -1)
            return dp[i][j];

        if(i + 1 == j)
            return dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
        
        int min = (int)1e8;
        for(int cut = i; cut <= j - 1; cut++){
            int a = minCut_RecII(s, i, cut, dp, sub);
            int b = minCut_RecII(s, cut + 1, j, dp, sub);

            min = Math.min(min, a + b);
        }

        return dp[i][j] = min + 1;
    }

    static int minCut_Tab(String s, int[][] dp){
        for(int gap = 1; gap < dp[0].length; gap++){
            for(int i = 0, j = gap; j < dp[0].length; i++, j++){
                if(gap == 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                else if(s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 0)
                        dp[i][j] = 0;
                else{
                    int min = (int)1e8;
                    for(int k = i; k < j; k++)
                        min = Math.min(min, dp[i][k] + dp[k + 1][j]);
                    
                    dp[i][j] = min + 1;
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    static int minCut_RecIII(int i, int j, int[] dp, boolean[][] isPalindrome){//O(n2)
        if(i > j) return -1;

        if(dp[i] != -1) return dp[i];

        int min = (int)1e8;
        for(int cut = i; cut <= j; cut++){
            if(isPalindrome[i][cut]){
                int rres = minCut_RecIII(cut + 1, j, dp, isPalindrome);
                min = Math.min(min, rres);
            }
        }

        return dp[i] = min + 1;
    }

    static int minCut_TabII(int[] dp, boolean[][] isPalindrome){
        int j = dp.length - 1;
        dp[j] = 0;
        for(int i = dp.length - 2; i >= 0; i--){
            int min = (int)1e8;
            for(int cut = i; cut <= j; cut++){
                if(isPalindrome[i][cut])
                    min = Math.min(min, cut + 1 < dp.length ? dp[cut + 1] : -1);
            }

            dp[i] = min + 1;
        }

        return dp[0];
    }

    public static int minCut(String s) {//leet 132
        // int[][] dp = new int[s.length()][s.length()];
        // for(int[] ar : dp)
        //     Arrays.fill(ar, -1);
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
}