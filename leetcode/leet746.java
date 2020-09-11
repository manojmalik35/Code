public class leet746 {
    static int minCostClimbingStairs_M(int[] cost, int idx, int[] dp){
        if(idx <= 1)
            return dp[idx] = cost[idx];
        
        if(dp[idx] != 0)
            return dp[idx];
        
        int nm1 = minCostClimbingStairs_M(cost, idx - 1, dp);
        int nm2 = minCostClimbingStairs_M(cost, idx - 2, dp);
        
        dp[idx] = Math.min(nm1, nm2) + (idx == cost.length ? 0 : cost[idx]);
        return dp[idx];
    }

    public static int minCostClimbingStairs_T(int[] cost, int[] dp) {
        for(int idx = 0; idx <= cost.length; idx++){
            if(idx <= 1){
                dp[idx] = cost[idx];
                continue;
            }
            
            int nm1 = dp[idx - 1];
            int nm2 = dp[idx - 2];
            
            dp[idx] = Math.min(nm1, nm2) + (idx == cost.length ? 0 : cost[idx]);
        }

        // display(dp);
        return dp[cost.length];
    }
    
    public static int minCostClimbingStairs_B(int[] cost){//Best
        int a = cost[0];
        int b = cost[1];
        int sum = Math.min(a, b);
        for(int i = 2; i <= cost.length; i++){
            sum = Math.min(a, b);
            sum += (i == cost.length ? 0 : cost[i]);
            a = b;
            b = sum;
        }
        return sum;
    }

    public static int minCostClimbingStairs(int[] cost) {//leetcode 746
        // int[] dp = new int[cost.length + 1];
        // int ans = minCostClimbingStairs_M(cost, cost.length, dp);
        // int ans = minCostClimbingStairs_T(cost, dp);
        int ans = minCostClimbingStairs_B(cost);
        return ans;
    }

    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost));
    }
}