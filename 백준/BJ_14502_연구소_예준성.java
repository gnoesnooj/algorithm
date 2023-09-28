import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 메모리 44096kb
 * 시간 352ms
 * 벽 3개 뽑고 -> 바이러스 확산 시키고 -> 0 개수 구하고 -> 제일 큰값 리턴해주기
 * */
public class Main {

    static int miro[][];
    static int[][] copied;

    static int N, M, emptyCnt;

    // 빈칸 좌표
    static List<int[]> empty = new ArrayList<>();

    // 바이러스 좌표
    static List<int[]> virus = new ArrayList<>();

    // 세워줄거 3개
    static int[] block = new int[3];

    // 방문 dfs
    static boolean[][] visited;

    // d
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        miro = new int[N][M];

        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(inputs[j]);
                miro[i][j] = value;

                // 0, 2 기억하기
                if (value == 0) {
                    emptyCnt++;
                    empty.add(new int[]{i, j});
                }

                if (value == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }

        combi(0, 0);

        System.out.println(answer);
    }

    private static void combi(int start, int cnt) {
        if (cnt == 3) {
            visited = new boolean[N][M];
            // 1. 배열 복사 +
            // 2. 위치 변경
            copyArray();
            // 3. bfs 확산하고
            for (int[] point : virus) {
                dfs(point[0], point[1]);
            }
            // 4. 개수 세고
            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (copied[i][j] == 0) {
                        result++;
                    }
                }
            }
            // 5. 결과 비교
            answer = Math.max(answer, result);

        } else {
            for (int i = start; i < emptyCnt; i++) {
                block[cnt] = i;
                combi(i+1, cnt + 1);
            }
        }
    }

    private static void dfs(int px, int py) {
        // 1. 들어온놈 true
        visited[px][py] = true;
        // 2. dfs 확산 -> for 4
        for (int i = 0; i < 4; i++) {
            int movedX = px + dx[i];
            int movedY = py + dy[i];

            // 범위, 0, false
            if (isRange(movedX, movedY) && copied[movedX][movedY] == 0 && visited[movedX][movedY] == false) {
                copied[movedX][movedY] = 2;
                dfs(movedX, movedY);
            }
        }
    }

    private static void copyArray() {
        copied = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copied[i][j] = miro[i][j];
            }
        }

        // 1로 바꿔주기
        for (int i : block) {
            int x = empty.get(i)[0];
            int y = empty.get(i)[1];

            copied[x][y] = 1;
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}