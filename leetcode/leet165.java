import java.util.ArrayList;
public class leet165{

    static ArrayList<String> split(String s){
        ArrayList<String> ans = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            StringBuilder sb = new StringBuilder("");

            while(i < s.length() && s.charAt(i) != '.')
                sb.append(s.charAt(i++));

            ans.add(sb.toString());
            i++;
        }
        return ans;
    }

    public static int compareVersion(String v1, String v2) {
        
        int i = 0, j = 0;
        ArrayList<String> va1 = split(v1);
        ArrayList<String> va2 = split(v2);
        
        while(i < va1.size() && j < va2.size()){
            String s1 = va1.get(i);
            String s2 = va2.get(j);
            
            int i1= Integer.parseInt(s1), i2 = Integer.parseInt(s2);
            if(i1 < i2) return -1;
            if(i1 > i2) return 1;
            i++; j++;
        }
        
        while(i < va1.size()){
            String s1 = va1.get(i);
            String s2 = "0";
            int i1= Integer.parseInt(s1), i2 = Integer.parseInt(s2);
            if(i1 < i2) return -1;
            if(i1 > i2) return 1;
            i++;
        }
        
        while(j < va2.size()){
            String s1 = "0";
            String s2 = va2.get(j);
            int i1= Integer.parseInt(s1), i2 = Integer.parseInt(s2);
            if(i1 < i2) return -1;
            if(i1 > i2) return 1;
            j++;
        }
        
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(compareVersion("1.0", "1.0.0"));
    }
}