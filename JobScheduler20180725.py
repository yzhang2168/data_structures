# Python3
"""
Yun Zhang, yzhxdy@gmail.com
July 2018

uses 𝑛 independent threads to process the given list
of 𝑚 jobs. Threads take jobs in the order they are given in the input. If there is a free thread,
it immediately takes the next job from the list. If a thread has started processing a job, it doesn’t
interrupt or stop until it finishes processing the job. If several threads try to take jobs from the list
simultaneously, the thread with smaller index takes the job.
"""
import time


class Job:
    def __init__(self, index, process_time):
        self.index = index
        self.process_time = process_time
        self.start_time = None
        self.finish_time = None
        self.assigned_thread = None

    def __str__(self):
        s = 'index ' + str(self.index) + '; process_time ' + str(self.process_time) + \
            '; start_time ' + str(self.start_time) + '; finish_time ' + str(self.finish_time) + \
            '; assigned_thread ' + str(self.assigned_thread)
        return s


class Thread:
    def __init__(self, index):
        self.index = index

    def __str__(self):
        s = 'index ' + str(self.index)
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
        if left < self.size:
            if self.array[left].finish_time < self.array[i].finish_time:
                smallest = left
            # if multiple jobs finish at the same time, sort by thread index
            if self.array[left].finish_time == self.array[i].finish_time:
                if self.array[left].assigned_thread < self.array[i].assigned_thread:
                    smallest = left
        if right < self.size:
            if self.array[right].finish_time < self.array[smallest].finish_time:
                smallest = right
            elif self.array[right].finish_time == self.array[smallest].finish_time:
                if self.array[right].assigned_thread < self.array[smallest].assigned_thread:
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
        while i > 0 and self.array[self.parent(i)].finish_time > self.array[i].finish_time:
            self.array[self.parent(i)], self.array[i] = self.array[i], self.array[self.parent(i)]
            i = self.parent(i)

    def __str__(self):
        first = True
        for obj in self.array:
            if first:
                first = False
                s = str(obj)
            else:
                s += '\n' + str(obj)
        return s


class JobScheduler:
    def __init__(self):
        self.jobs = [] # 0...m-1 Job objects
        self.threads = [] # 0...n-1 Thread objects

    def read(self):
        self.num_threads, self.num_jobs = map(int, input().split())
        self.job_process_times = list(map(int, input().split()))
        # assert statement: Python evaluates the accompanying expression, which is hopefully true.
        # If the expression is false, Python raises an AssertionError exception.
        assert self.num_jobs == len(self.job_process_times)
        for i in range(self.num_jobs):
            self.jobs.append(Job(i, self.job_process_times[i]))
        for j in range(self.num_threads):
            self.threads.append(Thread(j))

    def schedule_jobs(self):
        min_heap_jobs = MinHeap()
        # starting cycle - all min(threads, jobs) are scheduled
        starting_num = min(self.num_jobs, self.num_threads)
        for i in range(starting_num):
            self.jobs[i].assigned_thread = self.threads[i].index
            self.jobs[i].start_time = 0
            self.jobs[i].finish_time = self.jobs[i].start_time + self.jobs[i].process_time
            #min_heap_jobs.add_object(self.jobs[i]) # sift_up_object() by finish_time
        min_heap_jobs.build_heap_from_array(self.jobs[:starting_num])

        # if all threads are occupied and there are more jobs, continue scheduling
        if self.num_jobs > self.num_threads:
            for j in range(starting_num, self.num_jobs):
                finished_job = min_heap_jobs.extract_min()  # min_heapify()/sift_down_object
                self.jobs[j].assigned_thread = finished_job.assigned_thread
                self.jobs[j].start_time = finished_job.finish_time
                self.jobs[j].finish_time = self.jobs[j].start_time + self.jobs[j].process_time
                min_heap_jobs.add_object(self.jobs[j])  # sift_up_object()

    def write_to_console(self):
        for i in range(self.num_jobs):
            print(self.jobs[i].assigned_thread, self.jobs[i].start_time)


if __name__ == '__main__':
    job_queue = JobScheduler()
    job_queue.read()
    #time0 = time.time()
    job_queue.schedule_jobs()
    #time1 = time.time()
    '''
    print('processing time:', str(time1 - time0))

    print('------threads------')
    for t in job_queue.threads:
        print(t)
    print('------heap------')
    for j in job_queue.jobs:
        print(j)
    print('------output for homework-------')
    '''
    job_queue.write_to_console()