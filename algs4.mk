# master Makefile for Algorithms 4th Edition class
# Author: Mark Pauley
# no copyright, this file is yours to use as you wish!

allObjects := $(assignmentObjects) $(otherObjects)
assignmentJavaFiles = $(patsubst %,%.java,$(assignmentObjects))
assignmentClassFiles = $(patsubst %,%.class,$(assignmentObjects))
allJavaFiles = $(patsubst %,%.java,$(allObjects))
allClassFiles = $(patsubst %,%.class,$(allObjects))

testJavaFiles = $(patsubst %,%.java,$(testObjects))
testClassFiles = $(patsubst %,%.class,$(testObjects))

classpath = ./:../stdlib.jar:../algs4.jar
testclasspath = $(classpath):../junit-4.11/junit-4.11.jar:../junit-4.11/hamcrest-core-1.3.jar

JAVA = java -classpath $(classpath)
JAVAC = javac -classpath $(testclasspath)
JUNIT = java -classpath $(testclasspath) org.junit.runner.JUnitCore

.PHONY: test checkstyle findbugs zip clean

all: $(allClassFiles) checkstyle findbugs test

%.class: %.java
	$(JAVAC) $<

%.test: %.class
	$(JAVA) $(patsubst %.test,%,$@)

checkstyle: $(assignmentJavaFiles)
	java -classpath "./:../checkstyle-5.5/*" com.puppycrawl.tools.checkstyle.Main -c ../checkstyle-5.5/checkstyle.xml $?

findbugs: $(assignmentClassFiles)
	../findbugs-2.0.1/bin/fb analyze -home ../findbugs-2.0.1/ -auxclasspath $(classpath) $?

zip: $(assignmentName).zip

$(assignmentName).zip: checkstyle findbugs $(assignmentJavaFiles)
	zip $(assignmentName).zip $(assignmentJavaFiles)

test: $(patsubst %,%.test,$(testObjects))

clean:
	rm -f *.class $(assignmentName).zip *~
