import java.util.*;
public class crypto{

    static String unique(String s1,String s2,String s3){
        String ans="";
        boolean[] duplicates=new boolean[26];
        for(char ch : s1.toCharArray()){
            if(duplicates[ch-'a'] == false){
                ans += ch;
                duplicates[ch-'a'] = true;
            }            
        }

        for(char ch : s2.toCharArray()){
            if(duplicates[ch-'a'] == false){
                ans += ch;
                duplicates[ch-'a'] = true;
            }            
        }

        for(char ch : s3.toCharArray()){
            if(duplicates[ch-'a'] == false){
                ans += ch;
                duplicates[ch-'a'] = true;
            }            
        }
        return ans;
    }

    static int getNumber(String s,int[] map){
        int ans=0;
        int p=1;

        for(int i=s.length()-1;i>=0;i--){
            ans += map[s.charAt(i)-'a'] * p;
            p = p *10;
        }
        return ans;
    }

    static int counter=0;
    static void generateMapping(String unique,int[] map,boolean[] takenNumbers){
        if(unique.length()==0){
            int n1=getNumber(s1, map);
            int n2=getNumber(s2, map);
            int n3=getNumber(s3, map);
            if(n1 + n2 == n3){
                // char ch=s3.charAt(0);
                // if(map[ch-'a'] != 0){
                    System.out.print(++counter+"   ");
                    for(int i =0;i < map.length; i++){
                        if(map[i] != -1){
                            System.out.print((char)(i+'a') + " = " + map[i]+" ");
                        }
                    }
                    System.out.println();
                // }
            }
            return;
        }
        char ch=unique.charAt(0);
        String ros=unique.substring(1);
        for(int o=0;o<=9;o++){

            if(takenNumbers[o] == false){
                takenNumbers[o] = true;
                map[ch-'a'] = o;
                generateMapping(ros, map, takenNumbers);
                map[ch-'a'] = -1;
                takenNumbers[o] = false;
            }
        }

    }

    static String s1,s2,s3;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        s1 = sc.next();
        s2 = sc.next();
        s3 = sc.next();
        sc.close();

        String uniq=unique(s1, s2, s3);
        System.out.println(uniq);
        int[] map=new int[26];
        Arrays.fill(map, -1);
        boolean[] dused=new boolean[10];
        generateMapping(uniq, map, dused);
    }
}