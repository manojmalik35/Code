public class leet977 {
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int[] ans = new int[n];
        int i = 0, j = n - 1, k = n - 1;
        while(k >= 0){
            if(A[i] * A[i] < A[j] * A[j]){
                ans[k] = A[j] * A[j];
                j--;
                k--;
            }else{
                ans[k] = A[i] * A[i];
                i++;
                k--;
            }
        }
        
        return ans;
    }
}
