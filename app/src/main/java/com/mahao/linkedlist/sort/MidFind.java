package com.mahao.linkedlist.sort;

import android.preference.PreferenceActivity;
import android.support.annotation.IntegerRes;
import android.support.v4.util.LogWriter;

import java.util.Arrays;

/**
 * Created by mahao on 2018/4/14.
 */

public class MidFind {


    /**
     *   二分法查找非递归方式
     * @param arr
     * @param left
     * @param right
     * @param key
     * @return
     */
    public static int  binarySearch(int[] arr, int left , int right, int key){

        int low = left;
        int hight = right-1;
        while(low <= hight){

            int mid = (low + hight)/2;
            if(arr[mid] == key){
                return mid;
            }else if(arr[mid] > key){

                hight = mid-1;
            }else if(arr[mid] < key){
                low = mid + 1;
            }
        }
        return -(low+1);
    }


    /**
     *    二分法非递归调用方式
     * @param arr    递归return值是会传递的。
     * @param left
     * @param right
     * @param key
     * @return
     */
    public static int binarySearch2(int[] arr,int left,int right,int key){

        if(left > right){
            return -1;
        }
        int mid = (left + right)/2;
        if(arr[mid] == key){
               return mid;
        }else if(arr[mid] < key){
               return  binarySearch2(arr,mid+1,right,key);
        }else{
               return binarySearch2(arr,left,mid-1,key);
        }
    }


    /**
     *   快速排序是先序遍历    汉诺塔是中序遍历  归并排序是后续遍历
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static void quickSort(int[] arr,int left,int right){

        int low = left;
        int high = right;
        int index = arr[left];
        if(left < right){ //递归结束标志

            while(low < high){  //进行一趟排序，结束的标志 low < high

                while(arr[high] > index && low < high){
                    high--;
                }
                if(low < high){
                    arr[low] = arr[high];
                    low++;
                }
                while(arr[low] <= index && low < high){
                    low++;
                }
                if(low < high){
                    arr[high] = arr[low];
                    high--;
                }
            }
            arr[low] = index;
            System.out.println(Arrays.toString(arr));
            quickSort(arr,left,low-1);
            quickSort(arr,low+1,right);
        }
    }


    /**
     *   归并排序   拆分思想
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] arr,int left ,int mid ,int right){

        int leftSize = mid-left;
        int rightSize = right -mid +1;

        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];
        for(int i = left; i < mid; i++){
            leftArr[i-left] = arr[i];
        }
        for(int j = mid; j <= right; j++){
            rightArr[j-mid] = arr[j];
        }
        //合并数组
        int i= 0;
        int j = 0;
        int k = left;
        while(i < leftSize && j < rightSize){

            if(leftArr[i] < rightArr[j]){
                arr[k] = leftArr[i];
                k++;
                i++;
            }else {
                arr[k] = rightArr[j];
                k++;
                j++;
            }
        }
        while (i < leftSize){
            arr[k] = leftArr[i];
            k++;
            i++;
        }
        while (j < rightSize){
            arr[k] = rightArr[j];
            k++;
            j++;
        }
    }


    /**
     *    归并排序   解决了快速排序，如果是链表的话，快速排序效率低下。链表替换慢，查找慢；
     *
     *    归并排序是后续遍历的思想。
     *
     *    归并排序是分治法，细化到2个一组。然后合并----4个一组在合并---8个一组在合并。
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right){

        if(left == right){  //递归终止条件。
            return;
        }else {
            int mid = (left + right)/2;
            mergeSort(arr,left,mid);            //排好左边  左边2个合并结束 --- 4
            mergeSort(arr,mid+1,right);    //排好右边   右边2个合并结束 ---  4
            merge(arr,left,mid+1,right);   //合并左右   左右2个合并结束 ---- 8
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void  main(String[] args){

        int[] array = new int[]{1,3,5,7,9,4,10,8};
        //mergeSort(array,0,array.length-1);
         quickSort(array,0,array.length-1);
        System.out.println(binarySearch(array,0,array.length,4));
        System.out.println(binarySearch2(array,0,array.length,10));
    }

}
