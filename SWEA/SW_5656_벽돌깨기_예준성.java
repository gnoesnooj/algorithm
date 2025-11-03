import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 메모리 92,076 kb 실행시간 1,252 ms
 * - 벽돌 뽑을 곳 찾기 : 중복순열 사용
 * - 벽돌 깨기 : 중복 순열을 통해 나온 배열로, BFS 적용
 * - 벽돌 내리기 : stack 이용해서 벽돌 내려주기
 * - 결과값 비교하기 : Math.min 사용
 */
public class SW_5656_벽돌깨기_예준성 {

    static int N, W, H, answer;
    static int[][] blocks;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        String[] inputs;
        for (int T = 1; T <= tc; T++) {
            answer = Integer.MAX_VALUE;
            inputs = br.readLine().split(" ");

            N = Integer.parseInt(inputs[0]);
            W = Integer.parseInt(inputs[1]);
            H = Integer.parseInt(inputs[2]);

            blocks = new int[H][W];
            order = new int[N];

            for (int i = 0; i < H; i++) {
                inputs = br.readLine().split(" ");
                for (int j = 0; j < W; j++) {
                    blocks[i][j] = Integer.parseInt(inputs[j]);
                }
            }

            // 1. 중복순열로 N개 떨어트릴곳 뽑고
            perm(0);
            // 2. 뽑은곳 젤 위를 터뜨려서
            // 3. 주변까지 다 터뜨리고
            // 4. 빈곳 메꿔주고 -> 반복 (순열만큼)
            // 5. 결과 개수세서
            // 6. 남은거 최소값 구하기
            sb.append("#").append(T).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static int[] order;

    private static void perm(int cnt) {
        if (cnt == N) {
            // 다음 단계 시작
            play(order);
        } else {
            for (int i = 0; i < W; i++) {
                order[cnt] = i;
                perm(cnt + 1);
            }
        }
    }

    private static void play(int[] order) {
        // 1. 배열 복사
        int[][] temp = copy();
        // 2. 들어온 order 순서대로 블록 부수기 + 부서진 블록들을 아래로 내리기
        for (int o : order) {
            for (int i = 0; i < H; i++) {
                if (temp[i][o] != 0) { // 0 이 아닌 녀석을 처음으로 발견한다면
                    temp = destroy(i, o, temp);// 해당 좌표 i,o 를 가지고 블록을 부수러 간다.
                    temp = drop(temp);
                    break;
                }
            }
        }
        // 3. 해당 결과의 남은 블록 계산하기
        // 4. answer 최소값으로 바궈주기
        calc(temp);
    }

    private static void calc(int[][] temp) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (temp[i][j] > 0) {
                    cnt++;
                }
            }
        }
        answer = Math.min(answer, cnt);
    }

    private static int[][] drop(int[][] temp) {
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if (temp[j][i] > 0) {
                    s.add(temp[j][i]);
                    temp[j][i] = 0;
                }
            }
            int size = s.size();
            for (int h = H - 1; h > (H - 1) - size; h--) {
                temp[h][i] = s.pop();
            }
        }
        return temp;
    }

    private static int[][] destroy(int h, int w, int[][] temp) {
        // 좌표를 기준으로, 좌표가 가진 blocks[h][w] 의 값  -1 만큼 주변 4방향을 부순다.
        Queue<int[]> q = new LinkedList<>();
        int value = temp[h][w] - 1;
        if (value == 0) {
            temp[h][w] = 0;
            return temp;
        } else {
            q.add(new int[]{h, w, temp[h][w]});
            temp[h][w] = 0;
            while (!q.isEmpty()) {
                int[] v = q.poll(); // 가져온다
                int power = v[2]; // 파워 value
                for (int p = 1; p < power; p++) {
                    for (int i = 0; i < 4; i++) {
                        int nx = v[0] + dx[i] * p;
                        int ny = v[1] + dy[i] * p;
                        if (isRange(nx, ny) && temp[nx][ny] > 0) {
                            q.add(new int[]{nx, ny, temp[nx][ny]});
                            temp[nx][ny] = 0;
                        }
                    }
                }
            }

        }
        return temp;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < H && y < W;
    }

    private static int[][] copy() {
        int[][] temp = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                temp[i][j] = blocks[i][j];
            }
        }
        return temp;
    }

    private static void printArr(int[][] arr) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}