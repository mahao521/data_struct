package com.mahao.linkedlist.sort;

import android.accounts.AbstractAccountAuthenticator;
import android.view.inputmethod.InputMethodSubtype;

import java.util.Arrays;

/**
 * Created by mahao on 2018/4/22.
 */

public class InsertSort {

    //直接插入排序
    public static void insertSort(int[] array){

        for(int i = 0; i < array.length; i++){

            int j = i;
            int target = array[i];
            while(j > 0 && target < array[j-1]){
                array[j] = array[j-1];
                j--;
            }
            array[j] = target;
        }
    }


    /**
     *    希尔排序 是插入排序的一个优化算法
     * @param array
     * @param step  步长  每一次步长都是一次插入排序
     */
    public static void xierSort(int[] array, int step){

        for(int k = 0; k < step; k++){

            for(int j = k+step; j < array.length; j= j + step){

                int i = j;
                int target = array[j];
                while(i > step-1 && target < array[i-step]){

                    array[i] = array[i-step] ; //将大的向后面移动一个位置
                    i = i - step;
                }
                array[i] = target;
            }
        }
    }

    public static void main(String[] args){

        int[] array = new int[]{3,9,1,2,5,4,7,8,6};
        //insertSort(array);
       // System.out.println(Arrays.toString(array));

        xierSort(array,3);
        System.out.println(Arrays.toString(array));
        xierSort(array,1);
        System.out.println(Arrays.toString(array));
    }
}
