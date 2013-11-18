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
    private class Def {
        private int id;
        private String word;
        private String gloss;

        public Def(int id, String word, String gloss) {
            this.id = id;
            this.word = word;
            this.gloss = gloss;
        }
        
        int getID() {
            return id;
        }
        
        String getWord() {
            return word;
        }

        String getGloss() {
            return gloss;
        }
    }
    
    private ST<Integer, Def> idToDef;
    private ST<String, Def> wordToDef;
    private Digraph wordGraph;
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In synsetIn = new In(synsets);
        readDefs(synsetIn);
        In hypernymsIn = new In(hypernyms);
        readEdges(hypernymsIn);
    }

    private void readDefs(In in) {
        idToDef = new ST<Integer, Def>();
        wordToDef = new ST<String, Def>();
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            Def newDef = new Def(Integer.parseInt(fields[0]),
                                 fields[1], fields[2]);
            idToDef.put(newDef.getID(), newDef);
            wordToDef.put(newDef.getWord(), newDef);
        }
        wordGraph = new Digraph(idToDef.size());
    }

    private void readEdges(In in) {
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            int rootID = Integer.parseInt(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                wordGraph.addEdge(Integer.parseInt(fields[i]), rootID);
            }
        }
    }

    // the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns() {
        return wordToDef.keys();
    }

    // is the word a WordNet noun?
    //  (is it contained in this word net?)
    public boolean isNoun(String word) {
        return wordToDef.contains(word);
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
