import java.util.HashSet;
import java.util.Arrays;
public class leet1032 {
    class StreamChecker {

        class Node{
            char ch;
            Node[] children;
            boolean eow;
            Node(char ch){
                this.ch = ch;
                this.children = new Node[26];
                this.eow = false;
            }
        }
        
        Node root;
        HashSet<String> dic;
        public StreamChecker(String[] words) {
            this.dic = new HashSet<>(Arrays.asList(words));
            this.root = new Node('#');
        }
        
        
        public boolean query(char letter) {
            return false;
        }
    }
    
}