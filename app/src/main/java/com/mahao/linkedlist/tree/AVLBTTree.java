package com.mahao.linkedlist.tree;

import android.provider.DocumentsContract;
import android.support.annotation.IntegerRes;
import android.support.annotation.XmlRes;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by mahao on 2018/3/29.
 */

public class AVLBTTree<E extends Comparable<E>> {

    Node<E> root;
    int size = 0;
    private static final int LH = 1;
    private static final int RH = -1;
    private static final int EH = 0;


    public void midOrderDisplay(Node root){

        if(root == null){
            return;
        }else{
            midOrderDisplay(root.left);
            System.out.println("midOrder: " + root.element);
            midOrderDisplay(root.right);
        }
    }


    //对x节点进行左旋
    public void left_ratate(Node<E> x){

        if(x != null){
            Node<E> y = x.right;
            //step 1
            x.right = y.left;
            if(y.left != null){
                y.left.parent = x;
            }
            //step 2
            y.parent = x.parent;
            if(x.parent == null){
                root = y;
            }else{
                if(x.parent.left == x){
                    x.parent.left = y;
                }else if(x.parent.right == x){
                    x.parent.right = y;
                }
            }
            //step 3
            y.left = x;
            x.parent = y;
        }
    }

    //对y节点进行右旋
    public void right_rorate(Node<E> y){

        if(y != null){
            Node<E> y1 = y.left;
            //step 1
            y.left = y1.right;
            if(y1.right != null){
                y1.right.parent = y;
            }
            //step 2
            y1.parent = y.parent;
            if(y.parent == null){
                root = y1;
            }else{
                if(y.parent.left == y){
                    y.parent.left = y1;
                }else if(y.parent.right == y){
                    y.parent.right = y1;
                }
            }
            //step 3
            y1.right = y;
            y.parent = y1;
        }
    }

    public void rightBalance(Node<E> t){

        Node<E> tr = t.right;
        switch (tr.balance){

            case RH:
                left_ratate(t);
                 t.balance = EH;
                tr.balance = EH;
                break;
            case LH:
                Node<E> trl = tr.left;
                switch (trl.balance){
                    case RH:
                        t.balance = LH;
                        tr.balance = RH;
                        trl.balance = EH;
                        break;
                    case LH:
                        t.balance = LH;
                        trl.balance = EH;
                        tr.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tr.balance = EH;
                        tr.balance = EH;
                        break;
                }
                right_rorate(t.right);
                left_ratate(t);
                break;
            case EH:
                break;
        }
    }


    public void leftBalance(Node<E> t){

       Node<E> t1 = t.left;
       switch (t1.balance){

           case LH:
               right_rorate(t);
               t1.balance = EH;
               t.balance = EH;
               break;
           case RH:
               Node<E> trl = t1.right;
               switch (trl.balance){
                   case RH:
                       t.balance = EH;
                       trl.balance = EH;
                       t1.balance = LH;
                       break;
                   case LH:
                        t.balance = RH;
                        t1.balance = EH;
                        trl.balance = EH;
                        break;
                   case EH:
                       t.balance = EH;
                       t1.balance = EH;
                       trl.balance = EH;
                       break;
               }
               left_ratate(t1);
               right_rorate(t);
               break;
           case EH:
               break;
       }
    }


    public boolean inserElement(E element){

        Node<E> t = root;
        if(t == null){
           root = new Node<E>(element,null);
           size = 1;
           root.balance = 0;
           return true;
        }else{
            int cmp = 0;
            Node<E> parent;
            Comparable<? super E> e = element;
            do{
                parent = t;
                cmp = e.compareTo(t.element);
                if(cmp < 0){
                    t = t.left;
                }else if(cmp > 0){
                    t = t.right;
                }else{
                    return false;
                }
            }while (t != null);
            Node<E> child = new Node<E>(element,parent);
            if(cmp < 0){
               parent.left = child;
            }else{
                parent.right = child;
            }
            //节点已经插入，检查是否平衡 回溯查找
            while(parent != null){

                cmp = e.compareTo(parent.element);
                //元素在左边插入
                if(cmp < 0){
                    parent.balance++;
                }else {
                    parent.balance--;
                }
                if(parent.balance == 0){
                    break;
                }
                if(Math.abs(parent.balance) == 2){
                    //出现平衡问题
                    fixAfterInsertion(parent);
                    break;
                }else{
                    parent = parent.parent;
                }
            }
            size++;
            return true;
        }
    }

    private void fixAfterInsertion(Node<E> parent) {

        if(parent.balance == 2){
            leftBalance(parent);
        }

        if(parent.balance == -2){
            rightBalance(parent);
        }
    }


    public class Node<E extends Comparable<E>> {

        E element;
        int balance = 0;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element , Node<E> pare){
            this.element = element;
            this.parent = pare;
        }

        public E getElement(){
            return  element;
        }

        public void setElement(E element){
            this.element = element;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
    }

    class NodeBF{

        int level;
        Node<E> node;
        public NodeBF(Node<E> node,int level){
            this.level = level;
            this.node = node;
        }
    }

    public void levelDisplay(Node root){

        LinkedList<NodeBF> list = new LinkedList<>();
        NodeBF nodeBF = new NodeBF(root,0);
        list.offer(nodeBF);
         while (!list.isEmpty()){

             NodeBF pop = list.pop();
             System.out.println(pop.node.element +".." + pop.level);
             if(pop.node.left != null){
                 NodeBF nodeBFLeft = new NodeBF(pop.node.left,pop.level+1);
                 list.offer(nodeBFLeft);
             }
             if(pop.node.right != null){
                 NodeBF nodeBFRight = new NodeBF(pop.node.right,pop.level+1);
                 list.offer(nodeBFRight);
             }
         }
    }

    public static void main(String[] args){

        Integer[] nums = {5,8,2,0,1,-2};
        AVLBTTree<Integer> avlbtTree = new AVLBTTree<>();
        for(int i = 0; i < nums.length; i++){
          avlbtTree.inserElement(nums[i]);
        }
        avlbtTree.midOrderDisplay(avlbtTree.root);
        avlbtTree.levelDisplay(avlbtTree.root);
        System.out.println("...." + avlbtTree.root.element);
    }
}