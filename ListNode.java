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
    
    
    /**
     * Generate a linked list of length n where the nodes contains numbers from 0 to n-1 in order.
     * Assumption: n > 0.
     * Example: n = 3
     * Answer: 0->1->2->null
     * */    
    public static ListNode generateLinkedList(int n) {
    	ListNode head = new ListNode(0);
    	ListNode current = head;
    	for (int i = 1; i < n; i++) {
    		current.next = new ListNode(i);
    		current = current.next;
    	}
    	return head;
    }

    
    /**
     * Given a linked list, count the number of nodes in it.
     * Assumption: The linkedlist is not null or empty.
     * Example:
     * Given: 1->7->5->4->2->null
     * Answer: 5
     * */
    public static int countNodes(ListNode head) {
        int count = 0;
        ListNode current = head;
        // corner case
        if (head == null) {
        	return 0;
        }
        while (current != null) {
        	count++;
        	current = current.next;
        }        	
        return count;
    }
    
    
    /**
     * Reverse a singly-linked list.
     * L = null, return null
     * L = 1 -> null, return 1 -> null
     * L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
     * */
    public static ListNode reverseIterative(ListNode head) {
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
    public static ListNode reverseRecursion(ListNode head) {
    	/**
    	 * 1. subproblems: head --> reversed n-1 nodes (returns newhead)
    	 * 2. recursion rule: 
    	 * 	  node = head.next
    	 *    node.next = head
    	 *    head.next = null
    	 *    return new head
    	 * 3. base case: 0/1 node
    	 * 
    	 * time:  O(n): O(1) for each node
    	 * space: O(n), # stack frames: n; heap: no new objects 
    	 * */
    	// base case
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
    	// recursion rule
    	ListNode newHead = reverseRecursion(head.next);
    	
    	ListNode n2 = head.next;
    	n2.next = head;
    	head.next = null;
    	return newHead;
    }
    
    
    /**
     * find the middle node of a linked list
     * in case of even number of nodes, return the 1st of the 2 middle nodes 
     * */ 
    public static ListNode findMiddle(ListNode head) {
    	// corner case
    	if (head == null) {
    		return head;
    	}
    	ListNode s = head;
    	ListNode f = head;
    	// return the 2nd of the 2 middle nodes in case of even number of nodes 
    	//while(f != null && f.next != null) {
    	
    	// return the 1st of the 2 middle nodes in case of even number of nodes
    	while(f.next != null && f.next.next != null) {
    		s = s.next;
    		f = f.next.next;
    	}
    	return s;
    	}
    
    
    /**
     * detect cycle in a singly linked list
     * Floyd's tortoise and hare algorithm
     * */ 
    public static boolean detectCycle(ListNode head) {
    	ListNode slow = head;
    	ListNode fast = head;
    	while(fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    		if (slow == fast) {
    			return true;
    		}
    	}
    	// if hare reaches end of list, no loop found
    	return false;
    }
    
    
    /**
     * insert a node into a sorted linked list
     * */ 
    public static ListNode insertNode(ListNode head, int target) {
		ListNode newNode = new ListNode(target);
    	// corner case || inserting before the head
    	if (head == null || head.value >= target) {
    		newNode.next = head;
    		return newNode;
    	}
    	// target > head: inserting in the middle
    	// in case of identical values, insert before identical
    	ListNode curr = head;
    	while (curr.next != null) {
    		if (curr.value < target && curr.next.value >= target) {
    			newNode.next = curr.next;
    			curr.next = newNode;
    			return head;
    		}
    		curr = curr.next;
    	}
    	// after while(), curr points to tail
    	// target > tail: inserting after the tail
		curr.next = newNode;
    	return head;
    }

    
    public static ListNode insertNode2(ListNode head, int target) {
  		ListNode newNode = new ListNode(target);
      	// corner case || inserting before the head
      	if (head == null || head.value >= target) {
      		newNode.next = head;
      		return newNode;
      	}
      	// target > head: inserting in the middle
      	// in case of identical values, insert before identical
      	ListNode curr = head;
      	ListNode prev = null;
      	while (curr != null) {
      		if (curr.value < target) { 
      			prev = curr;
      			curr = curr.next;
      		} else { // if (curr.value >= target)
      			newNode.next = curr;
      			prev.next = newNode;
      			return head;
      		}
      	}
      	// after while(), curr points to null
      	// target > tail: inserting after the tail
  		prev.next = newNode;
      	return head;
      }
    
    
    /**
     * merge 2 sorted linked list into one sorted list
     * L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
     * L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
     * L1 = null, L2 = null, merge L1 and L2 to null
     * */ 
    public ListNode merge(ListNode one, ListNode two) {
    	/**
    	 * without using a dummy node, need to check corner cases
   	
    	ListNode head = null;
    	if (one == null) {
    		return two;
    	} 
    	if (two == null) {
    		return one;
    	}
    	if (one.value < two.value) {
    		head = one;
    		one = one.next;
    	} else {
    		head = two;
    		two = two.next;
    	}
    	
    	ListNode curr = head;
    	
   	 * */
     	// use a dummy node makes code a lot simpler
    	// also covers corner cases automatically (list 1 is null, list 2 is null, or both null)
    	ListNode curr1 = one;
    	ListNode curr2 = two;
    	ListNode dummy = new ListNode(-1);
    	ListNode curr = dummy;
    	while(curr1 != null && curr2 != null) {
    		if (curr1.value <= curr2.value) {
    			curr.next = curr1;
    			curr1 = curr1.next;
    		} else {
    			curr.next = curr2;
    			curr2 = curr2.next;
    		}
			curr = curr.next;
    	}
    	// if curr1 has remaining nodes
    	if (curr1 != null) {
    		curr.next = curr1;
    	}
    	// if curr2 has remaining nodes
    	if (curr2 != null) {
    		curr.next = curr2;
    	}
    	// exclude dummy node
    	return dummy.next;
    }
    
    /**
     * Given a linked list and a target value T, partition it such that 
     * all nodes less than T are listed before the nodes larger than or equal to target value T. 
     * The original relative order of the nodes in each of the two partitions should be preserved.
     * L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, 
     * is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null
     * */
    public static ListNode partition(ListNode head, int target) {
    	// corner case
    	if (head == null) {
    		return head;
    	}
    	
    	ListNode dummy_smaller = new ListNode(-1);
    	ListNode smaller = dummy_smaller;
    	ListNode dummy_larger = new ListNode(-1);
    	ListNode larger = dummy_larger;
    	ListNode curr = head;
    	
    	while (curr != null) {
    		if (curr.value < target) {
    			smaller.next = curr;
    			smaller = smaller.next;
    		} else {
    			larger.next = curr;
    			larger = larger.next;
    		}
			curr = curr.next;
    	}
    	smaller.next = dummy_larger.next;
    	larger.next = null; // larger could point to a smaller node, reset to null
    	return dummy_smaller.next;
    }

}
