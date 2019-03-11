package com.fcz.algorithm.sort;

import java.util.Arrays;

/**
 * 三向切分的快速排序
 * @author FCZ
 * @since 2019/2/28 09:53
 */
public class Quick3waySort {


    public static void main(String[] args) {
        int[] a = {3,8,5,9,20,2,8,5,2,7,11,6,5};
        sort(a,0,a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    /**
     * description: 从左到右遍历数组，维护一个指针lt，使得a[low...lt-1]的元素小于v，
     * 一个指针gt使得a[gt+1...high]中的元素都大于V
     * 一个指针i指的a[lt...i-1]的元素等于v
     * a[i...gt]中的元素未确定
     * @param a: 数组
     * @param low: 低位
     * @param high: 高位
     * @author FCZ
     * @since 2019-02-28
     */
    private static void sort(int[] a, int low, int high) {
        if (high <= low) return;
        int lt = low, i = low + 1, gt = high;
        int v = a[low];
        while (i <= gt) {
            int cmp = Integer.valueOf(a[i]).compareTo(v);
            if (cmp < 0) { // a[i] < v
                CommonUtils.exch(a,lt++,i++);
            } else if(cmp > 0) { // a[i] > v
                CommonUtils.exch(a,i,gt--);
            } else { // a[i] == v
                i++;
            }
            sort(a,low, lt-1);
            sort(a, gt + 1, high);

        }
    }
}
