package com.fcz.algorithm.sort;

/**
 * 希尔排序
 * @author FCZ
 * @since 2019/2/26 17:30
 */
public class ShellSort {

    /**
     * description:
     * 插入排序的基本方法是：每步将一个待排序的元素，按其排序码大小插入到前面已经排好序的一组元素的适当位置上去，直到元素全部插入为止。
     * @param a:
     * @author FCZ
     * @since 2019-02-26
     */
    private void sort(int[] a) {
        int h = 1;
        int N = a.length;
        while (h < N/3)
            h = 3*h + 1;
        while (h >= 1) {
            // 将数组变成h有序
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && a[j] < a[j - h] ; j++) {
                    int temp = a[j];
                    a[j] = a[j - h];
                    a[j - h] = temp;
                }
            }
            h = h / 3;
        }
    }

}
