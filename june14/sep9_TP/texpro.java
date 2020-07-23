import java.util.*;
public class texpro{

    static class Node{
        char data;
        HashMap<Character, Node> children = new HashMap<>();
        boolean eow;

        Node(char data, boolean eow){
            this.data = data;
            this.eow = eow;
        }
    }

    static void addWord(Node root, String word){
        if(word.length() == 0){
            root.eow = true;
            return;
        }

        char ch = word.charAt(0);
        String ros = word.substring(1);

        if(root.children.containsKey(ch) == false){
            Node nn = new Node(ch, false);
            root.children.put(ch, nn);
            addWord(nn, ros);
        }
        else
            addWord(root.children.get(ch), ros);
        
    }

    static boolean searchWord(Node root, String word){
        if(word.length() == 0){
            return root.eow;
        }

        char ch = word.charAt(0);
        String ros = word.substring(1);

        if(root.children.containsKey(ch) == false)
            return false;
        else
            return searchWord(root.children.get(ch), ros);
    }

    static void remove(Node root, String word){
        if(word.length() == 0){
            root.eow = false;
            return;
        }

        char ch = word.charAt(0);
        String ros = word.substring(1);

        if(root.children.containsKey(ch) == false)
            return;
        else{
            Node child = root.children.get(ch);
            remove(child, ros);
            if(child.eow == false && child.children.size() == 0)
                root.children.remove(ch);
        }
    }

    static void display(Node root){
        System.out.print(root.data + " -> ");
        for(char ch : root.children.keySet()){
            if(root.children.get(ch).eow)
                System.out.print(ch + "*, ");
            else
                System.out.print(ch + ", ");
        }
        System.out.println(".");

        for(char ch : root.children.keySet()){
            Node child = root.children.get(ch);
            display(child);
        }
    }

    static void displayAllWords(Node root, String wsf){
        if(root.eow)
            System.out.println(wsf);

        for(char ch : root.children.keySet()){
            Node child = root.children.get(ch);
            displayAllWords(child, wsf + ch);
        }
    }

    static int[] getlps(String pat){
        int[] lps = new int[pat.length()];
        int j = 0;
        int i = 1;
        lps[0] = 0;
        while(i < pat.length()){
            if(pat.charAt(i) == pat.charAt(j)){
                lps[i] = j + 1;
                i++;
                j++;
            }else{
                if(j == 0){
                    lps[i] = 0;
                    i++;
                }
                else
                    j = lps[j - 1];
            }
        }

        return lps;
    }

    static void kmp(String src, String pat){
        int[] lps = getlps(pat);
        int i = 0; 
        int j = 0;
        while(i < src.length()){
            if(src.charAt(i) == pat.charAt(j)){
                i++;
                j++;

                if(j == pat.length()){
                    System.out.println("Found at " + (i - j));
                    j = lps[j - 1];
                }
            }else{
                if(j == 0)
                    i++;
                else
                    j = lps[j - 1];
            }
        }
    }

    public static void main(String[] args) {
        // Node root = new Node('$', false);
        // addWord(root, "are");
        // addWord(root, "art");
        // addWord(root, "an");
        // addWord(root, "and");
        // addWord(root, "ant");
        // addWord(root, "as");
        // addWord(root, "ask");
        // addWord(root, "see");
        // addWord(root, "sea");
        // addWord(root, "seen");
        // System.out.println(searchWord(root, "art"));
        // remove(root, "art");
        // System.out.println(searchWord(root, "art"));
        // displayAllWords(root, "");
        // remove(root, "are");
        // remove(root, "art");
        // display(root);

        String src = "dkfjdkasdfdlkfjdlkasdflfjdlfjasfdvmkzlfoifjkvasdfldlvmcxmnvoi";
        String pat = "asdf";
        kmp(src, pat);

    }   
}