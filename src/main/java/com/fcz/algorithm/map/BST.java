package com.fcz.algorithm.map;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author FCZ
 * @since 2019/3/11 14:49
 * 二叉查找树
 * put 与 get 均使用二分查找
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key){
        return get(root, key);
    }

    // 二分查找 找到相同的即取出
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if(cmp > 0) return get(x.right, key);
        else return x.value;
    }

    public void put(Key key, Value value) {
      root = put(root, key, value);
    }

    // 使用二分 查找到节点位置 然后重新计算N
    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key,value,1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if(cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 查找最小值
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    // 查找最大值
    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }
    // 查找与节点Key最接近的节点
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }
    // 二分查找 判断在树的左侧还是右侧
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        // 可能在树右侧
        Node t = floor(x.right, key);
        if (t != null) return t; // 右侧没有找到
        else return x;
    }

    // 找到排名为k的键
    public Key select(int k) {
        return select(root, k).key;
    }
    // 如果左子树中的节点数量大于k，从左侧数直接递归找到排名
    // ----------------------小于k， 找（左子树节点数+右子树的排名）
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if(t < k) return select(x.right, k-t-1);
        else return x;
    }
    // 返回指定键的排名
    public int rank(Key key) {
        return rank(key,root);
    }
    // 判断指定键在左子树还是右子树
    // 如果在左子树 直接计算当前的节点
    // 如果在右子树 计算 (左子树节点数+右子树节点数)
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key,x.left);
        else if(cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }
    // 删除节点
    // 1.找到节点位置x
    // 2.先使用临时变量t=x
    // 3.x= 右子树的最小节点
    // 4.x.left = deleteMin(t的右子树);
    // 5. x.left = t.left 完成节点删除 并且重新排序

    private Node delete(Node x, Key key) {
        if (x == null) return  null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if(cmp > 0) x.right = delete(x.right, key);
        else { // 二叉查找树 对节点的删除
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t= x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 查找区间内节点
    public Iterable<Key> keys() {
        return keys(min(),max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new PriorityQueue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);  // 在区间内
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
}
