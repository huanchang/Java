
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class TreeBottomView{

    public static void main(String[] args) {
    
        // Create a tree
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(25);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        Tree tree = new Tree();
        tree.root = root;
                
        System.out.println("Bottom view of the given binary tree:");
        tree.displayBottomView();
    }

    private static class Tree {
        public TreeNode root;
        
        public Tree() {
            this.root = null;
        }
        
        public void displayBottomView() {
            if (root == null) {
                return ;
            }
            
            // Create a queue to do level order travel
            LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
            
            // Create a TreeMap to store hd->value
            TreeMap<Integer, Integer> map = new TreeMap<Integer,Integer>();
            
            // Initialize horizontal distance of root
            root.hd = 0;
            
            queue.add(root);
            
            while(!queue.isEmpty()) {
                
                // get the first node in queue
                TreeNode node = queue.get(0);
                queue.remove(0);
                
                int hd = node.hd;
                
                // Once get an hd update its value in map, since it traveled in level
                // If there's two nodes with same hd, then the latter visited one should be printed
                map.put(hd,node.val);
                
                // check children nodes
                if (node.left!=null) {
                    node.left.hd = hd-1;
                    queue.add(node.left);
                }
                
                if (node.right!=null) {
                    node.right.hd = hd+1;
                    queue.add(node.right);
                }
                
            }
            
            // Retrieve bottom view information from map
            Set<Entry<Integer,Integer>> set = map.entrySet();
            
            // Create an iterator
            Iterator<Entry<Integer,Integer>> iter = set.iterator();
            
            while(iter.hasNext()) {
                Map.Entry<Integer, Integer> entry = iter.next();
                System.out.printf("%d ", entry.getValue());
            }
            
            System.out.println("");
            
        }
        
    }
    
    private static class TreeNode {    
        public int val;
        public int hd;  //horizontalDistance;
        public TreeNode left, right;
        
        public TreeNode(int v) {
            this.val = v;
            left = null;
            right = null;
        }
    }
    
}