public class leet925 {
    public boolean isLongPressedName(String name, String typed) {
        
        int i = 0, j = 0;
        
        while(i < name.length() && j < typed.length()){
            
            char ch1 = name.charAt(i);
            char ch2 = typed.charAt(j);
            if(ch1 == ch2){
                i++;
                j++;
            }else{
                if(j > 0 && ch2 == typed.charAt(j - 1))
                    j++;
                else
                    return false;
            }
            
        }
        
        if(j == typed.length() && i < name.length()) return false;
        while(j < typed.length()){
            if(typed.charAt(j) != name.charAt(i - 1))
                return false;
            j++;
        }
        
        return true;
    }
}
