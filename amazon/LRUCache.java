// Least recent used cache: 

// Given an array and the maximum size of cache
// Output miss count

// example：   size = 4， input array   【1，2，3，4，5，4，1】
// 1 miss   2 miss   3 miss   4 miss   5 miss  replace 1   
// 4 hit    move 4 to head   1 miss  replace 2

//The most recently used pages will be near front end and least recently pages will be near rear end.

// get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
// set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

// Based on the idea from www.programcreek.com
// Create a hash table of keys and double linked list such that get() and remove/add node operation to be constant time O(1).

import java.util.HashMap;


public class LRUCache{

    private final int capacity; // Maximum number of keys
    
    private HashMap<Integer, Node> map;
    
    private Node head;
    private Node tail;
    
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);
        
        int[] keys = {1,2,3,4,5,1,5,4,2};
        
        for ( int i = 0; i < keys.length; ++i) {
            if (cache.get(keys[i])==-1) {
                System.out.printf("Miss-%d\n", keys[i]);
                cache.set(keys[i],i);
            } else {
                System.out.printf("Hit-%d\n", keys[i]);
            }
        }
    }
    
    
    // Constructor
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
        head = null;
        tail = null;
    }
    
    public int get(int key) {
        // Return the value of the key if found, and then move the key to head
        // Otherwise return -1
        
        if (map.containsKey(key)) {
            // Hit
            Node node = map.get(key);
            
            // move the node to head
            remove(node);
            insertHead(node);
            
            return node.val;
            
        } else {
            return -1;  // miss
        }
    }
    
    public void set(int key, int val) {
        // Check if key is available in cache
        if (map.containsKey(key)) {
            Node node  = map.get(key);
            
            remove(node);
            
            // Update the value and insert to head
            node.val = val;
            insertHead(node);        

        } else {
            // add this new key into cache
            if (map.size()==capacity) {
                // cache is full, then remove the tail node from cache
                remove(tail);
                
                // remove key from hashmap
                map.remove(tail.val);
            }
        
            // Create a new node and insert it at head
            Node newNode = new Node(key, val);
            insertHead(newNode);
            
            // update hash map
            map.put(key,newNode);
        }
        
    }
    
    public void insertHead(Node node) {
        if (head==null) {
            // empty linked list
            head = node;
            tail = head;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        
    }
    
    public void remove(Node node) {
        if (node.prev==null) {
            // this is head node
            head = node;
        } else {
            node.prev.next = node.next;
        }
        
        if (node.next==null) {
            // This is tail node
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
    }
    
    
    // Double linked list
    public class Node{
        public int key;
        public int val;
        public Node prev, next;
        
        public Node(int key, int v) {
            this.key = key; // key is the operation key
            this.val = v;   
            this.prev = null;
            this.next = null;
        }
    }

}

