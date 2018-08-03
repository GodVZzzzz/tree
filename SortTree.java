package tree;

import java.util.ArrayList;

/**
 * @author Zhang
 * @date 2018/7/26
 * @Description  二叉排序树
 */
public class SortTree {

    private static SortTreeNode root = null;

    public static void main(String[] args) {
        int[] a = {5 ,6 ,1 ,3 ,2 ,4 ,7,6,5};
        for(int i = 0; i < a.length; i++){
            InsertBST(a[i]);
        }
        DeleteBST(root,5);
        System.out.println("排序结果为：");
        InOrderTraverse(root);                             //中序遍历二叉树的结果为顺序

    }

    /*增加*/
    private static void InsertBST( int k){

        SortTreeNode treeNode1 = root;
        SortTreeNode treeNode2 = new SortTreeNode();


        while (treeNode1 != null){
            treeNode2 = treeNode1;
            if(k > treeNode1.getData()){
                treeNode1 = treeNode1.getRchild();
            }
            else if(k < treeNode1.getData()){
                treeNode1 = treeNode1.getLchild();
            }
            else
                return;
        }

        if(root == null) {
            root= new SortTreeNode(k);
        }
        else if (k > treeNode2.getData())
            treeNode2.setRchild(new SortTreeNode(k));
        else if (k < treeNode2.getData())
            treeNode2.setLchild(new SortTreeNode(k));


    }

    /*删除*/
    private static boolean DeleteBST(SortTreeNode treeNode, int k) {
       if(treeNode == null)
           return false;

       else{
           if(k == treeNode.getData())
               return delete(treeNode);
           else if(k > treeNode.getData())
               return DeleteBST(treeNode.getRchild(),k);
           else if(k < treeNode.getData())
               return DeleteBST(treeNode.getLchild(),k);
       }

       return false;
    }

    private static boolean delete(SortTreeNode treeNode){
        SortTreeNode treeNode1 = treeNode;
        SortTreeNode treeNode2 = treeNode;

        if(treeNode.getRchild() == null){
            treeNode1 = treeNode.getLchild();
            treeNode.setData(treeNode1.getData());
            treeNode.setLchild(treeNode1.getLchild());
            treeNode.setRchild(treeNode1.getRchild());
        }

        else if(treeNode.getLchild() == null){
            treeNode1 = treeNode.getRchild();
            treeNode.setData(treeNode1.getData());
            treeNode.setLchild(treeNode1.getLchild());
            treeNode.setRchild(treeNode1.getRchild());
        }
        else {
            treeNode2 = treeNode2.getLchild();
            while(treeNode2.getRchild() != null){
                treeNode1 = treeNode2;
                treeNode2 = treeNode2.getRchild();
            }
            treeNode.setData(treeNode2.getData());
            if(treeNode1 != treeNode){
                treeNode1.setRchild(treeNode2.getLchild());
            }
            else{
                treeNode1.setLchild(treeNode2.getLchild());
            }

        }
        return true;
    }

    /*查找*/
    private static boolean SearchBST(int k) {

        SortTreeNode current = root;
        while(current != null){
            if(k == current.getData())
                return true;
            else if(k < current.getData())
                current = current.getLchild();
            else
                current = current.getRchild();
        }
        return false;

    }

    /*中序遍历*/
    public static void InOrderTraverse(SortTreeNode treeNode){
        if(treeNode == null)
            return;
        InOrderTraverse(treeNode.getLchild());
        System.out.print(treeNode.getData()+", ");
        InOrderTraverse(treeNode.getRchild());
    }

    private static class SortTreeNode {
        private int data;
        private SortTreeNode lchild;
        private SortTreeNode rchild;

        public SortTreeNode() {
        }

        public SortTreeNode(int data) {
            this.data = data;
            lchild = null;
            rchild = null;
        }

        public void setData(int data) {
            this.data = data;
        }

        public void setLchild(SortTreeNode lchild) {
            this.lchild = lchild;
        }

        public void setRchild(SortTreeNode rchild) {
            this.rchild = rchild;
        }

        public Integer getData() {
            return data;
        }

        public SortTreeNode getLchild() {
            return lchild;
        }

        public SortTreeNode getRchild() {
            return rchild;
        }
    }
}

