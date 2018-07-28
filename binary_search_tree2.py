#!/usr/bin/python3
"""
Yun, yzhxdy@gmail.com
July 2018
class for BinarySearchTree
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

    def __in_order_recursion(self, node, result):
        if node.left is not None:
            self.__in_order_recursion(node.left, result)
        result.append(node.key)
        if node.right is not None:
            self.__in_order_recursion(node.right, result)

    def in_order_traversal(self):
        result = []
        if self.root is not None:
            self.__in_order_recursion(self.root, result)
        return result

    def pre_order_traversal(self):
        result = []
        if self.root is not None:
            self.__pre_order_recursion(self.root, result)
        return result

    def __pre_order_recursion(self, node, result):
        result.append(node.key)
        if node.left is not None:
            self.__pre_order_recursion(node.left, result)
        if node.right is not None:
            self.__pre_order_recursion(node.right, result)

    def __post_order_recursion(self, node, result):
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
        if key == node.key:
            return node
        elif key < node.key:
            if node.left is not None:
                return self.__find_recursion(key, node.left)
            # for a missing key, return the closest node
            return node
        elif key > node.key:
            if node.right is not None:
                return self.__find_recursion(key, node.right)
            # for a missing key, return the closest node
            return node

    def find(self, key):
        """
        For a missing key, return the closest node
        """
        if self.root is None:
            return 'tree is empty'
        else:
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
    def range_search(self, low, high):
        """
        constraints: low and high within tree range
        """
        result = []
        if self.root is not None:
            node = self.find(low)
            while node is not None and node.key <= high:
                if node.key >= low:
                    result.append(node.key)
                    node = self.__next_largest(node)
        return result

    def nearest_neighbors(self, key):
        pass

    def __left_descendant(self, node):
        if node.left is None:
            return node
        else:
            return self.__left_descendant(node.left)

    def __right_ancestor(self, node):
        # if node is the largest, return None
        if node.parent is not None:
            if node.key < node.parent.key:
                return node.parent
            else:
                return self.__right_ancestor(node.parent)
        return None

    def __next_largest(self, node):
        if node.right is not None:
            return self.__left_descendant(node.right)
        else:
            return self.__right_ancestor(node)

    def next_largest(self, key):
        """
        For users
        constraints: key needs to be in the tree range
        """
        node = self.find(key)
        if node.key == key:
            return self.__next_largest(node)
        else:
            return node

    def insert(self, key):
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

    def is_binary_search_tree(self):
        pass

    def __str__(self):
        s = ''
        for node in self.nodes:
            s = s + str(node) + '\n'
        return s
    '''

if __name__ == '__main__':
    tree = BinarySearchTree()
    tree.read_from_console()
    print(tree)
    print(tree.find(1))
    print(tree.find(2))
    print(tree.find(3))

    print('\nfinding missing keys')
    print(tree.find(0))
    print(tree.find(8))
    print(tree.find(3.5))

    '''
    print('\ninserting 0, 2.5, 3.5, 8')
    tree.insert(0)
    tree.insert(2.5)
    tree.insert(3.5)
    tree.insert(8)

    print('after insertions')
    '''
    print('in order')
    print(tree.in_order_traversal())
    print('pre order')
    print(tree.pre_order_traversal())
    print()
    print('post order')
    print(tree.post_order_traversal())

    print('\nrange_search(-2, 5)')
    print(tree.range_search(-2, 5))
    print('range_search(6, 8)')
    print(tree.range_search(6, 8))

    print('\n0\'s next largest:', tree.next_largest(0))
    print('3\'s next largest:', tree.next_largest(3))
    print('4.5\'s next largest:', tree.next_largest(4.5))
    print('8\'s next largest:', tree.next_largest(8))

'''
input
7
4 1 2
2 3 4
6 5 6
1 -1 -1
3 -1 -1
5 -1 -1
7 -1 -1
'''