import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
public class AnS2 {

    public int maxChunksToSorted(int[] arr) {// leetcode 769
        int n = arr.length;
        int max = -(int) 1e8;
        int chunks = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            if (i == max)
                chunks++;
        }

        return chunks;
    }

    public int maxChunksToSortedII(int[] arr) {// leetcode 768
        int n = arr.length;
        int[] rmin = new int[n];
        rmin[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--)
            rmin[i] = Math.min(arr[i], rmin[i + 1]);

        int lmax = -(int) 1e9;
        int chunks = 0;
        for (int i = 0; i < n - 1; i++) {
            lmax = Math.max(lmax, arr[i]);
            if (lmax <= rmin[i + 1])
                chunks++;
        }

        return chunks + 1;
    }

    public int[] productExceptSelf(int[] nums) {// leetcode 238
        int n = nums.length;
        int[] prefixProd = new int[n];
        prefixProd[0] = nums[0];
        int[] suffixProd = new int[n];
        suffixProd[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++)
            prefixProd[i] = prefixProd[i - 1] * nums[i];

        for (int i = n - 2; i >= 0; i--)
            suffixProd[i] = suffixProd[i + 1] * nums[i];

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0)
                ans[i] = suffixProd[i + 1];
            else if (i == n - 1)
                ans[i] = prefixProd[i - 1];
            else
                ans[i] = prefixProd[i - 1] * suffixProd[i + 1];
        }

        return ans;
    }

    public int[] productExceptSelf2(int[] nums) {// O(1) space except ans[]
        int n = nums.length;
        int[] prefixProd = new int[n];
        prefixProd[0] = nums[0];
        for (int i = 1; i < n; i++)
            prefixProd[i] = prefixProd[i - 1] * nums[i];

        int suffixProd = 1;
        for (int i = n - 1; i > 0; i--) {
            prefixProd[i] = prefixProd[i - 1] * suffixProd;
            suffixProd *= nums[i];
        }

        prefixProd[0] = suffixProd;
        return prefixProd;
    }

    public int majorityElement(int[] nums) {// leetcode 169
        // Boyer-Moore Voting Algorithm
        int n = nums.length;
        int val = nums[0];
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == val)
                count++;
            else {
                if (count != 0)
                    count--;
                else {
                    val = nums[i];
                    count = 1;
                }
            }
        }

        int occurence = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == val)
                occurence++;
        }

        return occurence > n / 2 ? val : -1;
    }

    public List<Integer> majorityElementII(int[] nums) {// leetcode 229
        int n = nums.length;
        if (n == 0)
            return new ArrayList<>();
        int val1 = nums[0];
        int val2 = -1;
        int count1 = 1;
        int count2 = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] == val1)
                count1++;
            else if (nums[i] == val2)
                count2++;
            else if (count1 == 0) {
                val1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                val2 = nums[i];
                count2 = 1;
            } else {// pair up
                count1--;
                count2--;
            }
        }

        List<Integer> ans = new ArrayList<>();
        int occurence1 = 0, occurence2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == val1)
                occurence1++;
            else if (nums[i] == val2)
                occurence2++;
        }

        if (occurence1 > n / 3)
            ans.add(val1);
        if (occurence2 > n / 3)
            ans.add(val2);
        return ans;
    }

    // https://www.geeksforgeeks.org/given-an-array-of-of-size-n-finds-all-the-elements-that-appear-more-than-nk-times/
    public static List<Integer> majorityElementIII(int[] nums, int k) { // O(nk)
        int n = nums.length;
        int[] val = new int[k - 1];
        int[] count = new int[k - 1];
        val[0] = nums[0];
        count[0] = 1;
        for (int i = 1; i < k - 1; i++)
            val[i] = -1;

        for (int i = 1; i < n; i++) {

            int idx = -1;
            for (int j = 0; j < k - 1; j++) {
                if (val[j] == nums[i]) {
                    idx = j;
                    break;
                }
            }

            if (idx != -1) {
                count[idx]++;
                continue;
            }

            idx = -1;
            for (int j = 0; j < k - 1; j++) {
                if (count[j] == 0) {
                    idx = j;
                    break;
                }
            }

            if (idx != -1) {
                val[idx] = nums[i];
                count[idx] = 1;
                continue;
            }

            for (int j = 0; j < k - 1; j++)
                count[j]--;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k - 1; i++) {

            int me = val[i];
            int occurence = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] == me)
                    occurence++;
            }

            if (occurence > n / k)
                ans.add(me);
        }

        return ans;
    }

    public static List<Integer> majorityElementIII_Best(int[] nums, int k) { // O(n)
        int n = nums.length;
        HashMap<Integer, Integer> fmap = new Hashmap<>();
        for(int i = 0; i < n; i++)
            fmap.put(nums[i], fmap.getOrDefault(nums[i], 0) + 1);

        List<Integer> ans = new ArrayList<>();
        for(Integer num : fmap.keySet()){
            if(fmap.get(num) > n / k)
                ans.add(num);
        }

        return ans;
    }

    public int dominantIndex(int[] nums) {//leetcode 747
        int n = nums.length;
        if(n == 1) return 0; 
        int max = -(int)1e8;
        int smax = -(int)1e8;
        int mi = -1;//max index
        for(int i = 0; i < n; i++){
            if(nums[i] >= max){
                smax = max;
                max = nums[i];
                mi = i;
            }else if(nums[i] > smax)
                smax = nums[i];
        }

        if(smax * 2 <= max) return mi;
        return -1;
    }

    public int minTotalDistance(int[][] grid) {//lintcode 912 && leetcode 296
        int r = grid.length;
        int c = grid[0].length;
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(grid[i][j] == 1)
                    rows.add(i);
            }
        }

        int rowAns = rows.get((rows.size() >> 1));
        
        for(int j = 0; j < c; j++){
            for(int i = 0; i < r; i++){
                if(grid[i][j] == 1)
                cols.add(j);
            }
        }

        int colAns = cols.get((cols.size() >> 1));

        int totalDist = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(grid[i][j] == 1)
                    totalDist += Math.abs(rowAns - i) + Math.abs(colAns - j);
            }
        }

        return totalDist;
    }

    public int maximumProduct(int[] nums) {//leetcode 628
        int n = nums.length;
        int max1 = -(int)1e8;
        int max2 = -(int)1e8;
        int max3 = -(int)1e8;
        int min1 = (int)1e8;
        int min2 = (int)1e8;
        for(int i = 0; i < n; i++){
            if(nums[i] >= max1){
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            }else if(nums[i] >= max2){
                max3 = max2;
                max2 = nums[i];
            }else if(nums[i] > max3)
                max3 = nums[i];

            if(nums[i] <= min1){
                min2 = min1;
                min1 = nums[i];
            }else if(nums[i] < min2)
                min2 = nums[i];
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }

    public static void main(String[] args) {
        int[] nums = {2,3,3,2}; 
        int k = 3;
        List<Integer> res = majorityElementIII(nums, k);
        System.out.println(res);
    }
}
