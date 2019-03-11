package com.fcz.algorithm.sort;

/**
 * @author FCZ
 * @since 2019/2/28 10:31
 */
public class CommonUtils {

    public static boolean less(int value1, int value2) {
        return value1 < value2;
    }

    public static void exch(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void exch( int i, int j) {
    }


}
