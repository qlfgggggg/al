package qiaolf.zuoshen.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 图的广度优先遍历
 * Author:qiaolf
 * @Date:2021/4/6 15:01
 **/
public class code01_BFS {
    public void bfs(Node start){
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll.value);
            for (Node next : poll.nexts) {
                if (!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
