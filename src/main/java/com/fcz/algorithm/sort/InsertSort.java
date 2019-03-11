package com.fcz.algorithm.sort;

/**
 * 插入排序
 * @author FCZ
 * @since 2019/2/26 17:17
 */
public class InsertSort {

    /**
     * description:
     * 类似于整理扑克牌
     * 将每一个元素插入已经有序的数组中的适当位置
     * 需要将其余所有预算向右移动一位
     *
     * 对于部分有序的数组 很高效
     * @param a:
     * @author FCZ
     * @since 2019-02-26
     */
    private void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }

}
