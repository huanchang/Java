// Kth largest number problem
// Example:
// Input: arr[] = {7, 10, 4, 3, 20, 15} and k = 3
// Output: 7

import java.util.Arrays;
import java.util.Random;

public class KthLargestNumber{
	public static void main(String[] args) {
		demo();
	}
	
	private static void demo() {
		Random rand = new Random();
		
		int n = 100;
		int[] arr = new int[n];
		
		System.out.printf("Data:[");
		for ( int i = 0; i < n; ++i ) {
			arr[i] = rand.nextInt(200)-100;
			System.out.printf("%d,",arr[i]);
		}
		System.out.printf("]\n");
		
		int k = rand.nextInt(n-1) + 1;
		
		System.out.printf( "Method 1: k = %d, result = %d\n", k, solution1(arr,k) );
		System.out.printf( "Method 2: k = %d, result = %d\n", k, solution2(arr,k) );
		System.out.printf( "Method 3: k = %d, result = %d\n", k, solution3(arr,k) );
		System.out.printf( "Method 4: k = %d, result = %d\n", k, solution4(arr,k) );
		
	}
	
	private static int solution1(int[] arr, int k) {
		
		// Method 1: sort array then return arr[k-1]
		// Time Complexity: O(nlogn)
		// No extra space if array is changeable
		if ( arr == null || arr.length < k ) {
			return Integer.MIN_VALUE;
		}
		
		int[] a = Arrays.copyOfRange( arr, 0, arr.length );
		displayArray(a);
		Arrays.sort(a);
		displayArray(a);
		
		return a[a.length - k];
	}
	
	private static int solution2( int[] arr, int k) {
		// Method 2: k bubble sort loop
		// Time Complexity: O(nk)
		// No extra space if array is changeable
		if ( arr == null || arr.length < k ) {
			return Integer.MIN_VALUE;
		}
		
		int[] a = Arrays.copyOfRange( arr, 0, arr.length );
		
		for ( int i = 1; i <= k; ++i ) {
			for ( int j = 0; j < a.length - i; ++j ) {
				if ( a[j] > a[j+1] ) {
					int tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;
				}
			}
			//displayArray(a);
		}
		
		return a[a.length-k];
	}
	
	private static int solution3( int[] arr, int k) {
		// Method 3: use temporary array
		// Time Complexity: O((n-k)*k)
		// Extra space: O(k)
		if ( arr == null || arr.length < k ) {
			return Integer.MIN_VALUE;
		}
		
		// Store the first k elements in a temporary array
		int[] tmp = Arrays.copyOfRange( arr, 0, k);
		int[] a = Arrays.copyOfRange( arr, 0, arr.length);
		int res = Integer.MAX_VALUE;
		
		
		// Loop for n-k times: each times for element in a[k] to a[n-1], find the largest to switch with tmp[j] for j = 0 to k-1
		for ( int i = 0; i < k; ++i ) {
			int max = tmp[i];
			int index = i;
			
			for ( int j = k; j < a.length; ++j ) {
				if ( a[j] > max ) {
					max = a[j];
					index = j;
				}
			}
			
			if ( index != i ) {
				a[index] = tmp[i];
				tmp[i] = max;
			}
			
			res = Math.min(res, tmp[i]);
		}
		
		
		return res;
	}
	
	private static int solution4( int[] arr, int k) {
		// Method 4: maximum heap tree 
		// Time Complexity: O((n-k)*k)
		// Extra space: O(k)
		if ( arr == null || arr.length < k ) {
			return Integer.MIN_VALUE;
		}
		
		MaxHeapTree heap = new MaxHeapTree(arr);
		System.out.printf("Time complexity: construct = %d", heap.count);
		
		int res = Integer.MIN_VALUE;
		
		for ( int i = 0; i < k; ++i ) {
			res = heap.getMax();
		}
		System.out.printf(", total = %d\n", heap.count);
		
		
		return res;
	}
	
	private static class MaxHeapTree{
		
			private int[] data;
			private int size;
			public int count;
			
			MaxHeapTree(int[] arr) {
				size = arr.length;
				data = new int[size];
				count = 0;
				
				// Construct max heap tree
				for ( int i = 0; i < size; ++i ) {
					
					int parent = (i-1) >> 1;
					int current = i;
					data[current] = arr[current];
					
					while ( parent >= 0 ) {
						// swap if parent is smaller than current node
						if ( data[current] > data[parent]) {
							int tmp = data[current];
							data[current] = data[parent];
							data[parent] = tmp;
						}
						
						current = parent;
						if ( current == 0 ) {
							break;
						}
						parent = (current-1) >> 1;
						++count;
					}
				}
				//displayArray(data);
				
			}
			
			public int getMax() {
				int res = data[0];
				
				// remove the maximum number and move the next maximum number into top
				data[0] = Integer.MIN_VALUE;
				
				int curr = 0;
				int next = curr;
				
				while ( curr*2+1 < size ) {
					int left = curr*2 + 1;
					int right = curr*2+2;
					
					if ( data[left] > data[curr] && (right >= size || data[left] > data[right])) {
						next = left;
					}else if ( right < size && data[right] > data[curr]) {
						next = right;
					}
					
					if ( next == curr ) {
						break;
					} else {
						int tmp = data[next];
						data[next] = data[curr];
						data[curr] = tmp;
						curr = next;
					}
					//System.out.printf("while-curr = %d, size = %d,",curr, size);
					//displayArray(data);
					++count;
				}
				
				while ( next < size -1 ) {
					data[next] = data[next+1];
					next++;
				}
				data[size-1] = Integer.MIN_VALUE;
				
				size--;
				//displayArray(data);
				
				return res;
			}
		}
	
	private static int solution( int[] arr, int k) {
		// Method **: 
		// Time Complexity: O((n-k)*k)
		// Extra space: O(k)
		if ( arr == null || arr.length < k ) {
			return Integer.MIN_VALUE;
		}
		
		int res = -1;
		
		return res;
		
	}
	
	
	private static void displayArray( int[] arr ) {
		if ( arr == null || arr.length == 0 ) {
			return;
		}
		
		System.out.printf("[");
		for ( int i = 0; i < arr.length - 1; ++i ) {
			System.out.printf("%d,", arr[i]);
		}
		System.out.printf("%d]\n", arr[arr.length-1]);
	}
	
}

/*


Method 5(Use Oder Statistics)
1) Use order statistic algorithm to find the kth largest element. Please see the topic selection in worst-case linear time O(n)
2) Use QuickSort Partition algorithm to partition around the kth largest number O(n).
3) Sort the k-1 elements (elements greater than the kth largest element) O(kLogk). This step is needed only if sorted output is required.

Time complexity: O(n) if we don’t need the sorted output, otherwise O(n+kLogk)

Thanks to Shilpi for suggesting the first two approaches.

Method 6 (Use Min Heap)
This method is mainly an optimization of method 1. Instead of using temp[] array, use Min Heap.

Thanks to geek4u for suggesting this method.

1) Build a Min Heap MH of the first k elements (arr[0] to arr[k-1]) of the given array. O(k)

2) For each element, after the kth element (arr[k] to arr[n-1]), compare it with root of MH.
……a) If the element is greater than the root then make it root and call heapify for MH
……b) Else ignore it.
// The step 2 is O((n-k)*logk)

3) Finally, MH has k largest elements and root of the MH is the kth largest element.

Time Complexity: O(k + (n-k)Logk) without sorted output. If sorted output is needed then O(k + (n-k)Logk + kLogk)

All of the above methods can also be used to find the kth largest (or smallest) element.

*/