import java.io.*;
import java.util.*;

public class Main {

    static int[][] dp;

    static int[] people;

    static int N;

    static List<Integer>[] town;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 마을 수
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][2];

        people = new int[N + 1];
        town = new List[N + 1];

        for (int i = 0; i <= N; i++) {
            town[i] = new LinkedList<>();
        }

        String[] inputs;
        inputs = br.readLine().split(" ");

        // 마을 사람 수
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(inputs[i - 1]);
        }

        // 인접 마을 입력 받기
        for (int i = 0; i < N - 1; i++) {
            inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            town[a].add(b);
            town[b].add(a);
        }

        dfs(1, 0);

        System.out.println(Math.max(dp[1][0], dp[1][1]));

    }

    private static void dfs(int n, int parent) {
        // 본인이 1이면 자식노드들의 0만을 더하고
        // 본인이 0이면 자식노드들의 0과 1중 큰값을 가져가면 됨
        for (int i : town[n]) {
            if (i != parent) {
                dfs(i, n);
                dp[n][1] += dp[i][0];
                dp[n][0] += Math.max(dp[i][0], dp[i][1]);
            }
        }
        dp[n][1] += people[n];
    }
}
