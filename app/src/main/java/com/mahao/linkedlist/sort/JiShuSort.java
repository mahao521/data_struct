package com.mahao.linkedlist.sort;


import android.net.LinkAddress;
import android.service.voice.VoiceInteractionService;

import java.util.LinkedList;

/**
 * Created by mahao on 2018/4/22.
 */

public class JiShuSort {


    /**
     *   基数排序   ---- 麻将
     * @param list
     */
    public static void radixSort(LinkedList<Mahjong> list){

        //先对点数进行分组
        LinkedList[] rankList = new LinkedList[9];
        for(int i = 0;i < rankList.length; i++){  //数组的每一个元素是一个链表   用于排序、
            rankList[i] = new LinkedList();
        }
        //把数据一个个放入数组中
        while(list.size() > 0){
            //取出一个
            Mahjong mahjong  = list.remove();
            //放入数组中
            rankList[mahjong.rank-1].add(mahjong);
        }
        //把9个组合到一起  串起来
        for(int i = 0; i < rankList.length;i++){
            list.addAll(rankList[i]);
        }

        //先对点数进行分组
        LinkedList[] suitList = new LinkedList[3];
        for(int i = 0;i < suitList.length; i++){
            suitList[i] = new LinkedList();
        }
        //把数据一个个放入数组中
        while(list.size() > 0){
            //取出一个
            Mahjong mahjong  = list.remove();
            //放入数组中
            suitList[mahjong.suit-1].add(mahjong);
        }
        //把3个组合到一起  串起来
        for(int i = 0; i < suitList.length;i++){
            list.addAll(suitList[i]);
        }
    }


    public static class Mahjong{

        public int suit; //花色，筒 条
        public int rank; //点数 1 2 3

        public Mahjong(int suit,int rank){
            this.suit = suit;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Mahjong{" +
                    "suit=" + suit +
                    ", rank=" + rank +
                    '}';
        }
    }

    public static void main(String[] args){

        LinkedList<Mahjong> list = new LinkedList<>();
        list.add(new Mahjong(3,1));
        list.add(new Mahjong(2,3));
        list.add(new Mahjong(3,7));
        list.add(new Mahjong(1,1));
        list.add(new Mahjong(3,8));
        list.add(new Mahjong(2,2));
        list.add(new Mahjong(3,2));
        list.add(new Mahjong(1,3));
        list.add(new Mahjong(3,9));
        System.out.println(list);
        radixSort(list);
        System.out.println(list);
    }
}












