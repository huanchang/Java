import java.util.HashMap;
import java.util.Set;

public class WordDictionary {
    TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode();
    }
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
        HashMap<Character,TrieNode> children = node.children;
        
        for ( int i = 0; i < word.length(); ++i ) {
            char c = word.charAt(i);
            
            if ( children.containsKey(c) ) {
                node = children.get(c);
            } else {
                node = new TrieNode(c);
                node.isLeaf = false;
                children.put( c, node);
            }
            
            children = node.children;
        }
        
        node.isLeaf = true;
    }
    
    private boolean search( TrieNode root, String word, int index ){
        if ( index == word.length() ) {
            return root.isLeaf;
        }
        
        char c = word.charAt(index);
        if( c == '.' ){
            Set<Character> keys = root.children.keySet();
            for ( char cc : keys ) {
                if ( search( root.children.get(cc), word, index + 1 ) ){
                    return true;
                }
            }
            
            return false;
        } else {
            if ( root.children.containsKey(c) ) {
                return search( root.children.get(c), word, index + 1 );
            } else {
                return false;
            }
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        TrieNode node = root;
        return search( root, word, 0 );
    }
    
    class TrieNode{
        char c;
        boolean isLeaf;
        HashMap<Character, TrieNode> children = new HashMap<>();
        
        public TrieNode(){}
        
        public TrieNode( char c ){
            this.c = c;
        }
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");