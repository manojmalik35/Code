import java.util.*;
public class leet139 {

    static boolean wordBreak_Rec(String s, int si, HashSet<String> dic, boolean[] dp) {
        if (si == s.length())
            return true;

        if (dp[si])
            return true;

        boolean res = false;
        for (int i = si + 1; i <= s.length() && !res; i++) {
            String q = s.substring(si, i);
            if (dic.contains(q)) {
                res = res || wordBreak_Rec(s, i, dic, dp);
            }
        }

        return dp[si] = res;
    }

    static boolean wordBreak_Tab(String s, HashSet<String> dic) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        int len = 0;
        for (String word : dic)
            len = Math.max(len, word.length());

        for (int i = 0; i < n; i++) {
            if (!dp[i])
                continue;

            for (int l = 1; i + l <= n && l <= len; l++) {
                if (dic.contains(s.substring(i, i + l)))
                    dp[i + l] = true;
            }
        }

        return dp[n];
    }

    public boolean wordBreak(String s, List<String> wordDict) {// leet 139
        HashSet<String> dic = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        return wordBreak_Rec(s, 0, dic, dp);
        // return wordBreak_Tab(s, dic);
    }
}