import java.util.*;
class ListNode {
    int key;
    int val;
    ListNode next = null;
    ListNode prev = null;

    ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

public class leet146 {

    // static class LRUCache {
    // private int capacity;
    // private LinkedList<Integer> ll;// key
    // private HashMap<Integer, Integer> map;// key,value

    // public LRUCache(int capacity) {
    // this.capacity = capacity;
    // this.ll = new LinkedList<>();
    // this.map = new HashMap<>();
    // }

    // public int get(int key) {
    // int rv = -1;
    // if (this.map.containsKey(key))// present
    // {
    // rv = this.map.get(key);
    // Integer i = key;
    // this.ll.remove(i); //not O(1)
    // this.ll.addFirst(key);
    // }

    // return rv;
    // }

    // public void put(int key, int value) {
    // if (this.map.containsKey(key)){// present
    // Integer i = key;
    // this.ll.remove(i);// not O(1)
    // }
    // else if (this.ll.size() == this.capacity) {
    // int removed = this.ll.removeLast();
    // this.map.remove(removed);
    // }

    // this.ll.addFirst(key);
    // this.map.put(key, value);
    // }
    // }

    static class LRUCache {
        private int capacity;
        private ListNode head;
        private ListNode tail;
        private int llsz;
        private HashMap<Integer, ListNode> map;// key,node

        private void addFirst(ListNode nn) {
            if (this.llsz == 0) {
                this.head = nn;
                this.tail = nn;
            } else {
                nn.next = this.head;
                this.head.prev = nn;
                this.head = nn;
            }
            this.llsz++;
        }

        private ListNode removeLast() {
            if (this.llsz == 0)
                return null;

            ListNode r = this.tail;
            if (this.llsz == 1) {
                this.head = null;
                this.tail = null;
            } else {
                this.tail = r.prev;
                this.tail.next = null;
                r.prev = null;
            }

            this.llsz--;
            return r;
        }

        private void remove(ListNode n) {
            if (this.llsz == 0)
                return;

            if (this.llsz == 1) {
                this.head = null;
                this.tail = null;
            } else {
                ListNode pr = n.prev;
                ListNode ne = n.next;
                if (pr != null)
                    pr.next = ne;
                if (ne != null)
                    ne.prev = pr;

                if (n == this.tail)
                    this.tail = pr;
                else if (n == this.head)
                    this.head = ne;
            }

            n.next = null;
            n.prev = null;
            this.llsz--;
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.head = null;
            this.tail = null;
            this.llsz = 0;
            this.map = new HashMap<>();
        }

        public int get(int key) {
            int rv = -1;
            if (this.map.containsKey(key))// present
            {
                ListNode t = this.map.get(key);
                rv = t.val;
                this.remove(t);
                this.addFirst(t);
            }

            return rv;
        }

        public void put(int key, int value) {
            if (this.map.containsKey(key)) {// present
                ListNode t = this.map.get(key);
                this.remove(t);
                t.val = value;
                this.addFirst(t);
                this.map.put(key, t);
                return;
            } else if (this.llsz == this.capacity) {
                ListNode removed = this.removeLast();
                this.map.remove(removed.key);
            }

            ListNode nn = new ListNode(key, value);
            this.addFirst(nn);
            this.map.put(key, nn);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // returns 1
        cache.put(3, 3); // evicts key 2
        System.out.println(cache.get(2)); // returns -1 (not found)
        cache.put(4, 4); // evicts key 1
        System.out.println(cache.get(1)); // returns -1 (not found)
        System.out.println(cache.get(3)); // returns 3
        System.out.println(cache.get(4)); // returns 4

    }

    // ListNode itr = this.head;
    // while(itr!=null){
    //     System.out.print(itr.key + "," + itr.val + "  ");
    //     itr = itr.next;
    // }
    // System.out.println(this.llsz);
}