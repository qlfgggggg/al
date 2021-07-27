package qiaolf.zuoshen.class15;

/**
 * @Description:
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Author:qiaolf
 * @Date:2021/4/5 17:47
 **/
/*
* 说明：二位矩阵中的每一个数组代表一个城市，这个这个城市中值为1代表和其他城市相连，比如其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连
* 所有相连组成一个省份,求所有省份
*
* */
public class code01_findCircleNum {
    public int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isConnected[i][j]==1){
                    unionFind.uinon(i,j);
                }
            }
        }
        return unionFind.set();
    }

    //并查集：这次用数组
    public class UnionFind{
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;
        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets=N;
            for (int i = 0; i < N; i++) {
                parent[i]=i;
                size[i]=1;
            }
        }
        private int find(int i){
            int hi=0;
            while (i!=parent[i]){
                help[hi++]=i;
                i=parent[i];
            }
            //hi有可能没有help数组的长度-1大,因为最后一次hi++时多了一次，所以要减去
            for (hi--; hi >=0; hi--) {
                parent[help[hi]]=i;
            }
            return i;
        }
        public void uinon(int i,int j){
            int i1 = find(i);
            int i2 = find(j);
            if (i1!=i2){
                int big = size[i1]>=size[i2] ? i1 : i2;
                int small = size[i1]>=size[i2] ? i2 : i1;
                parent[small]=big;
                size[big]= size[big]+size[small];
                sets--;
            }
        }
        public int set(){
            return sets;
        }
    }
}
