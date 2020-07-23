import java.util.*;
public class ukulele{

    static int uku(int n){
        int ans = 0;
        int mod = 1000000007;

        int n1 = 1, n2 = 0, n3 = 0, n4 = 0; 
        for(int i = 2; i <= n; i++){
            n4 = (n4 + n3) % mod;
            n3 = (n3 + n2) % mod;
            n2 = (n2 + n1) % mod;
            n1 = i % mod;
        }

        ans = (n1 + n2 + n3 + n4) % mod;

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // int mod = 1000000007;
        // int[][] strg = new int[4][n];
        // for(int i = 0; i < 4; i++){
        //     for(int j = 0; j < n; j++){
        //         if(i == 0)
        //             strg[i][j] = (j + 1) % mod; 
        //         else if(i == j)
        //             strg[i][j] = 1;
        //         else if(i > j)
        //             strg[i][j] = 0;
        //         else
        //             strg[i][j] = (strg[i][j - 1] + strg[i - 1][j - 1]) % mod;
        //     }
        // }

        // int ans = 0;
        // for(int i = 0; i < 4 ; i++)
        //     ans += strg[i][n - 1] % mod;

        int ans = uku(n);
        System.out.println(ans);

        sc.close();
    }
}