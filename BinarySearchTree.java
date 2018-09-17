package data_structures;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
	
	/**
	 * assumptions: no duplicate values; Integer.MIN_VALUE, Integer.MAX_VALUE not in the tree
	 * subproblems: left subtree and right subtrees are BSTs
	 * recursion rule: max in left subtree < root < min in right subtree; left subtree and right subtrees are BSTs
	 * time:  O(n)
	 * space: O(h), average O(logn), worst O(n)
	 * */	
	public static boolean isBST(TreeNode root) {
		return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private static boolean isBSTHelper(TreeNode root, int min, int max) {
		// corner case & base case
		if (root == null) {
			return true;
		}
		// logic
		if (root.value <= min || root.value >= max) {
			return false;
		}
		// ensure that this matches the recursion rule
		return isBSTHelper(root.left, min, root.value) && isBSTHelper(root.right, root.value, max);
	}
	
	/**
	 * given a binary search tree and a range, print all numbers in the range 
	 * BST inorder traversal, instead of visiting every node, add if () before visiting left and right child
	 * constrained BST traversal
	 * time:  O(n)
	 * space: O(h)
	 * */
	public static List<Integer> getRange(TreeNode root, int low, int high) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null) {
			return list;
		}
		getRangeHelper(root, low, high, list);
		return list;
	}
	
	private static void getRangeHelper(TreeNode root, int low, int high, List<Integer> list) {
		// base case
		if (root == null) {
			return;
		}
		
		// constrained BST inorder traversal:
		// if node <= low, do not visit left child
		if (root.value > low) {
			getRangeHelper(root.left, low, high, list);
		}
		
		if (low <= root.value && root.value <= high) {
		list.add(root.value);
		}
		
		// if node >= high, do not visit right child
		if (root.value < high) {
			getRangeHelper(root.right, low, high, list);
		}
	}
	
	public static List<Integer> preOrder(TreeNode root) {
		List<Integer> preOrderList = new ArrayList<Integer>(); // empty []
		if (root == null) {
			return preOrderList; // empty []
		}
		preOrderHelper(root, preOrderList);
		return preOrderList;
	}
	
	private static void preOrderHelper(TreeNode root, List<Integer> preOrderList) {
		// base case
		if (root == null) {
			return; 
		}
		preOrderList.add(root.value);
		preOrderHelper(root.left, preOrderList);
		preOrderHelper(root.right, preOrderList);
	}

	public static List<Integer> inOrder(TreeNode root) {
		List<Integer> inOrderList = new ArrayList<Integer>(); // empty []
		if (root == null) {
			return inOrderList; // empty []
		}
		inOrderHelper(root, inOrderList);
		return inOrderList;
	}
	
	private static void inOrderHelper(TreeNode root, List<Integer> inOrderList) {
		// base case
		if (root == null) {
			return; 
		}
		inOrderHelper(root.left, inOrderList);
		inOrderList.add(root.value);
		inOrderHelper(root.right, inOrderList);
	}
	
	public static List<Integer> postOrder(TreeNode root) {
		List<Integer> postOrderList = new ArrayList<Integer>(); // empty []
		if (root == null) {
			return postOrderList; // empty []
		}
		postOrderHelper(root, postOrderList);
		return postOrderList;
	}
	
	private static void postOrderHelper(TreeNode root, List<Integer> postOrderList) {
		// base case
		if (root == null) {
			return; 
		}
		postOrderHelper(root.left, postOrderList);
		postOrderHelper(root.right, postOrderList);
		postOrderList.add(root.value);
	}

}
