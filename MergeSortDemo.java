import java.util.Random;
import java.util.List;
import java.util.LinkedList;

class ListNode{
    public int val;
    public ListNode next;
    public ListNode( int x) {
        val = x;
        next = null;
    }
}


class Sort{
	public static int count;
	
	public static void quickSort( int[] arr, int low, int high ) {
		if ( low >= high ) {
			return;
		}
		
		// Quick Sort
		int middle = ( low + high ) / 2;
		int pivot = arr[middle];
		
		int i = low, j = high;
		
		while ( i <= j) {
			
			while ( i <= j && arr[i] < pivot ) {
				i++;
			}
			
			while ( i <= j && arr[j] > pivot ) {
				j--;
			}
			
			if ( i <= j && arr[i] < arr[j] ) {
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
			
		}
		
		quickSort( arr, low, j);
		quickSort( arr, i, high);
		
		return 
	}
	
    public static ListNode mergeSort( ListNode head ) {
        // Recurse for log(n) times,
        // At level k, 2^k ,LinkedList length O(n/2^k)--find middle
        // Total time complexity: O(nlogn)

        if ( head == null || head.next == null ) {
            return head;
        }

        // Find the middle nodes of linked list
        ListNode p1 = head, p2 = head;
        while ( p2.next != null && p2.next.next != null ) {
        	count++;
            p1 = p1.next;
            p2 = p2.next.next;
        }

        p2 = p1.next;
        p1.next = null;

        // Sort left and right parts separately
        ListNode left = mergeSort( head );
        ListNode right = mergeSort( p2 );

        return merge( left, right);

    }

    public static ListNode merge( ListNode p1, ListNode p2) {
        // Merge two linked list together
        // Space: O(1), Time Complexity: O(n1 + n2)

        // create a fake head
        ListNode head = new ListNode(0);
        ListNode p3 = head;

        while( p1 != null && p2 != null ) {
        	count++;
            if ( p1.val <= p2.val ) {
                p3.next = p1;
                p1 = p1.next;
            } else {
                p3.next = p2;
                p2 = p2.next;
            }
            p3 = p3.next;
        }

        if ( p1 != null ) {
        	count++;
            p3.next = p1;
        }

        if ( p2 != null ) {
        	count++;
            p3.next = p2;
        }

        return head.next;
    }
}

public class MergeSortDemo{
    public static void main( String[] args ) {
        // Demo for merge sort
		int n = 10;
        for ( int i = 1; i <= 5; i++, n *= 10 ) {
        	test( n );
        }
        
    }

    
    public static void test( int n ) {
    	Random rand = new Random();

        // Create a head first and then other nodes
        ListNode head = new ListNode( rand.nextInt(100) );
        ListNode p = head;

        for (  int i = 1; i < n; i++ ) {
            ListNode node = new ListNode( rand.nextInt(100) );
            p.next = node;
            p = p.next;
        }
        //System.out.println("Data:");
        //display( head );
        Sort.count = 0;
        head = Sort.mergeSort( head );
        //System.out.println("Sorted:");
        //display( head );
        System.out.printf("n-:%d,Time Complexity:%d\n", n, Sort.count);
    }

    public static void display( ListNode head ) {
        ListNode p = head;

        while ( p != null ) {
            System.out.printf( "%d,", p.val);
            p = p.next;
        }

        System.out.printf("\n");
    }
}