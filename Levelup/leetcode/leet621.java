package leetcode;
import java.util.Arrays;
public class leet621 {
    public int leastInterval(char[] tasks, int n) {
        if(tasks.length == 0)
            return 0;
        
        if(n == 0)
            return tasks.length;
        int[] freq = new int[26];
        for(char ch : tasks)
            freq[ch - 'A']++;
        
        Arrays.sort(freq);
        int maxf = freq[25] - 1;
        int spaces = maxf * n;
        for(int i = 24; i >= 0; i--)
            spaces -= Math.min(freq[i], maxf);
        
        if(spaces < 0)
            spaces = 0;
        return spaces + tasks.length;
    }

    public int leastIntervalII(char[] tasks, int n) {
        if(tasks.length == 0)
            return 0;
        
        if(n == 0)
            return tasks.length;
        int[] freq = new int[26];
        int maxf = 0;
        for(char ch : tasks){
            freq[ch - 'A']++;
            maxf = Math.max(maxf, freq[ch - 'A']);
        }
        
        int nomaxf = 0;
        for(int f : freq){
            if(f == maxf)
                nomaxf++;
        }
        
        return Math.max(tasks.length, (maxf - 1) * (n + 1) + nomaxf);
    }
}