/*************************************************************************
 * Name: Mark Pauley
 * Email: pauley@unsaturated.net
 *
 * Compilation:  javac SAP.java
 * Execution:
 * Dependencies: 
 *
 * Description: An API for the shortest ancestral path API
 *              Finds the common ancestor between two vertices in a Digraph
 *
 *************************************************************************/

public class SAP {

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {

    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return -1;
    }

    // a common ancestor of v and w 
    //  that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return -1;
    }

    // length of shortest ancestral path 
    //  between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return -1;
    }

    // a common ancestor that participates 
    //  in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return -1;
    }
}

