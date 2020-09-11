import java.util.List;
import java.util.ArrayList;
public class leet969 {

    void flip(int[] arr, int k){
        int l = 0, r = k;
        while(l < r){
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++; r--;
        }
    }

    public List<Integer> pancakeSort(int[] A) {
        List<Integer> ans = new ArrayList<>();
        int n = A.length;
        int noToResolve = n;
        while(noToResolve > 1){
            for(int i = 0; i < n; i++){
                if(A[i] == noToResolve){
                    if(i + 1 == noToResolve) break;

                    if(i != 0){
                        flip(A, i);
                        ans.add(i + 1);
                    }
                    
                    flip(A, noToResolve - 1);
                    ans.add(noToResolve);
                    break;
                }
            }
            noToResolve--;    
        }

        return ans;
    }
}