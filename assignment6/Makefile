assignmentObjects = WordNet SAP Outcast
otherObjects = 
testObjects = WordNet SAP Outcast
assignmentName = wordnet

include ../algs4.mk

WordNet.test: WordNet.class SAP.class WordNetTest.class
	$(JUNIT) WordNetTest

SAP.test: SAP.class SAPTest.class
	$(JUNIT) SAPTest

Outcast.test: SAP.class Wordnet.class Outcast.class
	$(JAVA) Outcast synsets.txt hypernyms.txt wordnet/outcast5.txt wordnet/outcast8.txt wordnet/outcast11.txt
