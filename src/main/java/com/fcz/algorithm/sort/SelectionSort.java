package com.fcz.algorithm.sort;

/**
 * 选择排序
 * @author FCZ
 * @since 2019/2/26 17:11
 */
public class SelectionSort {
    /**
     * description:
     * 首先找到数组中最小的元素 其次与数组第一个元素交换位置。
     * 然后找到下一个最小元素，与数组第二个元素交换位置
     * 需要 N^2/2 次比较 和N次交换
     * @author FCZ
     * @since 2019-02-26
     */
    private static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min])
                    min = j;
            }
            int swap = a[i];
            a[i] = a[min];
            a[min] = swap;
        }
    }
}
