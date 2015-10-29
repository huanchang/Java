public class LeastCoins {
    public static void main(String[] args) {
        int[] coins = {2,3,5};
        
        test(coins, 10);
        test(coins, 12);
        test(coins, 4);
        test(coins, 1);
        
    }
    
    public static void test(int[] coins, int v) {
        try {
            System.out.printf("Target: %d, Minimum number of coins: %d.\n", v, findMinimumNumOfCoins(coins, v));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static int findMinimumNumOfCoins(int[] coins, int v) throws Exception{
        // Dynamic programming uses an array to store the minimum number of coins needed for value from 0 to v
        // Time complexity is O(mv), space complexity is O(v)
        
        if (coins==null) {
            throw new Exception("No coins available.");
        }
        
        int[] counter = new int[v+1];
        counter[0] = 0;
        
        for (int i = 1; i <=v; ++i) {
            counter[i] = Integer.MAX_VALUE;
            
            for (int j = 0; j < coins.length; ++j) {
                if (i-coins[j]>=0 && counter[i-coins[j]] != Integer.MAX_VALUE) {
                    counter[i] = Math.min(counter[i],counter[i-coins[j]]+1);
                }
            }
        }
        
        if (counter[v]==Integer.MAX_VALUE) {
            throw new Exception("Cannot make the change of "+v);
        }
        return counter[v];
    }
    
    
    
    
}