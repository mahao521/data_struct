package com.mahao.linkedlist.sort;

import android.app.ApplicationErrorReport;

/**
 * Created by mahao on 2018/4/22.
 */

public class HeapSort {

    /**
     *     创建一个堆  特点就是一个满二叉树
     *     从最后一个父节点创建---保证创建的一个二叉树，根节点一定最小。
     * @param array
     * @param n   堆中共有多少个数据
     * @param k   准备进行筛选的节点
     */
    public static void createHeap(int[] array,int n,int k){

        int kleft = 2*k + 1;  //左孩子
        int kright = 2*k + 2; //右孩子
        if(kleft >= n && kright >= n){ //判断有没有子节点
            return;
        }
        int kLeftValue = Integer.MAX_VALUE;
        int kRightValue = Integer.MAX_VALUE;
        if(kleft < n){
            kLeftValue = array[kleft];
        }
        if(kright < n){
            kRightValue = array[kright];
        }
       //从最上向下递归
        //三个节点开始比较大小
        if(array[k] >= kLeftValue && array[k] >= kRightValue){
            return ;
        }
        if(kLeftValue < kRightValue){//左子树

            int temp = array[k];
            array[k] = array[kright];
            array[kright] = temp;
            createHeap(array,n,kright);


        }else{   //右子树
            int temp = array[k];
            array[k] = array[kleft];
            array[kleft] = temp;
            createHeap(array,n,kleft);  //主要用于递归 ，可以调整之前根节点的最小值， 最后，节点的位置一定这个节点二叉树里面最小的。

        }
    }

    /**
     *   堆排序
     * @param array
     */
    public static void heapSort(int[] array){

        //从最后一个非叶子节点开始建树
        for(int i = (array.length-1)/2; i >= 0 ;i--){

            createHeap(array,array.length,i);
        }
        //开始输出对顶。边调整堆
        int n = array.length;
        while(n>0){
            //根取出
            System.out.print(array[0]+" ");
            //最后一个节点放在根上
            array[0] = array[n-1];

            //重新调整
            createHeap(array,n,0);  //每次调整，保证所有的根节点都是最小的。。

            n--;
        }
    }

    public static void main(String[] args){
        int[] array = new int[]{6,3,9,2,4,5,1,8,7};
        heapSort(array);
    }
}



