public class leet208{
    class Trie {

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
        public Trie() {
            root = new Node('#');
        }
        
        private void add(Node root, String word, int idx){
            if(idx == word.length()){
                root.eow = true;
                return;
            }
            
            char ch = word.charAt(idx);
            if(root.children[ch - 'a'] == null)
                root.children[ch - 'a'] = new Node(ch);
            
            add(root.children[ch - 'a'], word, idx + 1);
        }
        
        /** Inserts a word into the trie. */
        public void insert(String word) {
            add(root, word, 0);    
        }
        
        
        private boolean find(Node root, String word, int idx){
            if(idx == word.length())
                return root.eow;
            
            char ch = word.charAt(idx);
            if(root.children[ch - 'a'] == null)
                return false;
            
            return find(root.children[ch - 'a'], word, idx + 1);
        }
        
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return find(root, word, 0);    
        }
        
        private boolean startsWith(Node root, String word, int idx){
            if(idx == word.length())
                return true;
            
            char ch = word.charAt(idx);
            if(root.children[ch - 'a'] == null)
                return false;
            
            return startsWith(root.children[ch - 'a'], word, idx + 1);
        }
        
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return startsWith(root, prefix, 0);   
        } 
    }
}