import java.io.*;
import java.util.*;

public class p11058_크리보드 {

    static int N;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new long[N + 1];

        for (int i = 1; i < N + 1; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i > 6) {
                for (int j = 2; j < 5; j++) {
                    dp[i] = Math.max(dp[i], dp[i - (j+1)] * (j));
                }
            }
        }
        System.out.println(dp[N]);
    }

}
