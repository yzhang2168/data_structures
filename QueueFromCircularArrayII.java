package data_structures;

public class QueueFromCircularArrayII {
	// fields
	private int[] array;
	private int head; // position to read is head + 1
	private int tail; // position to write
	// (head...tail) wrapping around is queue
	// empty: head + 1 == tail
	// full: head == tail
	
	// constructor
	public QueueFromCircularArrayII(int capacity) {
		array = new int[capacity];
		head = 0;
		tail = 1;
	}
	
	// methods
	public boolean isEmpty() {		
		return (head + 1) % array.length == tail;
	}
	
	public int size() {
		int size;
		if (tail > head) {
			size = tail - head - 1;
		} else {
			size = tail + array.length - head - 1; 
		}
		return size;
	}
	
	public boolean offer(int value) {
		if (head == tail) {
			return false;
		}
		array[tail] = value;
		tail = (tail + 1) % array.length;
		return true;
	}
	
	public Integer poll() {
		if (isEmpty()) {
			return null;
		}
		int result = array[(head + 1) % array.length];
		head = (head + 1) % array.length;
		return result;
	}
	
	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		return array[(head + 1) % array.length];
	}
	
	public static void main(String[] args) {
		QueueFromCircularArrayII queue = new QueueFromCircularArrayII(4);
		System.out.println(queue.peek());
		util.Util.printArray(queue.array);
		queue.offer(11);
		queue.offer(12);
		queue.offer(13);
		System.out.println(queue.offer(14));
		System.out.println(queue.offer(15));
		util.Util.printArray(queue.array);
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		queue.offer(14);
		util.Util.printArray(queue.array);
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());	
		util.Util.printArray(queue.array);
	}
}
