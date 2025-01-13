import java.util.*;
import java.io.*;

public class p14442_벽부수고이동하기2 {

    static int N, M, K;
    static boolean[][][] visited;
    static char[][] miro;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        K = Integer.parseInt(inputs[2]);

        visited = new boolean[N][M][K + 1];
        miro = new char[N][M];

        for (int i = 0; i < N; i++) {
            miro[i] = br.readLine().toCharArray();
        }

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0, 1, K});
        visited[0][0][K] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll(); // x, y, answer, k

            if (current[0] == N - 1 && current[1] == M - 1) {
                System.out.println(current[2]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + current[0];
                int ny = dy[i] + current[1];

                if (isRange(nx, ny)) {
                    if (miro[nx][ny] == '1' && current[3] > 0 && !visited[nx][ny][current[3] - 1]) {
                        visited[nx][ny][current[3] - 1] = true;
                        queue.add(new int[]{nx, ny, current[2] + 1, current[3] - 1});
                    } else if(miro[nx][ny] == '0') {
                        if (!visited[nx][ny][current[3]]) {
                            visited[nx][ny][current[3]] = true;
                            queue.add(new int[]{nx, ny, current[2] + 1, current[3]});
                        }
                    }
                }

            }
        }

        System.out.println(-1);

    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
