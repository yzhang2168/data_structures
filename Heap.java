package data_structures;

import java.util.Collections;
import java.util.Queue;
import java.util.PriorityQueue;

public class Heap {

	public static void main(String[] args) {
		// comparator object for self defined class
		ListNodeComparator comp = new ListNodeComparator();
		Queue<ListNode> pq = new PriorityQueue<ListNode>(comp);
		ListNode n1 = new ListNode(7);
		ListNode n2 = new ListNode(3);
		ListNode n3 = new ListNode(6);
		pq.offer(n1);
		pq.offer(n2);
		pq.offer(n3);
		System.out.println(pq.poll().value);
		
		//Collections.reverseOrder() for java class
		Queue<Integer> maxPQ = new PriorityQueue<Integer>(Collections.reverseOrder());
		maxPQ.offer(5);
		maxPQ.offer(7);
		maxPQ.offer(3);
		System.out.println(maxPQ.poll());
	}
}
