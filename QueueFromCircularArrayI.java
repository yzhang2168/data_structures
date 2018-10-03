package data_structures;

import util.Util.*;

public class QueueFromCircularArrayI {
	private int[] array;
	private int head; // position to read/poll
	private int tail; // position to write/offer
	// head == tail: empty or full, use size to determine
	private int size; 
	
	// constructor
	public QueueFromCircularArrayI(int capacity) {
		array = new int[capacity];
		head = 0;
		tail = 0;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
	
	public boolean isFull() {
		return size == array.length;
	}

	public boolean offer(final int value) {
		if (size == array.length) {
			return false;
		}
		array[tail] = value;
		tail = (tail + 1) % array.length; 
		size++;
		return true;
	}

	public Integer poll() {
		if (isEmpty()) {
			return null;
		}
		int result = array[head];
		head = (head + 1) % array.length;
		size--;
		return result;
	}

	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		return array[head];
	}
	
	
	public static void main(String[] args) {
		QueueFromCircularArrayI queue = new QueueFromCircularArrayI(4);
		System.out.println(queue.peek());
		util.Util.printArray(queue.array);
		queue.offer(11);
		queue.offer(12);
		queue.offer(13);
		queue.offer(14);
		System.out.println(queue.isFull());
		util.Util.printArray(queue.array);
		System.out.println(queue.offer(15));
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		queue.offer(15);
		util.Util.printArray(queue.array);
		System.out.println(queue.isFull());
		queue.poll();
		System.out.println(queue.isFull());
		queue.poll();
		queue.poll();
		queue.poll();
		queue.poll();
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());	
		util.Util.printArray(queue.array);
	}
}
