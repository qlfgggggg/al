package qiaolf.zuoshen.class21;

/**
 * 动态规划模型：从左往右的模型，范围尝试模型，样本对应模型
 * 动态规划：记忆化搜索，严格表结构
 * @Description: 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 * Author:qiaolf
 * @Date:2021/7/24 14:50
 **/
public class code1_matrix1 {
    public static int minDistance(int[][] matrix){
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        dp[0][0]=matrix[0][0];
        for (int i = 1; i <M ; i++) {
            dp[0][i]=dp[0][i-1]+matrix[0][i];
        }

        for (int i = 1; i <N ; i++) {
            dp[i][0]=dp[i-1][0]+matrix[i][0];
        }
        for (int i = 1; i <N; i++) {
            for (int j = 1; j <M; j++) {
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+matrix[i][j];
            }
        }

        return dp[N-1][M-1];
    }



    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col];
        dp[0] = m[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
            }
        }
        return dp[col - 1];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 11;
        int colSize = 11;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minDistance(m));
        System.out.println(minPathSum2(m));

    }
}
