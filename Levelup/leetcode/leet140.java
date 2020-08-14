import java.util.*;
public class leet140{

    public List<String> wordBreakII(String s, List<String> wordDict) {
        int n = s.length();
        List<String>[] dp = new ArrayList[n + 1];
        for(int i = 0; i < dp.length; i++)
            dp[i] = new ArrayList<>();
        dp[0].add("");
        int len = 0;
        HashSet<String> dic = new HashSet<>();
        for(String word : wordDict){
            dic.add(word);
            len = Math.max(len, word.length());
        }

        for(int i = 0; i < n; i++){
            if(dp[i].size() == 0)
                continue;

            for(int l = 1; i + l <= n && l <= len; l++){
                String q = s.substring(i, i + l);
                if(dic.contains(q)){
                    for(int k = 0; k < dp[i].size(); k++){
                        if(i + l == n)
                            dp[i + l].add(dp[i].get(k) + q);
                        else
                            dp[i + l].add(dp[i].get(k) + q + " ");
                    }
                }
            }
        }

        return dp[n];
    }

    static void dfs(String s, List<String> dict, StringBuilder ans, List<String> res){        
        if(ans.length() != 0)
            ans.append(" ");
        
        for(String word : dict){
            if(s.startsWith(word)){
                StringBuilder sb = new StringBuilder(ans);
                sb.append(word);

                if(s.equals(word))//means there is no more string left
                    res.add(sb.toString());
                else
                    dfs(s.substring(word.length()), dict, sb, res);
            }
        }   
    }

    static void dfs(String s, int si, HashSet<String> dic, StringBuilder ans, List<String> res){
        if(si == s.length()){
            res.add(ans.toString());
            return;
        }
        
        if(ans.length() != 0)
            ans.append(" ");
        
        for(int i = si + 1; i <= s.length(); i++){
            String q = s.substring(si, i);
            if(dic.contains(q)){
                StringBuilder sb = new StringBuilder(ans);
                sb.append(q);
                dfs(s, i, dic, sb, res);
            }
        }
    }
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        dfs(s, wordDict, new StringBuilder(), ans);
        return ans;
    }
}