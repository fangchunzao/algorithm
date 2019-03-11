package com.fcz.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author FCZ
 * @since 2019/2/27 10:05
 */
public class MergeSort {

    /**
     * 将两个有序的数组进行归并
     * 原地归并
     * 先将数组内容全部复制到aux中，zux用于进行数据的大小判断，赋值使用
     * 再对数组a 进行原地归并，有四种条件判断：左半边
     * @param a 数组
     * @param left 起点
     * @param mid 中点
     * @param right 终点
     */
    private void sort1(int a[], int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        // 进行数组判断
        int[] aux = new int[a.length];
        for (int k = left; k <= right; k++) {
            aux[k] = a[k];
        }
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > right) {
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]){
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    /**
     * description: 自顶向下的归并排序
     * 当数组过大，会产生很多的小数组排序
     * 使用分治的思想
     * @author FCZ
     * @since 2019-02-27
     */
    private void sort2(int[] a, int low, int high) {
        // 将数组从low-high排序
        if (low >= high) return;
        int mid = low + (high -low) / 2;
        // 左半边排序
        sort2(a,low,mid);
        // 右半边排序
        sort2(a,mid + 1, high);
        // 使用之前的归并
        sort1(a,low,mid,high);
    }

    /**
     * description: 自顶向上的归并排序
     * 会多次遍历整个数组，根据子数组的大小进行两两归并。
     * 子数组的大小sz的初始值为1，每次加倍
     * @param :
     * @author FCZ
     * @since 2019-02-27
     */
    private int aux[]; //辅助数组
    private void sort3(int[] a) {
        int N = a.length;
        aux = a.clone();
        // 遍历数组，分割成sz大小的子数组 每次翻倍
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N-sz; lo += sz+sz) {
                sort1(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
    }


}
