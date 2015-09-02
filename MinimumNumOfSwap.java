/**
 * Created by Huan Chang on 9/2/15.
 */


// Interview question: Minimum number of swap
// #Given pairs of persons and require partners to be adjacent.
// like  input: n = 3
// pairs[] = {1->3, 2->4, 3->5};
// arr[] = { 3, 5, 6, 4, 1, 2}
// Output: 2, swap 5&6, 6&1.

import java.util.HashMap;


class Solution{
    public static int minimumNumOfSwap( int n, int[] pairs, int[] array ) {
        // Solution 1: Recursive method

        // No need to swap, if there's no number or only two numbers
        if ( n == 2 ) {
            return 0;
        }

        // Create a HashMap pairs of numbers
        HashMap<Integer, Pair> pairsMap = new HashMap<>();
        createPairs( pairsMap, pairs , array);

        // There are more than 2 numbers
        // Here, since all numbers are in pair, we assume that there are even numbers in array
        // Each number occurs for exactly one time
        return helper( pairsMap, array, 0 );
    }

    // Generate HashMap for all pairs
    private static void createPairs( HashMap<Integer, Pair> pairsMap, int[] pairs , int[] array) {
        int n = array.length;
        int[] locations = new int[n];

        // Create a array to store location of each number in array
        for ( int i = 0; i < n; i++ ) {
            locations[array[i]-1] = i;
        }

        for ( int i = 0; i < pairs.length ; i += 2 ) {
            pairsMap.put( pairs[i], new Pair( pairs[i+1], locations[pairs[i+1]-1] ) );
            pairsMap.put( pairs[i+1], new Pair( pairs[i], locations[pairs[i]-1] ) );
        }

    }

    // Recursive method to find minimum NO. of swap
    private static int helper( HashMap<Integer, Pair> pairs, int[] array, int index ) {
        if ( index >= array.length ) {
            return 0;
        }

        // Consider: index, index+1 are paired numbers
        if ( index+1 == pairs.get( array[index] ).index ) {
            return helper( pairs, array, index + 2 );
        }

        // Otherwise: index vs index+1 are not paired
        // Two solutions: swap first number; swap second number
        int fSwapMin, sSwapMin;

        // Swap first number
        int firstSwapIndex = array[index + 1], secondSwapIndex = pairs.get( array[index] ).val;
        swap( pairs, array, firstSwapIndex, secondSwapIndex );
        fSwapMin = helper( pairs, array, index + 2 );
        swap( pairs, array, firstSwapIndex, secondSwapIndex );

        // Swap second number
        firstSwapIndex = array[index];
        secondSwapIndex = pairs.get( array[index+1] ).val;
        swap( pairs, array, firstSwapIndex, secondSwapIndex );
        sSwapMin = helper( pairs, array, index + 2 );
        swap( pairs, array, firstSwapIndex, secondSwapIndex );

        return 1 + Math.min( fSwapMin, sSwapMin);
    }

    // Swap paired location of a and b
    private static void swap( HashMap<Integer, Pair> pairs, int[] array, int a, int b ) {
        int fPosition = pairs.get(a).index, sPosition = pairs.get(b).index;

        // Swap numbers in array
        int temp = array[fPosition];
        array[fPosition] = array[sPosition];
        array[sPosition] = temp;

        // swap index in Pairs
        pairs.get(a).index = sPosition;
        pairs.get(b).index = fPosition;
    }

    // Class for paired number with its location
    private static class Pair{
        public int val;
        public int index;

        public Pair( int v, int i ) {
            this.val = v;
            this.index = i;
        }

    }
}

public class MinimumNumOfSwap{
    public static void main( String[] args){
        int n = 6;
        int[] pairs = { 1, 2, 3, 4, 5, 6};
        int[] array = { 1, 3, 6, 5, 4, 2};
        System.out.println("Results:" + Solution.minimumNumOfSwap(n, pairs, array) );
    }
}