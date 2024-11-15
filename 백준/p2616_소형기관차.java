import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p2616_소형기관차 {

    static int N, answer, K;

    static int[] numbers;

    static int[][] dp;

    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 객차의 수
        int N = Integer.parseInt(br.readLine());

        numbers = new int[N + 1];

        // 누적합 배열
        sum = new int[N + 1];

        String[] inputs = br.readLine().split(" ");

        // 각 객차별 손님 수
        for (int i = 0; i < N; i++) {
            numbers[i + 1] = Integer.parseInt(inputs[i]);
            sum[i + 1] = sum[i] + numbers[i + 1];
        }

        // 소형 기관차가 끌 수 있는 최대 객차 수
        K = Integer.parseInt(br.readLine());

        dp = new int[4][N + 1];

        for (int i = 1; i < 4; i++) {
            for (int j = i * K; j < N + 1; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-K] + sum[j]-sum[j-K]);
            }
        }

        System.out.println(dp[3][N]);
    }
}
