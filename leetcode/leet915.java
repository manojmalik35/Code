public class leet915 {
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
