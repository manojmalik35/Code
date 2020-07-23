import java.util.*;
public class dp1 {
    
    static int countpaths(int[] arr, int idx, int[] qb){
        if(idx == arr.length - 1)
            return 1;

        if(idx >= arr.length)
            return 0;

        if(qb[idx] != 0)
            return qb[idx];

        int res = 0;

        int noj = arr[idx];
        for(int i = 1; i <= noj; i++){
            int rres = countpaths(arr, idx + i, qb);
            res += rres;
        }

        qb[idx] = res;
        return res;
    }

    static int countPathsTab(int[] arr){
        int[] strg = new int[arr.length];
        strg[arr.length - 1] = 1;
        for(int i = arr.length - 2; i >= 0; i--){
            int noj = arr[i];
            for(int j = 1; j <= noj && i + j < arr.length; j++)
                strg[i] += strg[i + j];
        }

        return strg[0];
    }

    static int minJumpsTab(int[] arr){
        int[] strg = new int[arr.length];
        strg[strg.length - 1] = 0;
        for(int i = strg.length - 2; i >= 0; i--){
            int noj = arr[i];

            int mymin = arr.length;
            for(int j = 1; j <= noj && i + j < strg.length; j++)
                mymin = Math.min(mymin, strg[i + j]);
            
            strg[i] = mymin + 1;
        }

        return strg[0];
    }

    static void printMinCostPath(int[][] strg, int i, int j, String psf){
        if(i == strg.length - 1 && j == strg[0].length - 1){
            System.out.println(psf);
            return;
        }

        if(i == strg.length - 1)
            printMinCostPath(strg, i, j + 1, psf + "R");
        else if(j == strg[0].length - 1)
            printMinCostPath(strg, i + 1, j, psf + "D");
        else{
            if(strg[i][j + 1] < strg[i + 1][j])
                printMinCostPath(strg, i, j + 1, psf + "R");
            else if(strg[i + 1][j] < strg[i][j + 1])
                printMinCostPath(strg, i + 1, j, psf + "D");
            else{
                printMinCostPath(strg, i, j + 1, psf + "R");
                printMinCostPath(strg, i + 1, j, psf + "D");
            }
        }
    }

    static int minCost(int[][] arr){
        int[][] strg = new int[arr.length][arr[0].length];
        for(int i = strg.length - 1; i >= 0; i--){
            for(int j = strg[0].length - 1; j >= 0; j--){
                if(i == strg.length - 1 && j == strg[0].length - 1)
                    strg[i][j] = 0;
                else if(i == strg.length - 1)
                    strg[i][j] = arr[i][j] + strg[i][j + 1];
                else if(j == strg[0].length - 1)
                    strg[i][j] = arr[i][j] + strg[i + 1][j];
                else
                    strg[i][j] = arr[i][j] + Math.min(strg[i][j + 1], strg[i + 1][j]);
            }
        }

        printMinCostPath(strg, 0, 0, "");
        return strg[0][0];
    }

    static void printGoldMinePath(int[][] strg, int i, int j, String psf){
        if(j == strg[0].length - 1){
            System.out.println(psf);
            return;
        }

        if(i == 0){
            if(strg[i][j + 1] > strg[i + 1][j + 1])
                printGoldMinePath(strg, i, j + 1, psf + "S");
            else if(strg[i + 1][j + 1] > strg[i][j + 1])
                printGoldMinePath(strg, i + 1, j + 1, psf + "D");
            else{
                printGoldMinePath(strg, i, j + 1, psf + "S");
                printGoldMinePath(strg, i + 1, j + 1, psf + "D");
            }
        }else if(i == strg.length - 1){
            if(strg[i][j + 1] > strg[i - 1][j + 1])
                printGoldMinePath(strg, i, j + 1, psf + "S");
            else if(strg[i - 1][j + 1] > strg[i][j + 1])
                printGoldMinePath(strg, i - 1, j + 1, psf + "U");
            else{
                printGoldMinePath(strg, i, j + 1, psf + "S");
                printGoldMinePath(strg, i - 1, j + 1, psf + "U");
            }
        }else{
            int maxv = Math.max(strg[i- 1][j + 1], Math.max(strg[i][j + 1], strg[i + 1][j + 1]));
            if(strg[i - 1][j + 1] == maxv)
                printGoldMinePath(strg, i - 1, j + 1, psf + "U");

            if(strg[i][j + 1] == maxv)
                printGoldMinePath(strg, i, j + 1, psf + "S");

            if(strg[i + 1][j + 1] == maxv)
                printGoldMinePath(strg, i + 1, j + 1, psf + "D");
        }
    }

