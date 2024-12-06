import java.util.*;
import java.io.*;

public class p16930_달리기 {

    static int N, M, K, sx, sy, ex, ey, answer = -1;

    static char[][] miro;

    static boolean[][] visited; // x 좌표, y 좌표

    // 우 하 좌 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");

        //  세로
        N = Integer.parseInt(inputs[0]);

        // 가로
        M = Integer.parseInt(inputs[1]);

        // 최대 이동 거리
        K = Integer.parseInt(inputs[2]);

        // 미로 입력
        miro = new char[N][M];

        String input;

        for (int i = 0; i < N; i++) {
            input = br.readLine();
            miro[i] = input.toCharArray();
        }

        // 좌표 입력
        inputs = br.readLine().split(" ");

        // 시작 좌표
        sx = Integer.parseInt(inputs[0]) - 1;
        sy = Integer.parseInt(inputs[1]) - 1;

        // 끝 좌표
        ex = Integer.parseInt(inputs[2]) - 1;
        ey = Integer.parseInt(inputs[3]) - 1;

        // 방문 배열 초기화
        visited = new boolean[N][M];

        Queue<int[]> movings = new LinkedList<>();

        movings.add(new int[]{sx, sy, 0});
        while (!movings.isEmpty()) {
            int[] moving = movings.poll();

            for (int i = 0; i < 4; i++) {
                for (int k = 1; k <= K; k++) {
                    int mx = moving[0] + dx[i] * k;
                    int my = moving[1] + dy[i] * k;
                    if (isRange(mx, my) && miro[mx][my] == '.' && !visited[mx][my]) {
                        if (isGoal(mx, my)) {
                            System.out.println(moving[2] + 1);
                            return;
                        }
                        visited[mx][my] = true;
                        movings.add(new int[]{mx, my, moving[2] + 1});
                    } else { // 범위를 벗어나거나 벽을 만난다면 종료
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean isGoal(int x, int y) {
        return x == ex && y == ey;
    }

    private static boolean isRange(int x, int y) {
        return x < N && y < M && x >= 0 && y >= 0;
    }

    private static void printMiro() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(miro[i][j]);
            }
            System.out.println();
        }
    }
}
