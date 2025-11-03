import java.util.Arrays;

class 완전범죄 {
    static int[][] dp;
    static int min = 9999999;

    public int solution(int[][] info, int n, int m) {
        dp = new int[info.length + 1][m];
        fill();
        // dp[x][y] = k, x개수만큼 b가 y개의 흔적을 사용했을때 a의 흔적은 k이다.
        dp[0][0] = 0;

        for (int i = 1; i <= info.length; i++) {
            int a = info[i-1][0];
            int b = info[i-1][1];

            for (int j = 0; j < m; j++) {
                // a 선택
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);
                // b 선택
                if (j + b < m) {
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            min = Math.min(min, dp[info.length][i]);
        }
        return min >= n ? -1 : min;
    }

    public void fill() {
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 100000);
        }
    }
}
