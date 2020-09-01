public class nextGreaterPalindrome{

    static void incrementMiddle(int[] arr, int mid, int n){//143325
        int nd = arr[mid] + 1;
        int carry = nd / 10;
        arr[mid] = nd % 10;
        int i = mid - 1;
        while(i >= 0 && carry > 0){
            nd = arr[i] + carry;
            carry = nd / 10;
            arr[i] = nd % 10;
            i--;
        }
    }

    static int[] ngp(int[] arr){
        int n = arr.length;
        int[] ans = new int[n + 1];
        int i = (n >> 1) - 1, j = 0, mid = 0;

        if((n & 1) == 0){
            j = i + 1;
            mid = i;
        } 
        else{
            j = i + 2;
            mid = i + 1;
        } 

        while(i >= 0 && j < n && arr[i] == arr[j]){
            ans[i] = ans[j] = arr[i];
            i--; j++;
        }

        if(arr[i] < arr[j]) incrementMiddle(arr, mid, n);
        ans[mid] = arr[mid];

        while(i >= 0 && j < n){
            ans[i] = ans[j] = arr[i];
            i--; j++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = 999;
        String s = n + "";
        int[] arr = new int[s.length()];
        for(int i = 0; i < s.length(); i++)
            arr[i] = s.charAt(i) - '0';
        
        int[] ans = ngp(arr);
        for(int i = 0; i < ans.length; i++) System.out.print(ans[i] + " ");
        System.out.println();
    }
}