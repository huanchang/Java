import java.util.Random;
import java.util.HashMap;
import java.util.Arrays;

public class TwoSum{
	// Solution for classic two sum problem
	// Given an array of integers, find out if there exist any pair of numbers with sum as a target number 
	// Return the index of the pair of numbers
	
	public static void main(String[] args) {
		// Test array with length from 0 to 10
		Random rand = new Random();
		
		for ( int i = 0; i <= 20; i++ ) {
			int[] data = new int[i];
			
			for ( int j = 0; j < i; ++j ) {
				data[j] = rand.nextInt(100);
			}
			
			int target = rand.nextInt(100);
			
			System.out.printf( "DATA:");
			displayArray(data);
			System.out.printf( "\n");
			
			
			int[] res = sortSolution(data, target );
			
			if ( res[0] == -1 ) {
				System.out.printf( "\tTARGET: No valid pair found\n.", target);
			} else{
				System.out.printf( "\tTARGET: %d\tResult: [%d, %d], %d+%d=%d\n", target, res[0], res[1], data[res[0]], data[res[1]], target);
			}
			
			
			if ( i >= 2 ) {
				int a = i == 2 ? 0: rand.nextInt(i-1)+1;
				int b = i == 2? 1:rand.nextInt(i-1)+1 ;
				while ( a == b ) {
					b = rand.nextInt(i-1)+1;
				}
				target = data[a] + data[b];
				
				res = sortSolution(data, target );
				
				if ( res[0] == -1 ) {
					System.out.printf( "\tTARGET: No valid pair found.\n", target);
				} else{
					System.out.printf( "\tTARGET: %d\tResult: [%d, %d], %d+%d=%d\n", target, res[0], res[1], data[res[0]], data[res[1]], target);
				}
				
			}
			
			target = -1;
			res = sortSolution(data, -1);
			if ( res[0] == -1 ) {
				System.out.printf( "\tTARGET: No valid pair found.\n", target);
			} else{
				System.out.printf( "\tTARGET: %d\tResult: [%d, %d], %d+%d=%d\n", target, res[0], res[1], data[res[0]], data[res[1]], target);
			}
			
		}
		
	}
	
	private static int[] naiveSolution(int[] arr, int target) {
		// Time complexity is O(n^2)
		// No Extra space
		int[] res = new int[2];
		res[0] = -1;
		res[1] = -1;
		
		if ( arr == null || arr.length <= 1 ) {
			// Return empty array if the length of array is smaller than 2
			// Should make sure with the interviewee
			return res;
		}
		
		// Loop all possible pairs of number using two pass
		for ( int i = 0; i < arr.length; ++i ) {
			for ( int j = i + 1; j < arr.length; ++j ) {
				if ( arr[i] + arr[j] == target ) {
					res[0] = i;
					res[1] = j;
					return res;
				}
			}
		}
		
		return res;
	}
	
	private static int[] hashMapSolution( int[] arr, int target) {
		// Time complexity: O(n)
		// Extra space: worse case:O(n)
		
		int[] res = {-1,-1};
		
		if ( arr== null || arr.length < 2) {
			return res;
		}
		
		HashMap<Integer, Integer> used = new HashMap<>();
		
		for ( int i = 0; i < arr.length; ++i ) {
			if ( used.containsKey(arr[i]) ) {
				// Find a pair
				res[0] = used.get(arr[i]);
				res[1] = i;
				return res;
			} else {
				// Add a new pair: i and the extra number needed to reach target
				used.put(target-arr[i],i);
			}
		}
	
		return res;
	}
	
	private static int[] sortSolution( int[] arr, int target) {
		//Time complexity: O(nlogn)
		//Constant extra space
		int[] res = {-1,-1};
		if ( arr== null || arr.length < 2) {
			return res;
		}
		
		// Sort the array firstly and the using binary search to find a matched number
		Arrays.sort(arr);
		
		for ( int i = 0; i < arr.length; ++i ) {
		
			int paired = binarySearch(arr, i+1, target - arr[i]);
			
			if ( paired >= 0 ) {
				res[0] = i;
				res[1] = paired;
				return res;
			}
		}
		
		return res;
	}
	
	private static int binarySearch( int[] arr, int start, int target) {
		// Binary search in a sorted array
		// Return the index if there exists a number after start position with target value
		// This solution is applicable to the case that only need to printout the number, not the index
		
		int res = -1;
		if ( arr == null || arr.length == 0 ) {
			return res;
		}
		
		int left = start;
		int right = arr.length - 1;
		
		while ( left <= right ) {
			int middle = ( left + right ) >> 1;
			
			if ( arr[middle] == target ) {
				return middle;
			} else if ( arr[middle] > target ) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		
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
		System.out.printf("%d]", arr[arr.length-1]);
	}
	
}