public class leet122 {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int bd = 0;//buying day
        int sd = 0;//selling day
        for(int i = 1; i < prices.length; i++){
            if(prices[i] < prices[i - 1]){
                profit += prices[sd] - prices[bd];
                sd = bd = i;
            }else
                sd++;
        }
        
        profit += prices[sd] - prices[bd];
        return profit;
    }
}