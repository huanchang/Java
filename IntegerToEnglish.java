/*
  convert(19,234,556) = convert(19)+"million"+convert(234)+"thousand"+convert(556)  
*/

import java.util.List;
import java.util.ArrayList;

public class IntegerToEnglish{

    static final  String[] small = {"Zero", "One", "Two", "Three", "Four", "Five", "Six"
                        , "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen"
                        , "Seventeen", "Eighteen", "Nineteen"};
    static final  String[] ten = {"", "Twenty", "Thirty", "Forty", "Fifth", "Sixty" 
                        , "Seventy", "Eighty", "Ninety"};
    static final  String[] big = {"", "Thousand", "Million", "Billion"};
    static final  String hundred = "Hundred";
    static final String negative = "Negative";
    
    public static void main(String[] args) {
        System.out.println(convert(123456789));
        System.out.println(convert(0));
        System.out.println(convert(-123456789));
        System.out.println(convert(2123456789));
        System.out.println(convert(1000000001));
        System.out.println(convert(0000000001));
        System.out.println(convert(-000000001));
        
    }
    
    public static String convert(int num) {
        //   check sign
        if (num == 0) {
            // zero
            return small[0];
        } else if (num <0) {
            // negative number
            return negative + convert(-num);
        }
        
        List<String> parts = new ArrayList<String>();
        
        int chunkCounter = 0;
        
        // Divide number into chunks which contain a 3-digit number
        while( num > 0) {
            // lowest 3 bits
            if ( num % 1000 >0) {
                parts.add(convertChunk(num%1000)+" "+big[chunkCounter]);
            }
            
            num /= 1000;
            chunkCounter++;
        }
        
        return listToString(parts);
    }
    
    public static String convertChunk(int num) {
        StringBuilder sb = new StringBuilder();
        
        // Hundred
        if (num/100 > 0) {
            sb.append(small[num/100]).append(" ").append(hundred).append(" ");
            num %= 100;
        }
        
        // Tens
        if (num>=20){
            sb.append(ten[num/10-1]).append(" ");
            num %=10;
        }
        
        // one to Nineteen
        if (num >0){
            sb.append(small[num]).append(" ");
        }
        
        return sb.toString();
        
    }
    
    public static String listToString(List<String> parts) {
        if (parts==null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            for ( int i = parts.size() - 1; i>=0; --i) {
                sb.append(parts.get(i)).append(" ");
            }
            
            return sb.toString();
        }
    }
}