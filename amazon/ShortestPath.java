

public class ShortestPath{

    public static void main(String[] args) { 
        int[] arr = {4,2,5,8,1,3,10};
        // create a BST from an integer array
        BST bstree = createBST(arr);
        
        // Find the path from root to a leaf with minimum weights
        System.out.println("ShortestPath:"+findShortestPath(bstree));
    }
    
    public static BST createBST(int[] arr){
        BST bstree = new BST();
        if (arr!=null) {
            for (int i = 0; i < arr.length; ++i){
                bstree.insert(arr[i]);
                System.out.println(bstree);
            }
        }
        System.out.println(bstree);
        
        return bstree;
    }
        
    // DFS search + Backtrack
    public static int findShortestPath(BST bstree) {
        // Time complexity is O(n^2)
        if (bstree==null) {
            return 0;
        }
        return bstree.findShortestPath();
    }

    


    // Binary Search Tree 
    public static class BST{
        private TreeNode root;
        
        public BST() {
            root = null;
        }
        
        public void insert(int v) {
            System.out.printf("Insert %d\n",v);
            if (root==null) {
                root = new TreeNode(v, null, null);
            } else {
                root = root.insert(v);
            }
            
        }
        
        public int findShortestPath() {
            if( root == null) {
                return 0;
            } else {
                return root.findShortestPath();
            }
        }
        
        @Override
        public String toString() {
            return this.root.toString();
        }
        
        
    }
    
    // Binary Search Tree Node
    public static class TreeNode{
        public final int val;
        public final TreeNode left, right;
        
        public TreeNode(int v, TreeNode l, TreeNode r) {
            this.val = v;
            this.left = l;
            this.right = r;
        }
        
        public TreeNode insert(int v) {
        
            if ( v <= this.val) {
                // add to left child
                TreeNode newLeft;
                
                if (this.left == null) {
                    newLeft = new TreeNode(v,null,null);
                } else {
                    newLeft = this.left.insert(v);
                }
                
                return new TreeNode(this.val, newLeft, this.right);
            } else {
                TreeNode newRight;
                
                if (this.right == null) {
                    newRight = new TreeNode(v,null,null);
                } else {
                    newRight = this.right.insert(v);
                }
                
                return new TreeNode(this.val, this.left, newRight);
                
            }
            
        }
        
        public int findShortestPath() {
            // Recursive method
            
            if (left==null&&right==null) { 
                return this.val;    // This is a leaf
            } else {
                // Path from current node to a leaf is obtained as current node value plus the shortest path from children to a leaf
                // If only one child then the minimum path is calculate by adding that path length.
                int minWeight = Integer.MAX_VALUE;
                
                
                if (left!=null) {
                    minWeight = Math.min(minWeight,this.left.findShortestPath());
                }
                
                if (right !=null) {
                    minWeight = Math.min(minWeight,this.right.findShortestPath());
                }
                
                return minWeight+this.val;
            }
            
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            
            sb.append(this.val).append("(");
            
            if (this.left==null) {
                sb.append("null,");
            } else {
                sb.append(this.left).append(",");
            }
            
            if (this.right==null) {
                sb.append("null");
            } else {
                sb.append(this.right);
            }
            
            sb.append(")");
            
            return sb.toString();
        }
        
    }
    
}


