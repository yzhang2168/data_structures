package data_structures;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
	/**
	 * Time: # functional calls
	 * 		For each node including leaf nodes (.left == null, .right == null), we call on node.left and node.right
	 * 		2n = O(n)
	 * Space: 
	 * 		heap - no new objects
	 * 		stack - every level is O(1)
	 * 		# levels: O(h), worst case O(n), average case O(logn)
	 * 		Total: O(h) -- need to be accurate!! Worst case O(n), average case O(logn)
	 * */
	public static int getHeight(TreeNode root) {
		// corner case & base case
		if (root == null) {
			return 0;
		}
		
		// subproblems: left and right subtrees' heights
		// post order traversal
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		
		// logic
		return 1 + Math.max(leftHeight, rightHeight);
	}

	public static int countNodes(TreeNode root) {
		// corner case & base case
		if (root == null) {
			return 0;
		}
		// subproblems: left and right subtrees' nodes
		int leftNodes = countNodes(root.left);
		int rightNodes = countNodes(root.right);
		return 1 + leftNodes + rightNodes;
	}

	/**
	 *  not optimized, getHeight() on each node - repetition
	 * */
	public static boolean isBalanced(TreeNode root) {
		// corner case & base case
		if (root == null) {
			return true;
		}
		// subproblems: left and right subtrees' balance
		boolean left = isBalanced(root.left);
		boolean right = isBalanced(root.right);
		return left && right && Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1;	
	}
	
	/**
	 * optimization 1: for each node, save height
	 * 
	 * optimization 2: return both height and isBalanced()
	 * 		cannot return 2 values - using a class would be an overkill
	 * 		if any subtree is unbalanced, return -1
	 * 		else if both left and right subtrees are balanced and diff(left - right) <= 1, return current height 
	 * 		does not compute height repetitively
	 * 		time:  each call O(1) * n = O(n) 
	 * 		space: O(h). Worst case O(n), average case O(logn)
	 * */
	public static boolean isBalancedOptimized(TreeNode root) {
		/**
		// corner case
		if (root == null) {
			return true;
		}
		// subproblems: left and right subtrees' balance and height
		int height = isBalancedOptimzedHelper(root);
		if (height == -1) {
			return false;
		}
		return true; 
		*/
		return isBalancedOptimzedHelper(root) >= 0;		
	}
	
	// for unbalanced tree, return -1
	// for balanced tree, return height
	private static int isBalancedOptimzedHelper(TreeNode root) {
		// base case
		if (root == null) {
			return 0;
		}
		
		// post order
		int leftHeight = isBalancedOptimzedHelper(root.left);
		int rightHeight = isBalancedOptimzedHelper(root.right);
		
		// logic - O(1)
		/**
		if (leftHeight == -1 || rightHeight == -1) {
			return -1;
		}
		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}		
		return 1 + Math.max(leftHeight, rightHeight);		
		 * */
		// if leftHeight >= 0 is false, the rest won't be executed
		// a modification to getHeight()
		if (leftHeight >= 0 && rightHeight >= 0 && Math.abs(leftHeight - rightHeight) <= 1) {
			return 1 + Math.max(leftHeight, rightHeight);
		} else {
			return -1;
		}
	}

	public static boolean isSymmetric(TreeNode root) {
		// corner case
		if (root == null) {
			return true;
		}
		
		return isSymmetricHelper(root.left, root.right);
	}

	/**
	 * time:  O(1) for each node, O(n) in total
	 * space: O(h), average O(logn), worst O(n)
	 * */
	private static boolean isSymmetricHelper(TreeNode n1, TreeNode n2) {
		// base case
		if (n1 == null && n2 == null) {
			return true;
		}
		if (n1 == null || n2 == null) {
			return false;
		}
		// logic
		// if (a.value != b.value) {return false;}
		return (n1.value == n2.value) && isSymmetricHelper(n1.left, n2.right) && isSymmetricHelper(n1.right, n2.left);
	}

	public static boolean isIdentical(TreeNode n1, TreeNode n2) {
		// base case
		if (n1 == null && n2 == null) {
			return true;
		}
		if (n1 == null || n2 == null) {
			return false;
		}

		// logic
		if (n1.value != n2.value) {
			return false;
		} 		
		return isIdentical(n1.left, n2.left) && isIdentical(n1.right, n2.right);
	}
	
	/**
	 * time:  
	 * 		each level: level logn, n/2 nodes, (n/2)^2 func calls
	 * 		branching factor = 4
	 * 		level of recursion tree = logn = height of the input trees
	 * 		# nodes in the recursion tree: 4^logn) = O(n^2)
	 * space: O(h), average O(logn), worst O(n)
	 * */
	public static boolean isTweakedIdentical(TreeNode n1, TreeNode n2) {
		// base case
		if (n1 == null && n2 == null) {
			return true;
		}
		if (n1 == null || n2 == null) {
			return false;
		}
		
		// logic
		if (n1.value != n2.value) {
			return false;
		} 		
		return isTweakedIdentical(n1.left, n2.left) && isTweakedIdentical(n1.right, n2.right) ||
			  (isTweakedIdentical(n1.left, n2.right) && isTweakedIdentical(n1.right, n2.left));
	}
	
	/**
	 * given a binary tree, find the max difference between the # nodes in the left subtree and right subtree
	 * post-order: pass value from bottom to top
	 * return value 
	 * */
	public static int findMaxDiff(TreeNode root) {
		// corner case
		if (root == null) {
			return 0;
		}
		int[] maxDiff = new int[1]; // initialized to 0
		/**
		 * int maxDiff = 0; will not work!!! 
		 * passing values between recursion frames: use return. 
		 * Java passes by value only. Any parameters are just local variables and cannot be passed between recursion calls.
		 * since recursion returns # nodes, it cannot return maxDiff also. 
		 * Make a global variable that can be seen from all frames.
		 * */
		findMaxDiffHelper(root, maxDiff);
		return maxDiff[0];
	}
	
	// return # nodes
	private static int findMaxDiffHelper(TreeNode root, int[] maxDiff) {
		// base case 
		if (root == null) {
		return 0;
	}
		// logic for current layer: with results from recursion calls on left and right subtrees
		// find diff for current layer
		// then update max if needed
		int leftNum = findMaxDiffHelper(root.left, maxDiff);
		int rightNum = findMaxDiffHelper(root.right, maxDiff);
		int currDiff = Math.abs(leftNum - rightNum);
		maxDiff[0] = Math.max(maxDiff[0], currDiff);
		return 1 + leftNum + rightNum;
	}
	
	/**
	 * Given a binary tree, find maximum sum from any node to any node. 
	 * The two nodes can be the same node and they can be on the same path from an ancestor to one of the descendent nodes.
	 * Assumptions: the root of the given binary tree is not null
	 * */	
	public static int findMaxSum(TreeNode root) {
		int[] max = new int[]{Integer.MIN_VALUE};
		findMaxSumHelper(root, max, 0);
		return max[0];
	}

	private static void findMaxSumHelper(TreeNode root, int[] max, int preSum) {
		// base case
		if (root == null) {
			return;
		}
		// logic
		int currSum = preSum + root.value;
		max[0] = Math.max(max[0], currSum);
		currSum = Math.max(currSum, 0);
		// recursion calls
		findMaxSumHelper(root.left, max, currSum);
		findMaxSumHelper(root.right, max, currSum);
	}


	/**
	 * return pre-, in-, post-order in ArrayList - dynamic array
	 * Time: # functional calls
	 * 		For each node including leaf nodes, we call on node.left and node.right: 2n = O(n)
	 * Space: 
	 * 		heap - no new objects
	 * 		stack - every level is O(1)
	 * 		# levels: O(h), worst case O(n), average case O(logn)
	 * 		Total: O(h) -- need to be accurate!! Worst case O(n), average case O(logn)
	 * */
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
