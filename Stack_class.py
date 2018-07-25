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


class StackStaticArray:
    def __init__(self, n):
        self.size = n
        self.stack = [float('-inf')] * self.size
        self.top = -1

    def empty(self):
        return self.top == -1

    def push(self, element):
        self.top += 1
        if self.top > self.size:
            raise Exception('stack overflow')
        self.stack[self.top] = element

    def pop(self):
        if self.empty():
            raise Exception('stack underflow')
        self.top -= 1
        return self.stack[self.top + 1]

    def __str__(self):
        return str(self.stack[:self.top + 1])


if __name__ == "__main__":
    stack = StackIndex(10)
    print(stack.empty())
    stack.push('a')
    stack.push('b')
    print(stack)
    print(stack.pop())
    print(stack)
    print(stack.pop())
    print(stack.empty())
    #stack.pop()
