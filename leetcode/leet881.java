import java.util.Arrays;
public class leet881 {
    
    public int numRescueBoats(int[] arr, int limit) {//leetcode 881
        int n = arr.length;
        if(n == 1) return 1;
        Arrays.sort(arr);
        int i = 0, j = n - 1;
        int boats = 0;
        while(i < j){
            if(arr[j] + arr[i] <= limit)
                i++;
            j--;
            boats++;
        }

        if(i == j) boats++;
        return boats;
    }
}
