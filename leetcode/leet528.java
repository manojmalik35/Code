import java.util.Random;

public class leet528 {

    class Solution {

        int[] cf;
        public Solution(int[] w) {
            this.cf = new int[w.length];
            cf[0] = w[0];
            for(int i = 1; i < w.length; i++)
                cf[i] = cf[i - 1] + w[i];
        }

        // public int pickIndex() {
        //     int n = cf.length;
        //     //cf[ n - 1 ] pure arr k sum k equal h..... or +1 isliye kiya h kyuki value 0 se ni 1 se chahiye
        //     int rand = new Random().nextInt(cf[n - 1]) + 1;
        //     for(int i = 0; i < cf.length; i++){
        //         if(cf[i] >= rand)
        //             return i;
        //     }

        //     return 0;
        // }

        //2nd solution (Binary search)
        public int pickIndex() {
            int n = cf.length;
            //cf[ n - 1 ] pure arr k sum k equal h..... or +1 isliye kiya h kyuki value 0 se ni 1 se chahiye
            int rand = new Random().nextInt(cf[n - 1]) + 1;
            int lo = 0;
            int hi = n - 1;
            while(lo < hi){
                int mid = (lo + hi) >>> 1;
                if(cf[mid] < rand)
                    lo = mid + 1;
                else if(cf[mid] > rand)
                    hi = mid;
                else
                    return mid;
            }

            return lo;
        }
    }

}