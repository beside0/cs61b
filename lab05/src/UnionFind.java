public class UnionFind {
    // TODO: Instance variables

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    private  int[] disjoint;
    public UnionFind(int N) {
        disjoint=new int[N];
        for(int i=0;i<N;i++){
            disjoint[i]=-1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        v=find(v);
        return -disjoint[v];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return disjoint[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        int v11=find(v1);
        int v12=find(v2);
        return v11==v12;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v < 0 || v >= disjoint.length) {
            throw new IllegalArgumentException("IllegalArgument!");
        }
        if (parent(v) < 0) {
            return v;
        } else {
            disjoint[v] = find(parent(v));
            return parent(v);
        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        int v11=find(v1);
        int v12=find(v2);
        if(v11==v12){
            return;
        }
        if(sizeOf(v11)>sizeOf(v2)){
            disjoint[v11]+=disjoint[v12];
            disjoint[v12]=v11;
        }
        else if(sizeOf(v11)<=sizeOf(v2)) {
            disjoint[v12] += disjoint[v11];
            disjoint[v11] = v12;
        }
    }


}
