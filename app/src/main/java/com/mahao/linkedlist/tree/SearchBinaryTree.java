package com.mahao.linkedlist.tree;

import android.provider.ContactsContract;
import android.text.GetChars;

import java.lang.annotation.ElementType;
import java.util.NoSuchElementException;

/**
 * Created by mahao on 2018/3/19.
 */

public class SearchBinaryTree {

    public TreeNode root;
    public TreeNode getRoot(){
        return root;
    }

    //创建一个二叉树，这颗二叉树，左边孩子小于data，右边孩子大于data;
    public TreeNode put(int data){
        if(root == null){
            TreeNode node = new TreeNode(data);
            root = node;
            return  node;
        }
        TreeNode treeParent = null;
        TreeNode node  = root;
        for(; node != null;){
            treeParent = node;
            if(data < node.data){
                node = treeParent.leftChild;
            }else if(data > node.data){
                node = treeParent.rightChild;
            }else{
                return node;
            }
        }
        TreeNode newNode = new TreeNode(data);
        if(data < treeParent.data){
            treeParent.leftChild = newNode;
        }else {
            treeParent.rightChild = newNode;
        }
        //注意
        newNode.parent = treeParent;
        return newNode;
    }

    //二叉树的搜索
    public TreeNode searchNode(int data){

        if(root == null){
            return null;
        }
        TreeNode node = root;
        while(node != null){
            if(data < node.data){
                node = node.leftChild;
            }else if(data > node.data){
                node = node.rightChild;
            }else{
                return node;
            }
        }
        return null;
    }

    /**
     *   删除一个node
     * @param node
     */
    public void delNode(TreeNode node){

        if(node == null){
            throw new NoSuchElementException();
        }else{
            TreeNode parent = node.parent;
            //1 ：没有孩子
            if(node.leftChild == null && node.rightChild == null){
                if(parent == null){
                    root = null;
                }else if(parent.rightChild == node){
                    parent.rightChild = null;
                }else if(parent.leftChild == node){
                    parent.leftChild = null;
                }
                node.parent = null;
            }else if(node.leftChild != null && node.rightChild == null){
                //只有左孩子
                if(parent == null){
                    node.parent = null;
                    node.leftChild.parent = null;
                    root = node.leftChild;
                }else{
                    if(parent.leftChild == node){
                        parent.leftChild = node.leftChild;
                    }else if(parent.rightChild == node){
                        parent.rightChild = node.leftChild;
                    }
                    node.parent = null;
                }
            }else if(node.rightChild != null && node.leftChild == null){
                //只有右孩子
                if(parent == null){
                    node.parent = null;
                    node.rightChild.parent = null;
                    root = node.rightChild;
                }else{
                    if(parent.leftChild == node){
                        parent.leftChild = node.rightChild;
                    }else if(parent.rightChild == node){
                        parent.rightChild = node.rightChild;
                    }
                    node.parent = null;
                }
            }else if(node.leftChild != null && node.rightChild != null){
                //有左右两个孩子
                if(node.rightChild.leftChild == null){
                    node.rightChild.leftChild= node.leftChild;
                    if(parent == null){
                        root = node.rightChild;
                    }else{
                        if(parent.leftChild == node){
                            parent.leftChild = node.rightChild;
                        }else if(parent.rightChild == node){
                            parent.rightChild = node.rightChild;
                        }
                    }
                    node.parent = null;
                }else if(node.rightChild.leftChild != null){
                    //寻找最左子树
                    TreeNode minLeftNode = findMinLeftNode(node.rightChild.leftChild);
                    //001
                    minLeftNode.leftChild = node.leftChild;
                    //002
                    minLeftNode.parent.leftChild = minLeftNode.rightChild;
                    //003
                    minLeftNode.rightChild = node.rightChild;
                    if(parent == null){
                     root = minLeftNode;
                    }else if(parent.leftChild == node){
                        parent.leftChild = minLeftNode;
                    }else if(parent.rightChild == node){
                        parent.rightChild = minLeftNode;
                    }
                }
            }
        }
    }

    //寻找最右的子树
    public TreeNode findMinLeftNode(TreeNode node) {
        TreeNode currNode = null;
        if (node == null) {
            return null;
        }
        while (node != null) {
            currNode = node;
            node = node.leftChild;
        }
        return currNode;
    }

    //先序遍历二叉树 DLR
    public void preOrderTraverse(TreeNode root){
        //递归结束条件
        if(root == null){
            return ;
        }
        System.out.println("先序列遍历 = " + root.data);
        preOrderTraverse(root.leftChild);
        preOrderTraverse(root.rightChild);
    }

    //中序遍历二叉树 LDR
    public void midOrderTravserse(TreeNode root){
        if(root == null){
            return ;
        }
        midOrderTravserse(root.leftChild);
        System.out.println("中序列遍历=" + root.data);
        midOrderTravserse(root.rightChild);
    }

    //后续遍历二叉树
    public void postOrderTraverse(TreeNode root){
        if(root == null){
            return;
        }
        postOrderTraverse(root.leftChild);
        postOrderTraverse(root.rightChild);
        System.out.println("后续遍历=" + root.data);
    }

    public class TreeNode{

        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
        }

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        TreeNode parent;
        public TreeNode(int data){
            super();
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
        }
    }

    public static void main(String[] args){
        int[] arrays = {12,3,23,5,1,19,27,14,17};
        SearchBinaryTree tree = new SearchBinaryTree();
        for(int i = 0; i < arrays.length; i++){
            tree.put(arrays[i]);
        }
       // tree.preOrderTraverse(tree.root);
        tree.midOrderTravserse(tree.root);
      //  tree.postOrderTraverse(tree.root);
        TreeNode node = tree.searchNode(1);
        System.out.println("查询指定的data:" + node != null ? node.data :null);
        //删除节点
        TreeNode delNode = tree.searchNode(5);
        tree.delNode(delNode);
        tree.midOrderTravserse(tree.root);
    }
}
