import java.util.*;
public class hm{

    static char hfc(String s){
        HashMap<Character, Integer> fmap = new HashMap<>();
        for(char c : s.toCharArray()){
            if(fmap.containsKey(c))
                fmap.put(c, fmap.get(c) + 1);
            else
                fmap.put(c, 1);
        }

        char ans = s.charAt(0);
        for(char c : fmap.keySet()){
            if(fmap.get(c) > fmap.get(ans))
                ans = c;
        }
        return ans;
    }

    static void gce1(int[] one, int[] two){//get common elements
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int val : one)
            map.put(val, 1);

        for(int val : two){
            if(map.containsKey(val)){
                System.out.print(val + " ");
                map.remove(val);
            }
        }
    }

    static void gce2(int[] one, int[] two){//proper intersection
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int val : one){
            if(map.containsKey(val))
                map.put(val, map.get(val) + 1);
            else
                map.put(val, 1);
        }

        for(int val : two){
            if(map.containsKey(val) && map.get(val) > 0){
                System.out.print(val + " ");
                map.put(val, map.get(val) - 1);
            }
        }

    }

    static void longestSequenceConsecutiveElements(int[] arr){
        HashMap<Integer, Boolean> map = new HashMap<>();
        for(int val : arr)
            map.put(val, true);

        for(int val : arr){
            if(map.containsKey(val - 1))
                map.put(val, false);
        }

        int maxl = 0;
        int msp = 0;
        for(int val : arr){
            if(map.get(val)){
                int t = val;
                int tlen = 1;
                while(map.containsKey(t + tlen)){
                    tlen++;
                }
                if(tlen > maxl){
                    maxl = tlen;
                    msp = t;
                }
            }
        }

        for(int i = msp; i < msp + maxl; i++)
            System.out.print(i + " ");

    }

    static int countSubarrayssumTar(int[] arr, int tar){
        HashMap<Integer, Integer> psf = new HashMap<>();
        int ps = 0;
        int count = 0;
        psf.put(ps, 1);
        for(int val : arr){
            ps += val;

            int factor = ps - tar;
            if(psf.containsKey(factor)){
                count += psf.get(factor);
            }

            if(psf.containsKey(ps))
                psf.put(ps, psf.get(ps) + 1);
            else
                psf.put(ps, 1);

        }

        return count;
    }

    static int longestSubarraysumTar(int[] arr, int tar){
        int ps = 0;
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(ps, -1);
        for(int i = 0; i < arr.length; i++){
            ps += arr[i];

            int factor = ps - tar;
            if(map.containsKey(factor))
                len = Math.max(len, i - map.get(factor));

            if(map.containsKey(ps) == false)
                map.put(ps, i);
        }

        return len;
    }

    static int countsbaMultiplek(int[] arr, int k){
        int ps = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(ps, 1);
        for(int val : arr){
            ps += val;
            int f = ps % k;
            if(f < 0)
                f += k;

            if(map.containsKey(f) == false)
                map.put(f, 1);
            else{
                count += map.get(f);
                map.put(f, map.get(f) + 1);
            }

        }

        return count;
    }

    static int longestSubarrayMultiplek(int[] arr, int k){
        int ps = 0;
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(ps, -1);
        for(int i = 0; i < arr.length; i++){
            ps += arr[i];
            int f = ps % k;
            if(f < 0)
                f += k;

            if(map.containsKey(f) == false)
                map.put(f, i);
            else
                len = Math.max(len, i - map.get(f));
            
        }

        return len;
    }

    public static void main(String[] args) {
        // String s = "baaabbcc";
        // System.out.println(hfc(s));
        // int[] one = {2, 1, 1, 3, 5, 1, 2};
        // int[] two = {5, 2, 4, 1, 2, 2, 1};
        // gce1(one, two);
        // System.out.println();
        // gce2(one, two);
        // int[] arr = {2, 12, 9, 16, 10, 5, 3, 20, 25, 11, 1, 8, 6};
        // longestSequenceConsecutiveElements(arr);
        // int[] arr = {2, 3, -4, 1, 2, -3, 0, 1, 1, -5, 3, 0, 2, 1, 2, 4};
        // System.out.println(countSubarrayssumTar(arr, 3));
        // int[] arr = {2, 3, -4, 1, 2, -3, 0, 1, 1, -5, 3, 0, 2, 1, 2};
        // System.out.println(longestSubarraysumTar(arr, 4));
        int[] arr = {7, 4, 3, 4, 18, 9, 1, 7, -59, 4, -15, 3};
        System.out.println(countsbaMultiplek(arr, 5));
        System.out.println(longestSubarrayMultiplek(arr, 5));
    }
}