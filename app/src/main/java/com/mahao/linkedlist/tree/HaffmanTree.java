package com.mahao.linkedlist.tree;

import android.net.LinkAddress;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by mahao on 2018/3/27.
 */

public class HaffmanTree {

    TreeNode root;
    public static class TreeNode<T> implements Comparable<TreeNode<T>>{

        T data;
        int weight;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parent;

        public TreeNode(T data , int wei){
            this.data = data;
            this.weight = wei;
            leftChild = null;
            rightChild = null;
            parent = null;
        }

        @Override
        public int compareTo(@NonNull TreeNode<T> o) {

            if(this.weight > o.weight){
                return -1;
            }else if(this.weight < o.weight){
                return 1;
            }
            return 0;
        }
    }

    /**
     *   创建一颗二叉树
     * @param list
     * @return
     */
    public TreeNode createHaffTree(ArrayList<TreeNode> list){

        while(list.size() > 1){

            Collections.sort(list);
            TreeNode left = list.get(list.size()-1);
            TreeNode right = list.get(list.size() -2);
            TreeNode parent = new TreeNode("p",left.weight+ right.weight);
            parent.leftChild = left;
            parent.rightChild = right;
            left.parent = parent;
            right.parent = parent;
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        root = list.get(0);
        return list.get(0);
    }

    /**
     *  展示huffman树
     * @param root
     */
    public void showHuffMan(TreeNode root){

        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while(!list.isEmpty()){

           TreeNode  node = list.pop();
           if(!node.data.equals("p")){
               System.out.print(node.data+"-");
           }
           if(node.leftChild != null){
               list.offer(node.leftChild);
           }
           if(node.rightChild != null){
               list.offer(node.rightChild);
           }
        }
    }


    /**
     *   获取节点的路径
     * @param node
     */
    public void getCode(TreeNode node){

        Stack<String> stack = new Stack<>();
        TreeNode treeNode = node;
        while (treeNode != null && treeNode.parent != null){

            if(treeNode.parent.leftChild == treeNode){
                stack.push("0");
            }else if(treeNode.parent.rightChild == treeNode){
                stack.push("1");
            }
            treeNode = treeNode.parent;
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+"---");
        }
    }


    public static void main(String[] args){

        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode<String> node = new TreeNode<>("good",154);
        list.add(node);
        list.add(new TreeNode("morning",10));
        list.add(new TreeNode("aftertoon",20));
        list.add(new TreeNode("hello",100));
        list.add(new TreeNode("mi",300));
        HaffmanTree tree = new HaffmanTree();
        tree.createHaffTree(list);
        tree.showHuffMan(tree.root);
        System.out.println();
        tree.getCode(node);
    }

}












