package qiaolf.zuoshen.class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Description:
 * Author:qiaolf
 * @Date:2021/4/5 16:53
 **/
public class code05_UnionFind {
    //节点
    public static class Node<V>{
        V value;
        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionFind<V>{
        private HashMap<V,Node<V>> nodes;//存放所有节点
        private HashMap<Node<V>,Node<V>> parents;//值存放每个节点的父亲，键存放当前节点
        private HashMap<Node<V>,Integer> size;//每个代表节点的大小(每个代表节点下有多少节点，包括自己)

        //初始化
        public UnionFind(List<V> list) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            size = new HashMap<>();
            for (V v : list) {
                Node<V> vNode = new Node<>(v);
                nodes.put(v,vNode);
                parents.put(vNode,vNode);
                size.put(vNode,1);
            }
        }

        //给你一个节点，往上找，直到不能再向上  ====>经过该方法所有cur节点的上层节点都绑在做上层的父亲节点，实现节点的扁平化
        private Node<V> findFather(Node<V> cur){
            Stack<Node<V>> stack = new Stack<>();
            while (cur!=parents.get(cur)){//当前节点的父亲不是自己，一直网上找
                stack.push(cur);
                cur=parents.get(cur);//最终当前节点一定会到父亲
            }
            if (!stack.isEmpty()){
                parents.put(stack.pop(),cur);//最上层节点的父亲节点是该节点自己,其他节点的父亲节点是cur节点
            }
            return cur;
        }
        //判断两个节点是否在一个集合
        public boolean isSampleSet(V a,V b){
            //判断这两个节点的父亲节点是否是一个
            return findFather(nodes.get(a))==findFather(nodes.get(b));
        }

        //将两个节点所在的集合合并
        public void union(V a,V b){
            //为什么要这么写，因为a,b可能不在nodes里呀
            Node<V> afather = findFather(nodes.get(a));
            Node<V> bfather = findFather(nodes.get(b));
            //两个节点不在一个集合
            if (afather!=bfather){
                //看看哪个节点所在集合的size更大
                Integer aSzie = size.get(afather);
                Integer bSzie = size.get(bfather);
                if (aSzie>=bSzie){
                    parents.put(bfather,afather);
                    size.put(afather,aSzie+bSzie);
                    size.remove(bfather);
                }else{
                    parents.put(afather,bfather);
                    size.put(bfather,aSzie+bSzie);
                    size.remove(afather);
                }
            }
        }

        public int sets(){
            return size.size();
        }
    }
}
