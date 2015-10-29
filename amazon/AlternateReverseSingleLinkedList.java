// Reverse alternate K nodes in a Singly Linked List

// Given a linked list, write a function to reverse every alternate k nodes (where k is an input to the function) in an efficient way. 
// Give the complexity of your algorithm.

// Example:
// Inputs:   1->2->3->4->5->6->7->8->9->NULL and k = 3
// Output:   3->2->1->4->5->6->9->8->7->NULL. 


// Solution: Process the first k nodes and the recursively call for the rest of the list
// Use a boolean flag to indicate if reverse required

// Time complexity is O(n), each node is visited once


public class AlternateReverseSingleLinkedList {

    public static void main(String[] args) {
    
        int[] arr = {1,2,3,4,5,6,7,8,9};
    
        Node list = createLinkedList(arr);
        System.out.println("Given inked list:");
        consoleDisplayLinkedList(list);
        
        System.out.println("After reverse alternatively k nodes:");
        list = reverseAlternateKNodes(list,3);
        consoleDisplayLinkedList(list);
        
    }
    
    public static Node reverseAlternateKNodes( Node head, int k) {
        return reverseAlternateKNodes(head, k, true);
    }
    
    public static Node reverseAlternateKNodes( Node head, int k, boolean flag) {
        
        if (head==null) {
            return head;
        }
        
        Node current = head;
        Node prev = null;
        Node next = null;
    
        int counter = 0;    // count number of nodes
    
        // Loop from head to kth node or to the end of the list which ever comes first
        while(current!=null && counter <k) {
            next = current.next;
            // If flag is true then reverse next pointer
            if (flag) {
                current.next = prev;
            }
            prev = current;
            current = next;
            counter++;
        }
        
        if (flag) {
            // reverse first k nodes
            head.next = reverseAlternateKNodes( current, k, !flag);         
            
            return prev;
        
        } else {
            // If flag is not true, then attach the rest to the end after current.
            prev.next = reverseAlternateKNodes( current, k, !flag); 
                      
            return head;
        }
        
    }
    
    private static void consoleDisplayLinkedList(Node head) {
        Node current = head;
        System.out.printf("Linkedlist:");
        while(current !=null){
           System.out.printf("%d->",current.val); 
           current = current.next;
        }
        System.out.printf("null\n");
    }
    
    private static Node createLinkedList(int[] arr) {
        Node head = null;
        
        if (arr!=null) {
            Node tail = head;
            
            for (int i = 0; i < arr.length; ++i) {
                Node newNode = new Node(arr[i]);
                
                if (head==null) {
                    head = newNode;
                    tail = head;
                } else {
                    tail.next = newNode;
                    tail = tail.next;
                }
            }
        }
        
        return head;
    }

    private static class Node {
        public int val;
        public Node next;
        
        public Node(int v) {
            this.val = v;
        }
    }
}