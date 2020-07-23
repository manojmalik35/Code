import java.util.*;
public class selfhm{

    static class Node{
        String key;
        int val;

        Node(String key, int val){
            this.key = key;
            this.val = val;
        }
    }    

    static int nob = 4;
    static LinkedList<Node>[] buckets;

    static int hashfn(String key){
        int hc = key.hashCode();
        int bi = Math.abs(hc) % nob;
        return bi;
    }

    static int getDataIndex(int bi, String key){
        for(int i = 0; i < buckets[bi].size(); i++){
            if(buckets[bi].get(i).key == key)
                return i;
        }
        return -1;
    }   

    static void put(String key, int val){
        int bi = hashfn(key);
        int di = getDataIndex(bi, key);
        if(di == -1)
            buckets[bi].add(new Node(key, val));
        else
            buckets[bi].get(di).val = val;

        
    }

    static int get(String key){
        int bi = hashfn(key);
        int di = getDataIndex(bi, key);
        if(di == -1)
            return -1;
        else
            return buckets[bi].get(di).val;
    }

    static void display(){
        System.out.print("[");
        for(int bi = 0; bi < buckets.length; bi++){
            for(Node node : buckets[bi]){
                System.out.print(node.key + " = " + node.val + " ");
            }
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        int nob = 4;
        buckets = new LinkedList[nob];
        for(int i = 0; i < nob; i++)
            buckets[i] = new LinkedList<>();
            
        put("India", 135);
        put("Pak", 100);
        put("Afghanistan", 150);
        put("Bangla", 40);
        put("America", 13);
        put("Pak", 12);
        display();

        System.out.println(get("Pak"));
    }
}