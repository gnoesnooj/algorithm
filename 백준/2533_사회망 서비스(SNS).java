import java.io.*;
import java.util.*;

/*
 * dfs + dp
 * 본인이 얼리어답터일때와 아닐때를 생각하면서 전체 탐색 + dp 적용
 * 본인이 얼리어답터면 -> 자식 노드가 일수도 , 아닐 수도 -> 이 중 최소 값을 적용 
 *               dp[number][1] = Math.min(dp[number][1] + dp[node][1], dp[number][1] + dp[node][0]);
 * 본인이 얼리어답터가 아니면 -> 자식노드는 무조건 얼리어답터여야 한다.
 *               dp[number][0] = dp[number][0] + dp[node][1];
 */
public class Main {

    static int N;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));

        String[] inputs;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N - 1; i++) {
            inputs = br.readLine().split(" ");
            int start = Integer.parseInt(inputs[0]);
            int end = Integer.parseInt(inputs[1]);
            graph[start].add(end);
            graph[end].add(start);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int number) {
        visited[number] = true;
        dp[number][0] = 0;
        dp[number][1] = 1;

        for (int node : graph[number]) {
            if (!visited[node]) {
                dfs(node);
                // 내가 아니면 자식은 얼리
                dp[number][0] = dp[number][0] + dp[node][1];
                dp[number][1] = Math.min(dp[number][1] + dp[node][1], dp[number][1] + dp[node][0]);
            }
        }
    }
}
