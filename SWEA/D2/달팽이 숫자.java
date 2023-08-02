import java.util.Scanner;
import java.io.FileInputStream;

/**
 * @author joonseong 접근 ) 해당 좌표에서 오른쪽, 왼쪽, 위, 아래 방향의 순서대로 탐색을 진행한다. 배열 밖의 인덱스거나, 다음 이동좌표의 값이 0이 아니라면 진행하는 방향을 바꾼다.
 * 메모리 : 20,472kb
 * 실행시간 : 133ms
 */
class Solution {

    static int[][] miro;

    public static void main(String args[]) throws Exception {
        int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
        int[] dy = {1, 0, -1, 0};

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int size = sc.nextInt(); // 배열 크기

            miro = new int[size][size];

            int x = 0;
            int y = 0;
            int d = 0; // 현재 진행 방향
            for (int i = 1; i <= size * size; i++) {
                miro[x][y] = i;
                // 다음 이동 좌표 구하기

                int movedX = x + dx[d % 4];
                int movedY = y + dy[d % 4];

                // 다음 이동 좌표가 범위 밖으로 벗어나거나, 이미 방문한 좌표인 곳이라면, 진행 방향을 바꿔준다.
                if (movedX < 0 || movedY < 0 || movedX >= size || movedY >= size || miro[movedX][movedY] != 0) {
                    d++;
                    movedX = x + dx[d % 4];
                    movedY = y + dy[d % 4];
                }
                x = movedX;
                y = movedY;
            }

            System.out.println("#" + test_case);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(miro[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
