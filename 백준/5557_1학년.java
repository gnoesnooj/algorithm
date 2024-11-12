import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    static int[] numbers;

    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        dp = new long[N][21];

        String[] inputs = br.readLine().split(" ");
        for (int i = 0; i < inputs.length; i++) {
            numbers[i] = Integer.parseInt(inputs[i]);
        }

        dp[0][numbers[0]] = 1;

        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < 21; j++) {
                if (dp[i][j] != 0) {
                    int plus = j + numbers[i + 1];
                    int minus = j - numbers[i + 1];

                    if (plus <= 20) {
                        dp[i + 1][plus] += dp[i][j];
                    }

                    if (minus >= 0) {
                        dp[i + 1][minus] += dp[i][j];
                    }
                }
            }
        }

        System.out.println(dp[N - 2][numbers[N - 1]]);

    }
}
