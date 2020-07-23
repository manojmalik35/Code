import java.util.*;
public class ipltickets{
 
    static int getUnique(String no){
        int f = 0;
        for(int i = 0; i < no.length(); i++){
            if(f == 1023)
                return f;
            char ch = no.charAt(i);
            int k = ch - '0';
            int bm = 1 << k;
            if((f & bm) == 0)
                f = f | bm;
        }
        return f;
    }
    static int comb(String[] arr){
        int ans = 0;
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i + 1; j < arr.length; j++){
                String concat = arr[i] + arr[j];
                int flag = getUnique(concat);
                if(flag == 1023)
                    ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];
        for(int i = 0; i < n; i++)
            arr[i] = sc.next();
            
        System.out.println(comb(arr));
    }
}