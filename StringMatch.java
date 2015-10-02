// String match problem: Find the index of first occurrence of a string s in string t.

import java.lang.String;

public class StringMatch{
    public static void main(String[] args) {
        demo();
    }
    
    private static void demo() {
        String s = "ba";
        String t = "papalabbaaba";
        System.out.printf("s:%s, t:%s, Brute-force: %d, Rabin-Karps : %d\n", s, t, BruteForce(s,t), RabinKarp(s,t)  );
        
        s = "";
        t = "tutuhell0";
        System.out.printf("s:%s, t:%s, Brute-force: %d, Rabin-Karps : %d\n", s, t, BruteForce(s,t), RabinKarp(s,t)  );
        
        s = "hello";
        t = "";
        System.out.printf("s:%s, t:%s, Brute-force: %d, Rabin-Karps : %d\n", s, t, BruteForce(s,t), RabinKarp(s,t)  );
        
        
    }
    
    private static int BruteForce(String s, String t) {
        // Brute-force solution: use two nested loops
        // Time complexity: O(n^2)
        // No extra space
        
        // Return -1 if s or t is null pointer or s is longer than t
        if ( s == null || t == null || s.length() > t.length() ) {
            return -1;
        }
        
        for ( int i = 0; i < t.length() - s.length() + 1; ++i ) {
        
            int j = 0;
            
            for ( ; j < s.length(); ++j ) {
                if (s.charAt(j) != t.charAt(i+j)) {
                    break;
                }
            }
            
            // Found a matched substring
            if ( j == s.length() ) {
                return i;
            }
        }
        
        return -1;
    }
    
    
    private static int RabinKarp(String s, String t) {
        //Rabin-Karp solution: use hash incremental hash function
        // Calculate the hash function for substring and then compare with hash code of s
        // If there's a collision then check if s.substring is exactly same as s
        
        // Time complexity is dependent to hash function
        // O(m+n) if there's not too many collisions
    
        // Return -1 if s or t is null pointer or s is longer than t
        if ( s == null || t == null || s.length() > t.length() ) {
            return -1;
        }
        
        s = s.toLowerCase();
        t = t.toLowerCase();
        
        int base = 26;
        int mod = 997;
        
        int t_hash = 0;
        int s_hash = 0;
        int power = 1;  // The modulo result of base^m, m is the length of s
        for ( int i = 0; i < s.length(); ++i ) {
            power =  i == 0 ? 1 : (power*base)%mod;
            t_hash = (t_hash*base+(t.charAt(i)-'a'))%mod;
            s_hash = (s_hash*base+(s.charAt(i)-'a'))%mod;
        }
        
        for ( int i = s.length(); i < t.length(); ++i ) {
            //System.out.printf("i = %d, t_hash = %d, s_hash = %d.\n", i, t_hash, s_hash);
            
            if ( t_hash == s_hash && s.equals(t.substring(i-s.length(), i))) {
                // collision then check if substring matches s
                return i - s.length();
            }
            
            // Roll Slide window and update hash code
            t_hash -= ( ( t.charAt(i-s.length()) - 'a')*power ) % mod;
            //System.out.printf("power = %d, t_hash = %d]", power, t_hash);
            
            t_hash = t_hash >= 0 ? t_hash : (t_hash + mod);
            
            // Move to right by one character
            t_hash  = (t_hash*base+(t.charAt(i)-'a'))%mod; 
            
            
        }
        //System.out.printf("i = %d, t_hash = %d, s_hash = %d.\n", t.length(), t_hash, s_hash);
        
        if ( t_hash == s_hash && s.equals(t.substring(t.length()-s.length()))) {
            // collision then check if substring matches s
            return t.length() - s.length();
        }
        
        return -1;  // S is not a substring of t
        
    }
    
    private static int boyerMoore(String s, String t) {
        // well-suited for applications in which the pattern is much shorter than the text or where it persists across multiple searches
        
    }
    
}