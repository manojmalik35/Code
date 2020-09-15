import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class AnS4 {
    
    public int consecutiveNumbersSum(int N) {//leetcode 829
        int upperLimitOfK = (int)Math.sqrt(2 * N);
        int ans = 0;
        for(int k = 1; k <= upperLimitOfK; k++){
            if((N - (((k - 1) * k) / 2)) % k == 0)
                ans++;
        }

        return ans;
    }

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

    public List<Integer> partitionLabels(String S) {//leetcode 763
        List<Integer> ans = new ArrayList<>();
        int[] last = new int[26];
        int n = S.length();
        for(int i = 0; i < n; i++)
            last[S.charAt(i) - 'a'] = i;

        int startOfPartition = 0, endOfPartition = 0;
        for(int i = 0; i < n; i++){
            char ch = S.charAt(i);
            endOfPartition = Math.max(endOfPartition, last[ch - 'a']);
            if(i == endOfPartition){
                ans.add(endOfPartition - startOfPartition + 1);
                startOfPartition = endOfPartition + 1;
            }
        }

        return ans;
    }

    public int minDominoRotations(int[] A, int[] B) {//leetcode 1007
        int AA0 = 0, AB0 = 0, BA0 = 0, BB0 = 0;
        int n = A.length;
        for(int i = 0; i < n; i++){
            //1st variable
            if(AA0 != (int)1e8){
                if(A[i] == A[0]){}
                else if(B[i] == A[0])
                    AA0++;
                else
                    AA0 = (int)1e8;
            }

            //2nd variable
            if(AB0 != (int)1e8){
                if(A[i] == B[0]){}
                else if(B[i] == B[0])
                    AB0++;
                else
                    AB0 = (int)1e8;
            }

            //3rd variable
            if(BA0 != (int)1e8){
                if(B[i] == A[0]){}
                else if(A[i] == A[0])
                    BA0++;
                else
                    BA0 = (int)1e8;
            }

            //4th variable
            if(BB0 != (int)1e8){
                if(B[i] == B[0]){}
                else if(A[i] == B[0])
                    BB0++;
                else
                    BB0 = (int)1e8;
            }
        }

        int ans = Math.min(Math.min(AA0, AB0), Math.min(BA0, BB0));
        return ans == (int)1e8 ? -1 : ans;
    }

    // https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
    public static int minPlatforms(int[] arr, int[] dep){
        Arrays.sort(arr);
        Arrays.sort(dep);
        int n = arr.length;
        int currNoOfPlat = 0, maxPlat = 0;
        int i = 0, j = 0;
        while(i < n && j < n){
            if(arr[i] < dep[j]){
                currNoOfPlat++;
                maxPlat = Math.max(maxPlat, currNoOfPlat);
                i++;
            }else if(arr[i] > dep[j]){
                currNoOfPlat--;
                j++;
            }else{
                currNoOfPlat++;
                maxPlat = Math.max(maxPlat, currNoOfPlat);
                currNoOfPlat--;
                i++;
                j++;
            }
        }

        return maxPlat;
    }

    // https://www.geeksforgeeks.org/sort-array-wave-form-2/
    public static void waveArray(int[] arr){
        int n = arr.length;
        for(int i = 0; i < n - 1; i++){
            if((i & 1) == 0){
                if(arr[i] < arr[i + 1]){
                    //swap
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }else{
                if(arr[i] > arr[i + 1]){
                    //swap
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }

        for(int ele : arr)
            System.out.print(ele + " ");
        System.out.println();
    }

    public void reverse(int[] arr, int i, int j){
        while(i < j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    public void rotate(int[][] mat) {//leetcode 48
        //transpose
        int n = mat.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        //reverse the rows
        for(int[] ar : mat)
            reverse(ar, 0, n - 1);    
    }

    public int partitionDisjoint(int[] A) {//leetcode 915
        int leftMax = A[0], nextMax = A[0];
        int ansIdx = 0, n = A.length;;
        for(int i = 1; i < n; i++){
            nextMax = Math.max(A[i], nextMax);
            if(A[i] < leftMax){
                leftMax = nextMax;
                ansIdx = i;
            }
        }

        return ansIdx + 1;
    }
}
