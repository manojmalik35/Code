import java.util.*;
public class sqrt{
    static int[] oa;
    static int[] sa;
    static int sob;
    static int nob;

    public static void build(int[] arr){
        oa=arr;

        sob=(int)Math.sqrt(arr.length);
        nob=arr.length/sob;

        sa=new int[nob];
        for(int i=0;i<sob*nob;i++){
            int bi=i/sob;
            sa[bi]+=oa[i];
        }
    }

    public static int query(int l,int r){//gives sum in O(sqrt(n))
        int sum=0;
        int lbi=l/sob;
        int rbi=r/sob;

        //loop1
        for(int i=l;i/sob==lbi;i++)
            sum += oa[i];

        //loop2
        for(int bi=lbi+1;bi<=rbi-1;bi++)
            sum += sa[bi];
        
        //loop3
        for(int i=r;i/sob==rbi;i--)
            sum += oa[i];

        return sum;
    }

    public static void update(int idx,int val){
        int old=oa[idx];
        int delta=val-old;

        int bi=idx/sob;
        sa[bi] += delta;

        oa[idx]=val;
    }

    public static int trivialQuery(int l,int r){//gives same sum in O(n)
        int sum=0;
        for(int i=l;i<=r;i++)
            sum += oa[i];
        return sum;
    }
    public static void main(String[] args) {
        int arr[]=new int[58];
        for(int i=0;i<arr.length;i++)
            arr[i]=(int)(100 * Math.random());
        build(arr);
        System.out.println(query(20, 45)+" "+trivialQuery(20, 45));
        System.out.println(query(2, 40)+" "+trivialQuery(2, 40));
        System.out.println(query(18, 57)+" "+trivialQuery(18, 57));
        System.out.println(query(30, 42)+" "+trivialQuery(30, 42));
        update(25, 100);
        System.out.println("--------------");
        System.out.println(query(20, 45)+" "+trivialQuery(20, 45));
        System.out.println(query(2, 40)+" "+trivialQuery(2, 40));
        System.out.println(query(18, 34)+" "+trivialQuery(18, 34));
        System.out.println(query(30, 42)+" "+trivialQuery(30, 42));
    }
}