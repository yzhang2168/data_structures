package data_structures;

public class QueueImplemenation {
	private ListNode head;
	private ListNode tail;
	private int size;
	
	public QueueImplemenation() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return size;
	}
	
	public void offer(final int value) {
		ListNode newNode = new ListNode(value);
		if (isEmpty()) {
			head = newNode;
			tail = head;
		} else {
			tail.next = newNode;
			tail = tail.next;
		}
		size++;
	}
	
	public Integer poll() {
		if (isEmpty()) {
			return null;
		}
		ListNode result = head;
		head = head.next;
		if (head == null) {
			tail = null;
		}
		result.next = null;
		size--;
		return result.value;
	}
	
	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		return head.value;
	}
}
