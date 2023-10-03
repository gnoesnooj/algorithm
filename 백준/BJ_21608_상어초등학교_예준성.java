import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 16172kb
 * 시간 132ms
 * 문제 조건대로 구현해준다.
 * */

public class Main {

    static int N;
    // 애들 자리
    static int[][] seats;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // x 가 y를 좋아함
    static boolean[][] favorite;

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        N = Integer.parseInt(br.readLine());

        seats = new int[N][N];
        favorite = new boolean[N * N + 1][N * N + 1];

        for (int i = 0; i < N * N; i++) {
            inputs = br.readLine().split(" ");

            int student = Integer.parseInt(inputs[0]); // 이번에 앉을 학생의 번호

            // 좋아하는 학생
            for (int j = 1; j <= 4; j++) {
                favorite[student][Integer.parseInt(inputs[j])] = true;
            }

            int max_value = -1;
            int[] max_seat = new int[2];

            // 앉을 자리 찾기
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (seats[j][k] != 0) { // 이미 누가 앉아있다면
                        continue;
                    }
                    // 각 좌표 점수 뽑기
                    int value = getScore(j, k, student);
                    if (value > max_value) { // 더 큰 녀석 나타나면
                        max_value = value;
                        max_seat[0] = j;
                        max_seat[1] = k;
                    }
                }
            }
            // 큰 value에 자리 배정
            seats[max_seat[0]][max_seat[1]] = student;
        }

        getAnswer();

        System.out.println(answer);
    }
    private static int getScore(int x, int y, int student) {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isRange(nx, ny)) {
                if (seats[nx][ny] == 0) {
                    score += 1;
                } else if (favorite[student][seats[nx][ny]] == true) { // 해당 좌표의 값에 있는 학생이 favorite 이라면
                    score += 5;
                }
            }
        }
        return score;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private static void getAnswer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (isRange(nx, ny)) {
                        if (favorite[seats[i][j]][seats[nx][ny]] == true) {
                            cnt++;
                        }
                    }
                }
                answer += Math.pow(10, cnt - 1);
            }
        }
    }

}