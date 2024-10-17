import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        long[] answer = new long[tc];
        long[] dp = new long[5001];

        dp[0] = 1;
        dp[2] = 1;

        for (int num = 4; num <= 5000; num++) {
            if (num % 2 != 0) {
                continue;
            }
            for (int i = 0; i < num / 2; i++) {
                dp[num] += dp[(num - 2) - (2 * i)] * dp[i * 2];
                dp[num] %= 1000000007;
            }
        }

        StringBuilder answers = new StringBuilder();
        
        for (int test_case = 0; test_case < tc; test_case++) {
            answers.append(dp[Integer.parseInt(br.readLine())] + "\n");
        }
        
        System.out.println(answers);
    }
}
