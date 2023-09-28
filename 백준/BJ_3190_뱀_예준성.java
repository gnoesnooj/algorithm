import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 메모리 11940 실행시간 80
 * queue, deque 를 이용해서 조건에 맞게 풀어준다.
 * 실시간으로 뱀 길이 바꿔줄 것 -> deque
 * 뱀의 몸에 부딪힌 건지 파악할 배열 -> boolean visited[][]
 * dx, dy를 상우하좌 순서로 배열한 다음, direction 을 오른쪽에 해당하는 1로 초기화 해놓는다.
 * 이후 L과 D에 따라, 조건에 맞게 direction을 바꿔준다.
 */
public class Main {

    static int[][] miro;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dy = {0, 1, 0, -1}; // 0  1  2  3

    static int direction = 1;
    static int[] position = {0, 0};

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        // 보드 크기 입력받기
        N = Integer.parseInt(br.readLine());

        // 보드 초기화
        miro = new int[N][N];

        // 방문 배열 초기화, 뱀의 전체 몸통을 나타낼 배열
        visited = new boolean[N][N];

        // 뱀 길이
        Deque<int[]> snake = new LinkedList<>();

        // 사과 입력받기
        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            inputs = br.readLine().split(" ");
            miro[Integer.parseInt(inputs[0]) - 1][Integer.parseInt(inputs[1]) - 1] = 5; // 사과 정해주기
        }

        // 커맨드 입력받기
        int L = Integer.parseInt(br.readLine());

        Queue<int[]> commands = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            inputs = br.readLine().split(" ");

            int seconds = Integer.parseInt(inputs[0]);

            switch (inputs[1]) {
                case "L":
                    commands.add(new int[]{seconds, 0});
                    break;
                case "D":
                    commands.add(new int[]{seconds, 1});
                    break;
            }
        }

        // 뱀 이동 시키기
        // 1. 0,0으로 시작하기
        snake.add(new int[]{0, 0});
        visited[0][0] = true;

        int cnt = 0;
        while (true) {
            cnt++;
            int nx = position[0] + dx[direction];
            int ny = position[1] + dy[direction];

            if (isRange(nx, ny) || visited[nx][ny] == true) { // 범위를 벗어난다면, 뱀의 몸통이라면
                break;
            }
            if (miro[nx][ny] == 5) {
                snake.addLast(new int[]{nx, ny});
                miro[nx][ny] = 0;
                visited[nx][ny] = true;
            } else {
                snake.addLast(new int[]{nx, ny});
                visited[nx][ny] = true;
                int[] tail = snake.pollFirst(); // 사과가 아니라면 꼬리를 떼준다.
                visited[tail[0]][tail[1]] = false; // 꼬리가 떼졌으므로 길이에서 하나 빼준다.
            }

            if (!commands.isEmpty() && commands.peek()[0] == cnt) {
                if (commands.peek()[1] == 0) { // L
                    direction = (direction + 3) % 4; // 바라보는 기준 왼쪽으로 꺾기
                } else {
                    direction = (direction + 1) % 4; // 바라보는 기준 오른쪽으로 꺾기
                }

                commands.poll();
            }

            position[0] = nx;
            position[1] = ny;
        }

        System.out.println(cnt);
    }

    private static boolean isRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}
