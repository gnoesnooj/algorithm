import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p12026_BOJ거리 {

    static int N;

    static String words;

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        words = br.readLine();

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isCorrect(words.charAt(i), words.charAt(j)) && dp[i] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[i] + (j - i) * (j - i));
                }
            }
        }

        System.out.println(dp[N-1] == Integer.MAX_VALUE ? -1 : dp[N-1]);
    }

    private static boolean isCorrect(char a, char b) {
        return (a == 'B' && b == 'O') || (a == 'O' && b == 'J') || (a == 'J' && b == 'B');
    }
}
