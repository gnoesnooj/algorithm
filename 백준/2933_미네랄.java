import java.io.*;
import java.util.*;

/*
* BFS 시 , 큐에서 꺼낼 때 말고, 넣을때 방문처리를 해줘야 중복으로 노드가 들어가는 것을 막을 수 있다.
* */

public class Main {
    static char[][] minerals;

    static int N, M;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static boolean[][] visited;
    static Queue<int[]> down = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);

        M = Integer.parseInt(inputs[1]);

        minerals = new char[N][M];

        // 미네랄 입력
        for (int i = 0; i < N; i++) {
            minerals[i] = br.readLine().toCharArray();
        }

        // 던지는 횟수
        br.readLine();

        // 던지는 위치 입력
        inputs = br.readLine().split(" ");

        for (int i = 0; i < inputs.length; i++) {
            if (i % 2 == 0) {
                throwFromLeft(N - Integer.parseInt(inputs[i]));
            } else {
                throwFromRight(N - Integer.parseInt(inputs[i]));
            }

            // 공중 클러스터 확인
            // 해당 클러스터 내리기
            bfs();
        }

        printAnswer();
    }

    private static void bfs() {
        // 땅에 붙어있는 미네랄 클러스터를 방문처리 해주기
        visited = new boolean[N][M];
        for (int i = 0; i < M; i++) {
            if (minerals[N - 1][i] == 'x') {
                down.add(new int[]{N - 1, i});
                visited[N - 1][i] = true;
            }
        }
        while (!down.isEmpty()) {
            int[] node = down.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + node[0];
                int ny = dy[i] + node[1];

                if (isRange(nx, ny) && !visited[nx][ny] && minerals[nx][ny] == 'x') {
                    visited[nx][ny] = true;
                    down.add(new int[]{nx, ny});
                }
            }
        }

        // 방문처리가 안된 x 들은 공중에 있는 미네랄 클러스터이므로, 새로운 컬렉션에 담아 끌어내릴 준비를 해준다.
        List<int[]> up = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (minerals[i][j] == 'x' && !visited[i][j]) {
                    up.add(new int[]{i, j});
                    // . 으로 바꾸고 넣어준다
                    minerals[i][j] = '.';
                }
            }
        }
        boolean flag = true;
        while (flag) {
            if(up.isEmpty())
                break;
            for (int[] node : up) {
                // 범위를 벗어나거나, 다음 위치의 값이 미네랄이면
                if (!isRange(node[0] + 1, node[1]) || minerals[node[0] + 1][node[1]] != '.') {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                for (int[] node : up) {
                    node[0] += 1;
                }
            } else {
                for (int[] node : up) {
                    minerals[node[0]][node[1]] = 'x';
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    private static void throwFromLeft(int index) {
        for (int i = 0; i < M; i++) {
            if (minerals[index][i] == 'x') {
                minerals[index][i] = '.';
                break;
            }
        }
    }

    private static void throwFromRight(int index) {
        for (int i = M - 1; i >= 0; i--) {
            if (minerals[index][i] == 'x') {
                minerals[index][i] = '.';
                break;
            }
        }
    }

    private static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(minerals[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
