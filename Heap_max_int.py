# python3
import sys


class HeapMax:
    def __init__(self):
        self.swaps = []
        self.array = []
        self.size = 0

    def parent(self, i):
        return (i - 1) // 2

    def left(self, i):
        return 2 * i + 1

    def right(self, i):
        return 2 * i + 2

    def input_from_console(self):
        '''
        The first line of the input contains single integer 𝑛.
        The next line contains 𝑛 space-separated integers 𝑎𝑖.
        5
        1 2 3 4 5
        '''
        self.size = int(sys.stdin.readline())  # vertex [0...n-1]
        self.array = list(map(int, sys.stdin.readline().split()))
        assert self.size == len(self.array)

    def max_heapify(self, i):
        """
        assumes that the binary trees rooted at left(i) and right(i) are max heaps,
        but self.array[i] might violate the max heap property
        :running time: O(log n)
        """
        left = self.left(i)
        right = self.right(i)
        largest = i
        if left < self.size and self.array[left] > self.array[i]:
            largest = left
        if right < self.size and self.array[right] > self.array[largest]:
            largest = right
        if largest != i:
            self.swaps.append((i, largest))
            self.array[i], self.array[largest] = self.array[largest], self.array[i]
            # after swapping, largest's subtree may violate max heap property
            self.max_heapify(largest)

    def sift_up(self, i):
        while i > 0 and self.array[self.parent(i)] < self.array[i]:
            self.array[self.parent(i)], self.array[i] = self.array[i], self.array[self.parent(i)]
            i = self.parent(i)

    def build_max_heap(self, arr):
        """
        :modifies input array in place
        :running time: Theta(n)
        :return: None
        """
        self.array = arr
        self.size = len(arr)
        for i in range(self.size // 2, -1, -1): # [self.size//2 ... 0]
            self.max_heapify(i)

    def get_max(self):
        if self.size == 0:
            raise Exception('heap is empty')
        return self.array[0]

    def extract_max(self):
        if self.size == 0:
            raise Exception('heap is empty')
        result = self.array[0]
        self.array[0] = self.array[-1]
        self.array.pop()
        self.size -= 1
        self.max_heapify(0)
        return result

    def insert(self, key):
        self.size += 1
        self.array.append(key)
        self.sift_up(self.size - 1)

    def change_key(self, i, key):
        if i >= self.size:
            raise ValueError('index out of range')
        old = self.array[i]
        self.array[i] = key
        if key > old:
            self.sift_up(i)
        else:
            self.max_heapify(i)

    def __str__(self):
        return str(self.array)


if __name__ == '__main__':
    my_heap = HeapMax()
    #my_heap.input_from_console()
    l0 = []
    l1 = [1]
    l2 = [1, 2, 3]
    l3 = [0, 0, 0]

    print('\ninitial list:', l0)
    my_heap.build_max_heap(l0)
    print(my_heap)
    #print(my_heap.get_max())
    #print(my_heap.extract_max())
    my_heap.insert(99)
    print(my_heap)
    my_heap.change_key(0, -100)
    print(my_heap)

    print('\ninitial list:', l1)
    my_heap.build_max_heap(l1)
    print(my_heap)
    print(my_heap.get_max())
    print(my_heap.extract_max())
    print(my_heap)
    my_heap.insert(99)
    print(my_heap)
    my_heap.change_key(0, -100)
    print(my_heap)

    print('\ninitial list:', l2)
    my_heap.build_max_heap(l2)
    print(my_heap)
    print(my_heap.get_max())
    print(my_heap.extract_max())
    print(my_heap)
    my_heap.insert(99)
    print(my_heap)
    my_heap.change_key(0, -100)
    print(my_heap)

    print('\ninitial list:', l3)
    my_heap.build_max_heap(l3)
    print(my_heap)
    print(my_heap.get_max())
    print(my_heap.extract_max())
    print(my_heap)
    my_heap.insert(99)
    print(my_heap)
    my_heap.change_key(1, 100)
    print(my_heap)
