# python3
import sys
import threading

sys.setrecursionlimit(10**7) # max depth of recursion
threading.stack_size(2**27)  # new thread will get stack of such size


class Queue:
    def __init__(self):
        self.array = []

    def enqueue(self, key):
        self.array.append(key)

    def dequeue(self):
        if self.empty():
            raise Exception('empty queue')
        top = self.array.pop(0)
        return top

    def empty(self):
        if not self.array:
            return True
        return False

    def __str__(self):
        return str(self.array)


class Node:
    def __init__(self, key):
        self.key = key
        self.parent = None
        self.children = []
        self.level = None

    def __str__(self):
        children_list = []
        for child in self.children:
            children_list.append(str(child.key))
        p = ''
        if self.parent is not None:
            p = str(self.parent.key)
        s = 'node:' + str(self.key) + '\tparent:' + p \
            + '\tchildren:' + str(children_list) + '\tlevel:' + str(self.level)
        return s


class Tree:
    def __init__(self):
        self.nodes = []
        self.root = None

    def empty(self):
        if self.nodes:
            return False
        else:
            return True

    def read(self):
        self.n = int(sys.stdin.readline()) # vertex [0...n-1]
        self.parent_index = list(map(int, sys.stdin.readline().split()))
        assert self.n == len(self.parent_index)
        # create a list of node objects
        for i in range(self.n):
            self.nodes.append(Node(i))

        # read in parent for each node objects
        for j in range(self.n):
            if self.parent_index[j] == -1:
                self.root = self.nodes[j]
                self.root.level = 1
            else:
                self.nodes[self.parent_index[j]].children.append(self.nodes[j])
                self.nodes[j].parent = self.nodes[self.parent_index[j]]

    def find_leaves(self):
        leaves = []
        for node in self.nodes:
            if not node.children:
                leaves.append(node)
        return leaves

    def compute_height_iterative(self):
        max_height = 0
        for node in self.nodes:
            if not node.children:
                height = 1
                while node.parent is not None:
                    height += 1
                    node = node.parent
                if height > max_height:
                    max_height = height
        return max_height

    def compute_height_recursive(self):
        max_height = 0
        deepest_node = None
        for node in self.nodes:
            height = self.compute_height_recursive_helper(node)
            if height > max_height:
                max_height = height
                deepest_node = node.key
        return max_height, deepest_node

    def compute_height_recursive_helper(self, node):
        if node.parent is None:
            return 1
        else:
            return self.compute_height_recursive_helper(node.parent) + 1

    def compute_height_dynamic(self):
        max_height = 0
        height = [None] * self.n
        deepest_node = None
        for node in self.nodes:
            h = self.compute_height_dynamic_helper(node, height)
            height[node.key] = h
            if h > max_height:
                max_height = h
                deepest_node = node.key
        return max_height, deepest_node

    def compute_height_dynamic_helper(self, node, height):
        if height[node.key] is not None:
            return height[node.key]
        if node.parent is None:
            return 1
        else:
            return self.compute_height_dynamic_helper(node.parent, height) + 1

    def compute_height_breadth_first(self):
        if self.empty():
            return 0
        # root node only
        if self.n == 1:
            return 1

        q = Queue()
        q.enqueue(self.root)
        while not q.empty():
            node = q.dequeue()
            print(node)
            for child in node.children:
                child.level = child.parent.level + 1
                q.enqueue(child)
        return child.level

    def __str__(self):
        first = True
        for node in self.nodes:
            if first:
                s = str(node)
                first = False
            else:
                s += '\n' + str(node)
        return s


def main():
    tree = Tree()
    tree.read()
    print('tree:')
    print('=================')
    print('root:', tree.root)
    print('=================')
    print('leaves:')
    leaves = tree.find_leaves()
    for leaf in leaves:
        print(leaf)
    print('=================')
    print('max height from breadth first traversal:', tree.compute_height_breadth_first())
    print('max height after scanning all leaves:', tree.compute_height_iterative())
    print('max height from recursive calls:', tree.compute_height_recursive())
    print('max height from dynamic:', tree.compute_height_dynamic())

threading.Thread(target=main).start()