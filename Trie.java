import java.util.HashMap;

class TrieNode {
    // Initialize your data structure here.
    char c;
    boolean isLeaf;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        
    public TrieNode() {
    }
    
    public TrieNode( char c ){
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        HashMap<Character, TrieNode> children = node.children;
        
        for ( int i = 0; i < word.length(); ++i ) {
            // for each character
            char c = word.charAt(i);
            
            if ( children.containsKey( c ) ){
                node = children.get( c );
            } else {
                // ith char not in prefix tree
                node = new TrieNode( c );
                children.put( c, node );
            }
            
            node.isLeaf = false;
            children = node.children;
        }
        
        node.isLeaf = true;
        
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = searchNode( word );
        
        if ( node != null && node.isLeaf ) {
            return true;
        } else {
            return false;
        }
        
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }
    
    private TrieNode searchNode(String word) {
        TrieNode node = null;
        HashMap<Character, TrieNode> children = root.children;
        
        for ( int i = 0; i < word.length(); ++i ) {
            char c = word.charAt(i);
            
            if ( children.containsKey(c) ) {
                node = children.get(c);
            } else {
                return null;
            }
            children = node.children;
        }
        
        return node;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");