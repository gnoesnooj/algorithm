import java.util.*;

public class GoldMaze {
    static int N, M;
    static int[][] map;
    static int[][] dp;
    static final int MIN = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                dp[i][j] = MIN; // 초기화
            }
        }

        dp[0][0] = map[0][0]; // 시작점 초기화

        for (int i = 1; i < N; i++) {
            Arrays.fill(dp[i], MIN);
        }

        // 첫 번째 줄 초기화 (아래로는 못 오니까 → 만 가능)
        for (int j = 1; j < M; j++) {
            if (dp[0][j - 1] != MIN) {
                dp[0][j] = dp[0][j - 1] + map[0][j];
            }
        }

        // 아래로 내려가면서 처리
        for (int i = 1; i < N; i++) {
            int[] leftToRight = new int[M];
            int[] rightToLeft = new int[M];

            // 위에서 내려오는 것 먼저
            for (int j = 0; j < M; j++) {
                if (dp[i - 1][j] != MIN) {
                    dp[i][j] = dp[i - 1][j] + map[i][j];
                }
            }

            // 왼 → 오
            leftToRight[0] = dp[i][0];
            for (int j = 1; j < M; j++) {
                leftToRight[j] = Math.max(dp[i][j], leftToRight[j - 1] + map[i][j]);
            }

            // 오 → 왼
            rightToLeft[M - 1] = dp[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                rightToLeft[j] = Math.max(dp[i][j], rightToLeft[j + 1] + map[i][j]);
            }

            // dp 갱신
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(leftToRight[j], rightToLeft[j]);
            }
        }

        // 결과 출력
        System.out.println(dp[N - 1][M - 1]);
    }
}
