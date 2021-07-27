package qiaolf.zuoshen.class16_2;

import java.util.*;

/**
 * @Description: 图的广度优先遍历
 * Author:qiaolf
 * @Date:2021/5/24 9:54
 **/
public class BFS {

    public void bfs(Node start){
        //防止图的节点重复遍历,循环遍历
        Set<Node> nodeSet = new HashSet<>();
        //用到队列
        Queue<Node> nodeQueue=new LinkedList<>();
        nodeQueue.add(start);
        nodeSet.add(start);
        while (!nodeQueue.isEmpty()){
            Node poll = nodeQueue.poll();
            System.out.println(poll);
            Iterator<Node> iterator = poll.nexts.iterator();
            while (iterator.hasNext()){
                Node next = iterator.next();
                if (!nodeSet.contains(next)){
                    nodeSet.add(next);
                    nodeQueue.add(next);
                }
            }
        }
    }
}
