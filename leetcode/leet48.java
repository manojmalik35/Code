public class leet48 {
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
}
