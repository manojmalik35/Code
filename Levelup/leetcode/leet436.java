import java.util.Arrays;
public class leet436 {
    class Interval implements Comparable<Interval>{
        int si;
        int ei;
        int idx;
        
        Interval(int si, int ei, int idx){
            this.si = si;
            this.ei = ei;
            this.idx = idx;
        }
        
        public int compareTo(Interval other){
            return this.si - other.si;
        }
    }
    
    public int[] findRightInterval(int[][] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Interval[] inter = new Interval[n];
        for(int i = 0; i < n; i++)
            inter[i] = new Interval(arr[i][0], arr[i][1], i);
        
        Arrays.sort(inter);
        
        for(int i = 0; i < n; i++){
            Interval in = inter[i];
            int val = in.ei;
            int lo = 0, hi = n - 1;
            if(val > inter[hi].si){
                ans[in.idx] = -1;
                continue;
            } 
            while(lo < hi){
                int mid = (lo + hi) >> 1;
                if(inter[mid].si == val){
                    lo = mid;
                    break;
                }else if(inter[mid].si < val)
                    lo = mid + 1;
                else
                    hi = mid;
            }
            
            if(lo >= n)
                ans[in.idx] = -1;
            else
                ans[in.idx] = inter[lo].idx;
        }
        
        return ans;
    }

    public int[] findRightIntervalII(int[][] arr) {
        int minSi = (int)1e8, maxEi = -(int)1e8;
        int n = arr.length;
        for(int i = 0; i < n; i++){
            minSi = Math.min(minSi, arr[i][0]);
            maxEi = Math.max(maxEi, arr[i][1]);
        }

        int[] buckets = new int[maxEi - minSi + 1];
        for(int i = 0; i < buckets.length; i++) buckets[i] = -1;
        
        for(int i = 0; i < n; i++) buckets[arr[i][0] - minSi] = i;

        for(int i = buckets.length - 2; i >= 0; i--)
            if(buckets[i] == -1) buckets[i] = buckets[i + 1];

        int[] ans = new int[n];
        for(int i = 0; i < n; i++) ans[i] = buckets[arr[i][1] - minSi];

        return ans;
    }
}