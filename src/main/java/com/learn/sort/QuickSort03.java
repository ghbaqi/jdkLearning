package com.learn.sort;

/**
 * @author gaohui
 * @date 2019/6/2 19:35
 * @description:  快速排序
 */
public class QuickSort03 {


    private int adjust(int arr[], int left, int right) {

        int i = left;
        int j = right;
        int temp = arr[i];   // 挖坑  i 位置

        while (i < j) {

            while (i < j && temp < arr[j])  // 从 j 位置 ，从后往前找小的数
                j--;
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }

            while (i < j && temp > arr[i])  // 从 i 位置 ，从前往后找大的数
                i++;
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }

        }

        arr[i] = temp;

        return i;
    }

    private void quickSort(int arr[], int l, int r) {
        if (l < r) {
            int i = adjust(arr, l, r);
            quickSort(arr, l, i - 1);
            quickSort(arr, i + 1, r);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 0, 2, 5, 3, 4, 6, 6, 7, 7, 8, 3, 2, 2};
        new QuickSort03().quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

}
