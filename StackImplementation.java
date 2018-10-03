package data_structures;


public class StackImplementation {
	// fields
	private ListNode head;
	private int size;
	//private static int stakKounter = 0;

	// constructor
	public StackImplementation() {
		head = null;
		size = 0;
	}
	
	// functionalities
	public boolean isEmpty() {
		return head == null; 
	}
	
	public int size() {
		return size;
	}
	
	public void push(final int value) {
		ListNode newNode = new ListNode(value);		
		newNode.next = head;
		head = newNode;
		size++;
	}
	
	public Integer pop() {
		if (head == null) {
			return null;
		} 
		ListNode result = head;
		head = head.next;
		result.next = null;
		size--;
		return result.value;			
	}
	
	public  Integer peek() {
		if (head == null) {
			return null;
		} 
		return head.value;
	}
}
