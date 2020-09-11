public class leet238 {
    
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefixProd = new int[n];
        prefixProd[0] = nums[0];
        int[] suffixProd = new int[n];
        suffixProd[n - 1] = nums[n - 1];
        for(int i = 1; i < n; i++)
            prefixProd[i] = prefixProd[i - 1] * nums[i];

        for(int i = n - 2; i >= 0; i--)
            suffixProd[i] = suffixProd[i + 1] * nums[i];

        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            if(i == 0)
                ans[i] = suffixProd[i + 1];
            else if(i == n - 1)
                ans[i] = prefixProd[i - 1];
            else
                ans[i] = prefixProd[i - 1] * suffixProd[i + 1];
        }

        return ans;
    }

    public int[] productExceptSelf2(int[] nums) {//O(1) space except ans[]
        int n = nums.length;
        int[] prefixProd = new int[n];
        prefixProd[0] = nums[0];
        for(int i = 1; i < n; i++)
            prefixProd[i] = prefixProd[i - 1] * nums[i];

        int suffixProd = 1;
        for(int i = n - 1; i > 0; i--){
            prefixProd[i] = prefixProd[i - 1] * suffixProd;
            suffixProd *= nums[i];
        }

        prefixProd[0] = suffixProd;
        return prefixProd;
    }
}
