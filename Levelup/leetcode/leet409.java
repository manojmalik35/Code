import java.util.*;

public class leet409 {
    public int longestPalindrome(String s) {
        int even = 0;
        int odd = 0;
        HashMap<Character, Integer> fmap = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
        }
        
        for(Character c : fmap.keySet()){
            int val = fmap.get(c);
            if((val & 1) == 0)
                even += val;
            else{
                even += val - 1;
                odd++;
            }
        }

        return odd == 0 ? even : even + 1;
    }

    public int longestPalindromeII(String s) {
        int even = 0;
        int odd = 0;
        int[] fmap = new int[128];
        for(char c : s.toCharArray())
            fmap[c]++;
        
        for(int i = 0; i < 128; i++){
            int val = fmap[i];
            even += val / 2 * 2;
            if((val & 1) != 0)
                odd++;
        }

        return odd == 0 ? even : even + 1;
    }
}