    static int goldMine(int[][] arr){
        int[][] strg = new int[arr.length][arr[0].length];
        for(int j = strg[0].length - 1; j >= 0; j--){
            for(int i = 0; i < strg.length; i++){
                if(j == strg[0].length - 1)
                    strg[i][j] = arr[i][j];
                else if(i == 0)
                    strg[i][j] = arr[i][j] + Math.max(strg[i][j + 1], strg[i + 1][j + 1]);
                else if(i == strg.length - 1)
                    strg[i][j] = arr[i][j] + Math.max(strg[i][j + 1], strg[i - 1][j + 1]);
                else
                    strg[i][j] = arr[i][j] + Math.max(strg[i][j + 1], Math.max(strg[i - 1][j + 1], strg[i + 1][j + 1]));
            }
        }

        int maxi = 0;
        for(int i = 1; i < strg.length; i++){
            if(strg[i][0] > strg[maxi][0])
                maxi = i;
        }

        printGoldMinePath(strg, maxi, 0, "");
        return strg[maxi][0];
    }

    static int cobsolnwnc0(int n){//count of binary strings of length n with no consecutive zeroes
        int c0 = 1;
        int c1 = 1;
        while(n-- > 1){
            int oc0 = c0;//old
            int oc1 = c1;//old

            c0 = oc1;
            c1 = oc0 + oc1;
        }

        return c0 + c1;
    }

    static boolean isValid(String s){
        if(s.charAt(0) == '0')
            return false;

        int t = Integer.parseInt(s);
        if(t >= 1 && t <= 26)
            return true;
        return false;
    }

    static int encodings(String s){

        if(s.charAt(0) == '0')
            return 0;

        int[] strg = new int[s.length()];
        strg[0] = 1;
        strg[1] = s.charAt(1) == '0' ? 0 : 1;

        if(isValid(s.substring(0,2)))
            strg[1] += 1;

        for(int i = 2; i < strg.length; i++){
            char ch = s.charAt(i);

            if(ch != '0')
                strg[i] = strg[i - 1];

            if(isValid(s.charAt(i - 1) + "" + ch))
                strg[i] += strg[i - 2];
        }

        return strg[strg.length - 1];
    }

    static int codsonaibjck(String s){//count of distinct subsequences of nature a^i b^j c^k

        int ca = 0, cb = 0, cc = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(ch == 'a')
                ca = 2 * ca + 1;
            else if(ch == 'b')
                cb = 2 * cb + ca;
            else if(ch == 'c')
                cc = 2 * cc + cb;
        }

