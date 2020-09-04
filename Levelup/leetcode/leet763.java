import java.util.List;
import java.util.ArrayList;

public class leet763 {

    //Greedy
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        int[] last = new int[26];
        int n = S.length();
        for(int i = 0; i < n; i++)
            last[S.charAt(i) - 'a'] = i;

        int startOfPartition = 0, j = 0;
        for(int i = 0; i < n; i++){
            char ch = S.charAt(i);
            j = Math.max(j, last[ch - 'a']);
            if(i == j){
                ans.add(i - startOfPartition + 1);
                startOfPartition = j + 1;
            }
        }

        return ans;
    }
}
