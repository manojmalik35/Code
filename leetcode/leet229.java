import java.util.List;
import java.util.ArrayList;
public class leet229 {
    
    public List<Integer> majorityElement(int[] nums) {//leetcode 229
        int n = nums.length;
        if(n == 0) return new ArrayList<>();
        int val1 = nums[0];
        int val2 = -1;
        int count1 = 1;
        int count2 = 0;
        
        for(int i = 1; i < n; i++){
            if(nums[i] == val1)
                count1++;
            else if(nums[i] == val2)
                count2++;
            else if(count1 == 0){
                val1 = nums[i];
                count1 = 1;
            }else if(count2 == 0){
                val2 = nums[i];
                count2 = 1;
            }else{//pair up
                count1--;
                count2--;
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        int occurence1 = 0, occurence2 = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] == val1) occurence1++;
            else if(nums[i] == val2) occurence2++;
        }

        if(occurence1 > n / 3) ans.add(val1);
        if(occurence2 > n / 3) ans.add(val2);
        return ans;
    }
}
