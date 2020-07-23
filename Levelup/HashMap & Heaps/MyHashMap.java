import java.util.*;

public class MyHashMap <K, V>{

    private class Node {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private LinkedList<Node>[] buckets;
    private int size;

    private int findBucketIndex(K key) {
        int hc = key.hashCode();
        int bi = Math.abs(hc) % buckets.length;
        return bi;
    }

    private Node findWithinBucket(int bi, K key) {
        int sz = buckets[bi].size();
        while(sz-- > 0){
            Node n = buckets[bi].getFirst();
            if (n.key.equals(key))
                return n;
            buckets[bi].addLast(buckets[bi].removeFirst());
        }
        return null;
    }

    private void rehash() {
        LinkedList<Node>[] obuckets = this.buckets;
        this.buckets = new LinkedList[this.buckets.length * 2];
        for (int i = 0; i < this.buckets.length; i++)
            this.buckets[i] = new LinkedList<>();

        for (int i = 0; i < obuckets.length; i++) {
            int sz = obuckets[i].size();
            while(sz-- > 0){
                Node n = obuckets[i].removeFirst();
                put(n.key, n.val);
            }
        }
    }

    public MyHashMap() {
        this.buckets = new LinkedList[10];
        for (int i = 0; i < 10; i++)
            this.buckets[i] = new LinkedList<>();
        this.size = 0;
    }

    public void put(K key, V value) {
        int bi = findBucketIndex(key);
        Node n = findWithinBucket(bi, key);
        if (n != null) 
            n.val = value;
         else {
            buckets[bi].addFirst(new Node(key, value));
            this.size++;
            double lambda = this.size * 1.0 / this.buckets.length;
            if (lambda > 2.0)
                rehash();
        }

    }

    public V get(K key) {
        int bi = findBucketIndex(key);
        Node n = findWithinBucket(bi, key);
        if (n != null)
            return n.val;

        return null;
    }

    public Node remove(K key) {
        int bi = findBucketIndex(key);
        Node n = findWithinBucket(bi, key);
        if (n != null){
            buckets[bi].removeFirst();
            this.size--;
        }

        return n;
    }

    public int size(){
        return this.size;
    }

    public boolean containsKey(K key){
        int bi = findBucketIndex(key);
        Node n = findWithinBucket(bi, key);
        
        return n != null;
    }

    public V getOrDefault(K key, V defaultValue){
        int bi = findBucketIndex(key);
        Node n = findWithinBucket(bi, key);
        if (n != null)
            return n.val;

        return defaultValue;
    }

    public ArrayList<K> keySet(){
        ArrayList<K> ans = new ArrayList<>();
        for(int bi = 0; bi < buckets.length; bi++){
            int sz = buckets[bi].size();
            while(sz-- > 0){
                Node n = buckets[bi].removeFirst();
                ans.add(n.key);
                buckets[bi].addLast(n);
            }
        }

        return ans;
    }

}