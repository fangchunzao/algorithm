package com.fcz.algorithm.map;

/**
 * @author FCZ
 * @since 2019/3/26 16:32
 * 由2-3树 演变成的红黑树
 *  （1）根节点是黑色
 *  （2）红链接均为左链接。
 *  （3）没有任何一个结点同时和两条红链接相连。
 *  （4）该树是完美黑色平衡，即任意空链接到根结点的路径上的黑链接数量相同。
 *
 * 普通红黑树
 *  （1）每个节点或者是黑色，或者是红色。
 *  （2）根节点是黑色。
 *  （3）每个叶子节点（NIL）是黑色。 [注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！]
 *  （4）如果一个节点是红色的，则它的子节点必须是黑色的。
 *  （5）从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
 *
 *  2-3树演变出的红黑树 增加了一个条件 就是 红链接均为左链接。便于代码的维护，如果没有该限制，维护的情况增加
 */
public class RBTree<Key extends Comparable<Key>, Value>  {

    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value value;
        Node left, right;  // 左右子树
        int N;  // 这颗子树的节点总数
        boolean color; // 父节点指向它的链接的颜色

        Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }


    }
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }


    // 当红色链接在右侧时的左旋转
    private Node rotateLeft(Node h) {
        // 换节点位置
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        rotate(x,h);
        return x;
    }
    // 当红色链接在左侧时的右旋转
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        rotate(x,h);
        return x;
    }
    // 变色 以及 计算 子结点数
    private void rotate(Node x, Node h) {
        // 变色
        x.color = h.color;
        h.color = RED;
        // 重新计算子树的结点数
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
    }
    // 转换一个结点的两个红色子结点颜色
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        // 根节点初始化为黑色
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value value) {
        if (h == null)  // 没有节点 创建新节点 新节点均为红结点
            return new Node(key,value,1,RED);
        // 计算插入的key 与 当前节点的位置
        int cmp = key.compareTo(h.key);
        // 插入key 小于 当前节点 递归向左寻找
        if (cmp < 0) h.left = put(h.left, key, value);
        // 插入key 大于 当前节点 递归向右寻找
        if (cmp > 0) h.right = put(h.right, key, value);
        // 相同 替换值
        else h.value = value;
        /*
          根据节点的颜色进行旋转
          1. 如果右子结点是红色而左子结点是黑色的 进行左旋转
          2. 如果左子结点是红色且它的左子结点也是红色 进行右旋转
          3. 如果左右节点均为红色 进行颜色转换
         */
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.right)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        // 重新计算结点数量
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }


}
