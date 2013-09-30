assignmentObjects = Board Solver
otherObjects = 
testObjects = Board Solver
assignmentName = 8puzzle

include ../algs4.mk

Board.test: Board.class BoardTest.class
	$(JUNIT) BoardTest

Solver.test: Solver.class Board.class
	$(JAVA) Solver 8puzzle/puzzle01.txt
	$(JAVA) Solver 8puzzle/puzzle03.txt
	$(JAVA) Solver 8puzzle/puzzle04.txt
	$(JAVA) Solver 8puzzle/puzzle05.txt
	$(JAVA) Solver 8puzzle/puzzle06.txt


