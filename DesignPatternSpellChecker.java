import java.util.HashSet;

public class DesignPatternSpellChecker{
    // Replace with Hashtable to realize parallel programming
    // Currently use HashMap to optimize the performance for a single machine
    
    private HashSet dict;
    
    DesignPatternSpellChecker() {
        dict = new HashSet();
        dict.add("help");
        dict.add("new");
    }
    
    
    
}        