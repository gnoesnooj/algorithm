import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p1495_기타리스트 {

    static int N, S, M;

    static int[] V;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);
        S = Integer.parseInt(inputs[1]);
        M = Integer.parseInt(inputs[2]);

        V = new int[N + 1];

        inputs = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            V[i + 1] = Integer.parseInt(inputs[i]);
        }

        dp = new int[N + 1][M + 1];

        dp[0][S] = 1;

        for (int i = 0; i < N; i++) {
            boolean play = false;

            for (int j = 0; j < M + 1; j++) {
                if (dp[i][j] >= 1) {
                    int plus = j + V[i + 1];
                    int minus = j - V[i + 1];

                    if (plus <= M) {
                        play = true;
                        dp[i + 1][plus] = 1;
                    }
                    if (minus >= 0) {
                        play = true;
                        dp[i + 1][minus] = 1;
                    }
                }

            }

            if (!play) {
                System.out.println(-1);
                return;
            }
        }

        for (int i = M; i >= 0; i--) {
            if (dp[N][i] == 1) {
                System.out.println(i);
                return;
            }
        }
    }

    private static void printDP() {
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M + 1; j++) {
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }
    }
}
