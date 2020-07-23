import java.util.*;
public class stree{
    //if we use abstract class then we have to use extends keyword in rest of the classes
    public static interface IOperator{
        int operation(int lhs,int rhs);
        int identity();
    }
    static class SumOperator implements IOperator{
        public int operation(int lhs,int rhs){
            return lhs+rhs;
        }
        public int identity(){
            return 0;
        }
    }
    static class ProductOperator implements IOperator{
        public int operation(int lhs,int rhs){
            return lhs*rhs;
        }
        public int identity(){
            return 1;
        }
    }
    static class MaxOperator implements IOperator{
        public int operation(int lhs,int rhs){
            return Math.max(lhs,rhs);
        }
        public int identity(){
            return Integer.MIN_VALUE;
        }
    }
    static class MinOperator implements IOperator{
        public int operation(int lhs,int rhs){
            return Math.min(lhs,rhs);
        }
        public int identity(){
            return Integer.MAX_VALUE;
        }
    }
    static class GCDOperator implements IOperator{
        public int operation(int divs,int divd){
            if(divs==0)
                return divd;
            return operation(divd%divs, divs);
        }
        public int identity(){
            return 0;
        }
    }

    static int getLog(int n){
        int x=0;
        while((1<<x)<=n)
            x++;
        return x;
    }
    static int[] oa;
    static int[] sa;
    static IOperator opr;
    stree(int[] oa,IOperator opr){
        this.oa=oa;
        this.opr=opr;
        int h=getLog(oa.length)+1;
        int sas=(1<<h)-1;
        sa=new int[sas];
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

        sa[si] = opr.operation(sa[2*si+1], sa[2*si+2]);
    }
    static void update(int idx,int nv,int ss,int se,int si){
        if(ss==se){
            sa[si] = nv;
            return;
        }
        int mid=(se+ss)/2;
        if(idx<=mid)
            update(idx, nv, ss, mid,2*si+1);
        else
            update(idx, nv, mid+1, se,2*si+2);

        sa[si] = opr.operation(sa[2*si+1],sa[2*si+2]);
    }
    static int query(int qs,int qe,int ss,int se,int si){
        if(ss>=qs && se<=qe)
            return sa[si];
        else if(qs>se || qe<ss)
            return opr.identity();
        else{
            int mid=(ss+se)/2;
            int lc=query(qs, qe, ss, mid,2*si+1);
            int rc=query(qs, qe, mid+1, se,2*si+2);
            return opr.operation(lc, rc);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] oa={2,5,-3,6,7,14,1,9};
        IOperator opr=null;
        int choice=sc.nextInt();
        if(choice==1)
            opr=new SumOperator();
        else if(choice==2)
            opr=new ProductOperator();
        else if(choice==3)
            opr=new MaxOperator();
        else if(choice==4)
            opr=new MinOperator();
        else
            opr=new GCDOperator();
        stree st=new stree(oa,opr);
        System.out.println(st.query(2,5,0,7,0));
        System.out.println(st.query(5,7,0,7,0));
        st.update(4,10,0,7,0);
        System.out.println(st.query(2,5,0,7,0));
        System.out.println(st.query(5,7,0,7,0));
        // System.out.println(st.query(4,6,0,7,0));
    }
}