# python3
"""
Yun Zhang, yzhxdy@gmail.com
2018
min heap of objects
compare objects by an attribute set up by set_sort_by()
"""


class Node:
    def __init__(self, value1, value2, value3):
        self.map = dict()
        self.map['field1'] = value1
        self.map['field2'] = value2
        self.map['field3'] = value3

    def get_value(self, field_name):
        return self.map[field_name]

    def __str__(self):
        s = 'field1 ' + str(self.get_value('field1')) + '; field2 ' + str(self.get_value('field2')) + \
            '; field3 ' + str(self.get_value('field3'))
        return s


class MinHeap:
    def __init__(self):
        self.array = []
        self.size = 0
        self.sort_by = None

    def set_sort_by(self, sort_by):
        self.sort_by = sort_by

    def parent(self, i):
        return (i - 1) // 2

    def left(self, i):
        return 2 * i + 1

    def right(self, i):
        return 2 * i + 2

    def min_heapify(self, i):
        # aka sift_down
        left = self.left(i)
        right = self.right(i)
        smallest = i
        if left < self.size and self.array[left].get_value(self.sort_by) < self.array[i].get_value(self.sort_by):
            smallest = left
        if right < self.size and self.array[right].get_value(self.sort_by) < self.array[smallest].get_value(self.sort_by):
            smallest = right
        if smallest != i:
            self.array[i], self.array[smallest] = self.array[smallest], self.array[i]
            # after swapping, smallest's subtree may violate min heap property
            self.min_heapify(smallest)

    def build_min_heap(self, array):
        """
        :modifies input array in place
        :running time: Theta(n)
        :return: None
        """
        self.array = array
        self.size = len(self.array)
        for i in range(len(array) // 2, -1, -1): # [self.size//2 ... 0]
            self.min_heapify(i)

    def extract_min(self):
        result = self.array[0]

        self.array[0] = self.array[self.size - 1]
        self.size -= 1
        self.array.pop()
        self.min_heapify(0)
        return result

    def get_min(self):
        return self.array[0]

    def insert(self, obj):
        self.array.append(obj)
        self.size += 1
        self.sift_up(self.size - 1)

    def sift_up(self, i):
        # while i is not root
        while i > 0 and self.array[self.parent(i)].get_value(self.sort_by) > self.array[i].get_value(self.sort_by):
            self.array[self.parent(i)], self.array[i] = self.array[i], self.array[self.parent(i)]
            i = self.parent(i)

    def __str__(self):
        first = True
        for node in self.array:
            if first:
                first = False
                s = str(node)
            else:
                s += '\n' + str(node)
        return s


if __name__ == '__main__':
    object1 = Node('a', 10, 500)
    object2 = Node('b', 40, 400)
    object3 = Node('c', 20, 300)
    object4 = Node('d', 30, 200)
    object5 = Node('e', 50, 100)
    my_array = [object1, object2, object3, object4, object5]

    print('\nbuild min heap from inserting objects, sort by field1')
    my_heap1 = MinHeap()
    my_heap1.set_sort_by('field1')
    my_heap1.insert(object1)
    my_heap1.insert(object2)
    my_heap1.insert(object3)
    my_heap1.insert(object4)
    my_heap1.insert(object5)
    print(my_heap1)
    print('-' * 20)

    print('\nbuild_min_heap_from_array, sort by field2')
    my_heap2 = MinHeap()
    my_heap2.set_sort_by('field2')
    my_heap2.build_min_heap(my_array)
    print(my_heap2)
    print('\nmin:')
    print(my_heap2.get_min())

    print('\nbuild_min_heap_from_array, sort by field3')
    my_array3 = [object1, object2, object3, object4, object5]
    print('starting array ID')
    print(id(my_array3))

    my_heap3 = MinHeap()
    my_heap3.set_sort_by('field3')
    my_heap3.build_min_heap(my_array3)
    print(my_heap3)
    print('resulting min heap ID')
    print(id(my_heap3.array))
    print('\nextract_min()')
    print(my_heap3.extract_min())
    print('\nresulting heap')
    print(my_heap3)
    print('\nadd object5')
    my_heap3.insert(object5)
    print(my_heap3)


    print('\nbuild min heap from inserting objects, sort by field3')
    my_heap4 = MinHeap()
    my_heap4.set_sort_by('field3')
    my_heap4.insert(object1)
    my_heap4.insert(object2)
    my_heap4.insert(object3)
    my_heap4.insert(object4)
    my_heap4.insert(object5)
    print(my_heap4)



