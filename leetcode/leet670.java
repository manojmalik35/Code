public class leet670 {
    public int maximumSwap(int num) {//leetcode 670
        char[] arr = (num + "").toCharArray();
        int n = arr.length;
        int[] digits = new int[10];
        for(int i = 0; i < 10; i++) digits[i] = -1;
        for(int i = n - 1; i >= 0; i--){
            int d = arr[i] - '0';
            if(digits[d] == -1) digits[d] = i;
        }

        for(int i = 0; i < n; i++){
            int d = arr[i] - '0';
            if(d == 9) continue;

            for(int j = 9; j > d; j--){
                if(digits[j] > i){
                    char temp = arr[digits[j]];
                    arr[digits[j]] = arr[i];
                    arr[i] = temp;
                    return Integer.valueOf(new String(arr));
                }
            }
        }

        return Integer.valueOf(new String(arr));
    }
}
