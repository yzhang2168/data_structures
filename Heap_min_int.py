# python3
import sys


class HeapMin:
    def __init__(self):
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

    def min_heapify(self, i):
        # aka sift_down
        left = self.left(i)
        right = self.right(i)
        smallest = i
        if left < self.size and self.array[left] < self.array[i]:
            smallest = left
        if right < self.size and self.array[right] < self.array[smallest]:
            smallest = right
        if smallest != i:
            self.array[i], self.array[smallest] = self.array[smallest], self.array[i]
            # after swapping, smallest's subtree may violate min heap property
            self.min_heapify(smallest)

    def sift_up(self, i):
        # while i is not root
        while i > 0 and self.array[self.parent(i)] > self.array[i]:
            self.array[self.parent(i)], self.array[i] = self.array[i], self.array[self.parent(i)]
            i = self.parent(i)

    def build_min_heap(self, array):
        self.array = array
        self.size = len(self.array)
        for i in range(len(array) // 2, -1, -1):
            self.min_heapify(i)

    def get_min(self):
        if self.size == 0:
            raise Exception('heap is empty')
        return self.array[0]

    def extract_min(self):
        if self.size == 0:
            raise Exception('heap is empty')
        result = self.array[0]
        self.array[0] = self.array[self.size - 1]
        self.size -= 1
        self.array.pop()
        self.min_heapify(0)
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
        if key < old:
            self.sift_up(i)
        else:
            self.min_heapify(i)

    def __str__(self):
        return str(self.array)


if __name__ == '__main__':
    my_heap = HeapMin()
    #my_heap.input_from_console()
    l0 = []
    l1 = [1]
    l2 = [3, 2, 1]
    l3 = [0, 0, 0]

    print('\n' + str(l0))
    my_heap.build_min_heap(l0)
    print(my_heap)
    #print(my_heap.get_min())
    #print(my_heap.extract_min())
    my_heap.insert(-1)
    print(my_heap)
    my_heap.change_key(0, -100)
    print(my_heap)

    print('\n' + str(l1))
    my_heap.build_min_heap(l1)
    print(my_heap)
    print(my_heap.get_min())
    print(my_heap.extract_min())
    print(my_heap)
    my_heap.insert(-1)
    print(my_heap)
    my_heap.change_key(0, -100)
    print(my_heap)

    print('\n' + str(l2))
    my_heap.build_min_heap(l2)
    print(my_heap)
    print(my_heap.get_min())
    print(my_heap.extract_min())
    print(my_heap)
    my_heap.insert(-1)
    print(my_heap)
    my_heap.change_key(2, -100)
    print(my_heap)

    print('\n' + str(l3))
    my_heap.build_min_heap(l3)
    print(my_heap)
    print(my_heap.get_min())
    print(my_heap.extract_min())
    print(my_heap)
    my_heap.insert(-1)
    print(my_heap)
    my_heap.change_key(1, -100)
    print(my_heap)
