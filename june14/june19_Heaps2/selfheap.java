import java.util.*;
public class selfheap{
    private static ArrayList<Integer> list=new ArrayList<>();
    private static boolean type;//true for min - false for max
    selfheap(boolean type){
        this.type=type;
    }
    private static void swap(int i,int j){
        int ith=list.get(i);
        int jth=list.get(j);
        list.set(i,jth);
        list.set(j,ith);
    }
    private static boolean ishp(int i,int j){//if i has higher priority return true
        if(type==true){
            return list.get(i)<list.get(j);
        }else{
            return list.get(i)>list.get(j);
        }
    }
    private static void upheapify(int i){
        if(i==0)
            return;
        int pi=(i-1)/2;
        if(ishp(i,pi)==true){
            swap(i,pi);
            upheapify(pi);
        }
    }
    private static void downheapify(int i){
        int li=2*i+1;
        int ri=2*i+2;
        
        int hpi=i;//highest priority index
        if(li<list.size() && ishp(li,hpi)==true)
            hpi=li;
        if(ri<list.size() && ishp(ri,hpi)==true)
            hpi=ri;
        
        if(hpi!=i){
            swap(i,hpi);
            downheapify(hpi);
        }
    }
    public static void add(int val){
        list.add(val);
        upheapify(list.size()-1);
    }
    public static int peek(){
        return list.get(0);
    }
    public static int remove(){
        int rv=peek();
        swap(0,list.size()-1);
        list.remove(list.size()-1);
        downheapify(0);
        return rv;
    }
    public static int size(){
        return list.size();
    }
    public static void main(String[] args){
        selfheap pq=new selfheap(false);
        pq.add(10);
        pq.add(5);
        pq.add(20);
        pq.add(2);
        pq.add(60);

        while(pq.size()>0){
            int val=pq.remove();
            System.out.print(val+" ");
        }
    }
}