package leetcode;
import java.util.HashSet;
public class leet128 {
    public int longestConsecutive(int[] nums) {//leet 128
        HashSet<Integer> set = new HashSet<>();
        for(int ele : nums)
            set.add(ele);

        int len = 0;
        for(int ele : nums){
            if(!set.contains(ele))
                continue;

            set.remove(ele);
            int prev = ele - 1;
            int next = ele + 1;

            while(set.contains(prev)) set.remove(prev--);
            while(set.contains(next)) set.remove(next++);

            len = Math.max(len, next - prev - 1);
        }

        return len;
    }
}