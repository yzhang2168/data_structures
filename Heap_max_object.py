# python3
"""
Yun Zhang, yzhxdy@gmail.com
2018
max heap of objects
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


class MaxHeap:
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

    def max_heapify(self, i):
        # aka sift_down
        left = self.left(i)
        right = self.right(i)
        largest = i
        # cannot use str(variable_name) as an argument
        # because variable names are translated into machine after compilation
        if left < self.size and self.array[left].get_value(self.sort_by) > self.array[i].get_value(self.sort_by):
            largest = left
        if right < self.size and self.array[right].get_value(self.sort_by) > self.array[largest].get_value(self.sort_by):
            largest = right
        if largest != i:
            self.array[i], self.array[largest] = self.array[largest], self.array[i]
            # after swapping, smallest's subtree may violate min heap property
            self.max_heapify(largest)

    def build_max_heap(self, array):
        """
        :modifies input array in place
        :running time: Theta(n)
        :return: None
        """
        self.array = array
        self.size = len(self.array)
        for i in range(len(array) // 2, -1, -1): # [self.size//2 ... 0]
            self.max_heapify(i)

    def extract_max(self):
        result = self.array[0]

        self.array[0] = self.array[self.size - 1]
        self.size -= 1
        self.array.pop()
        self.max_heapify(0)
        return result

    def get_max(self):
        return self.array[0]

    def insert(self, obj):
        self.array.append(obj)
        self.size += 1
        self.sift_up(self.size - 1)

    def sift_up(self, i):
        # while i is not root
        while i > 0 and self.array[self.parent(i)].get_value(self.sort_by) < self.array[i].get_value(self.sort_by):
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

    print('\nbuild max heap from inserting objects, sort by field1')
    my_heap1 = MaxHeap()
    my_heap1.set_sort_by('field1')
    my_heap1.insert(object1)
    my_heap1.insert(object2)
    my_heap1.insert(object3)
    my_heap1.insert(object4)
    my_heap1.insert(object5)
    print(my_heap1)

    print('\nbuild_max_heap_from_array, sort by field1')
    my_heap2 = MaxHeap()
    my_heap2.set_sort_by('field1')
    my_heap2.build_max_heap(my_array)
    print(my_heap2)
    print('\nmax:')
    print(my_heap2.get_max())

    print('\nbuild_max_heap_from_array, sort by field2')
    my_array2 = [object1, object2, object3, object4, object5]
    print('starting array ID')
    print(id(my_array2))

    my_heap3 = MaxHeap()
    my_heap3.set_sort_by('field2')
    my_heap3.build_max_heap(my_array2)
    print(my_heap3)
    print('resulting max heap ID')
    print(id(my_heap3.array))
    print('\nextract_max()')
    print(my_heap3.extract_max())
    print('\nresulting heap')
    print(my_heap3)
    print('\nadd object5')
    my_heap3.insert(object5)
    print(my_heap3)


    print('\nbuild max heap from inserting objects, sort by field3')
    my_heap4 = MaxHeap()
    my_heap4.set_sort_by('field3')
    my_heap4.insert(object1)
    my_heap4.insert(object2)
    my_heap4.insert(object3)
    my_heap4.insert(object4)
    my_heap4.insert(object5)
    print(my_heap4)



