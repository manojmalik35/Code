package leetcode;
import java.util.Arrays;
public class leet940 {
    public int distinctSubseqII(String S) {// leet 940
        int mod = (int)1e9 + 7;
        int n = S.length();
        int[] loc = new int[26];
        Arrays.fill(loc, -1);
        int[] dp = new int[n];
        dp[0] = 2;
        loc[S.charAt(0) - 'a'] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = (dp[i - 1] * 2) % mod;
            char ch = S.charAt(i);
            if (loc[ch - 'a'] != -1) {
                int prevIndex = loc[ch - 'a'];
                dp[i] -= prevIndex == 0 ? 1 : dp[prevIndex - 1];
            }
            loc[ch - 'a'] = i;
            dp[i] %= mod;
        }

        int ans = --dp[n - 1];
        if(ans < 0) ans += mod;
        return ans;
    }
}