package qiaolf.zuoshen.class16;

import java.util.*;

/**
 * @Description: 拓扑排序
 * Author:qiaolf
 * @Date:2021/4/7 9:53
 **/
public class Code03_topologySort {
    public List<Node> topologySort(Graph graph){
        ArrayList<Node> list = new ArrayList<>();
        //入度为0的节点进入
        Queue<Node> queue = new LinkedList<>();
        //key：节点，value入度，
        HashMap<Node,Integer> map = new HashMap<>();
        for (Node node : graph.nodes.values()) {
            map.put(node,node.in);
            if (node.in==0){
                queue.add(node);
            }
        }
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            list.add(poll);
            for (Node next : poll.nexts) {
                map.put(next,map.get(next)-1);
                if (map.get(next)==0){
                    queue.add(next);
                }
            }
        }
        return list;
    }
}
