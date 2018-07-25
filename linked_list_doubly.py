"""
Review of doubly-linked list
Yun on 4/29/2018
"""


class Node:
    def __init__(self, key):
        self.key = key
        self.prev = None
        self.next = None


class DoublyLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def is_empty(self):
        return self.head is None

    def push_back(self, value):
        new = Node(value)
        if self.is_empty():
            self.head = new
        else:
            self.tail.next = new
            new.prev = self.tail
        self.tail = new

    def push_front(self, value):
        new = Node(value)
        if self.is_empty():
            self.tail = new
        else:
            new.next = self.head
            self.head.prev = new
        self.head = new

    def top_back(self):
        if self.is_empty():
            raise ValueError('empty list')
        return self.tail.key

    def top_front(self):
        if self.is_empty():
            raise ValueError('empty list')
        return self.head.key

    def pop_back(self):
        if self.is_empty():
            raise ValueError('empty list')
        elif self.head is self.tail:
            removed = self.tail
            self.head = None
            self.tail = None
            return removed.key
        else:
            self.tail.prev.next = None
            removed = self.tail
            self.tail = self.tail.prev
            return removed.key

    def pop_front(self):
        if self.is_empty():
            raise ValueError('empty list')
        self.head.next.prev = None
        removed = self.head
        self.head = self.head.next
        return removed.key

    def is_found(self, key):
        p = self.head
        while p is not None:
            if p.key == key:
                return True
            else:
                p = p.next
        return False

    def __iter__(self):
        self.__i = None
        return self

    def __next__(self):
        if self.__i is None:
            self.__i = self.head
        else:
            self.__i = self.__i.next

        if self.__i is not None:
            return self.__i.key
        else:
            raise StopIteration  # to signal completion

    def __str__(self):
        s = ''
        for node in self:
            s = s + node + ' '
        return s


if __name__ == "__main__":
    # testing cases: 1st node, middle node, last node
    # create one list and do all operations on it to expose potential errors
    # need to update head, tail, and pointers in the node, therefore check all these
    lst = DoublyLinkedList()
    print('check if list is empty')
    print(lst.is_empty())

    print('\npushback A, B, C, D')
    lst.push_back("A")
    lst.push_back("B")
    lst.push_back("C")
    lst.push_back("D")
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\npushfront 1, 2, 3')
    lst.push_front("1")
    lst.push_front("2")
    lst.push_front("3")
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\ntopfront 1 times and then topback 1 times')
    print(lst.top_front())
    print(lst.top_back())
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\npopback 2 times and then popfront 2 times')
    print(lst.pop_back())
    print(lst.pop_back())
    print(lst.pop_front())
    print(lst.pop_front())
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\ncheck if 1st node, middle node, last node, '
          'nonexisting node in a list')
    print(lst.is_found('1'))
    print(lst.is_found('A'))
    print(lst.is_found('B'))
    print(lst.is_found('XXX'))
