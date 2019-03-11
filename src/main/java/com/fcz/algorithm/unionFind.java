package com.fcz.algorithm;

/**
 * @author FCZ
 * @since 2019/2/26 16:23
 */
public class unionFind {

    private int[] id;
    private int count;

    public unionFind(){
    }

    public unionFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) id[i]=qID;
        }
        count--;
    }

}
