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


class StackDynamicArray:
    def __init__(self):
        self.stack = []

    def push(self, new):
        self.stack.append(new)

    def pop(self):
        if self.empty():
            raise Exception('empty stack')
        return self.stack.pop()

    def empty(self):
        return len(self.stack) == 0

    def __str__(self):
        first = True
        for item in self.stack:
            if first:
                s = str(item)
                first = False
            else:
                s = s + ';' + str(item)
        return s


class Node:
    def __init__(self, key, left=None, right=None, parent=None, visited=False):
        self.key = key
        self.left = left
        self.right = right
        self.parent = parent
        self.visited = False

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
        return 'key ' + s1 + '; left key ' + s2 + '; right key ' + s3 + '; parent key ' + s4 + '; visited ' + str(self.visited)


class Tree:
    def __init__(self):
        self.nodes = []
        self.root = None

    def empty(self):
        if self.nodes:
            return False
        else:
            return True

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

    def BFS(self):
        if self.empty():
            return None
        result = []
        q = Queue()
        q.enqueue(self.root)
        while not q.empty():
            node = q.dequeue()
            result.append(node.key)
            if node.left is not None:
                q.enqueue(node.left)
            if node.right is not None:
                q.enqueue(node.right)
        return result

    def DFS(self):
        if self.empty():
            return None
        result = []
        s = StackDynamicArray()
        s.push(self.root)
        while not s.empty():
            node = s.pop()
            # visit a node only when it's popped off the stack and when it's unvisited
            if node.visited is False:
                node.visited = True
                result.append(node.key)
                # allows duplicate nodes on the stack.
                # push all children onto the stack - do not check if they've been visited
                # if a node has been visited, when the same node on the stack is popped off,
                # it will skip the following steps
                if node.right is not None:
                    # right child is pushed first so that left child is processed first
                    s.push(node.right)
                if node.left is not None:
                    s.push(node.left)
        return result

    def preorder(self):
        result = []
        if self.empty():
            return None
        else:
            self.__preorder_util(self.root, result)
        return result

    def __preorder_util(self, node, result):
        if node is not None:
            result.append(node.key)
            self.__preorder_util(node.left, result)
            self.__preorder_util(node.right, result)

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
    #tree.read_from_console()

    str_list = []
    str_list.append('15 1 2')
    #str_list.append('6 3 4')
    str_list.append('6 4 3')
    str_list.append('18 5 6')
    str_list.append('3 7 8')
    str_list.append('7 -1 9')
    str_list.append('17 -1 -1')
    str_list.append('20 -1 -1')
    str_list.append('2 -1 -1')
    str_list.append('4 -1 -1')
    str_list.append('13 10 -1')
    str_list.append('9 -1 -1')
    str_list = []
    tree.read_for_test(len(str_list), str_list)

    #print(tree)

    print('breadth first traversal:', tree.BFS())
    print('depth first traversal:', tree.DFS())
    print('pre-order traversal:  ', tree.preorder())

threading.Thread(target=main).start()