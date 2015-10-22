
import java.util.TreeMap;


public class UniqueNumber{

    public static void main(String[] args) {
        int[] arr = {1,3,2,5,2,6,3,2,2,2};
        
        int index = findUniqueNumber(arr);
        if ( index >= 0) {
            System.out.printf("Result: arr[%d] = %d.\n",index, arr[index]);
        } else {
            System.out.printf("Not found.\n");
        }
    }
    
    // create a TreeMap to store numbers with their occurrence time.

    public static int findUniqueNumber(int[] arr) {
        // Return the index of the only number which exists for more than n/4 times

        if (arr== null || arr. length == 0) {
            return -1;
        }

        int n = arr.length;
        int m = n>>2;

        TreeMap<Integer, Integer> map = new TreeMap<Integer,Integer>();
        
        // Time complexity: O(logn) for containsKey, get, put

        for ( int i = 0; i <n; ++i) {
            Integer k = map.get(arr[i]);
            if (k != null) {
                if ( k > m) {
                    return i;
                } else {
                    map.put(arr[i],k+1);
                }
            } else {
                map.put(arr[i],1);
            }
        }
    

        return -1;
    }
}