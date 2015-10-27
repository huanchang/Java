public class CyclicLinkedList {
    public static void main(String[] args) {
        int[] arr = {2,4,1,6,3,8,9,-5};
        LinkedList head = null;

        for (int i = 0; i < arr.length; ++i) {
            System.out.printf("Insert %d, ", arr[i]);
            head = InsertCycle(head,arr[i]);
            consoleDisplayLinkedList(head);
        }
    }

    private static LinkedList InsertCycle(LinkedList head, int value) {
        // Insert a value into a sorted linkedlist
        // Time complexity is O(n)

        if (head==null) {
            // Empty linked list
            head = new LinkedList(value);
            head.next = head;
        } else {

            LinkedList prev = head;
            LinkedList curr = head.next;

            // Loop until go through all nodes or current node is larger than or equal to insertValue
            
            // If value is smaller than head, then loop a round to find the tail
            // If value is smaller than other nodes, then loop until get a larget node or reach to the end
            while(curr!=head&&(curr.val<value||head.val>=value)) {
                prev = curr;
                curr = curr.next;
            }

            LinkedList newNode = new LinkedList(value);

            // Set next pointer
            prev.next = newNode;
            newNode.next = curr;
            
            // Update head, if head is next pointer to newNode
            // Two possible cases: 1) newNode is tail node then newNode is larger or equal to head
            // 2) newNode is new head then it has smaller value than head. Then, update new head.
            if (newNode.next == head && newNode.val<head.val) {
                head = newNode;
            }
        }

        return head;
    }

    public static void consoleDisplayLinkedList(LinkedList head) {

        System.out.printf("LinkedList: %d",head.val);

        LinkedList curr = head.next;

        while(curr!=head) {
            System.out.printf("->%d",curr.val);
            curr = curr.next;
        }

        System.out.printf("\n");
    }

    public static class LinkedList{
        public int val;
        public LinkedList next;

        public LinkedList(int val) {
            this.val = val;
            this.next = null;
        }

    }
}