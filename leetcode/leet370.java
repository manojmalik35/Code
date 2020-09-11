public class leet370 {

    public int[] rangeAddition(int[] arr, int[][] q) {
        int n = arr.length;
        int[] temp = new int[n];
        for (int i = 0; i < q.length; i++) {
            temp[q[i][0]] += q[i][2];
            if (q[i][1] + 1 < n)
                temp[q[i][1] + 1] += -q[i][2];
        }

        arr[0] += temp[0];
        for (int i = 1; i < n; i++) {
            temp[i] += temp[i - 1];
            arr[i] += temp[i];
        }

        return arr;
    }

    public int[] getModifiedArray(int n, int[][] q) {// lintcode 903
        int[] temp = new int[n];
        for (int i = 0; i < q.length; i++) {
            temp[q[i][0]] += q[i][2];
            if (q[i][1] + 1 < n)
                temp[q[i][1] + 1] += -q[i][2];
        }

        for (int i = 1; i < n; i++) 
            temp[i] += temp[i - 1];
        
        return temp;
    }
}
