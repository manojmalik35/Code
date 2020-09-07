import java.util.TreeSet;
import java.util.HashMap;

public class leet220 {

    // Brute force
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j - i <= k && Math.abs(1L * nums[i] - nums[j]) <= t)
                    return true;
            }
        }

        return false;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int n = nums.length;
        if (n == 0 || k <= 0)
            return false;
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            Long floor = set.floor(1L * nums[i] + t);
            Long ceil = set.ceiling(1L * nums[i] - t);

            if ((floor != null && nums[i] <= floor) || (ceil != null && nums[i] >= ceil))
                return true;

            set.add(1L * nums[i]);
            if (i >= k)
                set.remove(1L * nums[i - k]);
        }

        return false;
    }

    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        int n = nums.length;
        if (n == 0 || k <= 0 || t < 0)
            return false;

        HashMap<Integer, Integer> buckets = new HashMap<>();
        for (int i = 0; i < n; i++) {

            int bucket = (int) (nums[i] / ((long) t + 1));
            if (nums[i] < 0) bucket--;

            if (buckets.containsKey(bucket)) return true;
            else {

                buckets.put(bucket, nums[i]);
                if (buckets.containsKey(bucket - 1) && (long) nums[i] - (long) buckets.get(bucket - 1) <= t) return true;
                if (buckets.containsKey(bucket + 1) && (long) buckets.get(bucket + 1) - (long) nums[i] <= t) return true;

                if (buckets.size() > k) {
                    int bucketToRemove = (int) (nums[i - k] / ((long) t + 1));
                    if (nums[i - k] < 0) bucketToRemove--;
                    buckets.remove(bucketToRemove);
                }
            }
        }

        return false;
    }
}
