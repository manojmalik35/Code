public class leet152 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int overallProd = nums[0];
        int currProd = nums[0];
        for(int i = 1; i < n; i++){
            currProd = Math.max(currProd * nums[i], nums[i]);
            if(overallProd < currProd) 
                overallProd = currProd;
        }   

        return overallProd;
    }
}
