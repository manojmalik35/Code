import java.util.*;
import java.io.*;

public class Q001 {

    static int bs = 0; // block size
    static int[] arr;
    static int[][][] blocks;

    static int query(int l, int r, int x) {
        int count = 0;
        while (l % bs != 0 && l <= r)
            count = arr[l++] <= x ? count + 1 : count;

        int[] ar = { x, (int) 1e8 };
        while (l + bs - 1 <= r) {
            int rv = Arrays.binarySearch(blocks[l / bs], ar, (a, b) -> {
                return a[0] - b[0];
            });
            if(rv < 0){
                rv *= -1;
                rv--;
            }else if(rv != bs)
                rv++;
            count += rv;
            l += bs;
        }

        while (l <= r)
            count = arr[l++] <= x ? count + 1 : count;

        return count;
    }

    static void update(int i, int val) {
        int bi = i / bs;
        arr[i] = val;

        for (int j = 0; j < blocks[bi].length; j++) {
            if (blocks[bi][j][1] == i) {
                blocks[bi][j][0] = val;
                break;
            }
        }

        Arrays.sort(blocks[bi], (a, b) -> {
            return a[0] - b[0];
        });
    }

    static void solve() {
        int n = sc.nextInt();
        int q = sc.nextInt();
        arr = new int[n];

        bs = (int) Math.ceil(Math.sqrt(n));
        blocks = new int[bs][bs][2];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            blocks[i / bs][i % bs][0] = arr[i];
            blocks[i / bs][i % bs][1] = i;
        }

        for (int i = 0; i < blocks.length; i++) {
            Arrays.sort(blocks[i], (a, b) -> {
                return a[0] - b[0];
            });
        }

        while (q-- > 0) {
            char c = sc.next().charAt(0);
            if (c == 'M') {
                int idx = sc.nextInt();
                // idx--;
                int val = sc.nextInt();
                update(idx, val);
            } else {
                int l = sc.nextInt();
                int r = sc.nextInt();
                // l--; r--;
                int x = sc.nextInt();
                System.out.println(query(l, r, x));
            }
        }
    }

    static Scanner sc = new Scanner(new InputStreamReader(System.in));

    public static void main(String[] args) {
        solve();
    }
}