import java.util.*;

public class leet211 {
    class WordDictionary {

        class Node {
            char ch;
            Node[] map;
            boolean eow;

            Node(char ch, boolean eow) {
                this.ch = ch;
                this.eow = eow;
                this.map = new Node[26];
            }
        }

        Node root;

        public WordDictionary() {
            root = new Node('#', false);
        }

        private void addWord(Node root, String word) {
            if (word.length() == 0) {
                root.eow = true;
                return;
            }

            char ch = word.charAt(0);
            if (root.map[ch - 'a'] != null)
                addWord(root.map[ch - 'a'], word.substring(1));
            else {
                Node nn = new Node(ch, false);
                root.map[ch - 'a'] = nn;
                addWord(nn, word.substring(1));
            }
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            addWord(root, word);
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot
         * character '.' to represent any one letter.
         */

        private boolean search(Node root, String word) {
            if (word.length() == 0) {
                return root.eow;
            }

            char ch = word.charAt(0);
            String ros = word.substring(1);

            boolean res = false;
            if (ch == '.') {
                for (int i = 0; i < 26; i++) {
                    if (root.map[i] != null)
                        res = res || search(root.map[i], ros);
                }
            } else if (root.map[ch - 'a'] != null)
                res = res || search(root.map[ch - 'a'], ros);
            else
                res = false;

            return res;
        }

        public boolean search(String word) {
            return search(root, word);
        }
    }
}