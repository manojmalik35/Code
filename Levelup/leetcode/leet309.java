public class leet309 {
    public int maxProfit(int[] prices) {
        if(prices.length == 0)
            return 0;
        int spodb = 0;//sell profit one day before
        int sp = 0;//sell profit
        int bp = -prices[0];//buy profit
        for(int i = 1; i < prices.length; i++){
            int obp = bp;//old bp
            int osp = sp;//old sp
            bp = Math.max(bp, spodb - prices[i]);
            sp = Math.max(sp, obp + prices[i]);
            spodb = osp;
        }
        
        return sp;
    }
}