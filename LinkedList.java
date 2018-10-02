package data_structures;


public class LinkedList {
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
    public static int length(ListNode head) {
        int length = 0;
    	// pass by value, head is a copy of the original head reference
    	// this will not change the original head
        while (head != null) {
        	length++;
        	head = head.next;
        }  
        // covers corner case: head == null
        return length;
    }
    
    
    public static ListNode get(ListNode head, int index) {
    	// input validation
    	if (index < 0) {
    		return null;
    	}
    	while (head != null && index > 0) {
    		head = head.next;
    		index--;
    	}
    	// now head == null or index == 0
    	// index out of range - return null
    	return head;
    }
    
    
    public static ListNode appendHead(ListNode head, int value) {
    	ListNode newNode = new ListNode(value);
    	// this covers when head == null
    	newNode.next = head;
    	return newNode;
    }
    
    
    public static ListNode appendTail(ListNode head, int value) {
    	ListNode newNode = new ListNode(value);
    	// corner
    	if (head == null) {
    		return newNode;
    	}
    	ListNode curr = head;
    	while (curr.next != null) {
    		curr = curr.next;
    	}
    	// now head.next = null
    	curr.next = newNode;
    	return head;
    }
    
    /**
     * remove the node with input value 
     * assumption: no duplicate values
     * input: 1 -> 2 -> 3 -> null, value = 2
     * output: 1 -> 3 -> null
     * */
    public static ListNode remove(ListNode head, int value) {
    	if (head == null) {
    		return null;
    	}
    	// remove head
    	if (head.value == value) {
    		ListNode newHead = head.next;
    		head.next = null;
    		return newHead;
    		// return head.next;
    	}
    	
    	ListNode curr = head;
    	// if remove middle node
    	while (curr.next != null && curr.next.value != value) {
    		curr = curr.next;
    	}
    	
    	// now curr.next == null or curr.next.value == value
    	if (curr.next != null) {
    		// curr.value = value, covers tail
        	curr.next = curr.next.next;
    	}
    	// covers curr.next == null, not found
    	return head;
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
    	// while (f != null && f.next != null) {

    	// return the 1st of the 2 middle nodes in case of even number of nodes
    	// while (fast.next.next != null && fast.next != null) // wrong!! When fast.next == null, fast.next.next NPE. DRAW A DIAGRAM!
    	while(f.next != null && f.next.next != null) {
    		s = s.next;
    		f = f.next.next;
    	}
    	return s;
    }
    
