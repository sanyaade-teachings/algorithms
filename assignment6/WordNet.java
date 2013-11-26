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
        private String synset;
        private String gloss;

        public Def(int id, String synset, String gloss) {
            this.id = id;
            this.synset = synset;
            this.gloss = gloss;
        }
        
        int getID() {
            return id;
        }
        
        String[] getWords() {
            return synset.split(" ");
        }

        String getSynset() {
            return synset;
        }

        String getGloss() {
            return gloss;
        }
    }
    
    private ST<Integer, Def> idToDef;
    private ST<String, SET<Integer>> wordToIDs;
    private Digraph wordGraph;
    private SAP wordNetSAP;
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In synsetIn = new In(synsets);
        readDefs(synsetIn);
        In hypernymsIn = new In(hypernyms);
        readEdges(hypernymsIn);
        wordNetSAP = new SAP(wordGraph);
    }

    private void readDefs(In in) {
        idToDef = new ST<Integer, Def>();
        wordToIDs = new ST<String, SET<Integer>>();
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            Def newDef = new Def(Integer.parseInt(fields[0]),
                                 fields[1], fields[2]);
            idToDef.put(newDef.getID(), newDef);

            for (String word : newDef.getWords()) {
                SET<Integer> idsForWord = wordToIDs.get(word);
                if (idsForWord == null) {
                    idsForWord = new SET<Integer>();
                    wordToIDs.put(word, idsForWord);
                }
                idsForWord.add(newDef.getID());
            }
        }
        wordGraph = new Digraph(idToDef.size());
    }

    private void readEdges(In in) {
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            int rootID = Integer.parseInt(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                wordGraph.addEdge(rootID, Integer.parseInt(fields[i]));
            }
        }
    }

    // the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns() {
        return wordToIDs.keys();
    }

    // is the word a WordNet noun?
    //  (is it contained in this word net?)
    public boolean isNoun(String word) {
        return wordToIDs.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (isNoun(nounA) && isNoun(nounB)) {
            printDefs(nounA);
            printDefs(nounB);
            return wordNetSAP.length(wordToIDs.get(nounA),
                                     wordToIDs.get(nounB));
        }
        return -1;
    }

    private void printDefs(String noun) {
        if (isNoun(noun)) {
            System.out.println("" + noun + "(n):");
            int defNum = 1;
            for (int i : wordToIDs.get(noun)) {
                Def nounDef = idToDef.get(i);
                System.out.println("" + defNum + ") " +  nounDef.getGloss());
                defNum++;
            }
        }
    }
    // a synset (second field of synsets.txt) 
    //  that is the common ancestor of nounA and nounB
    //  in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (isNoun(nounA) && isNoun(nounB)) {
            int ancestor = wordNetSAP.ancestor(wordToIDs.get(nounA),
                                               wordToIDs.get(nounB));
            return idToDef.get(ancestor).getSynset();
        }
        return null;
    }

    // Unit tests live in WordNetTest.java
}
