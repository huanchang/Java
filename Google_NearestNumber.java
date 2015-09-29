// Given unsigned integer 'x', write an algorithm thet returns unsigned integer 'y'
// such that it has the same number of bits set as 'x' and is not equal to 'x' and the distance |x-y| is minimized.

// Example:
// x: 01
// y: 10

// Note that one bit is set and 'x' is not equal 'y'. You may assume that x is positive integer between zero and 2^32-2;


// Example: 11--1011, 13-1101(closest right 1), 7-0111
// Difference: i(0) and j(1), if( i > j) then increase 2^(i)-2^(j); else then decrease 2^(i) - 2^(j)
// For one i: To find the closest integer is to find the closest j

// Using bit operation

// Which i is the best? lowest
// Example: 9: 1001

// Assume that input number is always valid: 0~ 2^32 - 2
import java.util.Random;

public class Google_NearestNumber{

    public static void main( String[] args ) {
        long test = 9;
        int n = 20;

        Random rand = new Random();

        for ( int i = 0; i < n; i++ ) {
            test = rand.nextLong();
            System.out.printf("Test-%d: %d=>%d\n", i, test, nearestNum(test));
        }

    }

    public static long nearestNum ( long a ) {

        long res = a;
        long i = 1;
        int p0 = 0;
        long currValue = 1;

        // Find the lowest '0' by bit operation
        // Time complexity is O(m)~O(32) = O(1)
        while ( p0 < 32 ) {
            if ( ( i & res ) == 0 ) {
                break;
            }
            i = i<<1;
            p0++;
            currValue = currValue<<1;
        }

        // Find the left closest '1', if reaches right and left at the same time then select right side
        int left = 0, right = 0;
        long leftValue = currValue, rightValue = currValue;
        // Find the left closest 1
        // Time complexity is O(m)~O(32) = O(1)

        i = 1<<p0;
        while ( left + p0 < 32 ){
            if( ( i & res ) != 0 ) {
                break;
            }
            left++;
            leftValue  = leftValue << 1;
            i = i<<1;
        }

        i = 1<<p0;

        while ( p0 - right >= 0 ){
            if( ( i & res ) != 0 ) {
                break;
            }
            right++;
            rightValue = rightValue >> 1;
            i = i>>1;
        }

        //compare left difference and right difference
        if ( ( left + p0 < 32) && ( p0 - right >= 0) ) {
            if ( ( leftValue - currValue ) <= ( currValue - rightValue ) ) {
                res = swapBits( res, p0);
                res = swapBits( res, p0 + left );
            } else {
                res = swapBits( res, p0);
                res = swapBits( res, p0 - right );
            }
        } else if ( p0 - right >= 0 ) {
            res = swapBits( res, p0);
            res = swapBits( res, p0 - right );
        } else if ( left + p0 < 32 ){
            res = swapBits( res, p0);
            res = swapBits( res, p0 + right );
        } else{
            System.out.print("Cannot find closest number with same bit set.");
        }
        // swap '0' and '1' by xor with 1

        return res;
    }

    // Time complexity is O(move)<O(32) = O(1)
    private static long swapBits( long data, int move) {
        long mask = 1 << move;
        return mask ^ data;
    }
}

