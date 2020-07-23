import java.util.*;

public class sqrt {

    static int bs = 0; // block size
    static int[] arr;
    static int[] blocks;

    static int query(int l, int r) {
        int sum = 0;
        while (l % bs != 0)
            sum += arr[l++];

        while (l + bs - 1 <= r) {
            sum += blocks[l / bs];
            l += bs;
        }

        while (l <= r)
            sum += arr[l++];

        return sum;
    }

    static void update(int i, int val) {
        int bi = i / bs;
        int diff = val - arr[i];
        arr[i] = val;

        blocks[bi] += diff;
    }

    static void solve() {
        int n = sc.nextInt();
        arr = new int[n];

        bs = (int) Math.ceil(Math.sqrt(n));
        blocks = new int[bs];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            blocks[i / bs] += arr[i];
        }

        int q = sc.nextInt();
        while (q-- > 0) {
            char c = sc.next().charAt(0);
            if (c == 'u') {
                int idx = sc.nextInt();
                int val = sc.nextInt();
                update(idx, val);
            } else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(query(l, r));
            }
        }
    }

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}