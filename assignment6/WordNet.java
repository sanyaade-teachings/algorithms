/*************************************************************************
 * Name: Mark Pauley
 * Email: pauley@unsaturated.net
 *
 * Compilation:  javac KdTree.java
 * Execution:
 * Dependencies: 
 *
 * Description: An implementation of the WordNet graph data type.
 *              This type is used to reasonabout symantic distance of nouns.
 *
 *************************************************************************/


public class WordNet {
    // This type is IMMUTABLE
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        
    }

    // the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns() {
        return null;
    }

    // is the word a WordNet noun?
    //  (is it contained in this word net?)
    public boolean isNoun(String word) {
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return 0;
    }

    // a synset (second field of synsets.txt) 
    //  that is the common ancestor of nounA and nounB
    //  in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return null;
    }

    // Unit tests live in WordNetTest.java
}
