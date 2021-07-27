package qiaolf.zuoshen.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 打印一个字符串的全部排列
 * Author:qiaolf
 * @Date:2021/7/5 23:05
 **/
public class code1_4 {

    public static void main(String[] args) {
        String s="abcc";
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        f(chars,0,ans);
        for (String an : ans) {
            System.out.println(an);
        }
    }

    public static List<String> f(char[] chars,int index,List<String> ans){
        if (index==chars.length){
            ans.add(String.valueOf(chars));
        }else{
            boolean[] visited = new boolean[256];
            for (int i = index; i < chars.length; i++) {
                if (!visited[chars[i]]){
                    visited[chars[i]]=true;
                    swap(chars,index,i);
                    f(chars,index+1,ans);
                    swap(chars,i,index);
                }
            }
        }
        return ans;
    }

    public static void swap(char[] chars,int i,int j){
        char temp = chars[i];
        chars[i]=chars[j];
        chars[j]=temp;
    }
}
