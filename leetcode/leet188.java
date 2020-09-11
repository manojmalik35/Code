import java.util.*;
public class leet188 {

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || n == 1 || k == 0)
            return 0;

        int ans = 0;
        if (k > n) {
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1])
                    ans += prices[i] - prices[i - 1];
            }
        } else {
            int[] dp = new int[n];
            for (int i = 1; i <= k; i++) {
                int[] temp = new int[n];
                int maxDiff = (int) -1e8;
                for (int j = 1; j < n; j++) {
                    int firstCall = temp[j - 1];
                    maxDiff = Math.max(maxDiff, dp[j - 1] - prices[j - 1]);
                    int secondCall = maxDiff + prices[j];
                    temp[j] = Math.max(firstCall, secondCall);
                }
                dp = temp;
            }

            ans = dp[n - 1];
        }

        return ans;
    }

    // will not submit on leetcode
    public int maxProfit2(int k, int[] prices) {
        int[][] dp = new int[k + 1][prices.length];
        for (int i = 0; i <= k; i++) {
            int maxDiff = Integer.MIN_VALUE;
            for (int j = 0; j < prices.length; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else {
                    int firstCall = dp[i][j - 1];
                    maxDiff = Math.max(maxDiff, dp[i - 1][j - 1] - prices[j - 1]);
                    int secondCall = maxDiff + prices[j];
                    dp[i][j] = Math.max(firstCall, secondCall);
                }
            }
        }

        return dp[k][prices.length - 1];
    }

    public int maxProfitMerging(int k, int[] prices) {//merging solution
        int n = prices.length;

        // solve special cases
        if (n <= 0 || k <= 0) {
            return 0;
        }

        // find all consecutively increasing subsequence
        ArrayList<int[]> transactions = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] >= prices[i - 1]) {
                end = i;
            } else {
                if (end > start) {
                    int[] t = { start, end };
                    transactions.add(t);
                }
                start = i;
            }
        }
        if (end > start) {
            int[] t = { start, end };
            transactions.add(t);
        }

        while (transactions.size() > k) {
            // check delete loss
            int delete_index = 0;
            int min_delete_loss = Integer.MAX_VALUE;
            for (int i = 0; i < transactions.size(); i++) {
                int[] t = transactions.get(i);
                int profit_loss = prices[t[1]] - prices[t[0]];
                if (profit_loss < min_delete_loss) {
                    min_delete_loss = profit_loss;
                    delete_index = i;
                }
            }

            // check merge loss
            int merge_index = 0;
            int min_merge_loss = Integer.MAX_VALUE;
            for (int i = 1; i < transactions.size(); i++) {
                int[] t1 = transactions.get(i - 1);
                int[] t2 = transactions.get(i);
                int profit_loss = prices[t1[1]] - prices[t2[0]];
                if (profit_loss < min_merge_loss) {
                    min_merge_loss = profit_loss;
                    merge_index = i;
                }
            }

            // delete or merge
            if (min_delete_loss <= min_merge_loss) {
                transactions.remove(delete_index);
            } else {
                int[] t1 = transactions.get(merge_index - 1);
                int[] t2 = transactions.get(merge_index);
                t1[1] = t2[1];
                transactions.remove(merge_index);
            }

        }

        int res = 0;
        for (int[] t : transactions) {
            res += prices[t[1]] - prices[t[0]];
        }

        return res;
    }
}