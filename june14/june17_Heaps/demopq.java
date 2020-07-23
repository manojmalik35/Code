import java.util.*;
public class demopq{

    static void pkle(int[] arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<arr.length;i++)
            pq.add(arr[i]);
        for(int i=0;i<k;i++){
            int val=pq.peek(); pq.remove();
            System.out.println(val);
        }
    }
    static void pkle2(int[] arr,int k){//space k , time nlogk
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<k;i++)
            pq.add(arr[i]);
        for(int i=k;i<arr.length;i++){
            if(arr[i]>pq.peek()){
                pq.remove();
                pq.add(arr[i]);
            }
        }
        while(pq.size()>0){
            int val=pq.peek(); pq.remove();
            System.out.println(val);
        }
    }
    static void ksorted(int[] kar,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<=k;i++)
            pq.add(kar[i]);
        for(int i=k+1;i<kar.length;i++){
            int val=pq.peek(); pq.remove();
            System.out.print(val+" ");
            pq.add(kar[i]);
        }
        while(pq.size()>0){
            int val=pq.peek(); pq.remove();
            System.out.print(val+" ");
        }
    }
    static class Helper implements Comparable<Helper>{
        int li;
        int di;
        int val;
        Helper(int li,int di,int val){
            this.li=li;
            this.di=di;
            this.val=val;
        }
        public int compareTo(Helper other){
            return this.val-other.val;
        }
    }
    static void mergekSortedLists(ArrayList<ArrayList<Integer>> lists){
        PriorityQueue<Helper> pq=new PriorityQueue<>();
        for(int i=0;i<lists.size();i++)
            pq.add(new Helper(i,0,lists.get(i).get(0)));
        while(pq.size()>0){
            Helper h=pq.peek(); pq.remove();
            System.out.print(h.val+" ");
            h.di++;
            if(h.di<lists.get(h.li).size()){
                h.val=lists.get(h.li).get(h.di);
                pq.add(h);
            }
        }
    }
    public static void main(String[] args){
        // int[] arr={2,8,14,5,6,1,9,4,20,3,11};
        // pkle(arr,4);
        // int[] kar={20,40,10,30,50,80,60,90,70,120,110,100};
        // ksorted(kar,2);
        ArrayList<ArrayList<Integer>> lists=new ArrayList<>();
        lists.add(new ArrayList<>(Arrays.asList(2,5,9,18,20,25)));
        lists.add(new ArrayList<>(Arrays.asList(3,7,8,15,22)));
        lists.add(new ArrayList<>(Arrays.asList(0,4,6,12)));
        lists.add(new ArrayList<>(Arrays.asList(1,10,16,21,28)));
        mergekSortedLists(lists);
    }
}