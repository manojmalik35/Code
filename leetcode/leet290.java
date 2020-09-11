import java.util.HashMap;
public class leet290 {

    public boolean wordPattern(String pat, String str) {
        String[] arr = str.split(" ");
        int n = pat.length();
        if(n != arr.length) return false;
     
        String[] cmap = new String[26];
        HashMap<String, Character> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            String word = arr[i];
            char ch = pat.charAt(i);
            if(cmap[ch - 'a'] != null && !cmap[ch - 'a'].equals(word))
                return false;
            else if(map.containsKey(word) && map.get(word) != ch)
                return false;
            else{
                cmap[ch - 'a'] = word;
                map.put(word, ch);
            }
        }

        return true;
    }
}
