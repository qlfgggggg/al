package qiaolf.zuoshen.class16_2;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 并查集:什么是并查集？
 * Author:qiaolf
 * @Date:2021/5/24 20:39
 **/
public class UnionFindDemo {

    public class Node<V>{
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public class UnionFind<V>{
        //普通节点
        private HashMap<V,Node<V>> nodes;
        //普通节点的父节点  k孩子节点，v父亲节点
        private HashMap<Node<V>,Node<V>> parents;
        //节点有多少个尺寸，包括孩子节点和自己
        private HashMap<Node<V>,Integer> sizes;

        //初始化并查集时要传入一个集合,集合里面都是V
        public UnionFind(List<V> list) {
            this.nodes=new HashMap<>();
            this.parents=new HashMap<>();
            this.sizes=new HashMap<>();
            for (V v : list) {
                Node<V> vNode = new Node<>(v);
                nodes.put(v,vNode);
                parents.put(vNode,vNode);//初始化时每个节点的父节点是自己
                sizes.put(vNode,1);//每个节点的大小都为1，是自己
            }
        }

        //找到父节点  其实在每次找父亲的过程中，也让结构更扁平化
        public Node<V> findFather(Node<V> cur){
            //为什么要用栈这种数据结构，因为找爸爸是一级一级往上找，最后找到的要先出来
            Stack<Node<V>> stack=new Stack<>();
            while (cur!=parents.get(cur)){//自己不是自己的父节点,当跳出循环时就是父亲节点
                stack.push(cur);
                cur=parents.get(cur);
            }
            //找到父亲节点了cur
            while (!stack.isEmpty()){
                Node<V> pop = stack.pop();
                parents.put(pop,cur);
            }
            return cur;
        }
        //判断是否在一个集合  也就是判断是否是同一个爸爸，并查集是一个集合的条件是一个爸爸
        public boolean isSameSet(V a,V b){
            Node<V> aNode = nodes.get(a);
            Node<V> bNode = nodes.get(b);
            return findFather(aNode)==findFather(bNode);
        }

        //合并两个值所在的集合
        public void union(V a,V b){
            //先判断是否是一个爸爸  不是同一个爸爸才合并
            Node<V> aNode = nodes.get(a);
            Node<V> bNode = nodes.get(b);
            Node<V> aFather = findFather(aNode);
            Node<V> bFather = findFather(bNode);
            if (aFather!=bFather){
                //先判断哪个集合的sizes更大
                int aSize = sizes.get(aFather);
                int bSize = sizes.get(bFather);
                if (aSize>=bSize){
                    parents.put(bFather,aFather);
                    sizes.put(aFather,aSize+bSize);
                    sizes.remove(bFather);
                }else {
                    parents.put(aFather,bFather);
                    sizes.put(bFather,aSize+bSize);
                    sizes.remove(aFather);
                }
            }
        }

        public int sets(){
            return sizes.size();
        }

    }

}
