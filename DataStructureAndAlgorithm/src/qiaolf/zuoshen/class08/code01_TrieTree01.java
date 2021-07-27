package qiaolf.zuoshen.class08;

import javax.swing.*;
import java.util.HashMap;
import java.util.Set;

/**
 * @Description: 前缀树
 * Author:qiaolf
 * @Date:2021/3/26 19:12
 **/
public class code01_TrieTree01 {

    //前缀树节点
    public static class Node{
        private int pass;//有多少字符串经过该节点
        private int end;//有多少字符串以该节点作为结尾
        private Node[] nexts;//这个节点下面有多少可能的路径'a'=0,'b'=1,我们假设有26个小写字母

        public Node() {
            pass=0;//如果p等于0说明没有经过该节点
            end=0;//end等于0说明没有以该节点为结尾
            nexts=new Node[26];//假设有26个字母，有26条路
        }
    }
    //前缀树
    public static class TrieTree{
        private Node root;//前缀树根节点

        public TrieTree() {
            root = new Node();
        }

        /**
         * 添加单词
         * @param word
         */
        public void insert(String word){
            //如果字符串是空的话
            if (word==null){
                return;
            }
            //先把字符串转为字符数组
            char[] chars = word.toCharArray();
            Node node = root;
            node.pass++;//上来对根节点pass++
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path=chars[i]-'a';//如果字符减去'a'得到0说明该字符是'a',如果得到1,说明是b....
                if (node.nexts[path]==null){
                    node.nexts[path] = new Node();
                }
                node=node.nexts[path];
                node.pass++;
            }
            node.end++;
        }
        //搜索word单词之前添加过几次   当到单词结尾是查看end
        public int search(String word){
            if (word==null){
                return 0;
            }
            Node node = root;
            char[] chars = word.toCharArray();
            int path=0;
            for (int i = 0; i < chars.length; i++) {
                path=chars[i]-'a';
                if (node.nexts[path]==null){
                    return 0;
                }
                node=node.nexts[path];
            }
            return node.end;
        }

       //所有加入的单词中以pre作为开头的   当到pre结尾时查看pass
        public int prefixNumber(String pre){
            if (pre==null){
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node node = root;
            int path=0;
            for (int i = 0; i < chars.length; i++) {
                path=chars[i]-'a';
                if (node.nexts[path]==null){
                    return 0;
                }
                node=node.nexts[path];
            }
            return node.pass;
        }

        /**
         * 删除单词
         * @param word
         */
        public void delete(String word){
            if (search(word)>0){
                Node node = root;
                node.pass--;
                int path=0;
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    path=chars[i]-'a';
                    //当该节点的psss减去1后等于0说明该节点后的所有节点都应该被删除
                    if (--node.nexts[path].pass==0){
                        node.nexts[path]=null;//这样将节点删除是为了防止内存泄漏，只有java可以这样做，因为null后面可能还跟着一堆节点，但是jvm虚拟机会自动回收，c++语言不行
                        return;
                    }
                    node=node.nexts[path];
                }
                node.end--;
            }
//            int search = search(word);
//            if (search==0){
//                throw new RuntimeException("前缀树中不存在该单词");
//            }
        }
    }

    //前缀树节点类型2
    public static class Node2{
        private int pass;
        private int end;
        private HashMap<Integer,Node2> nexts;

        public Node2() {
            pass=0;
            end=0;
            nexts=new HashMap<>();
        }
    }

    public static class TrieTree2{
        private Node2 root;

        public TrieTree2() {
            root=new Node2();
        }

        //insert  word
        public void insert(String word){
            if (word==null){
                return;
            }
            Node2 node2 = root;
            node2.pass++;
            int path=0;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                path=(int) chars[i];//这里不用0代表a,1代表b.......是多少就往map  key是什么比如'a'的ascii
                if (!node2.nexts.containsKey(path)){
                    node2.nexts.put(path,new Node2());
                }
                node2=node2.nexts.get(path);
                node2.pass++;
            }
            node2.end++;
        }

        //查找添加了多少个单词word
        public int search(String word){
            if (word==null){
                return 0;
            }
            Node2 node2 = root;
            int path=0;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                path=(int)chars[i];
                if (!node2.nexts.containsKey(path)){
                    return 0;
                }
                node2=node2.nexts.get(path);
            }
            return node2.end;
        }

        //查找有多少个以pre为前缀的单词
        public int prefixNumber(String pre){
            if (pre==null){
                return 0;
            }
            Node2 node2 = root;
            int path=0;
            char[] chars = pre.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                path=(int)chars[i];
                if (!node2.nexts.containsKey(path)){
                    return 0;
                }
                node2=node2.nexts.get(path);
            }
            return node2.pass;
        }
        //删除单词
        public void delete(String word){
            if (search(word)>0){
                Node2 node2=root;
                node2.pass--;
                int path=0;
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    path=(int)chars[i];
                    if (--node2.nexts.get(path).pass==0){
                        node2.nexts.remove(path);//这个路径下跟着的hashmap都是不见了，因为指向他们的节点已经消失了
                        return;
                    }
                    node2=node2.nexts.get(path);
                }
                node2.end--;
            }
        }
    }

    //为了使用对数器而写的
    public static class Right{
        private HashMap<String,Integer> hashMap;

        public Right() {
            hashMap = new HashMap<>();
        }

        public void insert(String word){
            if (hashMap.containsKey(word)){
                hashMap.put(word,hashMap.get(word)+1);
            }else{
                hashMap.put(word,1);
            }
        }

        public int search(String word){
            if (!hashMap.containsKey(word)){
                return 0;
            }else{
                return hashMap.get(word);
            }
        }

        public void delete(String word){
            if (hashMap.containsKey(word)) {
                if (hashMap.get(word)>1){
                    hashMap.put(word,hashMap.get(word)-1);
                }else {
                    hashMap.remove(word);
                }
            }
        }

        public int prefixNumber(String pre){
            Set<String> strings = hashMap.keySet();
            int count=0;
            for (String string : strings) {
                if (string.startsWith(pre)){
//                    count++;这样是错误的,因为每一个以pre开头的string可能对应着好几个
                    count  += hashMap.get(string);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            TrieTree trie1 = new TrieTree();
            TrieTree2 trie2 = new TrieTree2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
