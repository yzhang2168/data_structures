package data_structures;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinHeap {
	private int[] array;
	private int size;

	public MinHeap() {
		array = new int[10];
		size = 0;	
	}

	public MinHeap(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity cannot be <= 0");
		}
		array = new int[capacity];
		size = 0;
	}

	public MinHeap(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("input array cannot be null or empty");
		}
		this.array = array;
		size = array.length;
		heapify();
	}

	/**
	 * 差比数列（等差x等比）求和，错位相减法
	 * S(h) = 				 2^(h-2) * 1 + 2^(h-3) * 2 + ... + 2^1 * (h-2) + 2^0 * (h-1)
	 * 2S(h) = 2^(h-1) * 1 + 2^(h-2) * 1 + 2^(h-3) * 2 + ... + 2^1 * (h-1)
	 * 2S(h) - S(h) = 2^h - h - 1 = n - logn - 1 = O(n)
	*/
	private void heapify() {
		for (int i = size / 2 - 1; i >= 0; i--) {
			percolateDown(i);
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == array.length;
	}

	public int size() {
		return size;
	}

	public void offer(int element) {
		if (size == array.length) {
			int[] temp = Arrays.copyOf(array, (array.length + array.length / 2));
			array = temp;
		}

		array[size] = element;
		size++;
		percolateUp(size - 1);
	}

	public int peek() {
		if (size == 0) {
			throw new NoSuchElementException("heap is empty");
		}
		return array[0];
	}

	public int poll() {
		if (size == 0) {
			throw new NoSuchElementException("heap is empty");
		}

		int result = array[0];
		array[0] = array[size - 1];
		size--;
		percolateDown(0);
		return result;
	}

	// not available in java
	// logn when index is provided
	// if index is not provided, find the element requires traversing the array O(n)
	public void update(int index, int value) {
		if (index < 0 || index > size - 1) {
			throw new IllegalArgumentException("index out of range");
		}
		if (value > array[index]) {
			percolateDown(index);
		} else {
			percolateUp(index);
		}
	}

	private void percolateDown(int index) {
		// no need to check corner case b/c private method
		// if leftChildIndex is in array
		while (2 * index + 1 <= size - 1) {
			int leftChildIndex = 2 * index + 1;
			int rightChildIndex = 2 * index + 2;
			int swapCandidate = leftChildIndex;

			// do not forget to check if rightChild exists
			if (rightChildIndex < size && array[rightChildIndex] < array[leftChildIndex]) {
				swapCandidate = rightChildIndex;
			}

			if (array[index] > array[swapCandidate]) {
				swap(array, index, swapCandidate);
			} else {
				break; // no need to go all the way down
			}
			index = swapCandidate;
		}
	}

	private void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	private void percolateUp(int index) {
		while (index > 0) {
			int parentIndex = (index - 1) / 2;
			if (array[parentIndex] < array[index]) {
				swap(array, parentIndex, index);		
			} else {
				break;
			}
			index = parentIndex;
		}
	}
}