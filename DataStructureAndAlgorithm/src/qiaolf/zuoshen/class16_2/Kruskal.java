package qiaolf.zuoshen.class16_2;

import java.util.*;

/**
 * @Description: 图的最小生成树算法:思想：找出最小边，连接这些最小边，只要这些最小边连接后能够组成环，这条边就舍弃,
 * 翻译成代码语言：
 * 将所有的边都找出来(放在优先级队列中),再遍历优先级队列里的边，每条边对应的有两个点
 * 将边对应的点放入并查集,如果某条边对应的两个点已经都在并查集中的同一个集合中出现过，就放弃该条边，否则会形成环
 * Author:qiaolf
 * @Date:2021/5/24 17:11
 **/
public class Kruskal {
    //并查集
    public class UnionFind{
        //key 节点   value节点对应的父节点
        private HashMap<Node,Node> parents;
        //每个节点的孩子包括自己
        private HashMap<Node,Integer> sizes;

        public UnionFind() {
            this.parents=new HashMap<>();
            this.sizes=new HashMap<>();
        }

        //根据节点来创建并查集
        public void createSets(Collection<Node> nodes){
            parents.clear();
            sizes.clear();
            for (Node node : nodes) {
                parents.put(node,node);
                sizes.put(node,1);
            }
        }

        public Node findFather(Node cur){
            Stack<Node> stack=new Stack<>();
            while (cur!=parents.get(cur)){
                stack.push(cur);
                cur=parents.get(cur);
            }
            while (!stack.isEmpty()){
                parents.put(stack.pop(),cur);
            }
            return cur;
        }

        public boolean isSameSet(Node a,Node b){
            return findFather(a)==findFather(b);
        }

        public void union(Node a,Node b){
            if (a==null || b==null){
                return;
            }
            if (!isSameSet(a,b)){
                Node bFather = findFather(b);
                Node aFather = findFather(a);
                int asize=sizes.get(a);
                int bsize=sizes.get(b);
                if (asize>=bsize){
                    parents.put(bFather,aFather);
                    sizes.put(aFather,asize+bsize);
                    sizes.remove(bFather);
                }else{
                    parents.put(aFather,bFather);
                    sizes.put(bFather,asize+bsize);
                    sizes.remove(aFather);
                }
            }
        }
    }

    //将边根据权重大小放入小根堆
    public class MyComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight-o2.weight;
        }
    }


    //k算法生成最小生成树，要的是边
    public Set<Edge> kruskalMST(Graph graph){
        UnionFind unionFind=new UnionFind();
        Collection<Node> nodes = graph.nodes.values();
        unionFind.createSets(nodes);
        PriorityQueue<Edge> queue=new PriorityQueue<>(new MyComparator());
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }
        Set<Edge> set = new HashSet<>();
        while (!queue.isEmpty()){
            Edge poll = queue.poll();
            Node fromNode = poll.fromNode;
            Node toNode = poll.toNode;
            //只要一条边的两个端点不在一个集合中(父节点是同一个，这样的话会形成环状)就可以保留这条边
            if (!unionFind.isSameSet(fromNode,toNode)){
                set.add(poll);
                //当把某条边放入结果中后，这条边对应的端点要放入同一个集合，以后防止他们再一次在同一个集合从而形成环状
                unionFind.union(fromNode,toNode);
            }
        }
        return set;
    }
}


