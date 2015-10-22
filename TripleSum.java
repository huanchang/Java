// GOOGLE interview: find triples with sum less than or equal to T

import java.util.Arrays;


public class TripleSum{
    public static void main(String[] args) {
        int[] array = {1 ,4, 15, 9, -5};
        
        int t = 15;
        
        System.out.printf("Result:%d\n",findTripleSumBelow(array,t));
        
    }

    public static int findTripleSumBelow(int[] array, int t) {
    
        // Time complexity: O(n^2)
        
        if (array== null || array.length < 3) {
            return 0;
        }

        int counter = 0;

        // Sort first: time complexity is O(nlogn)
        Arrays.sort(array);

        // Outer loop from left to right 
        for( int i =0; i < array.length; ++i) {
            // For each number using two pointer to find the range of numbers that 
            int j = i+1;
            int k = array.length - 1;

            while( j < k ) {
                int sum = array[i] + array[j]+array[k];
                

                // check the sum of array[i], array[j] and the kth number
                // Whenever you're in a situation where A[i]+A[j]+A[k] < X, you know that the same holds for all j<k'<k

                if ( sum <= t ) {
                    // All triples: array[i], array[j] and a number between j+1 and k, could meet requirement
                    counter += k-j;
                    System.out.printf("j:%d,k:%d\n",j,k);
                    j++;
                } else {
                        // sum too large
                        k--;
                }
            }
        }

        return counter;
    }
}