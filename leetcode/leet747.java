public class leet747 {
    
    public int dominantIndex(int[] nums) {//leetcode 747
        int n = nums.length;
        if(n == 1) return 0; 
        int max = -(int)1e8;
        int smax = -(int)1e8;
        int mi = -1;//max index
        for(int i = 0; i < n; i++){
            if(nums[i] > max){
                smax = max;
                max = nums[i];
                mi = i;
            }else if(nums[i] == max)
                smax = max;
            else if(nums[i] > smax)
                smax = nums[i];
        }

        if(smax * 2 <= max) return mi;
        return -1;
    }
}
