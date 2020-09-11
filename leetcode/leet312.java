public class leet312 {
    static int burstBalloons_Rec(int[] nums, int i, int j, int[][] dp){
        if(i > j)
            return 0;

        if(dp[i][j] != 0)
            return dp[i][j];

        int l = i - 1, r = j + 1;
        int leftMultiplier = l >= 0 ? nums[l] : 1;
        int rightMultiplier = r < nums.length ? nums[r] : 1;

        if(i == j)//iske bina b ho jayga pr y tabulation me help krta h
            return dp[i][j] = leftMultiplier * nums[i] * rightMultiplier;

        int max = 0;
        for(int cut = i; cut <= j; cut++){
            int lcost = burstBalloons_Rec(nums, i, cut - 1, dp);
            int rcost = burstBalloons_Rec(nums, cut + 1, j, dp);

            int myCost = lcost + rcost + (leftMultiplier * nums[cut] * rightMultiplier);
            max = Math.max(max, myCost);
        }

        return dp[i][j] = max;
    }

    static int burstBalloons_Tab(int[] nums, int[][] dp){
        int n = nums.length;
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                int l = i - 1, r = j + 1;
                int leftMultiplier = l >= 0 ? nums[l] : 1;
                int rightMultiplier = r < n ? nums[r] : 1;
                if(gap == 0)//bina iske b ho jayga
                    dp[i][j] = leftMultiplier * nums[i] * rightMultiplier;
                else{
                    int max = 0;
                    for(int cut = i; cut <= j; cut++){
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

    public static int burstBalloons(int[] nums) {//leet 312
        int n = nums.length;
        int[][] dp = new int[n][n];
        // int ans = burstBalloons_Rec(nums, 0, n - 1, dp);
        int ans = burstBalloons_Tab(nums, dp);
        return ans;
    }
}