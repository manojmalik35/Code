package leetcode;
import java.util.*;
public class leet636{

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        int prev= logs.get(0).charAt(0) - '0';
        st.push(prev);
        for(int i = 1; i < logs.size(); i++){
            String s = logs.get(i);
            int id = 0, k = 0;
            while(s.charAt(k) != ':'){
                id = id * 10 + (s.charAt(k) - '0');
                k++;
            }
            char type = s.charAt(k + 1);
            String t = type == 's' ? s.substring(k + 7) : s.substring(k + 5);
            int itime = Integer.parseInt(t);
            if(type == 's'){
                if(st.size() != 0)
                    ans[st.peek()] += itime - prev;
                
                prev = itime;
                st.push(id);
            }else{
                ans[st.peek()] += itime - prev + 1;
                prev = itime + 1;
                st.pop();
            }
        }
    
        return ans;
    }
    
}

