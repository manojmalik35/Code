import java.util.*;

public class hm {

    public int[] intersection(int[] nums1, int[] nums2) {// leet 349
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums1)
            set.add(i);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                set.remove(i);
                list.add(i);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++)
            ans[i] = list.get(i);

        return ans;
    }

    public int[] intersect(int[] nums1, int[] nums2) {// leet 350
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int ele : nums1)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        for (int ele : nums2) {
            if (map.containsKey(ele)) {
                if (map.get(ele) > 0) {
                    list.add(ele);
                    map.put(ele, map.get(ele) - 1);
                }

                if (map.get(ele) == 0)
                    map.remove(ele);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++)
            ans[i] = list.get(i);

        return ans;
    }

    public int longestConsecutive(int[] nums) {// leet 128
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums)
            set.add(ele);

        int len = 0;
        for (int ele : nums) {
            if (!set.contains(ele))
                continue;

            set.remove(ele);
            int prev = ele - 1;
            int next = ele + 1;

            while (set.contains(prev))
                set.remove(prev--);
            while (set.contains(next))
                set.remove(next++);

            len = Math.max(len, next - prev - 1);
        }

        return len;
    }

    public int longestArithSeqLength(int[] A) {// leet 1027
        HashMap<Integer, Integer>[] dp = new HashMap[A.length];
        for (int i = 0; i < dp.length; i++)
            dp[i] = new HashMap<>();

        int maxLen = 0;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                int d = A[i] - A[j];

                dp[i].put(d, dp[j].getOrDefault(d, 1) + 1);
                maxLen = Math.max(maxLen, dp[i].get(d));
            }
        }

        return maxLen;
    }
}