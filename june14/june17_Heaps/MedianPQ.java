import java.util.*;
public class MedianPQ{
        private static PriorityQueue<Integer> left=new PriorityQueue<>(Collections.reverseOrder());
        private static PriorityQueue<Integer> right=new PriorityQueue<>();

        public static void add(int val){
            if(left.size()>0 && val<left.peek())
                left.add(val);
            else
                right.add(val);
            rebalance();
        }
        private static void rebalance(){
            if(left.size()-right.size()==2){
                int val=left.peek(); left.remove();
                right.add(val);
            }else if(left.size()-right.size()==-2){
                int val=right.peek(); right.remove();
                left.add(val);
            }
        }
        public static int peek(){
            if(left.size()>right.size())
                return left.peek();
            else
                return right.peek();
        }
        public static void remove(){
            if(left.size()>right.size())
                left.remove();
            else
                right.remove();
        }
        public static int size(){
            return left.size()+right.size();
        }
    public static void main(String[] args){
        MedianPQ pq=new MedianPQ();
        int[] test= {50,10,80,0,20,90,100,5,70,40};
        for(int i=0;i<test.length;i++)
        {
            pq.add(test[i]);
            System.out.print(pq.peek()+" ");
        }
        System.out.println();
        while(pq.size()>0)
        {
            int val=pq.peek(); pq.remove();
            System.out.print(val+" ");
        }
    }
}