
import java.util.Stack;

public class LongestValidSubString {
    public static void main(String[] args) {
        String s = "(())(()())";
        
        System.out.printf("String:%s, Result:%d.\n",s, findLongestValidSubString(s) );
        
    }
    
    public static int findLongestValidSubString(String s) {
        
        if(s==null ||s.length()<=1) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        
        stack.push(-1); // This is the base of next substring, start index - 1.
        
        int longest = 0;
        
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i)=='(') {
                stack.push(i);
            } else {
                stack.pop();
                
                if (!stack.isEmpty()) {
                    longest = Math.max(longest,i - stack.peek());
                } else {
                    stack.push(i);
                }
                
            }
        }
        
        return longest;
    }
}