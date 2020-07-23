public class nqueen {

    static boolean isSafe(int x, int y, int ca, int da, int sda, int size) {

        if((ca & (1 << y)) != 0 || (da & (1 << (x + y))) != 0 || (sda & (1 << (x - y + size - 1))) != 0)
            return false;
        return true;
    }

    static int nqueenf(int row, int size, int tnq, String ans, int ca, int da, int sda) {

        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        // row array is not necessary kyuki queen place krte hi hum next row me jare h
        // to same row me to queen dobara place hogi hi ni
        int count = 0;
        for (int col = 0; col < size; col++) {

            if (isSafe(row, col, ca, da, sda, size)) {

                ca ^= (1 << col);
                da ^= (1 << (row + col));
                sda ^= (1 << (row - col + size - 1));

                count += nqueenf(row + 1, size, tnq - 1, ans + "(" + row + "-" + col + " ) ", ca, da, sda);

                ca ^= (1 << col);
                da ^= (1 << (row + col));
                sda ^= (1 << (row - col + size - 1));
            }

        }

        return count;
    }

    public static void main(String[] args) {

        System.out.println(nqueenf(0, 4, 4, "", 0, 0, 0));
    }
}