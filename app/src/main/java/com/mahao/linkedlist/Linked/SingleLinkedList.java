package com.mahao.linkedlist.Linked;

/**
 * Created by mahao on 2018/3/10.
 */

public class SingleLinkedList<T> {


    //单链表--头插法
    private Node<T> head;
    public void add(T newItem){
        Node<T> node = new Node<>(newItem);
        if(head == null){
            head = node;
        }else {
            Node<T> tail = head;
            while (tail.next != null){
                tail = tail.next;
            }
            tail.next = node;
        }
    }

    /**
     *   单链表展示
     */
    public void display(){

        Node<T> node = head;
        int i = 0;
        while(node != null){
            System.out.print(String.format("%d=%d-->",i++,node.item));
            node = node.next;
        }
        System.out.println();
    }

    /**
     *  循环的方式转置
     */
    public void reverseList(){

        Node curr = head;
        Node reverse = null;
        while (curr != null){
            Node temp = curr;
            //遍历
            curr = curr.next;
            //头插法；
            temp.next = reverse;
            reverse = temp;
        }
        head = reverse;
    }

    public static void main(String[] args){

        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
        linkedList.add(1);
        linkedList.add(3);
        linkedList.add(5);
        linkedList.add(7);

        System.out.println("添加元素后");
        linkedList.display();

        System.out.println("循环方式转置");
        linkedList.reverseList();
        linkedList.display();
    }


}

class Node<T>{

    public T item;
    public Node<T> next;

    public Node(T item){
        this.item = item;
    }
}
