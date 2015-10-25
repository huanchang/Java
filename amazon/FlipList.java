public class FlipList{

    public static void main(String[] args) {
        int[] arr = {1,0,0,0,0,1,0,0};
        flipList(arr, 5);
        
    }
    
    
    public static void flipList(int[] arr, int flipTime) {
        // Time complexity is O(n*T) and space complexity is O(1)
        int currTime = 0;
        
        while( currTime < flipTime) {
            System.out.printf("Current Time: %d, Array: ",currTime );
            consoleDisplay(arr);
            
            int prev = 0;
            int tmp = 0;
            
            
            for (int i = 0; i < arr.length-1; ++i) {
                // temple store current node value
                tmp = arr[i];
                
                // Set as 0 if previous node and next node have the same value, otherwise set as 1
                arr[i] = prev^arr[i+1];
                
                // update previous value
                prev = tmp;
            }
            
            arr[arr.length-1] = prev^0;
            
            currTime++;
        }
        
        System.out.printf("Current Time: %d, Array: ",currTime );
        consoleDisplay(arr);
    }
    
    
    public static void consoleDisplay(int[] arr) throws NullPointerException{
        int n  = arr.length;
             
        System.out.print(arr[0]);
        for (int j = 1; j <n; ++j) {
           System.out.print(","+arr[j]); 
        }
        System.out.printf("\n");
            
    }
    
}