import java.util.List;
import java.util.ArrayList;

public class leet763 {

    //Greedy
    public List<Integer> partitionLabels(String S) {//leetcode 763
        List<Integer> ans = new ArrayList<>();
        int[] last = new int[26];
        int n = S.length();
        for(int i = 0; i < n; i++)
            last[S.charAt(i) - 'a'] = i;

        int startOfPartition = 0, endOfPartition = 0;
        for(int i = 0; i < n; i++){
            char ch = S.charAt(i);
            endOfPartition = Math.max(endOfPartition, last[ch - 'a']);
            if(i == endOfPartition){
                ans.add(endOfPartition - startOfPartition + 1);
                startOfPartition = endOfPartition + 1;
            }
        }

        return ans;
    }
}
