import java.util.*;
public class dp2{

    static int lcsq(String s1, String s2){//longest common subsequence

        int[][] strg = new int[s1.length() + 1][s2.length() + 1];
        for(int i = strg.length - 1; i >= 0; i--){
            for(int j = strg[0].length - 1; j >= 0; j--){
                if(i == strg.length - 1 || j == strg[0].length - 1)
                    strg[i][j] = 0;
                else{
                    char c1 = s1.charAt(i);
                    char c2 = s2.charAt(j);
                    if(c1 == c2)
                        strg[i][j] = strg[i + 1][j + 1] + 1;
                    else
                        strg[i][j] = Math.max(strg[i][j + 1], strg[i + 1][j]);
                }
            }
        }

        return strg[0][0];
    }

    static int lpsq(String s){//longest palindromic subsequence
        int[][] strg = new int[s.length()][s.length()];
        for(int gap = 0; gap < strg[0].length; gap++){
            for(int i = 0, j = i + gap; j < strg[0].length; i++, j++){
                if(gap == 0)
                    strg[i][j] = 1;
                else{
                    if(s.charAt(i) == s.charAt(j))
                        strg[i][j] = strg[i + 1][j - 1] + 2;
                    else
                        strg[i][j] = Math.max(strg[i][j - 1], strg[i + 1][j]);
                }
            }
        }

        return strg[0][strg.length - 1];
    }

    static int cpsq(String s){//count palindromic subsequences
        
        int[][] strg = new int[s.length()][s.length()];
        for(int gap = 0; gap < strg[0].length; gap++){
            for(int i = 0, j = i + gap; j < strg[0].length; i++, j++){
                if(gap == 0)
                    strg[i][j] = 1;
                else{
                    if(s.charAt(i) == s.charAt(j))
                        strg[i][j] = strg[i][j - 1] + strg[i + 1][j] + 1;
                    else    
                        strg[i][j] = strg[i][j - 1] + strg[i + 1][j] - strg[i + 1][j - 1];
                }
            }
        }
        return strg[0][strg[0].length - 1];
    }

    static int cpss(String s){//count palindromic substrings && length too
        int counter = 0;
        int length = 0;
        boolean[][] strg = new boolean[s.length()][s.length()];
        for(int gap = 0; gap < strg[0].length; gap++){
            for(int i = 0, j = i + gap; j < strg[0].length; i++, j++){
                if(gap == 0)
                    strg[i][j] = true;
                else if(gap == 1)
                    strg[i][j] = s.charAt(i) == s.charAt(j);
                else
                    strg[i][j] = s.charAt(i) == s.charAt(j) && strg[i + 1][j - 1];

                if(strg[i][j]){
                    counter++;
                    length = gap + 1;
                }
            }
        }

        System.out.println(length);
        return counter;
    }

