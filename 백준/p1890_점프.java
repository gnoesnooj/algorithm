import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p1890_점프 {

    static int N;

    static int[][] game;

    static long[][] dp;

    static boolean[][] isReachable;

    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 일 수
        N = Integer.parseInt(br.readLine());

        game = new int[N][N];
        dp = new long[N][N];
        isReachable = new boolean[N][N];

        String[] inputs;

        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                game[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        isReachable[0][0] = true;
        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isReachable[i][j] && !(i == N - 1 && j == N - 1)) {
                    for (int d = 0; d < 2; d++) {
                        int nx = i + dx[d] * game[i][j];
                        int ny = j + dy[d] * game[i][j];
                        if (isRange(nx, ny)) {
                            isReachable[nx][ny] = true;
                            dp[nx][ny] = dp[i][j] + dp[nx][ny];
                        }
                    }
                }
            }
        }
      
        System.out.println(dp[N - 1][N - 1]);
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
