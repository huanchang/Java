
import java.util.Random;

public class GreatestCommonDivider{

    private static final int LARGEARRAY_SIZE = 1000;
    private static final int SMALLARRAY_SIZE = 10;
    
    public static void main(String[] args) {
        int[][] arr = { {1,2,3,4,5,6}, {26,22,32,42,56,64},{1,2,3,0,5,6},{1,2,-3,0,5,6}, {26,-22,32,42,56,64}};
        
        for (int i= 0; i <arr.length; ++i) {
            System.out.printf("Test-%d, Array-[%d", i, arr[i][0]);
            for (int j = 1; j < arr[i].length; ++j) {
                System.out.printf(",%d", arr[i][j]);
            }
            System.out.printf("]\n");
            
            findGreatestCommonDivider(arr[i]);
            
        }
        
        // Random generator
        Random random = new Random();
        
        // small number test  
        System.out.printf("Small numbers test~[1,100], size = %d:\n",SMALLARRAY_SIZE);     
        int[] smallNumberArray = new int[SMALLARRAY_SIZE];
        for(int i = 0; i < SMALLARRAY_SIZE; ++i) {
            smallNumberArray[i] = random.nextInt(100);
        }
        findGreatestCommonDivider(smallNumberArray);
        
        // Large number test 
        System.out.printf("Large numbers test~[1000,1100], size = %d:\n",SMALLARRAY_SIZE);      
        int[] largeNumberArray = new int[SMALLARRAY_SIZE];
        for(int i = 0; i < SMALLARRAY_SIZE; ++i) {
            largeNumberArray[i] = random.nextInt(100)+1000;
        }
        findGreatestCommonDivider(largeNumberArray);
        
        // Large array test      
        System.out.printf("Large array test~[1,100], size = %d:\n",LARGEARRAY_SIZE); 
        int[] largeArray = new int[LARGEARRAY_SIZE];
        for(int i = 0; i < LARGEARRAY_SIZE; ++i) {
            largeArray[i] = random.nextInt(100);
        }
        findGreatestCommonDivider(largeArray);
        
    }
    
    public static void findGreatestCommonDivider(int[] arr) {
        FindGCDNaive solution1 = new FindGCDNaive();
        FindGCDWithMod solution2 = new FindGCDWithMod();
        FindGCDWithoutMod solution3 = new FindGCDWithoutMod();
        
        int gcd1 = arr[0];
        int gcd2 = arr[0];
        int gcd3 = arr[0];
        
        for (int i = 1; i < arr.length; ++i) {
            gcd1 = solution1.findGCD(gcd1, arr[i]);
            gcd2 = solution2.findGCD(gcd2, arr[i]);
            gcd3 = solution3.findGCD(gcd3, arr[i]);
        }
        
        System.out.printf("Result:%d, Time Complexity:%d\n", gcd1, solution1.getCounter());
        System.out.printf("Result:%d, Time Complexity:%d\n", gcd2, solution2.getCounter());
        System.out.printf("Result:%d, Time Complexity:%d\n", gcd3, solution3.getCounter());
    }

    
}


interface GreatestCommonDividerFinder{
    public int findGCD(int a, int b);
}

class Counter{
    protected long counter;
    
    public Counter() {
        resetCounter();
    }
    
    public void resetCounter(){
        this.counter = 0;
    }
    
    public long getCounter() {
        return this.counter;
    }
    
    public void addCounter() {
        this.counter++;
    }
}

class FindGCDNaive extends Counter implements GreatestCommonDividerFinder{

    public FindGCDNaive() {
        super();
    }
    
    public int findGCD(int a, int b) {
        addCounter();
        
        // Naive solution: 
        a = a<0? -a:a;
        b = b<0? -b:b;
        
        if ( a < b) {
            return findGCD(b,a);
        }
        
        if (b == 0) {
            return a;
        } else if ( a==b) {
            return b;
        } else {
            return findGCD(a-b,b);
        }
        
    }
}

class FindGCDWithMod extends Counter implements GreatestCommonDividerFinder {
    // Solution uses mod operation 
    // Suppose a >=b, and a = c*gcd, b = d*gcd + gcd*c. Then, c >=d.
    // a = (c+e)*gcd = d*f*gcd+g*gcd; (a%b, b) and (a,b) share the same greatest common divider
    
    public FindGCDWithMod() {
        super();
    }
    
    public int findGCD(int a, int b) {
        addCounter();
        
        a = a<0? -a:a;
        b = b<0? -b:b;
        
        if ( a < b) {
            return findGCD(b,a);
        }
        
        if (b ==0) {
            return a;
        } else if ( a%b==0) {
            return b;
        } else {
            return findGCD(a,a%b);
        }
    }
}

class FindGCDWithoutMod  extends Counter implements GreatestCommonDividerFinder {
    // Solution uses shift, without multiply/division/mod operation
    
    
    public FindGCDWithoutMod() {
        super();
    }
    
    public int findGCD(int a, int b) {
        addCounter();
        
        // Deal with negative number
        a = a<0? -a:a;
        b = b<0? -b:b;
        
        if (a==0) {
            return b;
        }
        if (b==0) {
            return a;
        }
        
        // Two even numbers
        if( (a&1)==0 && (b&1)==0) {
            // If a and b are two even number, then gcd(a,b) = gcd(a/2,b/2);
            return 2*findGCD(a>>1,b>>1);
        } else if ( (a&1)==0 && (b&1)==1) {
            // If a is even number and b is odd number
            // Then gcd(a,b) = gcd(a/2,b);
            return findGCD(a>>1,b);
        } else if ( (a&1)==1 && (b&1)==0) {
            // If a is odd number and b is even number
            // Then gcd(a,b) = gcd(a,b/2);
            return findGCD(a, b>>1);
        } else{
            // If a and b are two odd number
        
            if (a>b) {
                // If a is larger than b, Then gcd(a,b) = gcd(a-b,b);
                return findGCD(a-b,b);
            } else {
                // If b is larger than  or equal to b, Then gcd(a,b) = gcd(a,b-a);
                return findGCD(a,b-a);
            }
        }
        

    }
}
