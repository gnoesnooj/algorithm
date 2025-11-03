import java.io.*;
import java.util.*;

// dp 테이블을 만드는 기준에 대해서 생각하기.
public class p7579_앱 {

    static int N, M, answer;
    static int m[];
    static int c[];
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;
        inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]); // 앱 개수
        M = Integer.parseInt(inputs[1]); // 필요한 목표 바이트
        answer = Integer.MAX_VALUE;
        m = new int[N + 1];
        c = new int[N + 1];
        dp = new int[N + 1][10001];

        // 사용중인 메모리 바이트 받기
        inputs = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            m[i] = Integer.parseInt(inputs[i - 1]);
        }

        // 비활성화 비용 받기
        inputs = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(inputs[i - 1]);
        }

        // dp();
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < 10001; j++) {
                if (c[i] > j) { // 현재 앱을 끄면 안될때
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - c[i]] + m[i]);
                }

                if (dp[i][j] >= M) {
                    answer = Math.min(answer, j);
                }
            }
        }
        System.out.println(answer);
    }
}
