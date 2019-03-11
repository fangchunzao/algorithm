package com.fcz.algorithm.sort;

import java.util.Arrays;

/**
 * @author FCZ
 * @since 2019/2/27 17:26
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {8,5,9,20,2,8,5,2,7,11,6,5};
        mySort(a,0,a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    private static void mySort(int[] a, int low, int high) {
        if (high <= low) return;
        int i = low, j = high + 1;
        int t = a[low];

        while (i<j) {
            // 从左往右找大的
            while (a[++i] < t)
                if (i == high) break;
            // 从右往左找小的
            while (a[--j] > t)
                if (j == low) break;
            // 如果i > j 说明已经越界 跳出
            if (i >= j) break;
            // 替换大小元素的位置
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        // 从J的位置 分割数组 可以确保小的数字都是在左边
        int temp = a[low];
        a[low] = a[j];
        a[j] = temp;
        mySort(a,low,j - 1);
        mySort(a,j + 1,high);
    }

    private void sort(int a[]) {
        sort(a,0, a.length - 1);
    }

    private void sort(int a[], int low, int high) {
        if (high <= low) return;
        int j = partition(a,low, high);
        sort(a, low, j-1);
        sort(a, j+1, high);
    }

    private int partition(int a[], int low, int high) {
        int i =low,j = high + 1;  //左右指针
        int v = a[low];  // 分割元素
        while (true) {
            while (a[++i] < v)
                if (i == high) break;
            while (v < a[--j])
                if (j == low) break;
            if (i >= j) break;
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        // 切割元素放入中间
        int temp = a[low];
        a[low] = a[j];
        a[j] = temp;
        return j;
    }

}
