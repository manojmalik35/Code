public class leet769{
    
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int max = -(int)1e8;
        int chunks = 0;
        for(int i = 0; i < n; i++){
            max = Math.max(max, arr[i]);
            if(i == max)
                chunks++;
        }   

        return chunks;
    }
}