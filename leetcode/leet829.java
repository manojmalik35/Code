public class leet829 {
    public int consecutiveNumbersSum(int N) {//leetcode 829
        int upperLimitOfK = (int)Math.sqrt(2 * N);
        int ans = 0;
        for(int k = 1; k <= upperLimitOfK; k++){
            if((N - (((k - 1) * k) / 2)) % k == 0)
                ans++;
        }

        return ans;
    }
}
