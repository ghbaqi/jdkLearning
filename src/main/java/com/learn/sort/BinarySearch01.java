package com.learn.sort;

/**
 * @author gaohui
 * @date 2019/6/2 20:04
 * @description: 二分查找法
 */
public class BinarySearch01 {

    public static void main(String[] args) {
        BinarySearch01 search = new BinarySearch01();
        int[] arr = new int[]{0, 1, 2, 3, 5, 6, 7, 8};
        System.out.println(search.search(arr, 8));

    }


    private int search(int[] arr, int val) {

        int low = 0;
        int high = arr.length - 1;

        int mid;
        while (low <= high) {

            mid = (high + low) / 2;
            //            mid = (high + low) / 2;
            if (val == arr[mid]) {
                return mid;
            } else if (val < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }


        return -1;
    }
}
