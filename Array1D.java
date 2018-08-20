package playground;

import java.util.Arrays;

public class Array1D {
    /**
     * find max value in a 1d array
     * */
	public static int arrayMax(int[] arr) {
		Arrays.sort(arr);
		return arr[arr.length - 1];
	}

    /**
     * find max value in a 1d array by loop through the array
     * */
	public static int arrayMaxFor(int[] arr) {
	    int max = Integer.MIN_VALUE;
	    for (int i = 0; i < arr.length; i++) {
	    	if (arr[i] > max) {
	    		max = arr[i];
	    	}
	    }
        return max;
    }
	
	/**
	 * reverse an array
	 * {2, 3, 8, 4, 1} --> {1, 4, 8, 3, 2}
	 * */
	public static int[] arrayReverse(int[] arr) {
        if (arr.length == 0 || arr == null) {
        	return arr;
        }
        int arr_length = arr.length;
        int[] arr_new = new int[arr_length];
        for (int i = 0; i < arr.length; i++) {
        	arr_new[arr.length - 1 - i] = arr[i];
        }
        return arr_new;
	}

	/**
	 * given an array, an element to insert and the position to insert,
	 * return a new array with the element inserted
	 * */
	public static int[] arrayInsert(int[] arr, int key, int pos) {
		int arr_length = arr.length;
		if (pos < 0 || pos > arr_length) {
			throw new ArrayIndexOutOfBoundsException("The new position " + pos + " is out of bounds");
		}
		
		int[] arr2 = new int[arr_length + 1];
		arr2[pos] = key;
		for (int i = 0; i < pos; i++) {
			arr2[i] = arr[i];
		}
		for (int i = pos; i < arr_length; i++) {
			arr2[i + 1] = arr[i];
		}
		
		return arr2;
	}

	public static void main(String[] args) {
	      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};  
	      int[] empty = {};
	      Util.printArray(empty);
	      
	      int[] arr_new0 = arrayInsert(empty, 100, 0);
	      Util.printArray(arr_new0);
	      
	      int[] arr_new1 = arrayInsert(numbers, 100, 0);
	      Util.printArray(arr_new1);

	      int[] arr_new2 = arrayInsert(numbers, 100, 2);
	      Util.printArray(arr_new2);
	      
	      int[] arr_new3 = arrayInsert(numbers, 100, 7);
	      Util.printArray(arr_new3);
	      
	      int[] arr_new3_rev = arrayReverse(arr_new3);
	      Util.printArray(arr_new3_rev);

	      //int[] arr_new4 = arrayInsert(numbers, 100, 8);
	      //Util.printArray(arr_new4);
	      
	      System.out.println(arrayMax(numbers));
	      System.out.println(arrayMaxFor(numbers));
	}
}