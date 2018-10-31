package com.mahao.linkedlist.Linked;

/**
 * Created by mahao on 2018/2/8.
 */

public class LinkedList<E> {

    Node<E> first;
    Node<E> last;
    int size;

    public LinkedList(){

    }

    public void add(E e){
        linkLast(e);
    }


    /**
     *   添加一个元素
     * @param index
     * @param e
     */
    public void add(int index,E e){

        if(index < 0 || index > size){
            return ;
        }
        if(index == size){
            linkLast(e);
        }else {
            Node<E> target = node(index);
            Node<E> pre = target.pre;
            Node node = new Node(pre,e,target);
         // pre.next = node;
         // target.pre = node;
            if(pre == null){
                first = node;
            }else {
                pre.next = node;
            }
            target.pre = node;
            size++;
        }
    }


    /**
     *   获取节点的值
     * @param index
     * @return
     */
    public E get(int index){
        if(index < 0 || index > size){
            return null;
        }
        return node(index).item;
    }


    public void remove(int index){

        Node<E> target = node(index);
        unlinkNode(target);
    }

    private void unlinkNode(Node<E> target) {

       Node<E> pre = target.pre;
       Node<E> next = target.next;

       //相当于删除第一个节点
       if(pre == null){
           first = pre.next;
       }else{
           pre.next = next;
       }
       //相当于删除最后一个节点
       if(next == null){
           last = pre;
       }else {
           next.pre = pre;
       }
       size--;
    }


    /**
     *   找到需要的index点。
     * @param index
     * @return
     */
    private Node<E> node(int index){

        if(index < (size >> 1)){  //处于前半部分
            Node<E> node = first;
            for(int i = 0; i < index; i++){
                node = node.next;
            }
            return node;

        }else{  //处于后半部分

            Node<E> node = last;
            for(int i = size-1 ; i >= index; i--){

                node = node.pre;
            }
            return node;
        }
    }

    private void linkLast(E e) {

        Node<E> newNode = new Node<E>(last,e,null);
        Node<E> l = last;
        last = newNode;
        if(l == null){
            first = newNode;
        }else {
            l.next = newNode;
        }
        size++;
    }

    public static class Node<E>{

        E item;
        Node<E> next;
        Node<E> pre;

        Node(Node<E> prev,E element,Node<E> next){

            this.item = element;
            this.next = next;
            this.pre = prev;
        }
    }
}
