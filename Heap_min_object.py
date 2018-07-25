# python3
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

    def build_heap_from_array(self, array):
        self.array = array
        self.size = len(self.array)
        for i in range(len(array) // 2, -1, -1):
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

    def add_object(self, obj):
        self.array.append(obj)
        self.size += 1
        self.sift_up_object(self.size - 1)

    def sift_up_object(self, i):
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
    my_heap1 = MinHeap()

    object1 = Node('a', 10, 500)
    object2 = Node('b', 20, 400)
    object3 = Node('c', 30, 300)
    object4 = Node('d', 40, 200)
    object5 = Node('e', 50, 100)

    my_heap1.set_sort_by('field1')

    my_heap1.add_object(object1)
    my_heap1.add_object(object2)
    my_heap1.add_object(object3)
    my_heap1.add_object(object4)
    my_heap1.add_object(object5)
    print(my_heap1)
    print('-' * 20)

    my_heap2 = MinHeap()

    my_heap2.set_sort_by('field3')

    my_heap2.add_object(object1)
    my_heap2.add_object(object2)
    my_heap2.add_object(object3)
    my_heap2.add_object(object4)
    my_heap2.add_object(object5)
    print(my_heap2)
    print('\nmin:')
    print(my_heap2.get_min())

    print('\nbuild_heap_from_array()')
    my_array = [object1, object2, object3, object4, object5]
    my_heap3 = MinHeap()
    my_heap3.set_sort_by('field3')
    my_heap3.build_heap_from_array(my_array)
    print(my_heap3)
    print(my_heap3.size)
    print('\nextract_min()')
    print(my_heap3.extract_min())
    print('\nresulting heap')
    print(my_heap3)
    print('\nadd object5')
    my_heap3.add_object(object5)
    print(my_heap3, my_heap3.size)