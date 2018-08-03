#!/usr/bin/python3
"""
Yun Zhang, yzhxdy@gmail.com
July 2018
class for BinarySearchTree, tree may not be balanced
class for Node: has key and 3 pointers (parent, left child and right child)
"""
import sys


class Node:
    def __init__(self, key, left=None, right=None, parent=None):
        self.key = key
        self.left = left
        self.right = right
        self.parent = parent

    def __str__(self):
        s1 = str(self.key)
        if self.left is not None:
            s2 = str(self.left.key)
        else:
            s2 = 'n/a'
        if self.right is not None:
            s3 = str(self.right.key)
        else:
            s3 = 'n/a'
        if self.parent is not None:
            s4 = str(self.parent.key)
        else:
            s4 = 'n/a'
        return 'key ' + s1 + '; left key ' + s2 + '; right key ' + s3 + '; parent key ' + s4


class BinarySearchTree:
    def __init__(self):
        self.root = None
        self.nodes = []

    def read_for_test(self, n, str_list):
        if n > 0:
            for j in range(n):
                self.nodes.append(Node(None))
            for i in range(n):
                a, b, c = map(int, str_list[i].strip().split())
                self.nodes[i].key = a
                if b != -1:
                    self.nodes[i].left = self.nodes[b]
                    self.nodes[b].parent = self.nodes[i]
                else:
                    self.nodes[i].left = None
                if c != -1:
                    self.nodes[i].right = self.nodes[c]
                    self.nodes[c].parent = self.nodes[i]
                else:
                    self.nodes[i].right = None
            self.root = self.nodes[0]

    def read_from_console(self):
        n = int(sys.stdin.readline().strip())
        if n > 0:
            for j in range(n):
                self.nodes.append(Node(None))
            for i in range(n):
                a, b, c = map(int, sys.stdin.readline().strip().split())
                self.nodes[i].key = a
                if b != -1:
                    self.nodes[i].left = self.nodes[b]
                    self.nodes[b].parent = self.nodes[i]
                else:
                    self.nodes[i].left = None
                if c != -1:
                    self.nodes[i].right = self.nodes[c]
                    self.nodes[c].parent = self.nodes[i]
                else:
                    self.nodes[i].right = None
            self.root = self.nodes[0]

    def is_empty(self):
        return self.root is None

    def __in_order_recursion(self, node, result):
        if node is not None:
            self.__in_order_recursion(node.left, result)
            result.append(node.key)
            self.__in_order_recursion(node.right, result)

    def in_order_traversal(self):
        result = []
        if self.root is not None:
            self.__in_order_recursion(self.root, result)
        return result

    def in_order_iterative(self):
        result = []
        s = []
        current = self.root
        while current is not None or len(s) != 0:
            while current is not None:
                s.append(current)
                current = current.left
            # the last current is None
            current = s.pop()
            result.append(current.key)
            current = current.right
        return result

    def __in_order_reverse_recursion(self, node, result):
        if node is not None:
            self.__in_order_reverse_recursion(node.right, result)
            result.append(node.key)
            self.__in_order_reverse_recursion(node.left, result)

    def in_order_reverse_traversal(self):
        result = []
        if self.root is not None:
            self.__in_order_reverse_recursion(self.root, result)
        return result

    def __pre_order_recursion(self, node, result):
        '''
        if node is not None:
            result.append(node.key)
            self.__pre_order_recursion(node.left, result)
            self.__pre_order_recursion(node.right, result)
        '''
        result.append(node.key)
        if node.left is not None:
            self.__pre_order_recursion(node.left, result)
        if node.right is not None:
            self.__pre_order_recursion(node.right, result)

    def pre_order_traversal(self):
        result = []
        if self.root is not None:
            self.__pre_order_recursion(self.root, result)
        return result

    def __post_order_recursion(self, node, result):
        '''
        if node is not None:
            self.__post_order_recursion(node.left, result)
            self.__post_order_recursion(node.right, result)
            result.append(node.key)
        '''
        if node.left is not None:
            self.__post_order_recursion(node.left, result)
        if node.right is not None:
            self.__post_order_recursion(node.right, result)
        result.append(node.key)

    def post_order_traversal(self):
        result = []
        if self.root is not None:
            self.__post_order_recursion(self.root, result)
        return result

    def __find_recursion(self, key, node):
        if node is None or key == node.key:
            return node
        elif key < node.key:
            if node.left is not None:
                return self.__find_recursion(key, node.left)
            else: # down to a leaf already, key is missing
                # return the closest node
                return node
        elif key > node.key:
            if node.right is not None:
                return self.__find_recursion(key, node.right)
            else: # down to a leaf ready, key is missing
                # for a missing key, return the closest node
                return node

    def find(self, key):
        """
        For a missing key, return the closest node
        """
        return self.__find_recursion(key, self.root)
    '''
    def find2(self, key, node):
        # simplified, but requires 2 parameters
        if node is None or node.key == key:
            return node
        elif key < node.key:
            if node.left is not None:
                return self.find2(key, node.left)
        elif key > node.key:
            if node.right is not None:
                return self.find2(key, node.right)
    '''
    def max(self, node=None):
        """
        if no argument is provided, set it to root - max of the whole tree
        if argument is provided, find max of the subtree rooted at node
        :return:
        """
        if node is None:
            node = self.root
        if node is not None: # check if tree is empty or input node is valid
            while node.right is not None:
                node = node.right
            return node.key
        return node

    def max_recursive(self, node):
        if node is not None:
            if node.right is None:
                return node.key
            return self.max_recursive(node.right)
        return node

    def min(self, node=None):
        if node is None:
            node = self.root
        if node is not None: # check if tree is empty or input node is valid
            while node.left is not None:
                node = node.left
            return node.key
        return node

    def __left_ancestor(self, node):
        if node.parent is not None:
            if node.key > node.parent.key:
                return node.parent.key
            else:
                return self.__left_ancestor(node.parent)
        return None

    def __right_ancestor(self, node):
        # if node is the largest, return None
        if node.parent is not None:
            if node.key < node.parent.key:
                return node.parent.key
            else:
                return self.__right_ancestor(node.parent)
        return None

    def successor(self, key):
        """
        find smallest key > key
        """
        node = self.find(key)
        if key == node.key:
            if node.right is not None:
                return self.min(node.right)
            else:
                # go up the tree and find the closest right ancestor
                return self.__right_ancestor(node)
        elif key < node.key:
            return node.key
        elif key > node.key:
            return self.successor(node.key)

    def predecessor(self, key):
        """
        find largest key < key
        """
        node = self.find(key)
        if key == node.key:
            if node.left is not None:
                return self.max(node.left)
            else:
                return self.__left_ancestor(node)
        elif key > node.key:
            return node.key
        elif key < node.key:
            return self.predecessor(node.key)

    def nearest_neighbors(self, key):
        return self.predecessor(key), self.successor(key)

    def range_search(self, low, high):
        result = []
        if self.root is not None:
            # this will map low to a key in tree range
            k = self.find(low).key
            # if low > k, return []
            if low <= k:
                while k is not None and k <= high:
                    if k >= low:
                        result.append(k)
                        k = self.successor(k)
        return result

    def insert(self, key):
        """
        does not rebalance the tree
        """
        new = Node(key)
        # empty tree
        if self.root is None:
            self.nodes.append(new)
            self.root = self.nodes[0]
        else:
            parent_node = self.find(key)
            self.nodes.append(new)
            if key < parent_node.key:
                parent_node.left = new
            elif key > parent_node.key:
                parent_node.right = new
            new.parent = parent_node

    '''
    def delete(self, key):
        pass
    '''

    def is_BST_recursive(self, node):
        """
        allows duplicate keys: left < root <= right
        recursively check subtree min and max
        traverse some nodes many times
        """
        if node is None:
            return True
        if node.left is not None:
            if self.min(node.left) >= node.key:
                return False
        if node.right is not None:
            if self.max(node.right) < node.key:
                return False
        if self.is_BST_recursive(node.left) is False or self.is_BST_recursive(node.right) is False:
            return False
        return True

    def __is_BST_min_max_util(self, node, mini, maxi):
        if node is None:
            return True
        if node.key < mini or node.key >= maxi:
            # node.key <= maxi: duplicate key should be the right child, not left child
            return False
        return self.__is_BST_min_max_util(node.left, mini, node.key) and \
               self.__is_BST_min_max_util(node.right, node.key, maxi)

    def is_BST_min_max(self):
        """
        allows duplicate keys: left < node <= right
        """
        mini = -9223372036854775807
        maxi = 9223372036854775807
        # does not work for root = 2147483647
        return self.__is_BST_min_max_util(self.root, mini, maxi)

    def __find_BST(self, key, node, i, visited):
        if node is None:
            return node
        elif key == node.key:
            visited[i] = True
            #print('visited', visited)
            return node
        elif key < node.key:
            if node.left is not None:
                return self.__find_BST(key, node.left, i, visited)
        elif key > node.key:
            if node.right is not None:
                return self.__find_BST(key, node.right, i, visited)

    def is_BST_search_nodes(self):
        """
        no duplicate keys: left < root < right
        check BST property by checking if each node can be found
        """
        n = len(self.nodes)
        visited = [None] * n
        if self.root is None:
            return True
        for i in range(n):
            if visited[i] is None:
                if self.__find_BST(self.nodes[i].key, self.root, i, visited) is None:
                    #print(self.nodes[i].key, 'find() returns None')
                    return False
        return True

    def __is_BST_in_order_util(self, node, prev):
        """
        prev is mutable, different levels point to the same object
        """
        if node is not None:
            #print('current node', node.key)
            if self.__is_BST_in_order_util(node.left, prev) is False:
                return False
            if len(prev) == 0:
                prev.append(node.key)
            elif len(prev) != 0 and prev[0] < node.key:
                prev[0] = node.key
            else:
                print('here is the problematic key:', node.key)
                return False
            #print('current prev', prev)
            if self.__is_BST_in_order_util(node.right, prev) is False:
                return False
        return True

    def is_BST_in_order(self):
        """
        no duplicate keys: left < root < right
        check BST property by checking if in order traversal is in increasing order
        """
        if self.root is None:
            return True
        else:
            previous = []
            return self.__is_BST_in_order_util(self.root, previous)

    def is_BST_in_order_iterative(self):
        result = []
        s = []
        prev = float('-inf')
        current = self.root
        while current is not None or len(s) != 0:
            while current is not None:
                s.append(current)
                current = current.left
            # the last current is None
            current = s.pop()
            result.append(current)
            if current.key < prev:
                print('here is the problematic key:', current.key)
                return False
            else:
                prev = current.key
            current = current.right
        return True

    def __str__(self):
        s = ''
        for node in self.nodes:
            s = s + str(node) + '\n'
        return s


