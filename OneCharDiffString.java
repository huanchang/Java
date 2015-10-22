// google-interview-question
//Given a string and array of strings, find whether the array contains a string with one character difference from the given string. Array may contain string of different lengths. 

//Ex: Given string "banana" and array is  [bana, apple, banaba, bonanza, banamf]
//The outpost should be true as banana and banaba are one character difference.

public class OneCharDiffString{
    
    public static void main(String[] args) {
        String s = "banana";
        String[] array = {"baba", "apple","banaba","bonanza","banamf"};
        
        LoopSolution(s,array);
        
        LoopSolution(null, null);
        LoopSolution(null,array);
        LoopSolution(s, null);
        s = "bababa";
        String[] array2 = {"bababba", "bbaba", "baba"};
        LoopSolution(s,array2);
        
    }
    
    private static void LoopSolution(String s, String[] array) {
        // n is the length of s, m is the size of array
        // Time complexity is O(n*m)
        // Extra space O(nm)
        
        if ( s == null || array == null || array.length == 0 ) {
            return ;
        }
        
        System.out.printf("Given s=%s and array[",s);
        for ( String t:array ) {
            System.out.printf(" %s",t);
        }
        System.out.printf("]\n");
        
        // Loop for all strings in the array
        for ( String t:array ) {
        
            // Found a string with at most one character different to s
            if (isAtMostOneCharDiff(s,t)) {
                System.out.println("Found:"+t);
            }
        }
    }
    
    private static boolean isAtMostOneCharDiff(String s, String t) {
        // Check if two string is at most one character difference
    
        // Time complexity is O(n), n is the length of string s
        // Extra space O(3n)->O(n)
        if ( s == null && t == null ) {
            return false;
        }
        
        // The difference of length is larger than 1
        if ( Math.abs(s.length() - t.length()) > 1 ) {
            return false;
        }
        
        int[][] cost = new int[3][s.length()+1];
        cost[0][0] = 1;     // j = i -1; ith character of s, jth character of t
        cost[1][0] = 0;     // j  = i;
        cost[2][0] = 1;     // j = i+1;
        
        int minCost = 0;
        
        for ( int i = 0; i < s.length(); ++i ) {
                
                int a = ( t.length() <= i || s.charAt(i) != t.charAt(i) ) ? 1 : 0;
                int b = ( i ==0 ||t.length() <= i - 1 || s.charAt(i) != t.charAt(i-1) ) ? 1 : 0;
                int c = ( t.length() <= i+1 || s.charAt(i) != t.charAt(i+1) ) ? 1 : 0;
                
                cost[1][i+1] = Math.min(Math.min((cost[1][i]+a), (cost[0][i]+1)), (cost[2][i]));
                
                cost[0][i+1] = Math.min( (cost[0][i]+b), (cost[1][i+1]+1));
                
                cost[2][i+1] = Math.min( (cost[2][i]+c), (cost[1][i+1]+1));
                
                minCost = Math.min(Math.min( cost[0][i+1], cost[1][i+1]), cost[2][i+1]);
                
                //System.out.printf("\t[%d,%d,%d], min:%d\n",cost[0][i+1], cost[1][i+1], cost[2][i+1], minCost);
                
                if (minCost >= 2) {
                    return false;
                }
        }
        
        return true;
    }
}