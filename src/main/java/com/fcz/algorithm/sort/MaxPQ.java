package com.fcz.algorithm.sort;

/**
 * @author FCZ
 * @since 2019/2/28 11:48
 */
public class MaxPQ {

    private int[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = new int[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(int v) {
        pq[++N] = v;
        swim(N);
    }

    public int delMax() {
        int max = pq[1];
        CommonUtils.exch(1, N--);
        pq[N+1] = 0;
        sink(1);
        return max;
    }

    /**
     *  二叉堆 上浮
     * @param k
     */
    public void swim(int k) {
        // 父节点小于子节点时 节点上浮
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k,j)) break;
            exch(k,j);
            k = j;
        }
    }

    public void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public boolean less(int value1, int value2) {
        return value1 < value2;
    }
}
