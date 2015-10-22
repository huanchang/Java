
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;



public class BTreeDemo{
    public static void main(String[] args) {
        BTreeNode<String> btree1 = new BTreeNode<String>("hello");
        btree1.display();
        BTreeNode<String> btree2 = new BTreeNode<String>("ok");
        
        
        if ( btree1.compareTo( btree2) >=0) {
            System.out.println(">=");
        } else {
            System.out.println("<");
        }
        btree2.display();
    }

}

class BTree<T extends Comparable<T>>{
    private int minKeySize;
    private int maxKeySize;
    private int minChildSize;
    private int maxChildSize;
    
    private BTreeNode root;
    
    
    public BTree(int order) {
        minKeySize = order;
        maxKeySize = 2 * minKeySize;
        minChildSize = minKeySize + 1;
        maxChildSize = maxKeySize + 1;
    }
    
    public void display() {
        if (root == null) {
            System.out.println("Empty B-Tree.");
        } else {
            root.displayAll();
        }
        
    }
    
    public void insert(T v) {
        if (root == null) {
            root = new BTreeNode(v);
        } else {
            
        }
    }
    
    public void delete(T v) {
    }
    
    public void split() {
    }
}


class BTreeNode<T extends Comparable<T>> implements Comparator<BTreeNode<T>>{
    private T val;
    private List<BTreeNode<T>> children;
    private List<T> keys;
    private boolean isleaf;
    
    public BTreeNode(T v) {
        this.val = v;
        children = new LinkedList<BTreeNode<T>>();
        keys = new LinkedList<T>();
        isleaf = true;
    }
    
    public T getValue() {
        return val;
    }
    
    public void setValue(T v) {
        this.val = v;
    }
    
    public boolean isLeaf() {
        return isleaf;
    }
    
    
    public List<T> getKeys() {
        return keys;
    }
    
    public void addKey(int location,T v) {
        keys.add(location, v);
    }
    
    public List<BTreeNode<T>> getChildren() {
        return children;
    }
    
    public void addChild(int location,BTreeNode<T> child) {
        children.add(location, child);
    }
    
    public void display() {
        System.out.print("-"+val);
    }
    
    public void displayAll() {
        display();
        for (BTreeNode<T> child: children) {
            child.displayAll();
        }
    }
    
    public int compareTo(BTreeNode<T> node) {
        return this.val.compareTo(node.val);
    }
    
    public int compare(BTreeNode<T> node1, BTreeNode<T> node2) {
        return node1.val.compareTo(node2.val);
    }
}

