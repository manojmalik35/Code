public class leet795 {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {//leetcode 795
        int n = A.length;
        int ans = 0, prevValidCount = 0;
        int j = 0;
        for(int i = 0; i < n; i++){
            if(A[i] >= L && A[i] <= R){
                ans += (i - j + 1);
                prevValidCount = (i - j + 1);
            }else if(A[i] < L){
                ans += prevValidCount;
            }else{
                j = i + 1;
                prevValidCount = 0;
            }
        }
        
        return ans;
    }
}
