class Node:
    def __init__(self, key):
        self.key = key
        self.next = None


# A singly linked list class with a tail pointer
class SinglyLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def is_empty(self):
        return self.head is None

    def push_back(self, key):
        new_node = Node(key) # new_node is a reference to the new object's memory location
        # x = new_node # x is a reference to the same object, not new_node reference
        # new_node.next = None # unnecessary since __init__ sets .next to None
        if self.is_empty():
            self.head = new_node
        else:
            self.tail.next = new_node # self.tail. dereferencing to get the object rather than the reference
        self.tail = new_node

    def push_front(self, key):
        new_node = Node(key)
        if self.is_empty():
            self.tail = new_node
        else:
            new_node.next = self.head
        self.head = new_node

    def pop_back(self):
        if self.is_empty():
            raise ValueError('empty list')

        if self.head is self.tail:
            removed = self.head
            self.head = None
            self.tail = None
            return removed.key

        p = self.head
        while p.next.next is not None:
            p = p.next
        removed = p.next
        p.next = None
        self.tail = p
        return removed.key

    def pop_front(self):
        if self.is_empty():
            raise ValueError('empty list')

        if self.head is self.tail:
            removed = self.head
            self.head = None
            self.tail = None
        else:
            removed = self.head
            self.head = self.head.next
        return removed.key

    def top_front(self):
        if self.is_empty():
            raise ValueError('empty list')
        return self.head.key

    def top_back(self):
        if self.is_empty():
            raise ValueError('empty list')
        return self.tail.key

    def add_before(self, key, new_key):
        if self.is_empty():
            raise ValueError('empty list')
        new_node = Node(new_key)
        if self.head.key == key:
            new_node.next = self.head
            self.head = new_node
        else:
            p = self.head
            while p.next is not None:
                if p.next.key == key:
                    new_node.next = p.next
                    p.next = new_node
                    return
                else:
                    p = p.next
            raise ValueError('key not found')

    def add_after(self, key, new_key):
        if self.is_empty():
            raise ValueError('empty list')
        new_node = Node(new_key)

        # insert at the tail
        if self.tail.key == key:
            self.tail.next = new_node
            self.tail = new_node
            return

        # insert in the middle of the list
        p = self.head
        while p is not None:
            if p.key == key:
                new_node.next = p.next
                p.next = new_node
                return
            else:
                p = p.next
        raise ValueError('key not found')

    def is_found(self, key):
        p = self.head
        while p is not None:
            if p.key == key:
                return True
            else:
                p = p.next
        return False # p is None or key not found

    def erase(self, key):
        if self.is_empty():
            raise ValueError('empty list')

        # a single node list
        if self.head is self.tail:
            if self.head.key == key:
                self.head = None
                self.tail = None
                return
            else:
                raise ValueError('key not found')

        # >=2 nodes, erase 1st node
        if self.head.key == key:
            self.head = self.head.next
            return

        # >=2 nodes, erase middle or last node
        p = self.head
        while p.next is not None:
            if p.next.key == key:
                # if p.next is self.tail: #???
                if p.next is self.tail: # last node
                    p.next = None
                    self.tail = p
                else: # middle node
                    p.next = p.next.next
                return
            else:
                p = p.next
        raise ValueError('key not found')


    # Python iterator with __iter__ and __next__-- this would not be here in C or C++ implementation:
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
        first = True
        for node in self:
            if first:
                first = False
            else:
                s += ', '
            s += node
        return s

if __name__ == "__main__":
    # testing cases: 1st node, middle node, last node
    # create one list and do all operations on it to expose potential errors
    # need to update head, tail, and pointer in the node, therefore check all these
    lst = SinglyLinkedList()
    print('check if list is empty')
    print(lst.is_empty())

    print('\npushback A, B, C, D and then pushfront 1, 2, 3')
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

    print('\npopback 2 times and then popfront 2 times')
    print(lst.pop_back())
    print(lst.pop_back())
    print(lst.pop_front())
    print(lst.pop_front())
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\ntopfront 1 times and then topback 1 times')
    print(lst.top_front())
    print(lst.top_back())
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\ncheck if 1st node 1, middle node A, last node B, '
          'nonexisting node a in list')
    print(lst.is_found('1'))
    print(lst.is_found('A'))
    print(lst.is_found('B'))
    print(lst.is_found('a'))

    print('\nerase 1st node 1, last node B')
    lst.erase('1')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    lst.erase('B')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\nerase the only node A')
    lst.erase('A')
    print(lst)

    print('\ncheck if list is empty')
    print(lst.is_empty())

    print('\npushback a, b, c, d, e')
    lst.push_back('a')
    lst.push_back('b')
    lst.push_back('c')
    lst.push_back('d')
    lst.push_back('e')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\nerase the 1st key')
    lst.erase('a')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\nerase the last key')
    lst.erase('e')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\nerase a middle key c')
    lst.erase('c')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\nadd after 1st key')
    lst.add_after('b', 'aa1')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\nadd after last key')
    lst.add_after('d', 'aa2')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\nadd before 1st key')
    lst.add_before('b', 'ab1')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\nadd before last key')
    lst.add_before('aa2', 'ab2')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\nadd before a middle node')
    lst.add_before('ab2', 'ab3')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)

    print('\nerase ab1, aa2, d')
    lst.erase('ab1')
    print(lst)
    lst.erase('aa2')
    print(lst)
    lst.erase('d')
    print(lst)
    print('head:', lst.head.key)
    print('tail:', lst.tail.key)
