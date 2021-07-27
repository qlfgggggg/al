package qiaolf.zuoshen.class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Description: 给定一个由字符串组成的数组strs，
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 * Author:qiaolf
 * @Date:2021/4/4 10:47
 **/
public class TanXin1 {
    //定义比较器====比较器思想是两个字符串拼接，按照字典序小的拼接
    public static class MyComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }

    public static String lowerZiDian(String[] str){
        if (str==null || str.length==0){
            return "";
        }
        //排序
        Arrays.sort(str,new MyComparator());
        String ans= "";
        for (int i = 0; i < str.length; i++) {
            ans+=str[i];
        }
        return ans;
    }

    //移除字符串数组中的第index个元素
    public static String[] removeIndex(String[] str,int index){
        String st[] = new String[str.length-1];
        int in = 0;
        //遍历str中的元素将元素装到st数组里
        for (int i = 0; i < str.length; i++) {
            if (i!=index){//将index号元素移除
                st[in++]=str[i];
            }
        }
        return st;
    }

    public static String lowestZidian(String[] strings){
        if (strings==null || strings.length==0){
            return "";
        }
        //为什么要将返回值弄成treeset,因为有排序功能
        TreeSet<String> ans = process(strings);
        //由于treeset是有序的，找出第一个就是字典序最小的
        return ans.size()==0 ? "" : ans.first();
    }

    private static TreeSet<String> process(String[] strings) {
        TreeSet<String> ans = new TreeSet<>();
        if (strings.length==0){
            ans.add("");
            return ans;
        }
        //全排列，所有字符串的组合都列出来了，
        for (int i = 0; i < strings.length; i++) {
            String first = strings[i];
            String[] strings1 = removeIndex(strings, i);
            TreeSet<String> process = process(strings1);
            for (String s : process) {
                ans.add(first+s);
            }
        }
        return ans;
    }

    /*public static void main(String[] args) {
        String[] strings = {"ba","b"};
        System.out.println(lowerZiDian(strings));
        System.out.println(lowestZidian(strings));
    }*/

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowerZiDian(arr1).equals(lowestZidian(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
