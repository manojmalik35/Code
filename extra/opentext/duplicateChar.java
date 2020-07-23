import java.util.*;
public class duplicateChar{

    static char maxOccur(String s){
        int[] arr = new int[256];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            arr[c] += 1;
        }

        char ch = s.charAt(0);

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(arr[c] > arr[ch])
                ch = c;
        }

        return ch;
    }

    static char maxOccur2(String s){
        HashMap<Character, Integer> fmap = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(fmap.containsKey(c))
                fmap.put(c, fmap.get(c) + 1);
            else
                fmap.put(c, 1);
        }

        char ch = s.charAt(0);

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(fmap.get(c) > fmap.get(ch))
                ch = c;
        }

        return ch;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        char c = maxOccur2(s);
        System.out.println(c);
    }
}