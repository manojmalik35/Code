import java.util.*;
public class leet46 {
    void permutations(int[] arr, int idx, List<Integer> b, List<List<Integer>> ans){
        if(idx== arr.length){
            List<Integer> base = new ArrayList<>(b);
            ans.add(base);
            return;
        }
        
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != -(int)1e8){
                int val = arr[i];
                arr[i] = -(int)1e8;
                b.add(val);
                permutations(arr, idx + 1, b, ans);
                b.remove(b.size() - 1);
                arr[i] = val;
            }
        }
    }
    
    void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    void permutations(int[] arr, int idx, List<List<Integer>> ans){
        if(idx == arr.length - 1){
            List<Integer> base = new ArrayList<>();
            for(int n : arr) base.add(n);
            ans.add(base);
            return;
        }
        
        for(int i = idx; i < arr.length; i++){
            swap(arr, idx, i);
            permutations(arr, idx + 1, ans);
            swap(arr, idx, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // permutations(nums, 0, new ArrayList<>(), ans);
        permutations(nums, 0, ans);
        return ans; 
    }
}