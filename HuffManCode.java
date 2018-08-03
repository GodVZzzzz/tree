package tree;

import java.util.*;

/**
 * @author Zhang
 * @date 2018/7/23
 * @Description 赫夫曼编解码
 */
public class HuffManCode {
    public static void main(String[] args) {
        String s = "BABACACADADABBCBABEBEDDABEEEBB";
        ArrayList<Integer> num = new ArrayList<>();
        ArrayList<Character> characters = new ArrayList<>();
        System.out.println("各字符出现的次数为：");
        System.out.println(count(s));

        HuffNode[] huffNodes = HuffTree(count(s));

        enCode(huffNodes);
        System.out.println("各字符编码结果：");
        Map<Character, String> map = new HashMap<>();
        for(int i = 0; i < count(s).size(); i++){
            map.put(huffNodes[i].getData(),huffNodes[i].getCode());
            System.out.println(huffNodes[i].getData()+"的赫夫曼编码是： "+huffNodes[i].getCode());
        }

        StringBuilder sb = new StringBuilder();
        System.out.println("字符串编码为：");
        for(int i = 0; i < s.length(); i++){
            sb.append(map.get(s.charAt(i)));
            System.out.print(map.get(s.charAt(i)));
        }

        String h = decode(sb.toString(),huffNodes);
        System.out.println("\n解码后的字符串为：");
        System.out.println(h);

    }

    /*赫夫曼编码*/
    private static void enCode(HuffNode[] huffNodes){
        int l = huffNodes.length;
        int m = (l+1)/2;
        for(int i = m; i < l; i++){
                huffNodes[i].getLchild().setCode(String.valueOf(0));
                huffNodes[i].getRchild().setCode(String.valueOf(1));
        }

        for (int i = 0; i < m ; i++){
            StringBuilder sb = new StringBuilder(huffNodes[i].getCode());
            HuffNode huffNode = huffNodes[i];
            while (huffNode.getParent().getCode() != null ){
                sb.append(huffNode.getParent().getCode());
                huffNode = huffNode.getParent();
            }
            String code = sb.reverse().toString();
            huffNodes[i].setCode(code);
        }

    }

    /*赫夫曼解码*/
    private static String decode(String s, HuffNode[] huffNodes){

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < s.length(); i++){

            HuffNode huffNode = huffNodes[huffNodes.length-1];
            while (i < s.length() && huffNode.getLchild() != null || huffNode.getRchild() != null){
                if(i < s.length() && s.charAt(i) == '\u0030' && huffNode.getLchild() != null ){
                    huffNode = huffNode.getLchild();
                    i++;
                }
                if(i < s.length() && s.charAt(i) == '\u0031' && huffNode.getRchild() != null){
                    huffNode = huffNode.getRchild();
                    i++;
                }

            }

            stringBuilder.append(huffNode.getData());
            i--;

        }

        return stringBuilder.toString();
    }

    /*计算字符串中字符出现次数*/
    private static Map<Character, Integer> count(String s){
        if(s == null || s.isEmpty())
            return null;
        Map<Character, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            Character character = s.charAt(i);
            Integer count = hashMap.get(character);
            hashMap.put(character,count == null? 1:count+1);
        }

        return hashMap;
    }

    /*构造赫夫曼树*/
    private static HuffNode[] HuffTree(Map<Character, Integer> map) {

        int n = map.size();
        int m = 2*n-1;
        HuffNode[] huffNodes = new HuffNode[m];

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        for(int i=0;i<n;i++){
            huffNodes[i] = new HuffNode();
            huffNodes[i].setweight(list.get(i).getValue());
            huffNodes[i].setData(list.get(i).getKey());
        }

        for (int i = n; i < m; i++){

            int t = selectMin(huffNodes,i-1);
            huffNodes[t].setFlag(1);
            int t2 = selectMin(huffNodes,i-1);
            huffNodes[t2].setFlag(1);

            huffNodes[i] = new HuffNode();
            huffNodes[i].setLchild(huffNodes[t]);
            huffNodes[t].setParent(huffNodes[i]);
            huffNodes[i].setRchild(huffNodes[t2]);
            huffNodes[t2].setParent(huffNodes[i]);
            huffNodes[i].setweight(huffNodes[t].getweight()+huffNodes[t2].getweight());

        }

        return huffNodes;
    }

    /*查找数组中权值最小的元素下标*/
    private static int selectMin(HuffNode[] huffNodes,int end){
        HuffNode min = huffNodes[0];
        int minC = 0;
        while (min.getFlag() == 1 && minC <= end){
            minC++;
            min = huffNodes[minC];
        }
        for(int i=1; i <= end; i++){
            HuffNode h = huffNodes[i];
            if (h.getFlag() == 0 && min.getweight() > huffNodes[i].getweight()) {
                min = h;
                minC = i;
            }
        }
        return minC;
    }


}


class HuffNode {
    private Character data;
    private HuffNode lchild;
    private HuffNode Rchild;
    private HuffNode parent;
    private int weight;
    private int Flag;
    private String code;

    public void setData(Character data) {
        this.data = data;
    }

    public void setLchild(HuffNode lchild) {
        this.lchild = lchild;
    }

    public void setRchild(HuffNode rchild) {
        Rchild = rchild;
    }

    public void setweight(int weight) {
        this.weight = weight;
    }

    public HuffNode getLchild() {
        return lchild;
    }

    public HuffNode getRchild() {
        return Rchild;
    }

    public int getweight() {
        return weight;
    }

    public Character getData() {
        return data;
    }

    public void setFlag(int flag) {
        Flag = flag;
    }

    public int getFlag() {
        return Flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setParent(HuffNode parent) {
        this.parent = parent;
    }

    public HuffNode getParent() {
        return parent;
    }
}