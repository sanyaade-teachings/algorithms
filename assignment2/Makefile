assignmentObjects = Deque RandomizedQueue Subset
testObjects = Deque RandomizedQueue Subset
assignmentName = queues

include ../algs4.mk

Deque.test: Deque.class DequeTest.class
	$(JUNIT) DequeTest

RandomizedQueue.test: RandomizedQueue.class RandomizedQueueTest.class
	$(JUNIT) RandomizedQueueTest

Subset.test: Subset.class
	echo AA BB BB BB BB BB CC CC | $(JAVA) Subset 8
	echo A B C D E F G H I | $(JAVA) Subset 3
	echo A B | $(JAVA) Subset 2

