allObjects := $(assignmentObjects) $(otherObjects)
assignmentJavaFiles = $(patsubst %,%.java,$(assignmentObjects))
assignmentClassFiles = $(patsubst %,%.class,$(assignmentObjects))
allJavaFiles = $(patsubst %,%.class,$(allObjects))
allClassFiles = $(patsubst %,%.class,$(allObjects))

all: $(allClassFiles) checkstyle findbugs test

%.class: %.java
	javac -classpath ./:../stdlib.jar:../algs4.jar $<

checkstyle: $(assignmentJavaFiles)
	java -classpath "./:../checkstyle-5.5/*" com.puppycrawl.tools.checkstyle.Main -c ../checkstyle-5.5/checkstyle.xml $(assignmentJavaFiles)

findbugs: $(assignmentClassFiles)
	../findbugs-2.0.1/bin/fb analyze -home ../findbugs-2.0.1/ -auxclasspath ./:../stdlib.jar:../algs4.jar $(assignmentClassFiles)

zip: $(assignmentName).zip

$(assignmentName).zip: checkstyle findbugs
	zip $(assignmentName).zip Percolation.java PercolationStats.java

clean:
	rm -f *.class $(assignmentName).zip *~ 
