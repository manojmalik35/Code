package leetcode;
import java.util.*;
public class leet322 {
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
        
        return ans >= (int) 1e8 ? -1 : ans;
    }
}