    static int lpss(String s){//optional

        int[][] strg = new int[s.length()][s.length()];
        for(int gap = 0; gap < strg[0].length; gap++){
            for(int i = 0, j = i + gap; j < strg[0].length; i++, j++){
                if(gap == 0)
                    strg[i][j] = 1;
                else if(gap == 1)
                    strg[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 1;
                else{
                    if(s.charAt(i) == s.charAt(j) && strg[i + 1][j - 1] == gap - 1)
                        strg[i][j] = strg[i + 1][j - 1] + 2;
                    else
                        strg[i][j] = Math.max(strg[i][j - 1], strg[i + 1][j]);
                }
            }
        }

        return strg[0][strg[0].length - 1];
    }

    static int cdsq(String s){//count distinct subsequences

        int[] strg = new int[s.length()];
        int[] loa = new int[26];
        Arrays.fill(loa, -1);
        strg[0] = 2;
        loa[s.charAt(0) - 'a'] = 0;
        for(int i = 1; i < strg.length; i++){
            strg[i] = 2 * strg[i - 1];

            char ch = s.charAt(i);
            if(loa[ch - 'a'] == 0)
                strg[i] -= 1;
            else if(loa[ch - 'a'] != -1){
                int lo = loa[ch - 'a'];
                strg[i] -= strg[lo - 1];
            }

            loa[ch - 'a'] = i;
         }

        return strg[strg.length - 1];
    }

    static int cosqdbn(String s, int n){//count of subsequences divisble by n

        int[][] strg = new int[s.length()][n];
        strg[0][0] = 1;
        for(int i = 0; i < strg.length; i++){
            int d = s.charAt(i) - '0';
            if(i == 0)
                strg[i][d % n] += 1;
            else{
                for(int j = 0; j < strg[0].length; j++){
                    strg[i][j] += strg[i - 1][j];
                    int factor = j * 10 + d;
                    factor = factor % n;
                    strg[i][factor] += strg[i - 1][j];
                }
            }
        }

        return strg[strg.length - 1][0];
    }

    static int minimumPalindromicCut(String s){

        int[][] strg = new int[s.length()][s.length()];
        for(int gap = 0; gap < strg[0].length; gap++){
            for(int i = 0, j = i + gap; j < strg[0].length; i++, j++){
                if(gap == 0)
                    strg[i][j] = 0;
                else if(gap == 1)
                    strg[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                else{
                    if(s.charAt(i) == s.charAt(j) && strg[i + 1][j - 1] == 0)
                        strg[i][j] = 0;
                    else{
                        int mymin = Integer.MAX_VALUE;
                        for(int k = i; k < j; k++)
                            mymin = Math.min(mymin, strg[i][k] + strg[k + 1][j]);

                        strg[i][j] = mymin + 1;
                    }
                }                                    
            }
        }
        
        return strg[0][strg[0].length - 1];
    }

    static int eggDrop(int f, int e){
        
        int[][] strg = new int[e + 1][f + 1];
        for(int i = 1; i < strg.length; i++){
            for(int j = 0; j < strg[0].length; j++){
                if(i == 1 || j == 0 || j == 1)
                    strg[i][j] = j;
                else{
                    int mymin = f + 1;
                    for(int k = 0; k < j; k++)
                        mymin = Math.min(mymin, Math.max(strg[i - 1][k], strg[i][j - k - 1]));
                    
                    strg[i][j] = mymin + 1;
                }
            }
        }

        return strg[e][f];
    }

    static int matrixChainMultiplication(int[] mat){

        int[][] strg = new int[mat.length - 1][mat.length - 1];
        for(int gap = 1; gap < strg[0].length; gap++){
            for(int i = 0, j = i + gap; j < strg[0].length; i++, j++){
                if(gap == 1)
                    strg[i][j] = mat[i] * mat[j] * mat[j + 1];
                else{
                    int mymin = Integer.MAX_VALUE;
                    for(int k = i; k < j; k++){
                        int multcost = mat[i] * mat[k + 1] * mat[j + 1];
                        mymin = Math.min(mymin, strg[i][k] + strg[k + 1][j] + multcost);
                    }
        
                    strg[i][j] = mymin;
                }
            }
        }

        // for(int i = 0; i < strg.length; i++){
        //     for(int j = 0; j < strg[0].length; j++)
        //         System.out.print(strg[i][j] + " ");
        //     System.out.println();
        // }

        return strg[0][strg[0].length - 1];
    }

    static int maxCoinGame(int[] pile){
        int[][] strg = new int[pile.length][pile.length];
        for(int gap = 1; gap < strg[0].length; gap += 2){
            for(int i = 0, j = i + gap; j < strg[0].length; i++, j++){
                if(gap == 1)
                    strg[i][j] = Math.max(pile[i], pile[j]);
                else{
                    int factor1 = Math.min(strg[i][j - 2], strg[i + 1][j - 1]) + pile[j];
                    int factor2 = Math.min(strg[i + 2][j], strg[i + 1][j - 1]) + pile[i];
                    strg[i][j] = Math.max(factor1, factor2);
                }
            }
        }

        // for(int i = 0; i < strg.length; i++){
        //     for(int j = 0; j < strg[0].length; j++)
        //         System.out.print(strg[i][j] + " ");
        //     System.out.println();
        // }

        return strg[0][strg[0].length - 1];
    }

    static int optimalBST(int[] arr, int[] freq){

        int[] fpsa = new int[freq.length];
        fpsa[0] = freq[0];
        for(int i = 1; i < fpsa.length; i++)
            fpsa[i] = fpsa[i - 1] + freq[i];


        int[][] strg = new int[arr.length][arr.length];
        for(int gap = 0; gap < strg[0].length; gap++){
            for(int i = 0, j = i + gap; j < strg[0].length; i++, j++){
                if(gap == 0)
                    strg[i][j] = freq[i];
                else if(gap == 1)
                    strg[i][j] = Math.min(freq[i] + (2 * freq[j]), freq[j] + (2 * freq[i]));
                else{
                    int mymin = Integer.MAX_VALUE;
                    int fsum = 0;
                    for(int k = i - 1; k < j; k++){
                        int left = k < 0 ? 0 : strg[i][k];
                        int right = k + 2 > j ? 0 : strg[k + 2][j];
                        mymin = Math.min(mymin, left + right);
                        fsum += k > i - 1 ? freq[k] : 0;
                    }
                    // fsum = i >=1 ? fpsa[j] - fpsa[i - 1] : fpsa[j];
                    // strg[i][j] = mymin + fsum;
                    strg[i][j] = mymin + fsum + freq[j];
                }
            }
        }

        for(int i = 0; i < strg.length; i++){
            for(int j = 0; j < strg[0].length; j++)
                System.out.print(strg[i][j] + " ");
            System.out.println();
        }
        return strg[0][strg[0].length - 1];
    }

    public static void main(String[] args) {
        // String s1 = "abcd";
        // String s2 = "aebd";
        // System.out.println(lcsq(s1, s2));

        // String s = "abckycbc";
        // System.out.println(lpsq(s));
        // System.out.println(cpsq(s));

        // String s = "abccbc";
        // System.out.println(cpss(s));
        // System.out.println(lpss(s));

        // String s = "abcbcb";
        // System.out.println(cdsq(s));

        // String s = "235168"; int n = 6;
        // System.out.println(cosqdbn(s, n));

        // String s = "abccbc";
        // System.out.println(minimumPalindromicCut(s));

        // System.out.println(eggDrop(10, 2));

        // int[] mat = {10, 20, 30, 40, 50, 60};
        // System.out.println(matrixChainMultiplication(mat));

        // int[] pile = {20, 30, 2, 2, 2, 10};
        // System.out.println(maxCoinGame(pile));

        int[] arr = {10, 20, 30, 40, 50, 60, 70};
        int[] freq = {2, 1, 4, 1, 1, 3, 5};
        System.out.println(optimalBST(arr, freq));
    }
}