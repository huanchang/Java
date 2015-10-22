public class LongestIncreaseSequence {
    public static void main(String[] args) {
        int[] arr = {1,3,5,2,7,9,10,3,0,0,0,0,0,0,0,0,0};
        
        System.out.println("Result:"+findLongestIncreaseSequence(arr));
    }
	public static int findLongestIncreaseSequence(int[] arr) {
		if ( arr == null ) {
			return 0;
		}

		int max  = 0;
		int curr = 1;

		for ( int i = 1; i < arr.length; ++i) {
			if (arr[i] >= arr[i-1]) {
				//increasing order
				curr++;
			} else {
				max = Math.max(max, curr);
				curr = 1;
			}
		}

		return Math.max(curr,max);
        }
}


// Problem2: input is a tree with numbers
class TreeNode{
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int v) {
        this.val = v;
        left = null;
        right = null;
    }
    
}

// Directed acyclic graph: no cycles