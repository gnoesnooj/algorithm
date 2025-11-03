import java.io.*;
/*
* DFS, DP 메모이제이션
* dfs 하면서 dfs 가 지날때 각 위치에 대한 cnt 값을 기억해놓고
* 추후에 다른 dfs를 하면서 해당 cnt 값이 현재 진입할때 cnt보다 높으면 해당 분기는 탐색 x
 */
public class p1103_게임 {

    static int N, M;
    static int max = Integer.MIN_VALUE;
    static int[][] miro;
    static int[][] dp;
    static boolean[][] visited;

    static boolean isLoop;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);

        M = Integer.parseInt(inputs[1]);

        miro = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split("");
            for (int j = 0; j < inputs.length; j++) {
                if (inputs[j].equals("H")) {
                    miro[i][j] = 999;
                } else {
                    miro[i][j] = Integer.parseInt(inputs[j]);
                }
            }
        }
        visited[0][0] = true;
        dfs(0, 0, 1);
        if (isLoop) {
            System.out.println("-1");
        } else {
            System.out.println(max);
        }
    }

    private static void dfs(int x, int y, int cnt) {
        if (cnt > max) {
            max = cnt;
        }
        dp[x][y] = cnt;

        for (int i = 0; i < 4; i++) {
            int mx = x + (dx[i] * miro[x][y]);
            int my = y + (dy[i] * miro[x][y]);

            if (mx < 0 || my < 0 || mx >= N || my >= M || miro[mx][my] == 999) {
                continue;
            }

            if (visited[mx][my]) {
                isLoop = true;
                return;
            }

            if (dp[mx][my] > cnt) {
                continue;
            }

            visited[mx][my] = true;
            dfs(mx, my, cnt + 1);
            visited[mx][my] = false;
        }
    }
}
