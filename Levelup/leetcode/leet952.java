import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
public class leet952 {

    static int findParent(HashMap<Integer, Integer> par, int u){
        if(par.get(u) == u)
            return u;
        else{
            int parent = findParent(par, par.get(u));
            par.put(u, parent);
            return parent;
        }
    }

    static void merge(HashMap<Integer, Integer> par, HashMap<Integer, Integer> size, int u, int v){
        int aPar = findParent(par, u);
        int bPar = findParent(par, v);
        if(aPar == bPar) return;
        if(size.get(aPar) < size.get(bPar)){
            par.put(aPar, bPar);
            size.put(bPar, size.get(bPar) + size.get(aPar));
        }else{
            par.put(bPar, aPar);
            size.put(aPar, size.get(aPar) + size.get(bPar));
        }
    }

    static HashSet<Integer> primeFactors(int n){
        HashSet<Integer> set = new HashSet<>();
        for(int i = 2; i * i <= n; i++){
            while(n % i == 0){
                set.add(i);
                n /= i;
            }
        }

        if(n != 1) set.add(n);
        return set;
    }

    public static int largestComponentSize(int[] A) {
        int n = A.length;
        if(n == 1) return 1;
        HashMap<Integer, Integer> par = new HashMap<>();
        HashMap<Integer, Integer> size = new HashMap<>(); 
        HashMap<Integer, HashSet<Integer>> primes = new HashMap<>();       
        for(int i = 0; i < n; i++){
            par.put(A[i], A[i]);
            size.put(A[i], 1);
            primes.put(A[i], primeFactors(A[i]));
        }

        HashMap<Integer, ArrayList<Integer>> reverseMap = new HashMap<>();
        for(int key : primes.keySet()){
            HashSet<Integer> set = primes.get(key);
            for(int val : set){
                reverseMap.compute(val, (k, v)->{
                    if(v == null) v = new ArrayList<>();
                    v.add(key);
                    return v;
                });
            }
        }

        // for(int i = 0; i < n; i++){
        //     int u = A[i];
        //     for(int j = i + 1; j < n; j++){
        //         int v = A[j];
        //         int aPar = findParent(par, u);
        //         int bPar = findParent(par, v);
        //         if(aPar != bPar && isPossible(primes, u, v)) merge(par, size, aPar, bPar);
        //     }
        // }

        for(ArrayList<Integer> list : reverseMap.values()){
            for(int i = 1; i < list.size(); i++)
                merge(par, size, list.get(i - 1), list.get(i));
        }

        int largestCompSize = 0;
        for(int v : par.keySet()){
            if(v == par.get(v)) largestCompSize = Math.max(largestCompSize, size.get(v));
        }

        return largestCompSize;
    }

    static int findParent2(int[] par, int u){
        if(par[u] == u)
            return u;
        else{
            int parent = findParent2(par, par[u]);
            par[u] = parent;
            return parent;
        }
    }

    static void merge2(int[] par, int[] size, int u, int v){
        int aPar = findParent2(par, u);
        int bPar = findParent2(par, v);
        if(aPar == bPar) return;
        if(size[aPar] < size[bPar]){
            par[aPar] = bPar;
            size[bPar] += size[aPar];
        }else{
            par[bPar] = aPar;
            size[aPar] += size[bPar];
        }
    }

    public static int largestComponentSize2(int[] A) {
        int n = A.length;
        if(n == 1) return 1;
        int[] par = new int[n];
        int[] size = new int[n];
        HashMap<Integer, HashSet<Integer>> primes = new HashMap<>();       
        for(int i = 0; i < n; i++){
            par[i] = i;
            size[i] = 1;
            primes.put(i, primeFactors(A[i]));
        }

        HashMap<Integer, ArrayList<Integer>> reverseMap = new HashMap<>();
        for(int key : primes.keySet()){
            HashSet<Integer> set = primes.get(key);
            for(int val : set){
                reverseMap.compute(val, (k, v)->{
                    if(v == null) v = new ArrayList<>();
                    v.add(key);
                    return v;
                });
            }
        }

        for(ArrayList<Integer> list : reverseMap.values()){
            for(int i = 1; i < list.size(); i++)
                merge2(par, size, list.get(i - 1), list.get(i));
        }

        int largestCompSize = 0;
        for(int i = 0; i < n; i++){
            if(i == par[i]) largestCompSize = Math.max(largestCompSize, size[i]);
        }

        return largestCompSize;
    }

    static int[] sieve(int lim){
        int[] spf = new int[lim + 1];
        for(int i = 2; i <= lim; i++){
            if(spf[i] == 0){
                for(int j = i; j <= lim; j += i)
                    spf[j] = spf[j] == 0 ? i : spf[j];
            }
        }

        return spf;
    }

    public static int largestComponentSize3(int[] A) {
        int n = A.length;
        if(n == 1) return 1;
        int[] par = new int[n];
        int[] size = new int[n];
        int[] spf = sieve(1000);
        int[] label = new int[1000];
        
        for(int i = 0; i < label.length; i++) label[i] = -1;

        for(int i = 0; i < n; i++){
            par[i] = i;
            size[i] = 1;

            int val = A[i];
            while(val > 1){
                int primeFactor = spf[val];
                if(label[primeFactor] == -1) label[primeFactor] = i;
                else merge2(par, size, i, label[primeFactor]);
                val /= primeFactor;
            }
        }

        int largestCompSize = 0;
        for(int i = 0; i < n; i++){
            findParent2(par, i);
            if(i == par[i]) largestCompSize = Math.max(largestCompSize, size[i]);
        }

        return largestCompSize;
    }

    public static void main(String[] args) {
        int[] A = {2,3,6,7,4,12,21,39};
        // System.out.println(largestComponentSize(A));
        // System.out.println(largestComponentSize2(A));
        System.out.println(largestComponentSize3(A));
    }
}