package tree;

import java.util.Stack;

/**
 * @author Zhang
 * @date 2018/7/28
 * @Description   平衡二叉树
 */
public class AVLTree {

    private AvlNode root;

    public AVLTree(){
        root = null;
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        int[] a = {3,2,1,4,5,6,7,10,9,8};
        for(int i = 0; i < 10; i++){
            avlTree.insert(a[i]);
        }

        InOrderTraverse(avlTree.root);

    }

    public void insert(Integer x)
    {
        root = insert(x,root);
    }

    private int height(){

        return height(root);
    }

    //return the height of node t, or -1, if null.
    private int height(AvlNode t){
        return t == null?-1:t.height;
    }

    private AvlNode insert (Integer x,AvlNode t){

        if(t==null)
            return new AvlNode(x,null,null);

        int compareResult = x.compareTo(t.data);

        if(compareResult<0){

            t.lchild = insert(x, t.lchild);
            if(height(t.lchild)-height(t.rchild)==2)
                if(x.compareTo(t.lchild.data)<0)
                    t = rotateWithlchildChild(t);  //左—左单旋转
                else
                    t = doubleWithlchildChild(t);  //左—右双旋转
        }
        else if(compareResult>0){

            t.rchild = insert(x, t.rchild);
            if(height(t.rchild)-height(t.lchild)==2)
                if(x.compareTo(t.rchild.data)>0)
                    t = rotateWithrchildChild(t);  //右—右单旋转
                else
                    t = doubleWithrchildChild(t);  //右—左双旋转
        }
        else
            ;

        t.height = Math.max(height(t.lchild), height(t.rchild))+1;

        return t;
    }


    private AvlNode rotateWithlchildChild(AvlNode k2) {

        AvlNode k1 = k2.lchild;
        k2.lchild = k1.rchild;
        k1.rchild = k2;
        k2.height = Math.max(height(k2.lchild), height(k2.rchild))+1;
        k1.height = Math.max(height(k1.lchild), k2.height)+1;

        return k1;
    }


    private AvlNode rotateWithrchildChild(AvlNode k1){

        AvlNode k2 = k1.rchild;
        k1.rchild = k2.lchild;
        k2.lchild = k1;

        k1.height = Math.max(height(k1.lchild),height(k1.rchild))+1;
        k2.height = Math.max(height(k2.rchild), k1.height)+1;

        return k2;

    }

    private AvlNode doubleWithlchildChild(AvlNode k3){

        k3.lchild = rotateWithrchildChild(k3.lchild);
        return rotateWithlchildChild(k3);
    }


    private AvlNode doubleWithrchildChild(AvlNode k1){
        k1.rchild = rotateWithlchildChild(k1.rchild);
        return rotateWithrchildChild(k1);
    }

    public static void InOrderTraverse(AvlNode treeNode){
        if(treeNode == null)
            return;
        InOrderTraverse(treeNode.lchild);
        System.out.print(treeNode.data+", ");
        InOrderTraverse(treeNode.rchild);
    }

    private static class AvlNode{

        Integer data;
        AvlNode lchild;
        AvlNode rchild;
        int height;        //高度

        public AvlNode(Integer thedata){
            this(thedata,null,null);
        }

        public AvlNode(Integer thedata, AvlNode lt, AvlNode rt) {
            data = thedata;
            lchild = lt;
            rchild = rt;
        }

    }

}
