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
    private Digraph G;
    private Digraph gRev;
    
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        // O(E)
        this.G = new Digraph(G);

        // O(E)
        gRev = this.G.reverse();
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        // 1) get a list of all ancestors of v
        //  store this in ancestors. O(V + E)
        ST<Integer, Integer> ancestors = new ST<Integer, Integer>();
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(v);
        ancestors.put(v, 0);
        while (!q.isEmpty()) {
            int curAncestor = q.dequeue();
            int ancestorDistance = ancestors.get(curAncestor);
            for (int n : G.adj(curAncestor)) {
                if (!ancestors.contains(n)) {
                    ancestors.put(n, ancestorDistance + 1);
                    q.enqueue(n);
                }
            }
        }
        
        // 2) find the shortest path from rootPath to w (do BFS on G_rev)
        //   O(V + E)
        BreadthFirstDirectedPaths bfs 
            = new BreadthFirstDirectedPaths(gRev, ancestors);
        
        if (!bfs.hasPathTo(w)) {
            return -1;
        }
        else {
            return bfs.distTo(w) 
                + ancestors.get(bfs.pathTo(w).iterator().next());
        }
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

