package com.mahao.linkedlist.Linked;

/**
 * Created by mahao on 2018/2/9.
 */

public class LinkMain {

    public static void main(String[] args){

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0,4);
        linkedList.add(7);
        linkedList.add(6);
        linkedList.add(3);
        linkedList.add(9);
        linkedList.add(2);
        for(int i =0; i < linkedList.size;i++){
            System.out.print(i +":" + linkedList.get(i) +"...");
        }
    }
}
