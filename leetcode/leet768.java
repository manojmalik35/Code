public class leet768 {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] rmin = new int[n];        
        rmin[n - 1] = arr[n - 1];
        for(int i = n - 2; i >= 0; i--)
            rmin[i] = Math.min(arr[i], rmin[i + 1]);

        int lmax = -(int)1e9;
        int chunks = 0;
        for(int i = 0; i < n - 1; i++){
            lmax = Math.max(lmax, arr[i]);
            if(lmax <= rmin[i + 1])
                chunks++;
        }

        return chunks + 1;
    }       
}
