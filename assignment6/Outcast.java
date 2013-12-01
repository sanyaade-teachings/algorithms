/*************************************************************************
 * Name: Mark Pauley
 * Email: pauley@unsaturated.net
 *
 * Compilation:  javac Outcast.java
 * Execution:
 * Dependencies: Wordnet SAP
 *
 * Description: An API using the wordnet datatype for finding an outlier
 *              among nouns in the wordnet
 *
 *************************************************************************/


public class Outcast {
    // constructor takes a WordNet object
    private WordNet wordnet;
    
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int[][] distances = new int[nouns.length][];
        for (int i = 0; i < nouns.length; i++) {
            distances[i] = new int[nouns.length];
            for (int j = 0; j < nouns.length; j++) {
                distances[i][j] = -1;
            }
        }
        
        String theOutcast = null;
        int maxDistance = -1;
        for (int i = 0; i < nouns.length; i++) {
            int sumDistance = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (distances[i][j] < 0) {
                    distances[i][j] = wordnet.distance(nouns[i], nouns[j]);
                    distances[j][i] = distances[i][j];
                }
                sumDistance += distances[i][j];
            }
            if (sumDistance > maxDistance) {
                theOutcast = nouns[i];
                maxDistance = sumDistance;
            }
        }
        return theOutcast;
    }

    // for unit testing of this class (such as the one below)
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            String[] nouns = In.readStrings(args[t]);
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}