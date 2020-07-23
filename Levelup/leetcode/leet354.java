package leetcode;
import java.util.*;
public class leet354 {

    static int binarySearch(int[] arr, int lo, int hi, int data){
        while(lo <= hi){
            int mid = (lo + hi) / 2;

            if(arr[mid] < data)
                lo = mid + 1;
            else
                hi = mid - 1;
        }

        return lo;
    }

    static int LIS_Eff(int[][] arr){//O(nlogn)
        int[] dp = new int[arr.length];
        int lo = 0, hi = 0;
        dp[0] = arr[0][1];
        for(int i = 1; i < arr.length; i++){

            if(dp[hi] < arr[i][1]){
                dp[hi + 1] = arr[i][1];
                hi++;
            }else{
                int ceilIndex = binarySearch(dp, lo, hi, arr[i][1]);
                dp[ceilIndex] = arr[i][1];
            }
        }

        return hi + 1;
    }

    public int maxEnvelopes(int[][] envelopes) {//leet 354
        if(envelopes.length == 0)
            return 0;

        Arrays.sort(envelopes, (int[] a, int[] b)->{
            if(a[0] != b[0])
                return a[0] - b[0];
            return b[1] - a[1];
        });

        //return LIS_Eff(envelopes);
        
        int lis = 1;
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        for(int i = 1; i < envelopes.length; i++){
            dp[i] = 1;
            for(int j = 0; j <i; j++){
                if(envelopes[i][1] > envelopes[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            lis = Math.max(lis, dp[i]);
        }
        
        return lis;
    }
}