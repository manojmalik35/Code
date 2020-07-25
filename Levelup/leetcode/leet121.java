package leetcode;

public class leet121 {
    public int maxProfit(int[] nums) {
        if(nums.length == 0)
            return 0;
        int profit = 0;
        int minv = nums[0];
        for(int i = 1; i < nums.length; i++){
            int cp = 0;
            if(nums[i] > minv)
                cp = nums[i] - minv;
            else
                minv = nums[i];
            
            if(cp > profit)
                profit = cp;
        }
        
        return profit;
    }
}