        return cc;
    }

    static int maxShareProfit(int[] arr, int tc){

        int bp = 0, sp = 0;
        bp -= arr[0];
        for(int i = 1; i < arr.length; i++){
            int obp = bp;
            int osp = sp;

            int tp = arr[i];//today's price
            bp = Math.max(obp, osp - tp);
            sp = Math.max(osp, obp + tp - tc);
        }

        return sp;
    }

    static boolean targetSumSubsets(int[] arr, int tar){
        boolean[][] strg = new boolean[arr.length][tar + 1];
        for(int i = 0; i < strg.length; i++){
            for(int j = 0; j < strg[0].length; j++){
                if(j == 0)
                    strg[i][j] = true;
                else if(i == 0)
                    strg[i][j] = arr[i] == j;
                else{
                    if(arr[i] > j)
                        strg[i][j] = strg[i - 1][j];
                    else
                        strg[i][j] = strg[i - 1][j] || strg[i - 1][j - arr[i]];
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        printTargetSumSubsets(strg, arr, strg.length - 1, tar, res);
        return strg[strg.length - 1][tar];
    }

    static int countTargetSumSubsets(int[] arr, int tar){
        int[][] strg = new int[arr.length][tar + 1];
        for(int i = 0; i < strg.length; i++){
            for(int j = 0; j < strg[0].length; j++){
                if(j == 0)
                    strg[i][j] = 1;
                else if(i == 0)
                    strg[i][j] = arr[i] == j ? 1 : 0;
                else{
                    if(arr[i] > j)
                        strg[i][j] = strg[i - 1][j];
                    else
                        strg[i][j] = strg[i - 1][j] + strg[i - 1][j - arr[i]];
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        printTargetSumSubsets(strg, arr, strg.length - 1, tar, res);
        return strg[strg.length - 1][tar];
    }

    static void printTargetSumSubsets(boolean[][] strg, int[] arr, int i, int j, ArrayList<Integer> res){
        
        if(j == 0){
            Collections.reverse(res);
            System.out.println(res);
            return;
        }

        if(i == 0){
            res.add(arr[i]);
            Collections.reverse(res);
            System.out.println(res);
            res.remove(res.size() - 1);
            return;
        }

        if(strg[i - 1][j])
            printTargetSumSubsets(strg, arr, i - 1, j, res);

        if(j - arr[i] >= 0 && strg[i - 1][j - arr[i]]){
            res.add(arr[i]);
            printTargetSumSubsets(strg, arr, i - 1, j - arr[i], res);
            res.remove(res.size() - 1);
        }
    }

    static void printTargetSumSubsets(int[][] strg, int[] arr, int i, int j, ArrayList<Integer> res){
        
        if(j == 0){
            Collections.reverse(res);
            System.out.println(res);
            return;
        }

        if(i == 0){
            res.add(arr[i]);
            Collections.reverse(res);
            System.out.println(res);
            res.remove(res.size() - 1);
            return;
        }

        if(strg[i - 1][j] != 0)
            printTargetSumSubsets(strg, arr, i - 1, j, res);

        if(j - arr[i] >= 0 && strg[i - 1][j - arr[i]] != 0){
            res.add(arr[i]);
            printTargetSumSubsets(strg, arr, i - 1, j - arr[i], res);
            res.remove(res.size() - 1);
        }
    }

    static int coinChangePermutation(int[] coins, int amt){
        int[] strg = new int[amt + 1];
        strg[0] = 1;
        for(int i = 1; i < strg.length; i++){
            for(int j = 0; j < coins.length; j++){
                if(coins[j] <= i)
                    strg[i] += strg[i - coins[j]];
            }
        }

        return strg[amt];
    }

    static int coinChangeCombination(int[] coins, int amt){
        int[] strg = new int[amt + 1];
        strg[0] = 1;
        for(int i = 0; i < coins.length; i++){
            for(int j = 1; j < strg.length; j++){
                if(coins[i] <= j)
                    strg[j] += strg[j - coins[i]];
            }
        }

        return strg[amt];
    }

    static int zoknapsack(int[] wts, int[] prices, int cap){

        int[][] strg = new int[wts.length][cap + 1];
        for(int i = 0; i < strg.length; i++){
            for(int j = 0; j < strg[0].length; j++){
                if(j == 0)
                    strg[i][j] = 0;
                else if(i == 0)
                    strg[i][j] = j >= wts[i] ? prices[i] : 0;
                else{
                    if(j < wts[i])
                        strg[i][j] = strg[i - 1][j];
                    else
                        strg[i][j] = Math.max(strg[i - 1][j], prices[i] + strg[i - 1][j - wts[i]]);
                }
            }
        }

        pathzoknapsack(strg, strg.length - 1, cap, "", wts, prices);
        return strg[strg.length - 1][cap];
    }

    static void pathzoknapsack(int[][] strg, int i, int j, String psf, int[] wts, int[] prices){
        if(j == 0){
            System.out.println(psf);
            return;
        }

        if(i == 0){
            psf += wts[i];
            System.out.println(psf);
            return;
        }

        if(strg[i - 1][j] == strg[i][j])
            pathzoknapsack(strg, i - 1, j, psf, wts, prices);

        if(j - wts[i] >= 0 && strg[i - 1][j - wts[i]] + prices[i] == strg[i][j])
            pathzoknapsack(strg, i - 1, j - wts[i], psf + wts[i] + " ", wts, prices);
    }

    static int unboundedKnapsack(int[] wts, int[] prices, int cap){

        int[] strg = new int[cap + 1];
        strg[0] = 0;
        for(int i = 0; i < wts.length; i++){
            for(int j = 1; j < strg.length; j++){
                if(j >= wts[i])
                    strg[j] = Math.max(strg[j], prices[i] + strg[j - wts[i]]);
            }
        }

        pathUnboundedKnapsack(strg, cap, "", wts, prices);
        return strg[cap];
    }

    static void pathUnboundedKnapsack(int[] strg, int i, String asf, int[] wts, int[] prices){
        if(i == 0){
            System.out.println(asf);
            return;
        }

        int count = 0;
        for(int j = 0; j < wts.length; j++){
            if(i - wts[j] >= 0 && strg[i] - prices[j] == strg[i - wts[j]]){
                if(count == 0){
                    count++;
                    pathUnboundedKnapsack(strg, i - wts[j], asf + wts[j] + " ", wts, prices);
                }
            }
        }
    }

    static int friendsPair(int n){
        if(n == 0 || n == 1)
            return 1;
        

        int fnm1 = friendsPair(n - 1);
        int fnm2 = friendsPair(n - 2);
        return fnm1 + (n - 1) * fnm2;
    }

    static int friendsPairDP(int n){
        if(n == 0 || n == 1)
            return 1;

        int fnm1 = 1, fnm2 = 1;
        int fn = 0;
        for(int i = 2; i <= n; i++){
            fn = fnm1 + (i - 1) * fnm2;
            fnm2 = fnm1;
            fnm1 = fn;
        }

        return fn;
    }

    static int tileArea(int m, int n){
        if(n < m)
            return 1;

        int[] strg = new int[n + 1];
        strg[0] = 0;
        strg[1] = 1;
        for(int i = 2; i < strg.length; i++)
            strg[i] = strg[i - 1] + strg[i - m];

        return strg[n];
    }

    public static void main(String[] args) {
        // int[] arr = {4, 2, 0, 0, 2, 4, 6, 3, 1, 0, 1, 2, 3, 1, 1};
        // int[] qb = new int[arr.length];
        // System.out.println(countpaths(arr, 0, qb));
        // System.out.println(countPathsTab(arr));
        // System.out.println(minJumpsTab(arr));

        // int[][] arr = {{2,7,3,8,0,3,7},
        //                {4,0,1,2,4,3,4},
        //                {3,8,5,9,0,8,1},
        //                {1,6,0,4,5,5,2},
        //                {6,2,9,5,7,0,6},
        //                {0,8,7,9,6,3,0},
        //                {1,3,5,0,5,1,0}};

        // System.out.println(minCost(arr));

        // int[][] arr = {{1, 5, 1, 0, 3},
        //                {1, 4, 0, 2, 3},
        //                {4, 0, 1, 3, 2},
        //                {2, 4, 0, 4, 1},
        //                {1, 2, 3, 2, 0}};
        // System.out.println(goldMine(arr));

        // System.out.println(cobsolnwnc0(7));

        // System.out.println(encodings("12132612014"));

        // System.out.println(codsonaibjck("abcabc"));

        // int[] arr = {9, 1, 3, 10, 1, 4, 6, 9};
        // int tc = 2;
        // System.out.println(maxShareProfit(arr, tc));

        // int[] arr = {5, 2, 4, 3, 7, 6}; int tar = 7;
        // System.out.println(targetSumSubsets(arr, tar));
        // System.out.println(countTargetSumSubsets(arr, tar));

        // int[] coins = {2, 3, 5, 6}; int amt = 7;
        // System.out.println(coinChangePermutation(coins, amt));
        // System.out.println(coinChangeCombination(coins, amt));

        // int[] wts = {2, 5, 1, 3, 4};
        // int[] prices = {15, 14, 10, 45, 30};
        // int cap = 7;
        // System.out.println(zoknapsack(wts, prices, cap));
        // System.out.println(unboundedKnapsack(wts, prices, cap));

        // System.out.println(friendsPair(45));
        // System.out.println(friendsPairDP(45));

        System.out.println(tileArea(2, 10));
    }
}