import java.util.*;

public class shellRotate {
    static int[] retrieve(int[][] mat, int rmin, int rmax, int cmin, int cmax, int tle) {
        int[] rarr = new int[tle];
        int k = 0;
        while (tle > 0) {
            // top wall
            for (int j = cmin; j <= cmax && tle > 0; j++) {
                rarr[k] = mat[rmin][j];
                k++;
                tle--;
            }
            rmin++;

            // right wall
            for (int i = rmin; i <= rmax && tle > 0; i++) {
                rarr[k] = mat[i][cmax];
                k++;
                tle--;
            }
            cmax--;

            // bottom wall
            for (int j = cmax; j >= cmin && tle > 0; j--) {
                rarr[k] = mat[rmax][j];
                k++;
                tle--;
            }
            rmax--;

            // left wall
            for (int i = rmax; i >= rmin && tle > 0; i--) {
                rarr[k] = mat[i][cmin];
                k++;
                tle--;
            }
            cmin++;
        }

        return rarr;
    }

    static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    static void rotate(int[] arr, int r) {
        int n = arr.length;
        
        r %= n;
        if (r < 0)
            r += n;

        if (r == 0)
            return;
        reverse(arr, 0, r - 1);
        reverse(arr, r, n - 1);
        reverse(arr, 0, n - 1);
    }

    static void fill(int[][] mat, int rmin, int rmax, int cmin, int cmax, int tle, int[] arr) {
        int k = 0;
        while (tle > 0) {
            // top wall
            for (int j = cmin; j <= cmax && tle > 0; j++) {
                mat[rmin][j] = arr[k];
                k++;
                tle--;
            }
            rmin++;

            // right wall
            for (int i = rmin; i <= rmax && tle > 0; i++) {
                mat[i][cmax] = arr[k];
                k++;
                tle--;
            }
            cmax--;

            // bottom wall
            for (int j = cmax; j >= cmin && tle > 0; j--) {
                mat[rmax][j] = arr[k];
                k++;
                tle--;
            }
            rmax--;

            // left wall
            for (int i = rmax; i >= rmin && tle > 0; i--) {
                mat[i][cmin] = arr[k];
                k++;
                tle--;
            }
            cmin++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] mat = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                mat[i][j] = sc.nextInt();
        }
        int s = sc.nextInt();
        int r = sc.nextInt();

        int rmin = s - 1, cmin = s - 1;
        int rmax = mat.length - s;
        int cmax = mat[0].length - s;
        int toMinus = s - 1;
        int tlHor = mat[0].length - (toMinus * 2);
        int tlVer = mat.length - (toMinus * 2);
        tlHor = cmin == cmax ? tlHor : tlHor * 2;
        tlVer = rmin == rmax ? tlVer : tlVer * 2;
        int tle = tlHor + tlVer - 4;

        int[] arr = retrieve(mat, rmin, rmax, cmin, cmax, tle);

        rotate(arr, r);

        fill(mat, rmin, rmax, cmin, cmax, tle, arr);
        display(mat);
        sc.close();
    }

    public static void display(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}