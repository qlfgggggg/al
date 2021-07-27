package qiaolf.zuoshen.class18;

/**
 * @Description: 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 * Author:qiaolf
 * @Date:2021/4/2 11:36
 **/
public class PaiScore {
    public static int win1(int[] arr){
        if (arr==null || arr.length==0){
            return 0;
        }
        //先手和后手都有可能拿到最大值
        int first = f1(arr,0,arr.length-1);
        int second = g1(arr,0,arr.length-1);
        return Math.max(first,second);
    }

    //先手获得最好的分数返回
    private static int f1(int[] arr, int l, int r) {
        if (l==r){
            return arr[l];
        }
        //
        int p1 = arr[l]+g1(arr,l+1,r);
        int p2 = arr[r]+g1(arr,l,r-1);
        return Math.max(p1,p2);
    }
    //后手    先手不会让后手获得最好的分数
    private static int g1(int[] arr, int l, int r) {
        if (l==r){
            return 0;
        }
        //作为后手的先手
        int p1 = f1(arr,l+1,r);
        int p2 =f1(arr,l,r-1);
        //先手留给后手的一定是小的
        return Math.min(p1,p2);
    }

    public static int win2(int arr[]){
        if (arr==null || arr.length==0){
            return 0;
        }
        int fmap[][]=new int[arr.length][arr.length];
        int gmap[][]=new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                fmap[i][j]=-1;
                gmap[i][j]=-1;
            }
        }
        int first=f2(arr,0,arr.length-1,fmap,gmap);
        int second=g2(arr,0,arr.length-1,fmap,gmap);
        return Math.max(first,second);
    }

    //先手

    //记忆化搜索   -----当我们要求目标表格时，需要用到一些表格，这个表格我们没有设置值就设置，如果设置了值就直接返回
    private static int f2(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        if (fmap[l][r]!=-1){
            return fmap[l][r];
        }
        int ans=0;
        if (l==r){
            ans = arr[l];
        }
        else{
            int p1 = arr[l]+g2(arr,l+1,r,fmap,gmap);
            int p2 = arr[r]+g2(arr,l,r-1,fmap,gmap);
            ans=Math.max(p1,p2);
        }
        fmap[l][r]=ans;
        return ans;
    }
    //后手     记忆化搜索
    private static int g2(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        //如果要找后手，后手存在就直接返回
        if (gmap[l][r]!=-1){
            return gmap[l][r];
        }
        int ans =0;
        if (l==r){
            ans=0;
        }
        else{
            int p1 = f2(arr,l,r-1,fmap,gmap);
            int p2 = f2(arr,l+1,r,fmap,gmap);
            ans = Math.min(p1,p2);
        }
        gmap[l][r]=ans;
        return ans;
    }

    //严格表结构   根据对应关系直接写出表结构
    public static int win3(int arr[]){
        if (arr==null || arr.length==0){
            return 0;
        }
        int fmap[][]=new int[arr.length][arr.length];
        int gmap[][]=new int[arr.length][arr.length];
        for (int i = 0; i <arr.length; i++) {
            //fmap对角线全部设置，gmap对角线全部为0
            fmap[i][i]=arr[i];
        }
        for (int startCol = 1; startCol < arr.length; startCol++) {
            int startRow = 0;
            int col=startCol;
            while (col<arr.length){
                fmap[startRow][col]=Math.max(arr[startRow]+gmap[startRow+1][col],arr[col]+gmap[startRow][col-1]);
                gmap[startRow][col]=Math.min(fmap[startRow+1][col],fmap[startRow][col-1]);
                col++;
                startRow++;
            }
        }
        //fmap[l][r]代表的是找出从f(l,r)最大值
        return Math.max(fmap[0][arr.length-1],gmap[0][arr.length-1]);
    }

    public static void main(String[] args) {
//        int arr[]={70,50,20,30};
//        int arr[]={80,100,20,30};
        int arr[]={50,100,70,20,60,80,90};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }
}
