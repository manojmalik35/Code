import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class AnS3 {

    public int numSubarrayBoundedMax(int[] A, int L, int R) {// leetcode 795
        int n = A.length;
        int ans = 0, prevValidCount = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] >= L && A[i] <= R) {
                ans += (i - j + 1);
                prevValidCount = (i - j + 1);
            } else if (A[i] < L) {
                ans += prevValidCount;
            } else {
                j = i + 1;
                prevValidCount = 0;
            }
        }

        return ans;
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // https://www.geeksforgeeks.org/segregate-0s-and-1s-in-an-array-by-traversing-array-once/
    public void sort01(int[] arr) {
        int n = arr.length;
        int i = 0, j = 0;
        while (j < n) {
            if (arr[j] == 1)
                j++;
            else {
                swap(arr, i, j);
                i++;
                j++;
            }
        }
    }

    // https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
    public void sort012(int[] arr) {
        int n = arr.length;
        int i = 0, j = 0, k = 0;
        while (k < n) {
            if (arr[k] == 2)
                k++;
            else if (arr[k] == 1) {
                swap(arr, j, k);
                j++;
                k++;
            } else {
                swap(arr, j, k);
                k++;
                swap(arr, i, j);
                i++;
                j++;
            }
        }
    }

    public void sort012II(int[] arr) {
        int n = arr.length;
        int i = 0, j = 0, k = n - 1;
        while (j <= k) {
            if (arr[j] == 1)
                j++;
            else if (arr[j] == 0) {
                swap(arr, i, j);
                i++;
                j++;
            } else {
                swap(arr, j, k);
                k--;
            }
        }
    }

    public int maximumSwap(int num) {// leetcode 670
        char[] arr = (num + "").toCharArray();
        int n = arr.length;
        int[] digits = new int[10];
        for (int i = 0; i < 10; i++)
            digits[i] = -1;
        for (int i = n - 1; i >= 0; i--) {
            int d = arr[i] - '0';
            if (digits[d] == -1)
                digits[d] = i;
        }

        for (int i = 0; i < n; i++) {
            int d = arr[i] - '0';
            if (d == 9)
                continue;

            for (int j = 9; j > d; j--) {
                if (digits[j] > i) {
                    char temp = arr[digits[j]];
                    arr[digits[j]] = arr[i];
                    arr[i] = temp;
                    return Integer.valueOf(new String(arr));
                }
            }
        }

        return Integer.parseInt(new String(arr));
    }

    // https://www.geeksforgeeks.org/sieve-of-eratosthenes/
    public static void SOE(int n) {
        boolean[] arr = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (!arr[i]) {
                for (int j = 2; i * j <= n; j++)
                    arr[i * j] = true;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!arr[i])
                System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void SOE(int n, ArrayList<Integer> primes) {
        boolean[] arr = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (!arr[i]) {
                for (int j = 2; i * j <= n; j++)
                    arr[i * j] = true;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!arr[i])
                primes.add(i);
        }
    }

    // https://www.spoj.com/problems/PRIME1/
    public static void primeGenerator(int m, int n) {
        boolean[] arr = new boolean[n - m + 1];
        int len = arr.length;
        if(m == 1) arr[0] = true;
        ArrayList<Integer> primes = new ArrayList<>();
        int s = (int) Math.sqrt(n);
        SOE(s, primes);
        for (int p : primes) {
            int firstFactor = (int) (Math.ceil(m * 1.0 / p) * p);
            int si = firstFactor - m;
            if(firstFactor == p) si += p;
            for (int i = si; i < len; i += p) 
                arr[i] = true;
        }

        for (int i = 0; i < len; i++) {
            if (!arr[i])
                System.out.println(i + m);
        }
        System.out.println();
    }

    // https://www.geeksforgeeks.org/given-an-array-a-and-a-number-x-check-for-pair-in-a-with-sum-as-x/
    boolean targetSumPair(int[] arr, int tar){
        Arrays.sort(arr);
        int n = arr.length;
        int i = 0, j = n - 1;
        while(i < j){
            if(arr[i] + arr[j] < tar)
                i++;
            else if(arr[i] + arr[j] > tar)
                j--;
            else
                return true;
        }

        return false;
    }

    // https://www.geeksforgeeks.org/find-a-pair-with-the-given-difference/
    public static boolean targetDiffPair(int[] arr, int d){
        Arrays.sort(arr);
        int n = arr.length;
        int i = 0, j = 1;
        while(j < n){
            if(arr[j] - arr[i] < d)
                j++;
            else if(arr[j] - arr[i] > d)
                i++;
            else
                return true;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] st = br.readLine().split(" ");
            int m = Integer.parseInt(st[0]);
            int n = Integer.parseInt(st[1]);
            primeGenerator(m, n);
        }
    }

}