if __name__ == '__main__':
    tree = BinarySearchTree()
    #tree.read_from_console()

    str_list = []
    str_list.append('15 1 2')
    #str_list.append('6 3 4')
    str_list.append('6 4 3')
    # checking BST property recursive: False
    # checking BST property search: False
    # checking BST property in order: True
    # testing is_BST_in_order_comparison: True
    str_list.append('18 5 6')
    str_list.append('3 7 8')
    str_list.append('7 -1 9')
    str_list.append('17 -1 -1')
    str_list.append('20 -1 -1')
    str_list.append('2 -1 -1')
    str_list.append('4 -1 -1')
    str_list.append('13 10 -1')
    str_list.append('9 -1 -1')

    tree.read_for_test(11, str_list)

    #print(tree)
    print('in order:', tree.in_order_iterative())
    print('\nchecking BST property - recursive:', tree.is_BST_recursive(tree.root))
    print('checking BST property - search nodes:', tree.is_BST_search_nodes())
    print('checking BST property - in order iterative comparison:', tree.is_BST_in_order_iterative())
    print('checking BST property - in order:', tree.is_BST_in_order())
    print('checking BST property - search within range of ancestors', tree.is_BST_min_max())

    print('\nfinding existing key', tree.find(7))

    print('\nfinding missing keys')
    print(tree.find(0))
    print(tree.find(3.5))
    print(tree.find(8))
    '''
    print('\ninserting 0, 2.5, 3.5, 7.5')
    #tree.insert(0)
    #tree.insert(2.5)
    #tree.insert(3.5)
    #tree.insert(7.5)
    '''
    print('after insertions')
    print('in order')
    print(tree.in_order_traversal())
    print('in order iterative')
    print(tree.in_order_iterative())
    print('in order reverse')
    print(tree.in_order_reverse_traversal())
    print('pre order')
    print(tree.pre_order_traversal())
    print('post order')
    print(tree.post_order_traversal())
    '''
    print('\nmax', tree.max())
    print('max of left subtree', tree.max(tree.root.left))
    print('max recursive', tree.max_recursive(tree.root))
    print('max of left subtree', tree.max_recursive(tree.root.left))

    print('\nmin', tree.min())
    print('min of right subtree', tree.min(tree.root.right))

    print('\nsuccesor: < min, missing key in range, no right child, == max, > max')
    print('0\'s next largest:', tree.successor(0))
    print('3\'s next largest:', tree.successor(3))
    print('4.5\'s next largest:', tree.successor(4.5))
    print('8\'s next largest:', tree.successor(8))
    print('13\'s next largest:', tree.successor(13))
    print('20\'s next largest:', tree.successor(20))
    print('21\'s next largest:', tree.successor(21))

    print('\npredecessor: < min, missing key in range, no left child, == min, > max')
    print('0\'s previous largest:', tree.predecessor(0))
    print('3\'s previous largest:', tree.predecessor(3))
    print('3.5\'s previous largest:', tree.predecessor(3.5))
    print('4\'s previous largest:', tree.predecessor(4))
    print('9\'s previous largest:', tree.predecessor(9))
    print('20\'s previous largest:', tree.predecessor(20))
    print('21\'s previous largest:', tree.predecessor(21))

    print('\nnearest neighbors')
    print('0', tree.nearest_neighbors(0))
    print('13', tree.nearest_neighbors(13))
    print('20', tree.nearest_neighbors(20))

    print('\nrange_search(-2, 5):', tree.range_search(-2, 5))
    print('range_search(6, 18):', tree.range_search(6, 18))
    print('range_search(0, 100):', tree.range_search(0, 100))
    print('range_search(-30, -10):', tree.range_search(-30, -10))
    print('range_search(30, 100):', tree.range_search(30, 100))
    '''


'''
input
------
11
15 1 2
6 3 4
18 5 6
3 7 8
7 -1 9
17 -1 -1
20 -1 -1
2 -1 -1
4 -1 -1
13 10 -1
9 -1 -1
        15
      /    \
     6     18
    / \    / \
   3   7  17 20
  / \   \
 2   4  13
        /
       9       
       
10
15 1 2
6 3 4
18 5 6
3 -1 7
7 -1 8
17 -1 -1
20 -1 -1
4 -1 -1
13 9 -1
9 -1 -1

----------------
11
15 1 2
6 4 3
18 5 6
3 7 8
7 -1 9
17 -1 -1
20 -1 -1
2 -1 -1
4 -1 -1
13 10 -1
9 -1 -1
        15
      /    \
     6     18
    / \    / \
   7   3  17 20
    \ / \
   13 2  4
    /
   9   
'''