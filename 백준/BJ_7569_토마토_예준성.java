import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 메모리 149808kb 시간 884ms
 * 3차원 배열을 만든 후, 조건에 맞게 BFS를 진행해준다.
 */
public class Main {

    static int[][][] tomatoes;

    static int M, N, H;

    // 동 서 남 북 상 하
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int[] dh = {-1, 1};

    static Queue<int[]> yummy = new LinkedList<>();
    static boolean[][][] visited;

    static int answer, zero_tomato;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");

        M = Integer.parseInt(inputs[0]);
        N = Integer.parseInt(inputs[1]);
        H = Integer.parseInt(inputs[2]);

        tomatoes = new int[N][M][H];
        visited = new boolean[N][M][H];

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                inputs = br.readLine().split(" ");
                for (int m = 0; m < M; m++) {
                    tomatoes[n][m][h] = Integer.parseInt(inputs[m]);
                    if (tomatoes[n][m][h] == 1) { // 익은 토마토라면
                        yummy.add(new int[]{n, m, h, 0}); // 익은거 기억하기
                        visited[n][m][h] = true;
                    } else if (tomatoes[n][m][h] == 0){
                        zero_tomato++;
                    }
                }
            }
        }

        bfs();

        System.out.println(getAnswer());
    }

    private static void bfs() {
        while (!yummy.isEmpty()) {
            int[] tomato = yummy.poll();
            answer = Math.max(answer, tomato[3]);
            // 같은 높이
            for (int i = 0; i < 4; i++) {
                int nx = tomato[0] + dx[i];
                int ny = tomato[1] + dy[i];
                int h = tomato[2];
                if (isRangeNM(nx, ny) && !visited[nx][ny][h] && tomatoes[nx][ny][h] == 0) {
                    tomatoes[nx][ny][h] = 1;
                    zero_tomato--;
                    visited[nx][ny][h] = true;
                    yummy.add(new int[]{nx, ny, h, tomato[3] + 1});
                }
            }

            for (int i = 0; i < 2; i++) {
                int x = tomato[0];
                int y = tomato[1];
                int nh = tomato[2] + dh[i];
                if (nh >= 0 && nh < H) {
                    if (!visited[x][y][nh] && tomatoes[x][y][nh] == 0) {
                        tomatoes[x][y][nh] = 1;
                        zero_tomato--;
                        visited[x][y][nh] = true;
                        yummy.add(new int[]{x, y, nh, tomato[3] + 1});
                    }
                }
            }
        }
    }

    private static boolean isRangeNM(int n, int m) {
        return n >= 0 && m >= 0 && n < N && m < M;
    }

    private static int getAnswer() {
//        for (int h = 0; h < H; h++) {
//            for (int n = 0; n < N; n++) {
//                for (int m = 0; m < M; m++) {
//                    if (tomatoes[n][m][h] == 0) { // 안익은 토마토가 있다면
//                        return -1;
//                    }
//                }
//            }
//        }
        if(zero_tomato != 0) return -1;
        return answer;
    }
}