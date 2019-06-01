package com.learn.sort;

/**
 * @author gaohui
 * @date 2019/5/29 19:26
 * @description: 挖坑填数 + 分而治之
 */
public class QuickSort02 {

    public int adjust(int[] arr, int l, int r) {

        int i = l;
        int j = r;
        int x = arr[l];

        while (i < j) {

            // j 从右向左 找小的数
            while (i < j && arr[j] >= x)
                j--;
            if (i < j) {
                arr[i] = arr[j];        // 填坑
                i++;
            }

            // i 从前向后 找大的数
            while (i < j && arr[i] < x)
                i++;
            if (i < j) {
                arr[j] = arr[i];       // 填坑
                j--;
            }
        }

        // 退出时  i=j , 将 x 填到这个坑中
        arr[i] = x;

        return i;
    }

    void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int i = adjust(arr, l, r);
            quickSort(arr, l, i - 1);
            quickSort(arr, i + 1, r);

        }
    }
}

















