import java.util.*;

public class techgig {

    static long solve(long[] req, long[] aval) {
        long min = Long.MAX_VALUE;
        for (int i = 0; i < req.length; i++) {
            long r = req[i];
            long a = aval[i];
            if(a % r == 0)
                min = Math.min(min, (a / r));
            
        }

        return min;
    }

    static long solve2(long[] grev, long[] opp){
        long max = 0;
        Arrays.sort(grev);
        Arrays.sort(opp);
        for(int i = 0; i < grev.length; i++)
            System.out.print(grev[i] + " ");
        System.out.println();
        for(int i = 0; i < grev.length; i++)
            System.out.print(opp[i] + " ");
        System.out.println();
        int i = 0, j = 0;
        while(i < grev.length && j < opp.length){
            if(grev[i] > opp[j]){
                max++;
                i++;
                j++;
            }else
                i++;

            System.out.print(max);
        }

        return max;
    }

    static int minEffort = (int)1e8;
    static void minEfforts_Rec(int pat, int prevDoc, int eff, String s, int[][] efforts, int flag){

        if(pat == efforts[0].length){
            // System.out.println(s + " " + eff + " " + Integer.toBinaryString(flag));
            minEffort = Math.min(minEffort, eff);
            return;
        }

        for(int d = 0; d < efforts.length; d++){
            if((flag & (1 << d)) == 0){
                if(prevDoc != -1 && d != prevDoc)
                    flag = (flag ^ (1 << prevDoc));
                minEfforts_Rec(pat + 1, d, eff + efforts[d][pat], s + d, efforts, flag);
                if(prevDoc != -1 && d != prevDoc)
                    flag = (flag ^ (1 << prevDoc));
            }
        }
    }

    static int minEfforts(){
        int P = sc.nextInt();
        int D = sc.nextInt();
        int[][] efforts = new int[D][P];
        for(int i = 0; i < D; i++){
            for(int j = 0; j < P; j++)
                efforts[i][j] = sc.nextInt();
        }
        
        minEfforts_Rec(0, -1, 0, "", efforts, 0);
        return minEffort;
    }

    static int mod = (int)1e9 + 7;
    static int zones_Rec(int prevCol, int n, int[][] all){
        if(n == 0)
            return 1;
        
        int count = 0;
        for(int col = 0; col < 3; col++){
            if(prevCol != -1 && col != prevCol){
                if(all[prevCol][col] == 0)
                    continue;
                all[prevCol][col]--;
                count += (zones_Rec(col, n - 1, all) % mod) % mod;
                all[prevCol][col]++;
            }else
                count += (zones_Rec(col, n - 1, all) % mod) % mod;
        }

        return count;
    }

    static int zones(){
        int n = sc.nextInt();
        int[][] all = new int[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(i == j)
                    all[i][j] = (int)1e8;
                else
                    all[i][j] = sc.nextInt();
            }
        }

        if(n == 1)
            return 3;
        int ans = zones_Rec(-1, n, all);
        return ans;
    }

    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]) throws Exception {

        // int n = sc.nextInt();
        // long[] req = new long[n];
        // long[] aval = new long[n];
        // for (int i = 0; i < n; i++)
        //     req[i] = sc.nextInt();

        // for (int i = 0; i < n; i++)
        //     aval[i] = sc.nextInt();

        // System.out.println(solve2(req, aval));

        // System.out.println(minEfforts());

        System.out.println(zones());
        sc.close();

    }
}