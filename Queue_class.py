class QueueDynamicArray:
    def __init__(self):
        self.array = []

    def enqueue(self, key):
        self.array.append(key)

    def dequeue(self):
        if self.empty():
            raise Exception('queue underflow')
        top = self.array.pop(0)
        return top

    def empty(self):
        if not self.array:
            return True
        return False

    def __str__(self):
        return str(self.array)


class QueueStaticArray:
    def __init__(self, n):
        # to store n elements, construct [0...n]: storing n elements + 1 empty
        self.last_index = n
        self.queue = [float('-inf')] * (self.last_index + 1)
        self.head = 0
        self.tail = 0

    def empty(self):
        return self.head == self.tail

    def enqueue(self, element):
        if (self.head == 0 and self.tail == self.last_index) or\
                (self.tail + 1 == self.head):
            raise Exception('queue overflow')

        self.queue[self.tail] = element
        if self.tail == self.last_index:
            self.tail = 0
        else:
            self.tail += 1

    def dequeue(self):
        if self.empty():
            raise Exception('queue underflow')
        top = self.queue[self.head]
        if self.head == self.last_index:
            self.head = 0
        else:
            self.head += 1
        return top

    def __str__(self):
        if self.head <= self.tail:
            return str(self.queue[self.head:self.tail])
            # excluding self.tail which is not part of queue
        else:
            return str(self.queue[self.head:] + self.queue[:self.tail])
            # excluding self.tail which is not part of queue


if __name__ == '__main__':
    q = QueueStaticArray(4)
    #q = QueueDynamicArray()
    print(q.empty())
    print('enqueuing...')
    q.enqueue('a')
    print(q)
    q.enqueue('b')
    print(q)
    q.enqueue('c')
    print(q)
    q.enqueue('d')
    print(q)
    print('queue is full w 1 empty left, head & tail:', q.head, q.tail)
    #q.enqueue('e') # overflow
    print('dequeuing...')
    print(q.dequeue())
    print(q)
    print('head & tail:', q.head, q.tail)
    print(q.dequeue())
    print(q)
    print('head & tail:', q.head, q.tail)
    q.enqueue('e')
    print(q)
    print('head & tail:', q.head, q.tail)
    q.enqueue('f')
    print(q)
    print(q.queue)
    print('b is not part of queue anymore')
    print('head & tail:', q.head, q.tail)
    #q.enqueue('g') # overflow
