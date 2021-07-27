package qiaolf.zuoshen.class18_2;

/**
 * @Description: 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 * Author:qiaolf
 * @Date:2021/7/6 15:06
 **/
public class PaiScore {
    public static void main(String[] args) {
        int arr[]={50,100,70,20,60,80,75};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }

    public static int win1(int arr[]){
        if (arr.length==0 || arr==null){
            return 0;
        }
        return Math.max(f1(arr,0,arr.length-1),g1(arr,0,arr.length-1));
    }

    //先手
    public static int f1(int[] arr,int l,int r){
        if (l==r){
            return arr[l];
        }
        //先手的分数  先手先拿左边的牌   先手和后手都是绝顶聪明的，他们拿走之后，会让对手拿不到最大的牌，所以
        int p1 = arr[l] + g1(arr,l+1,r);//先手拿走一张牌后就作为后手了
        int p2 = arr[r] + g1(arr,l,r-1);
        return Math.max(p1,p2);
    }

    //后手    先手和后手都有可能赢
    public static int g1(int[] arr,int l,int r){
        if (l==r){
            return 0;
        }
        //先手拿走一张牌后，后手就作为后手的先手
        int p1 = f1(arr,l+1,r);
        int p2 = f1(arr,l,r-1);
        //先手留给后手一定是最小的
        return Math.min(p1,p2);
    }


    public static int win2(int[] arr){
        if (arr.length==0 || arr==null){
            return 0;
        }
        //先手和后手 fmax[l][r]是以l为左边界，r为右边界的先手的最大值，gmax[l][r]是以l为左边界，r为右边界的最大值
        //我们最终要找出以0，arr.length为左右边界的
        int[][] fmax = new int[arr.length][arr.length];
        int[][] gmax = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                fmax[i][j]=-1;
                gmax[i][j]=-1;
            }
        }
        return Math.max(f2(arr,0,arr.length-1,fmax,gmax),g2(arr,0,arr.length-1,fmax,gmax));
    }

    private static int g2(int[] arr, int l, int r, int[][] fmax, int[][] gmax) {
        if (gmax[l][r]!=-1){
            return gmax[l][r];
        }
        int ans = 0;
        if (l==r){
            ans = 0;
        }else{
            int p1 = f2(arr,l+1,r,fmax,gmax);
            int p2 = f2(arr,l,r-1,fmax,gmax);
            ans = Math.min(p1,p2);
        }
        gmax[l][r]=ans;
        return ans;
    }

    private static int f2(int[] arr, int l, int r, int[][] fmax, int[][] gmax) {
        if (fmax[l][r]!=-1){
            return fmax[l][r];
        }
        int ans=0;
        if (l==r){
            ans=arr[l];
        }else{
            int p1 = arr[l]+g2(arr,l+1,r,fmax,gmax);
            int p2 = arr[r]+g2(arr,l,r-1,fmax,gmax);
            ans = Math.max(p1,p2);
        }
        fmax[l][r]=ans;
        return ans;
    }

    public static int win3(int[] arr){
        if (arr==null || arr.length==0){
            return 0;
        }
        int fmax[][]=new int[arr.length][arr.length];
        int gmax[][]=new int[arr.length][arr.length];
        //由于l==r是对角线fmax在对角线上都为，gmax在对角线上都为0
        for (int i = 0; i < arr.length; i++) {
            fmax[i][i]=arr[i];
        }
        //这里startCol=1是有原因的，因为r应该是大于l的，前面已经把所有的r==l都包括了
        //这个循环的作用是将整个二维数组填充
        for (int startCol = 1; startCol < arr.length; startCol++) {
            //startRow代表l
            int startRow = 0;
            //代表r
            int col = startCol;
            while (col<arr.length){
                fmax[startRow][col]=Math.max(arr[startRow]+gmax[startRow+1][col],arr[col]+gmax[startRow][col-1]);
                gmax[startRow][col]=Math.min(fmax[startRow][col-1],fmax[startRow+1][col]);
                startRow++;
                col++;
            }
        }
        return Math.max(fmax[0][arr.length-1],gmax[0][arr.length-1]);
    }
}
