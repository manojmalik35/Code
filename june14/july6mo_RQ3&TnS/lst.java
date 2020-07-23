import java.util.*;
public class lst{
    
    static int getLog(int n){
        int x=0;
        while((1<<x)<=n)
            x++;
        return x;
    }
    static int[] oa;
    static int[] sa;
    static int[] la;
    lst(int[] oa){
        this.oa=oa;
        int h=getLog(oa.length)+1;
        int sas=(1<<h)-1;
        sa=new int[sas];
        la=new int[sas];
        build(0, oa.length-1, 0);        
    }
    static void build(int ss,int se,int si){
        if(ss==se){
            sa[si]=oa[ss];
            return;
        }
        int mid=(ss+se)/2;
        build(ss, mid,2*si+1);
        build(mid+1,se,2*si+2);

        sa[si] = sa[2*si+1] + sa[2*si+2];
    }
    void update(int qs,int qe,int delta,int ss,int se,int si){
        handlependingwork(ss, se, si);
        if(ss>=qs && se<=qe){
            la[si] += delta;
            handlependingwork(ss, se, si);
        }
        else if(qs>se || qe<ss)
            return;
        else{
            int mid=(ss+se)/2;
            update(qs, qe, delta, ss, mid,2*si+1);
            update(qs, qe, delta, mid+1, se,2*si+2);
            sa[si] = sa[2*si+1] + sa[2*si+2];
        }
    }
    int query(int qs,int qe,int ss,int se,int si){
        handlependingwork(ss, se, si);
        if(ss>=qs && se<=qe)
            return sa[si];
        else if(qs>se || qe<ss)
            return 0;
        else{
            int mid=(ss+se)/2;
            int lc=query(qs, qe, ss, mid,2*si+1);
            int rc=query(qs, qe, mid+1, se,2*si+2);
            return lc + rc;
        }
    }
    private static void handlependingwork(int ss,int se,int si){
        sa[si] += la[si] * (se-ss+1);
        if(ss!=se){
            la[2 * si + 1] += la[si];
            la[2 * si + 2] += la[si];
        }
        la[si] = 0;
    }
    public static void main(String[] args) {
        int[] oa={1,2,3,4,5,6,7,8};
        lst st=new lst(oa);
        System.out.println(st.query(2,5,0,7,0));
        st.update(3,7,10,0,7,0);
        System.out.println(st.query(2,5,0,7,0));
    }
}