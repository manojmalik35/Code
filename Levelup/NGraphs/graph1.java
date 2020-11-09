import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class graph1 {

    public int shortestPathBinaryMatrix(int[][] grid) {// leetcode 1091
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;

        int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.addLast(0);

        grid[0][0] = 1;
        int dist = 1;
        while (que.size() > 0) {

            int size = que.size();
            while (size-- > 0) {
                int point = que.removeFirst();
                if (point == n * n - 1)
                    return dist;
                int r = point / n;
                int c = point % n;

                for (int d = 0; d < 8; d++) {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if (x >= 0 && y >= 0 && x < n && y < n && grid[x][y] == 0) {
                        grid[x][y] = 1;// visited mark
                        que.addLast(x * n + y);
                    }
                }
            }
            dist++;
        }

        return -1;
    }

    public int[][] updateMatrix(int[][] mat) {// leetcode 542
        int n = mat.length;
        int m = mat[0].length;

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int[][] ans = new int[n][m];
        for (int[] ar : ans)
            Arrays.fill(ar, -1);

        ArrayDeque<Integer> que = new ArrayDeque<>();
        int countOnes = n * m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    que.addLast(i * m + j);
                    ans[i][j] = 0;
                    countOnes--;
                }
            }
        }

        int dist = 1;
        while (que.size() > 0) {

            int size = que.size();
            while (size-- > 0) {

                int point = que.removeFirst();
                int r = point / m;
                int c = point % m;

                for (int d = 0; d < 4; d++) {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && ans[x][y] == -1) {
                        ans[x][y] = dist;
                        countOnes--;
                        que.addLast(x * m + y);
                        if (countOnes == 0)
                            return ans;
                    }
                }
            }
            dist++;
        }

        return ans;
    }

    int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    void dfs(int[][] A, int i, int j) {

        A[i][j] = 0;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < A.length && c < A[0].length && A[r][c] == 1)
                dfs(A, r, c);
        }
    }

    public int numEnclaves(int[][] A) {// leetcode 1020
        int n = A.length;
        int m = A[0].length;

        for (int j = 0; j < m; j++) {
            if (A[0][j] == 1)
                dfs(A, 0, j);
            if (A[n - 1][j] == 1)
                dfs(A, n - 1, j);
        }

        for (int i = 0; i < n; i++) {
            if (A[i][0] == 1)
                dfs(A, i, 0);
            if (A[i][m - 1] == 1)
                dfs(A, i, m - 1);
        }

        int count = 0;
        for (int[] ar : A) {
            for (int ele : ar) {
                if (ele == 1)
                    count++;
            }
        }

        return count;
    }

    public int numEnclaves2(int[][] A) {

        int n = A.length;
        int m = A[0].length;
        ArrayDeque<Integer> que = new ArrayDeque<>();
        int ones = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ones += A[i][j];
                if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && A[i][j] == 1) {
                    que.addLast(i * m + j);
                    ones--;
                    A[i][j] = 0;
                }
            }
        }

        while (que.size() > 0) {

            int point = que.removeFirst();
            int r = point / m;
            int c = point % m;

            for (int d = 0; d < 4; d++) {
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if (x >= 0 && y >= 0 && x < n && y < m && arr[x][y] == 1) {
                    que.addLast(x * m + y);
                    ones--;
                    arr[x][y] = 0;
                    if (ones == 0)
                        return ones;
                }
            }
        }

        return ones;
    }

    public int numBusesToDestination(int[][] routes, int S, int T) {// leetcode 815
        if(S == T) return 0;
        HashMap<Integer, ArrayList<Integer>> sbmap = new HashMap<>();// station bus map
        for (int i = 0; i < routes.length; i++) {
            for (int station : routes[i]) {
                sbmap.putIfAbsent(station, new ArrayList<>());
                sbmap.get(station).add(i);
            }
        }

        boolean[] busVis = new boolean[routes.length];
        HashSet<Integer> stationVis = new HashSet<>();
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.addLast(S);
        stationVis.add(S);
        int count = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int st = que.removeFirst();

                ArrayList<Integer> buses = sbmap.get(st);
                for (int busIdx : buses) {

                    if (!busVis[busIdx]) {
                        busVis[busIdx] = true;
                        for (int allStation : routes[busIdx]) {
                            if (!stationVis.contains(allStation)) {
                                stationVis.add(allStation);
                                que.addLast(allStation);
                                if (allStation == T)
                                    return count + 1;
                            }
                        }
                    }

                }

            }
            count++;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}