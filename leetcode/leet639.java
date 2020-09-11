public class leet639 {
    static int mod = (int) 1e9 + 7;
    static long numDecodingsII_Rec(String s, int idx, long[] dp) {
        if (idx == s.length())
            return 1;

        if (dp[idx] != 0)
            return dp[idx];

        if (s.charAt(idx) == '0')
            return dp[idx] = 0;

        long ans = 0;
        char ch = s.charAt(idx);
        if (ch == '*') {
            ans = (ans + numDecodingsII_Rec(s, idx + 1, dp) * 9) % mod;
            if (idx + 1 < s.length()) {
                char ch1 = s.charAt(idx + 1);
                if (ch1 == '*')
                    ans = (ans + numDecodingsII_Rec(s, idx + 2, dp) * 15) % mod;
                else if (ch1 >= '0' && ch1 <= '6')
                    ans = (ans + numDecodingsII_Rec(s, idx + 2, dp) * 2) % mod;
                else
                    ans = (ans + numDecodingsII_Rec(s, idx + 2, dp)) % mod;
            }
        } else {
            ans = (ans + numDecodingsII_Rec(s, idx + 1, dp)) % mod;
            if (idx + 1 < s.length()) {
                char ch1 = s.charAt(idx + 1);
                if (ch1 == '*') {
                    if (ch == '1')
                        ans = (ans + numDecodingsII_Rec(s, idx + 2, dp) * 9) % mod;
                    else if (ch == '2')
                        ans = (ans + numDecodingsII_Rec(s, idx + 2, dp) * 6) % mod;
                } else {
                    int num0 = ch - '0';
                    int num1 = ch1 - '0';
                    int num01 = num0 * 10 + num1;
                    if (num01 <= 26)
                        ans = (ans + numDecodingsII_Rec(s, idx + 2, dp)) % mod;
                }
            }
        }

        return dp[idx] = ans;
    }

    static long numDecodingsII_Tab(String s, long[] dp){
        for(int i = s.length() - 1; i >= 0; i--){
            char ch = s.charAt(i);
            if(ch == '*'){
                dp[i] = (dp[i] + dp[i + 1] * 9) % mod;
                if(i + 1 < s.length()){
                    char ch1 = s.charAt(i + 1);
                    if(ch1 == '*')
                        dp[i] = (dp[i] + dp[i + 2] * 15) % mod;
                    else if(ch1 >= '0' && ch1 <= '6')
                        dp[i] = (dp[i] + dp[i + 2] * 2) % mod;
                    else
                        dp[i] = dp[i] + dp[i + 2] % mod;
                }
            }else if(ch != '0'){
                dp[i] = (dp[i] + dp[i + 1]) % mod;
                if(i + 1 < s.length()){
                    char ch1 = s.charAt(i + 1);
                    if(ch1 == '*'){
                        if(ch == '1')
                            dp[i] = (dp[i] + dp[i + 2] * 9) % mod;
                        else if(ch == '2')
                            dp[i] = (dp[i] + dp[i + 2] * 6) % mod;
                    }else{
                        int num = (ch - '0') * 10 + (ch1 - '0');
                        if(num <= 26)
                            dp[i] = (dp[i] + dp[i + 2]) % mod;
                    }
                }
            }
        }

        return dp[0];
    }

    public int numDecodingsII(String s) {//leet 639
        long[] dp = new long[s.length() + 1];
        dp[dp.length - 1] = 1;
        int ans = (int)numDecodingsII_Tab(s, dp);
        return ans;
    }
}