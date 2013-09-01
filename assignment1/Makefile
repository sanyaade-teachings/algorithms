all: Percolation.class PercolationStats.class PercolationVisualizer.class checkstyle test

%.class: %.java
	javac -classpath ./:../stdlib.jar:../algs4.jar $<

test: Percolation.class
	java -classpath ./:../stdlib.jar:../algs4.jar Percolation

checkstyle: PercolationStats.java Percolation.java
	java -classpath "./:../checkstyle-5.5/*" com.puppycrawl.tools.checkstyle.Main -c ../checkstyle-5.5/checkstyle.xml Percolation.java PercolationStats.java

findbugs: PercolationStats.java Percolation.java
	../findbugs-2.0.1/bin/fb

zip: checkstyle
	zip percolation.zip Percolation.java PercolationStats.java

clean:
	rm -f *.class *.zip *~ 
