package leetcode;

public class leet673 {
    public static int findNumberOfLIS(int[] nums){//Leet 673
        if(nums.length == 0)
            return 0;
            
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];

        int maxLen = 0;
        int maxCount = 0;
        for(int i = 0; i < nums.length; i++){
            dp[i] = 1; count[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    if(dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }else if(dp[j] + 1 == dp[i])
                        count[i] += count[j];
                }
            }

            if(dp[i] > maxLen){
                maxLen = dp[i];
                maxCount = count[i];
            }else if(dp[i] == maxLen)
                maxCount += count[i];
        }
        
        return maxCount;
    }
}