// Google interview:
// Given an array int32 arr[] of size n, 
// return the number of non-empty contigious subarrays whose sum lies in range [a, b] 



public class SubarraySum{
    public static void main(String[] args) {
        
        int[] array = {1,2,3};
        naiveSolution(array, 0, 3);
        naiveSolution(array, -1, 2);
        
        int[] array1 = {-2,5,-1};
        naiveSolution(array1, -2, 2);
        naiveSolution(array1, 0, 2);
    }
    
    private static int numOfSubarraySum(int[] array, int a, int b) {
        // Solution: using slide window
        if ( array == null || a > b ) {
            return 0;
        }
        
        int count = 0;
        int times = 0;
        
        int left = 0;
        int right = 0;
        
        int sum = array[left];
        
        while ( left < array.length && right < array.length ) {
            
            if ( sum < a ) {}
            
            times++;
        }
        
        System.out.printf("\tSlide Window Solution : Time Complexity:%d. Result:%d\n", times,count);
        return count;
    }
    
    private static int naiveSolution(int[] array, int a, int b) {
        if ( array == null || a > b ) {
            return 0;
        }
        
        int count = 0;
        int times = 0;
        
        // two loops
        for ( int i = 0; i < array.length; ++i) {
            int sum = 0;
            
            for ( int j = i; j < array.length; ++j) {
                times++;
                
                // sum up consecutive subarray from i to j
                sum += array[j];
                if ( sum <= b && sum >= a) {
                    count++;
                }
                
            }
            
        }
        
        System.out.printf("Given a = %d, b = %d, and array[",a, b);
        for ( int i:array ) {
            System.out.printf(" %d",i);
        }
        System.out.printf("]\n");
        System.out.printf("\tNaive Solution : Time Complexity:%d. Result:%d\n", times,count);
        return count;
    }
    
}