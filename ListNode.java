package data_structures;

public class ListNode {
    int      value;
    ListNode next; // a reference to ListNode object
    
    public ListNode(int x) {
    	value = x;
    	next = null;
    }
    
    public ListNode(int x, ListNode n) {
    	value = x;
    	next = n;
    }

}
