import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 30856kb
 * 실행시간 356ms
 *
 * 주어진 조건에 맞게 구현해준다.
 * */

public class BJ_17144_미세먼지안녕_예준성 {
    static int X;
    static int Y;
    static int seconds;
    static int[][] dust;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs;

        inputs = br.readLine().split(" ");
        X = Integer.parseInt(inputs[0]);
        Y = Integer.parseInt(inputs[1]);

        seconds = Integer.parseInt(inputs[2]);
        dust = new int[X][Y];

        int[] conditioner = new int[2];
        // 값 입력 받기
        for (int i = 0; i < X; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < Y; j++) {
                dust[i][j] = Integer.parseInt(inputs[j]);
                if (dust[i][j] == -1) {
                    conditioner[0] = i;
                    conditioner[1] = j;
                }
            }
        }

        // 확산
        // seconds 만큼 반복
        int[][] tmp;
        for (int second = 0; second < seconds; second++) { // 1000
            tmp = new int[X][Y]; // 더해줄 확산 값을 넣어줄 배열
            // 확산
            for (int x = 0; x < X; x++) { // 50
                for (int y = 0; y < Y; y++) { // 50
                    if (dust[x][y] > 0) {
                        int cnt = 0;
                        for (int d = 0; d < 4; d++) {
                            int moveX = x + dx[d];
                            int moveY = y + dy[d];
                            if (moveY >= 0 && moveY < Y && moveX >= 0 && moveX < X && dust[moveX][moveY] != -1) {
                                tmp[moveX][moveY] += dust[x][y] / 5;
                                cnt++;
                            }
                        }
                        dust[x][y] -= (dust[x][y] / 5) * cnt;
                        if (dust[x][y] > 0) {
                            tmp[x][y] += dust[x][y];
                        }
                    }
                }
            }
            dust = tmp;

            // 공기 청정기
            // 위쪽 돌리기 ----
                // 왼쪽
            for (int i = conditioner[0] - 2; i > 0; i--) {
                dust[i][0] = dust[i - 1][0];
            }
                // 위쪽
            for (int i = 0; i < Y - 1; i++) {
                dust[0][i] = dust[0][i + 1];
            }
                // 오른쪽
            for (int i = 0; i < conditioner[0] - 1; i++) {
                dust[i][Y - 1] = dust[i + 1][Y - 1];
            }
                // 아래쪽
            for (int i = Y - 1; i > 1; i--) {
                dust[conditioner[0] - 1][i] = dust[conditioner[0] - 1][i - 1];
            }
            dust[conditioner[0] - 1][1] = 0; // 청정기에서 나온건 0

            // 아래쪽 돌리기 ----
                // 왼쪽
            for (int i = conditioner[0] + 1; i < X - 1; i++) {
                dust[i][0] = dust[i + 1][0];
            }
                // 아래쪽
            for (int i = 0; i < Y - 1; i++) {
                dust[X - 1][i] = dust[X - 1][i + 1];
            }
                // 오른쪽
            for (int i = X - 1; i > conditioner[0]; i--) {
                dust[i][Y - 1] = dust[i - 1][Y - 1];
            }
                // 위쪽
            for (int i = Y - 1; i > 1; i--) {
                dust[conditioner[0]][i] = dust[conditioner[0]][i - 1];
            }
            dust[conditioner[0]][1] = 0; // 청정기에서 나온건 0

            // 청정기 위치 다시 넣어주기
            dust[conditioner[0]][0] = -1;
            dust[conditioner[0]-1][0] = -1;
        }
        System.out.println(calc(dust));
    }

    private static int calc(int[][] dust) {
        int sum = 0;
        for (int i = 0; i < dust.length; i++) {
            for (int num : dust[i]) {
                if (num != -1) {
                    sum += num;
                }
            }
        }

        return sum;
    }
}
