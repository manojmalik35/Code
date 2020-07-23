import java.util.*;
public class generichm<k,v>{
    class HMNode{
        k key;
        v value;
        HMNode(k key,v value){
            this.key=key;
            this.value=value;
        }
    }
    LinkedList<HMNode>[] buckets;
    int n;//total no. of elements
    int N;//no. of buckets
    generichm(){
        N=4;
        buckets=new LinkedList[N];
        for(int i=0;i<buckets.length;i++)
            buckets[i]=new LinkedList<>();
        n=0;
    }
    private int hashfn(k key){
        int hc=key.hashCode();
        hc=Math.abs(hc);
        return hc%N;
    }
    private int getDataIndex(int bi,k key){
        int counter=0;
        for(HMNode node:buckets[bi]){
            if(node.key.equals(key))
                return counter;
            counter++;
        }
        return -1;
    }
    private void rehash(){
        LinkedList<HMNode>[] oba=buckets;
        N=N*2;
        buckets=new LinkedList[N];
        for(int i=0;i<buckets.length;i++)
            buckets[i]=new LinkedList<>();
        n=0;

        for(int bi=0;bi<oba.length;bi++){
            for(HMNode node: oba[bi])
                put(node.key, node.value);
        }
    }
    public void put(k key,v value){
        int bi=hashfn(key);
        int di=getDataIndex(bi, key);
        if(di==-1){
            HMNode n2a=new HMNode(key, value);
            buckets[bi].add(n2a);
            n++;
        }else{
            HMNode n2u=buckets[bi].get(di);
            n2u.value=value;
        }
        double lambda=n*1.0/N;
        if(lambda>2.0)
            rehash();
    }
    public boolean containsKey(k key){
        int bi=hashfn(key);
        int di=getDataIndex(bi, key);
        if(di==-1)
            return false;
        else
            return true;
    }
    public v get(k key){
        int bi=hashfn(key);
        int di=getDataIndex(bi, key);
        if(di==-1)
            return null;
        else{
            HMNode n2g=buckets[bi].get(di);
            return n2g.value;
        }
    }
    public v remove(k key){
        int bi=hashfn(key);
        int di=getDataIndex(bi, key);
        if(di==-1)
            return null;
        else{
            HMNode n2r=buckets[bi].remove(di);
            n--;
            return n2r.value;
        }
    }
    public int size(){
        return n;
    }
    public ArrayList<k> keyset(){
        ArrayList<k> keys=new ArrayList<>();
        for(int i=0;i<buckets.length;i++){
            for(HMNode node:buckets[i])
                keys.add(node.key);
        }
        return keys;
    }
    public void display(){
        System.out.println("-------------------------");
        for(int i=0;i<buckets.length;i++){
            System.out.print(i+" -> ");
            for(HMNode node:buckets[i])
                System.out.print("["+node.key+"="+node.value+"]");
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    static class Point{
        int x;
        int y;
        Point(int x,int y){
            this.x=x;
            this.y=y;
        }
        public int hashCode(){
            return x+y;
        }
        public boolean equals(Object o){
            Point po=(Point)o;
            return this.x==po.x && this.y==po.y;
        }
        public String toString(){
            return this.x+" "+this.y;
        }
    }
    public static void main(String[] args) {
        // generichm<String,Integer> hm=new generichm<>();
        // hm.put("India", 200);
        // hm.put("Pak", 30);
        // hm.put("China", 250);
        // System.out.println(hm.get("China"));
        // System.out.println(hm.containsKey("Pak"));
        // hm.remove("Pak");
        // System.out.println(hm.containsKey("Pak"));
        // hm.put("India", 135);
        // hm.put("US", 260);
        // hm.put("UK", 120);
        // hm.put("Bangladesh", 20);
        // hm.put("California", 90);
        // hm.put("Pak", 10);
        // hm.put("Afghanistan", 15);
        // hm.put("Norway", 23);
        // hm.display();

        generichm<Point,String> map=new generichm<>();
        Point p1=new Point(0, 2);
        Point p2=new Point(3, 4);
        Point p3=new Point(2, 2);
        Point p4=new Point(4, 7);
        Point p5=new Point(2, 1);
        Point p6=new Point(2, 1);

        map.put(p1, "Delhi");
        map.put(p2, "Chandigarh");
        map.put(p3, "Mumbai");
        map.put(p4, "Shimla");
        map.put(p5, "Murthal");

        System.out.println(map.get(p6));
        System.out.println(p5);
        map.display();
    }
}