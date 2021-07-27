package qiaolf.zuoshen.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 打印一个字符串的全部子序列:按照顺序，可以不连续：关键在于在字符数组的index位置，要还是不要这个字符来组成字符串
 * Author:qiaolf
 * @Date:2021/7/5 22:04
 **/
public class code1_1 {
    public static void main(String[] args) {
        String s = "abc";
        char[] chars = s.toCharArray();
        List<String> ans = new ArrayList<>();
        String path = "";
        f(chars,0,ans,path);
        for (String an : ans) {
            System.out.println(an);
        }
    }

    public static List<String> f(char[] chars,int index,List<String> ans,String path){
        if (index==chars.length){
            ans.add(path);
        }else{
            f(chars,index+1,ans,path+String.valueOf(chars[index]));
            f(chars,index+1,ans,path);
        }
        return ans;
    }



}
