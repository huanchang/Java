// Integer Array Ques: 
// Given an integer array of variable length like so [9, 8, 8, 3] where each item in array could be 0 to 9
// Write a function that would take would interpret the array [9, 8, 8, 3] as a number 9883 and increment it by 1. 
// The return of the function would be an integer array containing the addition like so [9,8,8,4]. 
// No zeros in the first position like [0,1,2,3]. 


// Brute-force solution: Convert integer array to a String then use Integer.parseInt() to convert it into an integer
// Finally, after adding by 1, conver that integer into array
// !!!! Issue: may not work if the number extends the range of Integer

// What if there's 0 in the first place in given array? 

import java.util.Random;

public class Google_IntegerArray{
    public static void main( String[] args ) {
        // This is the demo method
        
        // NO. of tests
        int m = 10;
        
        // Length of integer array in each test
        int n = 10;
        
        
        Random rand = new Random();
        
        int[] test = new int[n];
        
        for ( int i = 0; i < m; i++ ) {
        	// One test
        	System.out.printf( "Test-%d: ", i );
        	
        	// Generate random integer array
        	test[0] = rand.nextInt(8) + 1;
        	for ( int j = 1; j < n; ++j ) {
        		test[j] = rand.nextInt(9);
        	}
        	
        	System.out.printf( "\tData: \t");
        	display(test);
        	
        	System.out.printf( "\tResult: \t");
        	display( integerArray(test) );
        	
        	System.out.printf( "\n");
        }
        
        // Edge test:
		for ( int j = 0; j < n; ++j ) {
			test[j] = 9;
		}
		
		System.out.printf( "\tData: \t");
		display(test);
		
		System.out.printf( "\tResult: \t");
		display( integerArray(test) );
		
		System.out.printf( "\n");
        
        
        
    }
    
    public static int[] integerArray( int[] array ) {
    	// Given an number represented by an integer array
    	// Solution: iterative loop from the back to front, using d to represent additional number to be added
    	
    	int d = 1, n = array.length;
    	
    	// create an array to store the result
    	int[] res = new int[n+1];
    	
    	for ( int i = n - 1; i >= 0; i-- ) {
    	
    		int sum = array[i] + d;
    		
    		d = sum / 10;
    		array[i] = sum % 10;
    		res[i+1] = array[i];
    	}
    	
    	if ( d == 0 ) {
    		return array;
    	} else {
    		res[0] = d;
    		return res;
    	}
    	
    }
    
    public static void display( int[] array ) {
    	// method: output an integer array in console
    	
    	for ( int i = 0; i < array.length; ++i ) {
    		System.out.printf( "%d", array[i] );
    	}
    }
    
}

