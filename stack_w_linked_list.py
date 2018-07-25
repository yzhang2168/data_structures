from singly_linked_list import SinglyLinkedList


class Stack:
    def __init__(self, desc=None):
        self.__linked_list = SinglyLinkedList()
        self.__desc = desc
        # self = SinglyLinkedList() does not work. Why???

    def push(self, key):
        self.__linked_list.push_front(key)

    def pop(self):
        return self.__linked_list.pop_front()

    def top(self):
        return self.__linked_list.top_front()

    def is_empty(self):
        return self.__linked_list.is_empty()

    def get_desc(self):
        return self.__desc

    # cannot use __str__ for the linked_list, b/c it's hidden
    def __str__(self):
        return str(self.__linked_list) # str(o) calls __str__ method of object o



if __name__ == "__main__":
    my_stack = Stack('this is my first stack')
    my_stack.push('A')
    my_stack.push('B')
    my_stack.push('C')
    print(my_stack)
    print(my_stack.top())
    print(my_stack.pop())
    print(my_stack)
    print(my_stack.pop())
    print(my_stack.pop())
    print(my_stack)
    print(my_stack.is_empty())
    print(my_stack)
    print(my_stack.get_desc())
    #print(my_stack.pop()) # error: empty