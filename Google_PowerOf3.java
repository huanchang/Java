// Write function to determine if given unsigned 32-bit number is a power of 3

// Brute-Force solution: straight forward loop, each time divided by 3 until =>0
// Return 0 if there's one loop cannot divided by 3

// Solution2: 
// Math: if a number can be divided by 3
// then we have that the sum of each digits can be divided by 3
// => Solution: if number is larger than 10, then sum up all digits 
// if the sum is larger than 10 then sum again, otherwise return true only if sum = 3 or 6 or 9

public class Google_PowerOf3{
	public static void main( String[] args ) {
	}
	
	public static boolean powerOf3 ( int a ) {
		//consider negative number
		if ( a < 0 ) {
			a = -a;
		}
		
		// sum up all digits until smaller than 10
		while ( a >= 10 ) {
			
		}
		
		return a % 3 == 0 ;
	}
	
}