package qiaolf.zuoshen.class17;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 * Author:qiaolf
 * @Date:2021/7/5 22:20
 **/
public class code1_2 {

    public static void main(String[] args) {
        String s = "abbbbc";
        char[] chars = s.toCharArray();
        Set<String> set = new HashSet<>();
        String path = "";
        f(chars,0,set,path);
        for (String s1 : set) {
            System.out.println(s1);
        }
    }

    public static Set<String> f(char[] chars,int index,Set<String> set,String path){
        if (index==chars.length){
            set.add(path);
        }else{
            f(chars,index+1,set,path+chars[index]);
            f(chars,index+1,set,path);
        }
        return set;
    }
}
