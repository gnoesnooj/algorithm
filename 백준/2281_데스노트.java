import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;

    static int[] names;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputs[] = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        names = new int[N];

        dp = new int[2000][2000];

        for (int i = 0; i < N; i++) {
            names[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < 2000; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(calc(0, 0));
    }

    private static int calc(int index, int used) {
        if (index == N) {
            return 0;
        }
        if (dp[index][used] != -1) {
            return dp[index][used];
        }

        // 1. 강제로 다음줄로 보내기
        int val = calc(index + 1, names[index] + 1) + (int) Math.pow(M - used + 1, 2);

        // 2. 현재줄 뒤에 붙이기
        if (M >= used + names[index]) {
            val = Math.min(val, calc(index + 1, used + names[index] + 1));
        }

        return dp[index][used] = val;
    }
}
