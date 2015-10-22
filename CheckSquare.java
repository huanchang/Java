
/*
    Problem: given four points to check if they form a square
    two requirements:
    1) four edges has the same length
    2) two adjacent edges are straight. Crossing edge^2 is double as the square of each edge.
    
*/

public class CheckSquare{
    public static void main(String[] args) {
        int[][] arr = {{1,2},{1,4},{0,2},{0,4}};
        
        if ( formSquare(arr)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
    
    public static boolean formSquare(int[][] arr) {
        if (arr==null || arr.length!=4 || arr[0].length!=2) {
            return false;
        }
        
        // four points
        long e1 = distance(arr[0],arr[1]);
        long e2 = distance(arr[1],arr[2]);
        long e3 = distance(arr[2],arr[3]);
        long e4 = distance(arr[3],arr[0]);
        
        long e5 = distance(arr[0],arr[2]);
        long e6 = distance(arr[1],arr[3]);
        
        System.out.printf("e1:%d,e2:%d,e3:%d,e4:%d,e5:%d,e5:%d\n",e1,e2,e3,e4,e5,e6);
        return (e1==e3)&&(e2==e4)&&(e1+e2 == e5)&&(e2+e3==e6);
    }
    
    public static long distance(int[] point1, int[] point2) {
        long x = (point1[0] - point2[0])*(point1[0] - point2[0]);
        long y = (point1[1] - point2[1])*(point1[1] - point2[1]);
        return x+y;
    }
}