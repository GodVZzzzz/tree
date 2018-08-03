package tree;

import java.util.ArrayList;

/**
 * @author Zhang
 * @date 2018/7/22
 * @Description 二叉树的结构
 */
public class BiTree {

    private ArrayList<TreeNode> treeNodes;

    public void createTree(ArrayList<String> arrayList){

        treeNodes = new ArrayList<>();

        for(String s:arrayList)
            treeNodes.add(new TreeNode(s));

        int length = arrayList.size();

        for(int i = 0; i < length/2-1; i++){
            treeNodes.get(i).setLchild(treeNodes.get(2*i+1));
            treeNodes.get(i).setRchild(treeNodes.get(2*i+2));
        };
        int position = length/2-1;
        TreeNode lastParent = treeNodes.get(position);
        lastParent.setLchild(treeNodes.get(2*position+1));
        if(length % 2 == 1)
            lastParent.setRchild(treeNodes.get(2*position+2));
    }

    public ArrayList<TreeNode> getTreeNodes() {
        return treeNodes;
    }
}

class TreeNode{
    private String data;
    private TreeNode lchild;
    private TreeNode rchild;

    public TreeNode(){};

    public TreeNode(String data){
        this.data = data;
        lchild = null;
        rchild = null;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLchild(TreeNode lchild){
        this.lchild = lchild;
    }

    public void setRchild(TreeNode rchild){
        this.rchild = rchild;
    }

    public String getData() {
        return data;
    }

    public TreeNode getLchild() {
        return lchild;
    }

    public TreeNode getRchild() {
        return rchild;
    }


}