    public static ListNode findMiddle2(ListNode head) {
    	// corner case
    	if (head == null) {
    		return head;
    	}
    	int length = length(head); 
    	int midIndex = length / 2;
    	ListNode curr = head;    	
    	while (midIndex > 0) {
    		curr = curr.next;
    	}
    	// midIndex == 0
    	return curr;
    }
    
    
    /**
     * detect cycle in a singly linked list
     * Floyd's tortoise and hare algorithm
     * */ 
    public static boolean hasCycle(ListNode head) {
    	ListNode slow = head;
    	ListNode fast = head;
    	// input: >= 2 nodes
    	while(fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    		if (slow == fast) {
    			return true;
    		}
    	}
    	// input: 1 node
    	// input: >= 2 nodes, after while(), no loop found
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
    	// target > head: traverse the list until the last node or the node >= target
    	ListNode curr = head;
    	/**
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
    	*/
    	while (curr.next != null && curr.next.value < target) {
    		curr = curr.next;
    	}
    	newNode.next = curr.next;
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
     * merge 2 sorted linked lists into one sorted list
     * L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
     * L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
     * L1 = null, L2 = null, merge L1 and L2 to null
     * */ 
    public static ListNode mergeSortedLists(ListNode one, ListNode two) {
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
     * interleave 2 linked list into one list
     * L1 = 1 -> 4 -> 6 -> 8 -> null, L2 = 2 -> 5 -> null, interleave L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> 8 -> null
     * L1 = null, L2 = 1 -> 2 -> null, interleave L1 and L2 to 1 -> 2 -> null
     * L1 = null, L2 = null, interleave L1 and L2 to null
     * */ 
    public static ListNode interleaveTwoLists(ListNode one, ListNode two) {
    	if (one == null) {
    		return two;
    	}
    	
    	if (two == null) {
    		return one;
    	}
    	
    	ListNode dummy = new ListNode(0);
    	ListNode curr = dummy;
    	while (one != null && two != null) {
    		curr.next = one;
    		one = one.next;
    		curr = curr.next;
    		curr.next = two;
    		two = two.next;
    		curr = curr.next;
    	}
    	// if one of them has remaining elements, append the next node 
    	if (one != null) {
    		curr.next = one;
    	} else if (two != null) {
    		curr.next = two;
    	}
    	
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
    	// larger could point to a smaller node, reset to null
    	larger.next = null; 
    	return dummy_smaller.next;
    }
    
    
    /**
     * Given a singly-linked list, where each node contains an integer value, sort it in ascending order. 
     * The merge sort algorithm should be used to solve this problem.
	Examples
	null, is sorted to null
	1 -> null, is sorted to 1 -> null
	1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
	4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6
     * */
    public static ListNode mergesort(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
    	ListNode mid = findMiddle(head);
    	ListNode leftHead = head;
    	ListNode rightHead = mid.next;
    	mid.next = null;
    	ListNode leftList = mergesort(leftHead);
    	ListNode rightList = mergesort(rightHead);
    	ListNode result = mergeSortedLists(leftList, rightList);
    	return result;
    }
    
    
    /**
     * Given a linked list, check whether it is a palindrome.
     * Examples:
     * Input:   1 -> 2 -> 3 -> 2 -> 1 -> null
     * output: true.
     * Input:   1 -> 2 -> 3 -> null  
     * output: false.
     * Requirements: Space complexity must be O(1)
     * */
    public static boolean isPalindrome(ListNode head) {
    	if (head == null || head.next == null) {
    		return true;
    	}
    	ListNode mid = findMiddle(head);
    	ListNode leftList = head;
    	ListNode rightList = reverseIterative(mid.next);
    	mid.next = null;
    	while (rightList != null) {
    		if (leftList.value != rightList.value) {
    			return false;
    		}	
    		leftList = leftList.next;
    		rightList = rightList.next;    		
    	}
    	return true;
    }
    
    /**
     * You are given two linked lists representing two non-negative numbers. 
     * The digits are stored in reverse order and each of their nodes contain a single digit. 
     * Add the two numbers and return it as a linked list.  
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) = 342 + 465 = 807
     * Output: 7 -> 0 -> 8
     * */
    public static ListNode addTwoNumbersI(ListNode one, ListNode two) {
    	int oneSum = 0;
    	int twoSum = 0;
    	int ten = 1;
    	while (one != null) {
    		oneSum = oneSum + one.value * ten;
    		ten = ten * 10;
    		one = one.next;
    	}
    	
    	//System.out.println("oneSum: " + oneSum);
    	ten = 1;
       	while (two != null) {
    		twoSum = twoSum + two.value * ten;
    		two = two.next;
    		ten = ten * 10;
    	}
       	
    	//System.out.println("twoSum: " + twoSum);    	
    	int sum = oneSum + twoSum;
    	ListNode dummy = new ListNode(0);
    	ListNode curr = dummy;
    	if (sum == 0) {
    		return dummy;
    	}
    	
    	while (sum != 0) {
    		int digit = sum % 10;
    		sum = (sum - digit) / 10;
    		ListNode node = new ListNode(digit);
    		curr.next = node;
    		curr = node;
    	}
       	return dummy.next;
    }
    
    /**
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) 
     * Output2: 243 + 564 = 807
     * */
    public static ListNode addTwoNumbersII(ListNode one, ListNode two) {
    	int oneSum = 0;
    	int twoSum = 0;
    	while (one != null) {
    		oneSum = oneSum * 10 + one.value;
    		one = one.next;
    	}    	
    	//System.out.println("oneSum: " + oneSum);
       	while (two != null) {
    		twoSum = twoSum * 10 + two.value;
    		two = two.next;
    	}
    	//System.out.println("twoSum: " + twoSum);
    	
    	int sum = oneSum + twoSum;
    	ListNode curr = null;
    	if (sum == 0) {
    		return new ListNode(0);
    	}
    	
    	while (sum != 0) {
    		int digit = sum % 10;
    		sum = (sum - digit) / 10;
    		ListNode node = new ListNode(digit);
    		node.next = curr;
    		curr = node;
    	}
       	return curr;
    }
    
    /**
     * Remove all nodes with target value. 
     * Input: 2 -> 3 -> 6 -> 4 -> 6 -> null, target = 6
     * Output: 2 -> 3 -> 4 -> null
     * head may change - use a dummyhead
     * */
    public static ListNode removeAllTargets(ListNode head, int target) {
    	ListNode dummy = new ListNode(0);
    	ListNode curr = dummy;
    	while (head != null) {
    		if (head.value != target) {
    			curr.next = head;
    			curr = head;
    		}
    		head = head.next;
    	}
    	curr.next = null;
    	return dummy.next;
    }
    
    /**
     * Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null to be 
     * N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null
     * Examples
     * L = null, is reordered to null
     * L = 1 -> null, is reordered to 1 -> null
     * L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
     * L = 1 -> 2 -> 3 -> null, is reordred to 1 -> 3 -> 2 -> null
     * */
    public static ListNode reorder(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode mid = findMiddle(head);
    	ListNode rightHead = mid.next;
    	mid.next = null;
    	ListNode rightReversed = reverseIterative(rightHead);
    	ListNode newHead = interleaveTwoLists(head, rightReversed);
    	return newHead;
    }
    
    public static void main(String[] args) {
		ListNode node0 = null;
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(4);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(5);
		ListNode node5 = new ListNode(6);
		ListNode node6 = new ListNode(1);
		ListNode node7 = new ListNode(9);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		//util.Util.printLinkedList(interleaveTwoLists(node0, node0));
		//util.Util.printLinkedList(interleaveTwoLists(node0, node1));
		//util.Util.printLinkedList(interleaveTwoLists(node1, node0));
		//util.Util.printLinkedList(interleaveTwoLists(node1, node4));
		//util.Util.printLinkedList(interleaveTwoLists(node4, node1));
		util.Util.printLinkedList(node1);
		util.Util.printLinkedList(mergesort(node1));
		//util.Util.printLinkedList(reorder(node1));
		//util.Util.printLinkedList(addTwoNumbersI(node0, node0));
		//util.Util.printLinkedList(addTwoNumbersII(node1, node4));
		//util.Util.printLinkedList(removeAllTargets(node4, 2));
		//System.out.println(isPalindrome(node1));
    }
}
