package qiaolf.zuoshen.class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/4/7 9:36
 **/
public class DFS {
    public static void dfs(Node start){
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            for (Node next : pop.nexts) {
                if (!set.contains(next)){
                    System.out.println(next.value);
                    stack.push(pop);
                    stack.push(next);
                    set.add(next);
                    break;
                }
            }
        }
    }
}
