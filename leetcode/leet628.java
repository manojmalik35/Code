public class leet628 {
    
    public int maximumProduct(int[] nums) {//leetcode 628
        int n = nums.length;
        int max1 = -(int)1e8;
        int max2 = -(int)1e8;
        int max3 = -(int)1e8;
        int min1 = (int)1e8;
        int min2 = (int)1e8;
        for(int i = 0; i < n; i++){
            if(nums[i] >= max1){
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            }else if(nums[i] >= max2){
                max3 = max2;
                max2 = nums[i];
            }else if(nums[i] > max3)
                max3 = nums[i];

            if(nums[i] <= min1){
                min2 = min1;
                min1 = nums[i];
            }else if(nums[i] < min2)
                min2 = nums[i];
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
