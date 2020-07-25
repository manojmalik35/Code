package leetcode;

public class leet123 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0)
            return 0;
        int[] ltr = new int[n];   
        int[] rtl = new int[n];

        int minv = prices[0];
        for(int i = 1; i < n; i++){
            if(prices[i] < minv)
                minv = prices[i];
            
            ltr[i] = Math.max(ltr[i - 1], prices[i] - minv);
        }

        int maxv = prices[n - 1];
        for(int i = n - 2; i >= 0; i--){
            if(prices[i] > maxv)
                maxv = prices[i];

            rtl[i] = Math.max(rtl[i + 1], maxv - prices[i]);
        }

        int ans = 0;
        for(int i = 0; i < n; i++)
            ans = Math.max(ans, ltr[i] + rtl[i]);
        
        return ans;
    }
}