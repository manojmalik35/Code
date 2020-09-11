public class leet556 {

    void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int nextGreaterElement(int number) {
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

    public int nextGreaterElement2(int number) {
        String s = number + "";
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        int i = n - 2;
        while(i >= 0 && sb.charAt(i) >= sb.charAt(i + 1))
            i--;

        if(i < 0) return -1;
        char dipEle = sb.charAt(i);
        char jge = 255;//just greater element(ceil)
        int jgi = 0;//just greater index
        for(int j = i + 1; j < n; j++){
            if(sb.charAt(j) > dipEle && sb.charAt(j) <= jge){
                jge = sb.charAt(j);
                jgi = j;
            }
        }

        //swap
        sb.setCharAt(i, s.charAt(jgi));
        sb.setCharAt(jgi, s.charAt(i));

        s = sb.toString();
        //reverse
        int l = i + 1, r = n - 1;
        while(l < r){
            sb.setCharAt(l, s.charAt(r));
            sb.setCharAt(r, s.charAt(l));
            l++;
            r--;
        }

        s = sb.toString();
        long ans = Long.parseLong(s);    
        if(ans > Integer.MAX_VALUE) return -1;
        return (int)ans;
    }
}
