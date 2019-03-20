package ca.mcgill;

class TreeTraversal{

    private int[] id, sz;

    public TreeTraversal(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int p) {
        int root = p;
        while (root != id[root])
            root = id[root];
        while (p != root) { // Do path compression
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int size(int p) {
        return sz[find(p)];
    }

    public void union(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);
        if (root1 == root2) return;
        if (sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
        }
    }

}