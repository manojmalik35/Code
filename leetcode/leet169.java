public class leet169 {
    
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
                else{
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
}
