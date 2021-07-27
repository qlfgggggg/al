package qiaolf.labuladong.dp;

//没用动态规划之前，算的非常慢
public class Fib {
    static int fib(int n){
        if (n==1 || n==2) return 1;
        return fib(n-1)+fib(n-2);
    }

    public static void main(String[] args) {
        System.out.println(fib(50));
    }
}
