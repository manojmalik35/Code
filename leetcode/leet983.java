public class leet983 {

    //no dp brute force TLE
    int mincostTickets_Rec(int[] days, int[] costs, int idx) {
        if(idx >= days.length) return 0;

        int min = (int)1e9;
        int oneDayPass = mincostTickets_Rec(days, costs, idx + 1);

        int i = idx + 1;
        while(i < days.length && days[i] < days[idx] + 7) i++;

        int sevenDayPass = mincostTickets_Rec(days, costs, i);

        i = idx + 1;
        while(i < days.length && days[i] < days[idx] + 30) i++;

        int thirtyDayPass = mincostTickets_Rec(days, costs, i);

        min = Math.min(min, oneDayPass + costs[0]);
        min = Math.min(min, Math.min(sevenDayPass + costs[1], thirtyDayPass + costs[2]));

        return min;
    }

    int mincostTickets_Mem(int[] days, int[] costs, int idx, Integer[] dp) {
        if(idx >= days.length) return 0;

        if(dp[idx] != null) return dp[idx];

        int min = (int)1e9;
        int oneDayPass = mincostTickets_Mem(days, costs, idx + 1, dp);

        int i = idx + 1;
        while(i < days.length && days[i] < days[idx] + 7) i++;

        int sevenDayPass = mincostTickets_Mem(days, costs, i, dp);

        i = idx + 1;
        while(i < days.length && days[i] < days[idx] + 30) i++;

        int thirtyDayPass = mincostTickets_Mem(days, costs, i, dp);

        min = Math.min(min, oneDayPass + costs[0]);
        min = Math.min(min, Math.min(sevenDayPass + costs[1], thirtyDayPass + costs[2]));

        return dp[idx] = min;
    }

    static int BS(int[] days, int val, int lo, int hi){//finding ceil
        if(val > days[hi]) return hi + 1;
        while(lo < hi){
            int mid = (lo + hi) >>> 1;
            if(days[mid] == val)
                return mid;
            else if(days[mid] < val)
                lo = mid + 1;
            else
                hi = mid; 
        }

        return lo;
    }

    static int mincostTickets_Best(int[] days, int[] costs, int idx, Integer[] dp) {
        if(idx >= days.length) return 0;

        if(dp[idx] != null) return dp[idx];

        int min = (int)1e9;
        int oneDayPass = mincostTickets_Best(days, costs, idx + 1, dp);

        int i = BS(days, days[idx] + 7, idx + 1, days.length - 1);
        int sevenDayPass = mincostTickets_Best(days, costs, i, dp);
        
        i = BS(days, days[idx] + 30, idx + 1, days.length - 1);
        int thirtyDayPass = mincostTickets_Best(days, costs, i, dp);

        min = Math.min(min, oneDayPass + costs[0]);
        min = Math.min(min, Math.min(sevenDayPass + costs[1], thirtyDayPass + costs[2]));

        return dp[idx] = min;
    }

    public static int mincostTickets(int[] days, int[] costs) {
        Integer[] dp = new Integer[days.length];
        // return mincostTickets_Rec(days, costs, 0);
        // return mincostTickets_Mem(days, costs, 0, dp);
        int ans = mincostTickets_Best(days, costs, 0, dp);
        for(int i = 0; i < dp.length; i++) System.out.print(dp[i] + " ");
        System.out.println();
        return ans;
    }

    public static void main(String[] args) {
        int[] days = { 1,2,3,4,5,6,7,8,9,10,30,31};
        int[] costs = { 2, 7, 15 };
        System.out.println(mincostTickets(days, costs));
    }
}