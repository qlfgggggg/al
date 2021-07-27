package qiaolf.zuoshen.class19;

/**
 * @Description: 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * Author:qiaolf
 * @Date:2021/4/2 22:04
 **/
public class Code02_numberToStr {

    public static int number(String str){
        if (str==null || str.length()==0){
            return 0;
        }
        //将str字符串转为char字符数组
        char[] chars = str.toCharArray();
        return process(chars,0);
    }

    //我们只关注从index索引位置开始的
    private static int process(char[] chars, int index) {
        //为什么越界了要返回1？因为能够走到越界说明前面的都符合了，走到越界我们收集到一种结果，比如：213  2->B 1->A 3->C  第四个位置越界了收集到一种结果
        if (index==chars.length){
            return 1;
        }
        //0字符没有对应的，我们前面的努力都白费了，前面收集到的结果遇到0就不再有效
        if (chars[index]=='0'){
            return 0;
        }
        //正常情况下的：
        //第一种情况：第index个位置单独作为一个
        int way = process(chars,index+1);//看看第index+1位置有多少个
        //第二种情况：index+1位置没有越界,且第index位置+index+1位置的和小于27也能作为一种情况
        if (index+1<chars.length && (chars[index]-'0')*10+chars[index+1]-'0'<27){
            way += process(chars,index+2);//看看第index+2位置有多少个
        }
        return way;
    }

    public static int dp(String str){
        if (str==null || str.length()==0){
            return 0;
        }
        char[] chars = str.toCharArray();
        int N = chars.length;
        int dp[]=new int[N+1];
        dp[N]=1;//对应暴力递归越界的时候
        for (int i = N-1; i >=0 ; i--) {
            if (chars[i]!='0'){//只有当以该字符为单独一部分开头字符不为'0'时才能继续
                //对应第一种情况
                int way=dp[i+1];
                //第二种情况
                if (i+1<N && (chars[i]-'0')*10+chars[i+1]-'0'<27){
                    way+=dp[i+2];
                }
                dp[i]=way;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("12314155"));
        System.out.println(dp("12314155"));
        System.out.println(number("10314155"));
        System.out.println(dp("10314155"));
        System.out.println(number("100314155"));
        System.out.println(dp("100314155"));
    }
}
