import java.util.Stack;
public class leet1249 {

    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        int n = s.length();
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == '(')
                st.push(i);
            else if(ch == ')'){
                if(st.size() != 0) st.pop();
                else sb.setCharAt(i, '#');
            }
        }

        while(st.size() != 0) sb.setCharAt(st.pop(), '#');

        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < n; i++) 
            if(sb.charAt(i) != '#') ans.append(sb.charAt(i));
        
        return ans.toString();
    }
    
    public String minRemoveToMakeValid2(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int open = 0;
        for(int i = 0; i < n; i++){
            char ch = arr[i];
            if(ch == '(') open++;
            else if(ch == ')'){
                if(open > 0) open--;
                else arr[i] = '#';
            }
        }
        
        for(int i = n - 1; i >= 0; i--)
            if(arr[i] == '(' && open-- > 0) arr[i] = '#';
            
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < n; i++) 
            if(arr[i] != '#') ans.append(arr[i]);
        
        return ans.toString();
    }
}
