import java.util.Arrays;
public class leet1365{

    static int fi(int[] arr, int data){
        int l = 0, r = arr.length - 1;
        while(l < r){
            int mid = (l + r) / 2;
            if(arr[mid] < data)
                l = mid + 1;
            else if(arr[mid] > data)
                r = mid - 1;
            else
                r = mid - 1;
        }

        return r;
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] temp = new int[n];
        for(int i = 0; i < n; i++)
            temp[i] = nums[i];
        
        Arrays.sort(temp);
        for(int i = 0; i < n; i++){
            int ce = nums[i];
            int nose = fi(temp, ce);//no of small elements
            ans[i] = nose;
        }
        
        return ans;
    }

    public int[] smallerNumbersThanCurrentII(int[] nums) {
        int[] freq = new int[101];
        for(int n : nums)
            freq[n]++;
        
        int nose = freq[0];//no of small elements
        for(int i = 1; i < 101; i++){
            int foce = freq[i];//freq of curr element
            freq[i] = nose;
            nose += foce;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0) continue;
            nums[i] = freq[nums[i]];
        }
        
        return nums;
    }
}