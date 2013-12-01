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
        Bag<Integer> vBag = new Bag<Integer>();
        Bag<Integer> wBag = new Bag<Integer>();
        vBag.add(v);
        wBag.add(w);
        return length(vBag, wBag);
    }

    // a common ancestor of v and w 
    //  that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        Bag<Integer> vBag = new Bag();
        Bag<Integer> wBag = new Bag();
        vBag.add(v);
        wBag.add(w);
        return ancestor(vBag, wBag);
    }

    // length of shortest ancestral path 
    //  between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int i = -1;
        // we start at the first point, so this will yield
        //  a path with one vertex as 0 length
        // coincidentally it works well for no path
        Iterable<Integer> shortestPath = sap(v, w);
        if (shortestPath != null) {
            for (int j : shortestPath) {
                i++;
            }
        }
        return i;
    }

    private SET<Integer> ancestors(Iterable<Integer> v) {
        SET<Integer> ancestors = new SET<Integer>();
        Queue<Integer> q = new Queue<Integer>();
        for (int u : v) {
            q.enqueue(u);
            ancestors.add(u);
        }
        while (!q.isEmpty()) {
            int curAncestor = q.dequeue();
            for (int n : G.adj(curAncestor)) {
                if (!ancestors.contains(n)) {
                    ancestors.add(n);
                    q.enqueue(n);
                }
            }
        }
        return ancestors;
    }
    
    private Iterable<Integer> sap(Iterable<Integer> u,
                                  Iterable<Integer> w) {
        // 1) get a list of all ancestors common to u and v
        //  store this in ancestors. O(V + E)
        SET<Integer> uAncestors = ancestors(u);
        SET<Integer> wAncestors = ancestors(w);
        SET<Integer> ancestors = uAncestors.intersects(wAncestors);
        
        // 2) find the shortest path from any ancestor to w (do BFS on G_rev)
        //   O(V + 2E)
        
        // Make a copy of G, add shortest upward paths from u to ancestors
        BreadthFirstDirectedPaths ancestorBFS
            = new BreadthFirstDirectedPaths(G, u);
        
        Digraph gRevAndAncestors = new Digraph(gRev);
        for (int v : ancestors) {
            // add the shortest path to each of the ancestors
            //  to gRevAndAncestors
            Iterable<Integer> ancestorPath;
            ancestorPath = ancestorBFS.pathTo(v);
            int curVertex = -1;
            for (int nextVertex : ancestorPath) {
                if (curVertex >= 0) {
                    gRevAndAncestors.addEdge(curVertex, nextVertex);
                }
                curVertex = nextVertex;
            }
        }
        
        // Find the destination with the shortest path from u
        //  to w O(V)
        int minDistance = Integer.MAX_VALUE;
        Iterable<Integer> path = null;
        BreadthFirstDirectedPaths bfs 
            = new BreadthFirstDirectedPaths(gRevAndAncestors, u);
        for (int dest : w) {
            int distance = bfs.distTo(dest);
            if (distance < minDistance) {
                minDistance = distance;
                path = bfs.pathTo(dest);
            }
        }
        return path;
    }
    
    // a common ancestor that participates 
    //  in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int lastVertex = -1;
        Iterable<Integer> shortestPath = sap(v, w);
        if (shortestPath != null) {
            for (int i : shortestPath) {
                // we know we're the common ancestor
                // because we've started to go upstream
                if (lastVertex >= 0) {
                    for (int j : gRev.adj(lastVertex)) {
                        if (j == i) return lastVertex;
                    }
                }
                lastVertex = i;
            }
            // At this point, the ancestor must have been in G, 
            //  and is therefore the last thing in the path (lastVertex)
        }
        return lastVertex;
    }
}

