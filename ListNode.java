package data_structures;

public class ListNode {
    int      value;
    public ListNode next; // a reference to ListNode object
    
    public ListNode(int x) {
    	value = x;
    	next = null;
    }
    
    public ListNode(int x, ListNode n) {
    	value = x;
    	next = n;
    }
    
    public static ListNode reverseLinkedList(ListNode head) {
    	/**
    	 * time:  O(n)
    	 * space: O(1)
    	 * */
    	// corner case
    	if (head == null) {
    		return head;
    	}
    	
    	// reverse all nodes except head
    	ListNode curr = head;
    	ListNode prev = null;
    	ListNode next = null;
    	
    	/** termination condition
    	 * 			1 --> 2 --> 3 --> null
    	 * null <-- 1 <-- 2 --> 3 --> null
    	 * 				 prev  curr   next 
    	 * null <-- 1 <-- 2 <-- 3     null
    	 * 				       prev   curr
    	 * 							  next	 
    	 * */
    	while (curr != null) {
    		next = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = next;
    	}
    	// head
    	return prev;
    	
    	/** while() iteration condition was not thought through
    	while (curr.next != null) {
    		next = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = next;    		
    	}
    	// after while(), curr lost next pointer
    	curr.next = prev;
    	return curr;
    	*/
    }
    
    // recursion
    public static ListNode reverse(ListNode head) {
    	/**
    	 * 1. subproblems: head --> reversed n-1 nodes (returns newhead)
    	 * 2. recursion rule: 
    	 * 	  node = head.next
    	 *    node.next = head
    	 *    head.next = null
    	 *    return new head
    	 * 3. base case: 0/1 node
    	 * */
    	// base case
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
    	// recursion rule
    	ListNode newHead = reverse(head.next);
    	
    	ListNode n2 = head.next;
    	n2.next = head;
    	head.next = null;
    	return newHead;
    }

    public static void main(String[] args) {
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(2);
    	ListNode node3 = new ListNode(3);
    	node1.next = node2;
    	node2.next = node3;
    	ListNode head = reverse(node1);
    	System.out.println(head.value);
    	System.out.println(head.next.value);
    	System.out.println(head.next.next.value);
    }
}
