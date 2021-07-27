package qiaolf.zuoshen.class14;

/**
 * @Description: 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 * Author:qiaolf
 * @Date:2021/4/5 9:58
 **/

/*
*
* */
public class tanxin2 {
    public static int leastLingth1(String str){
        char[] chars = str.toCharArray();
        if (chars==null || chars.length==0){
            return 0;
        }
        //我们只关心从第index位置之后需要多少栈灯
        int i=0,light=0;
        while (i<chars.length){
            if (chars[i] == 'X') {
                i++;
            }else{
                light++;
                if (i+1==chars.length){
                    break;
                }else {
                    if (chars[i + 1] == 'X') {
                        i=i+2;
                    }else{
                        i=i+3;
                    }
                }
            }
        }
        return light;
    }

    public static void main(String[] args) {
        String s="X...XXXX..XX.X......XX.";
        System.out.println(leastLingth1(s));
    }
}
