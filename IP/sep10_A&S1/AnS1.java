public class AnS1{

    public boolean isLongPressedName(String name, String typed) {//leetcode 925
        
        int i = 0, j = 0;
        
        while(i < name.length() && j < typed.length()){
            
            char ch1 = name.charAt(i);
            char ch2 = typed.charAt(j);
            if(ch1 == ch2){
                i++;
                j++;
            }else{
                if(j > 0 && ch2 == typed.charAt(j - 1))
                    j++;
                else
                    return false;
            }
            
        }
        
        if(j == typed.length() && i < name.length()) return false;
        while(j < typed.length()){
            if(typed.charAt(j) != name.charAt(i - 1))
                return false;
            j++;
        }
        
        return true;
    }

    public int[] rangeAddition(int[] arr, int[][] q) {//leetcode 370
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

    public int maxArea(int[] height) {//leetcode 11
        int n = height.length;
        int i = 0, j = n - 1;
        int area = 0;
        while(i < j){
            area = Math.max(area, (j - i) * Math.min(height[i], height[j]));
            if(height[i] <= height[j]) i++;
            else j--;
        }    

        return area;
    }

    public int[] sortedSquares(int[] A) {//leetcode 977
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

    void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int nextGreaterElement(int number) {//leetcode 556
        char[] arr = (number + "").toCharArray();
        int n = arr.length;
        int i = n - 2;
        while(i >= 0 && arr[i] >= arr[i + 1])
            i--;

        if(i < 0) return -1;
        char dipEle = arr[i];
        char jge = 255;//just greater element(ceil)
        int jgi = 0;//just greater index
        for(int j = i + 1; j < n; j++){
            if(arr[j] > dipEle && arr[j] <= jge){
                jge = arr[j];
                jgi = j;
            }
        }

        //swap
        swap(arr, i, jgi);

        //reverse
        int l = i + 1, r = n - 1;
        while(l < r){
            swap(arr, l, r);
            l++;
            r--;
        }

        long ans = 0;
        for(int x = 0; x < n; x++){
            ans = ans * 10 + (arr[x] - '0');
        }
        
        if(ans > Integer.MAX_VALUE) return -1;
        return (int)ans;
    }

}