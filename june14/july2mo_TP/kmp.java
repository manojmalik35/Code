public class kmp{
    static int[] lps(String pat){
        int[] arr=new int[pat.length()];
        arr[0]=0;
        int i=1,l=0;
        while(i<pat.length()){
            if(pat.charAt(i)==pat.charAt(l)){
                arr[i]=l+1;
                i++;
                l++;
            }else{
                if(l>0)
                    l=arr[l-1];
                else
                    i++;
            }
        }
        return arr;
    }
    static void kmp(String src,String pat){
        int[] lps=lps(pat);
        System.out.println();
        int i=0,j=0;
        while(i<src.length()){
            if(src.charAt(i)==pat.charAt(j)){
                i++;
                j++;
                if(j==pat.length()){
                    System.out.print(i-pat.length()+" ");
                    j=lps[j-1];
                }
            }else{
                if(j>0)
                    j=lps[j-1];
                else
                    i++;
            }
        }
    }
    public static void main(String[] args) {
        String src="abcabcababcabcab";
        String pat="bc";
        kmp(src,pat);  
    }
}