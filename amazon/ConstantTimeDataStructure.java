
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class ConstantTimeDataStructure{
    // Create a data structure that support 4 operation with constant time complexity O(1)
    // Solution: hashmap + ArrayList
    // 1) add
    // 2) remove
    // 3) search
    // 4) getRandom
    
    private ArrayList<Integer> arr;
    private HashMap<Integer,Integer> hash;
    
    private ConstantTimeDataStructure() {
        arr = new ArrayList<Integer>();
        hash = new HashMap<Integer,Integer>();
    }
    
    public static void main(String[] args) {
        ConstantTimeDataStructure myDS = new ConstantTimeDataStructure();
        
        for (int i =0; i < 100; ++i) {
            myDS.add(i);
        }
        System.out.println("Search:"+myDS.search(10));
        System.out.println("Search:"+myDS.search(5));
        myDS.remove(10);
        
        for (int i =0; i < 20; ++i) {
            System.out.println("Random:"+myDS.getRandom());
        }
        
    }
    
    
    public void add(int v) {
        if (!hash.containsKey(v)) {
            // If value is not in the hash map then append it to the end of array
            // put the value and index into hash map
            arr.add(v);
            hash.put(v, arr.size()-1);
        }
    }
    
    
    public void remove(int v) {
        if (!hash.containsKey(v)) {
            // find the index if v exists in hashmap
            int index = hash.get(v);
            
            // swap v and last element to ensure constant time complexity
            Collections.swap(arr, index, arr.size()-1);
            
            // Remove the last element
            arr.remove(arr.size()-1);
            
            // Update hash map
            hash.remove(v);
            hash.put(arr.get(index), index);
        }
    }
    
    public Integer search(int v) {
        if (hash.containsKey(v)) {
            return hash.get(v);
        } else {
            return null;
        }
    }
    
    public int getRandom() {
        Random rand = new Random();
        
        // Get a random number between 0 and arr.size()-1
        int index = rand.nextInt(arr.size()-1) ;
        
        return arr.get(index);
    }
    
}