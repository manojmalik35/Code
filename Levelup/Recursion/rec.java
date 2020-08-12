package Recursion;

import java.util.*;

public class rec {

    static boolean isValidToPlaceNo(int[][] board, int r, int c, int num) {

        for (int i = 0; i < 9; i++) {
            if (board[r][i] == num)
                return false;
            if (board[i][c] == num)
                return false;
        }

        r = (r / 3) * 3;
        c = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if (board[r + i][c + j] == num)
                    return false;
        }

        return true;
    }

    static int dfs(int[][] board, int idx) {
        if (idx == 81)
            return 1;

        int r = idx / 9;
        int c = idx % 9;
        if (board[r][c] != 0)
            return dfs(board, idx + 1);

        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (isValidToPlaceNo(board, r, c, i)) {
                board[r][c] = i;
                count += dfs(board, idx + 1);
                board[r][c] = 0;
            }
        }

        return count;
    }

    static int dfs2(int[][] board, int idx, ArrayList<Integer> locOfZeroes) {
        if (idx == locOfZeroes.size())
            return 1;

        int loc = locOfZeroes.get(idx);
        int r = loc / 9;
        int c = loc % 9;
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (isValidToPlaceNo(board, r, c, i)) {
                board[r][c] = i;
                count += dfs2(board, idx + 1, locOfZeroes);
                board[r][c] = 0;
            }
        }

        return count;
    }

    public boolean isValidSudoku(int[][] board) {// leet 36
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0 && !isValidToPlaceNo(board, i, j, board[i][j]))
                    return false;
            }
        }

        return true;
    }

    static int[] row;
    static int[] col;
    static int[][] sub;

    static boolean dfs3(char[][] board, int idx, ArrayList<Integer> locOfZeroes) {
        if (idx == locOfZeroes.size())
            return true;

        int loc = locOfZeroes.get(idx);
        int r = loc / 9;
        int c = loc % 9;
        for (int i = 1; i <= 9; i++) {
            char ch = (char) (i + '0');
            int mask = (1 << i);
            if ((row[c] & mask) == 0 && (col[r] & mask) == 0 && (sub[r / 3][c / 3] & mask) == 0) {
                row[c] ^= mask;
                col[r] ^= mask;
                sub[r / 3][c / 3] ^= mask;

                board[r][c] = ch;
                if (dfs3(board, idx + 1, locOfZeroes))
                    return true;
                board[r][c] = '.';

                row[c] ^= mask;
                col[r] ^= mask;
                sub[r / 3][c / 3] ^= mask;
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {// leet 37
        row = new int[9];
        col = new int[9];
        sub = new int[3][3];
        ArrayList<Integer> locOfZeroes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    locOfZeroes.add(i * 9 + j);
                else {
                    int num = board[i][j] - '0';
                    int mask = (1 << num);
                    row[j] |= mask;
                    col[i] |= mask;
                    sub[i / 3][j / 3] |= mask;
                }
            }
        }

        dfs3(board, 0, locOfZeroes);
    }

    // N-Queens
    static boolean[] rows;
    static boolean[] cols;
    static boolean[] diag;
    static boolean[] Adiag;

    static int nqueens_booleanArr(int n, int tnq, int row) {
        if (row == n || tnq == 0) {
            if (tnq == 0)
                return 1;
            return 0;
        }

        int count = 0;
        for (int col = 0; col < n; col++) {
            if (!rows[row] && !cols[col] && !diag[row + col] && !Adiag[row - col + n - 1]) {
                rows[row] = true;
                cols[col] = true;
                diag[row + col] = true;
                Adiag[row - col + n - 1] = true;

                count += nqueens_booleanArr(n, tnq - 1, row + 1);

                rows[row] = false;
                cols[col] = false;
                diag[row + col] = false;
                Adiag[row - col + n - 1] = false;
            }
        }

        return count;
    }

    static int rowsB;
    static int colsB;
    static int diagB;
    static int AdiagB;

    static int nqueens_bits(int n, int tnq, int row) {
        if (row == n || tnq == 0) {
            if (tnq == 0)
                return 1;
            return 0;
        }

        int count = 0;
        for (int col = 0; col < n; col++) {
            if ((rowsB & (1 << row)) == 0 && (colsB & (1 << col)) == 0 && (diagB & (1 << (row + col))) == 0
                    && (AdiagB & (1 << (row - col + n - 1))) == 0) {
                rowsB ^= (1 << row);
                colsB ^= (1 << col);
                diagB ^= (1 << (row + col));
                AdiagB ^= (1 << (row - col + n - 1));

                count += nqueens_bits(n, tnq - 1, row + 1);

                rowsB ^= (1 << row);
                colsB ^= (1 << col);
                diagB ^= (1 << (row + col));
                AdiagB ^= (1 << (row - col + n - 1));
            }
        }

        return count;
    }

    public int totalNQueens(int n) {// leet 52
        // rows = new boolean[n];
        // cols = new boolean[n];
        // diag = new boolean[n + n - 1];
        // Adiag = new boolean[n + n - 1];

        rowsB = 0;
        colsB = 0;
        diagB = 0;
        AdiagB = 0;
        // return nqueens_booleanArr(n, n, 0);
        return nqueens_bits(n, n, 0);
    }
}