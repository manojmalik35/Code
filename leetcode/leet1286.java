import java.util.*;
public class leet1286 {
    static class CombinationIterator {

        LinkedList<String> stack;
        private void fillPQ(String s, int q, int idx, int[] boxes, int len){
            if(idx == s.length() || q == 0){
                if(q == 0){
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < s.length(); i++){
                        int mask = (1 << i);
                        if((boxes[0] & mask) != 0)
                            sb.append(s.charAt(i));
                    }
                    stack.addLast(sb.toString());
                }
                return;
            }

            for(int i = idx; i < s.length(); i++){
                int mask = (1 << i);
                if((boxes[0] & mask) == 0){
                    boxes[0] ^= mask;
                    fillPQ(s, q - 1, i + 1, boxes, len);
                    boxes[0] ^= mask;
                }
            }
        }

        public CombinationIterator(String characters, int combinationLength) {
            stack = new LinkedList<>();
            int[] boxes = new int[1];
            fillPQ(characters, combinationLength, 0, boxes, combinationLength);
        }

        public String next() {
            return stack.removeFirst();
        }

        public boolean hasNext() {
            return stack.size() != 0;
        }
    }

    class CombinationIteratorII {

        private PriorityQueue<String> pq;
        private void generateComb(String s, int len){
            int n = s.length();
            for(int i = 0; i < (1 << n); i++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < n; j++){
                    if((i & (1 << j)) != 0)
                        sb.append(s.charAt(j));
                }
                if(sb.length() == len)
                    this.pq.add(sb.toString());
            }
        }

        public CombinationIteratorII(String characters, int combinationLength) {
            this.pq = new PriorityQueue<>();
            this.generateComb(characters, combinationLength);
        }

        public String next() {
            return this.pq.remove();
        }

        public boolean hasNext() {
            return this.pq.size() != 0;
        }
    }
}