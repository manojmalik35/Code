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
        StringBuilder sb;
        public StreamChecker(String[] words) {
            this.root = new Node('#');
            for(String word : words) this.addWord(root, word, word.length() - 1);
            this.sb = new StringBuilder();
        }

        private void addWord(Node root, String word, int idx){
            if(idx < 0){
                root.eow = true;
                return;
            }

            char ch = word.charAt(idx);
            if(root.children[ch - 'a'] == null)
                root.children[ch - 'a'] = new Node(ch);
            
            addWord(root.children[ch - 'a'], word, idx - 1);
        }
    
        public boolean query(char letter) {
            sb.append(letter + "");
            Node curr = root;
            for(int i = sb.length() - 1; i >= 0; i--){
                char ch = sb.charAt(i);
                curr = curr.children[ch - 'a'];
                if(curr == null) return false;
                if(curr != null && curr.eow) return true;
            }

            return false;
        }
    }

}