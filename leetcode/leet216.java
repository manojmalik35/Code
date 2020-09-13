import java.util.List;
import java.util.ArrayList;
public class leet216 {
    void recursion(int k, int n, int boolArr, int lastNoUsed, List<Integer> temp, List<List<Integer>> ans){
        if(k == 0){
            if(n == 0){
                List<Integer> base = new ArrayList<>(temp);
                ans.add(base);
            }
            return;
        }
        
        for(int i = lastNoUsed + 1; i <= 9; i++){
            int mask = (1 << i);
            if((boolArr & mask) == 0){
                boolArr ^= mask;
                temp.add(i);
                recursion(k - 1, n - i, boolArr, i, temp, ans);
                temp.remove(temp.size() - 1);
                boolArr ^= mask;
            }
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        recursion(k, n, 0, 0, new ArrayList<>(), ans);
        return ans;
    }
}
