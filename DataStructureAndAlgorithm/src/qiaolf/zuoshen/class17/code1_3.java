package qiaolf.zuoshen.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 打印一个字符串的全部排列
 * Author:qiaolf
 * @Date:2021/7/5 22:31
 **/
public class code1_3 {

    public static void main(String[] args) {
        String s="abc";
        List<String> ans = new ArrayList<>();
        String path="";
        List<Character> rest=new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            rest.add(s.charAt(i));
        }
        f(rest,path,ans);
        for (String an : ans) {
            System.out.println(an);
        }
    }

    public static List<String> f(List<Character> rest,String path,List<String> ans){
        if (rest.isEmpty()){
            ans.add(path);
        }else{
            int size = rest.size();
            for (int i = 0; i < size; i++) {
                Character remove = rest.remove(i);
                f(rest,path+String.valueOf(remove),ans);
                rest.add(i,remove);
            }
        }
        return ans;
    }
}
