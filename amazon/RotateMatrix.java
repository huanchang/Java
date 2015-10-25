

public class RotateMatrix{

    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6}};
        
        testRotateMatrix(arr);
        
        testRotateMatrix(null);
    }
    
    public static void testRotateMatrix(int[][] arr) {
        
        try{
            consoleDisplay(arr);
        
            System.out.println("Clockwise:");
            consoleDisplay(rotateMatrix(arr, 0));
            
            System.out.println("Non-Clockwise:");
            consoleDisplay(rotateMatrix(arr, 1));
            
            System.out.println("Invalid flag test:");
            consoleDisplay(rotateMatrix(arr, 3));
            
            
        } catch (NullPointerException e) {
            System.out.println("Nullpointer Exception");
        } catch (InvalidRotateFlagException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static int[][] rotateMatrix(int[][] arr, int flag) throws InvalidRotateFlagException, NullPointerException{
        int m = arr.length;
        int n = arr[0].length;
        
        //create a new array
        int[][] res = new int[n][m];
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j <n; ++j) {
                switch(flag) {
                    case 0: // clock-wise
                        res[j][m-1-i] = arr[i][j];
                        break;
                    case 1: // non clockwise
                        res[n-1-j][i] = arr[i][j];
                        break;
                    default:
                        throw new InvalidRotateFlagException();
                    
                }
            }
        }
        
        return res;
    }
    
    public static class InvalidRotateFlagException extends Exception {
        public InvalidRotateFlagException() {
            super("Invalid rotate flag exception");
        }
    }
    
    public static void consoleDisplay(int[][] arr) throws NullPointerException{
        int m  = arr.length;
        int n = arr[0].length;
        
        for (int i = 0; i <m; ++i) {
            System.out.print(arr[i][0]);
            for (int j = 1; j <n; ++j) {
               System.out.print(","+arr[i][j]); 
            }
            System.out.printf("\n");
        }
    }
}