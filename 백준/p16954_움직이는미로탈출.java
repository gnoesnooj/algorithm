import java.util.*;
import java.io.*;

public class p16954_움직이는미로탈출 {

    static int answer = 0;
    static int CHESS_SIZE = 8;
    static int DIRECTION = 8;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static char[][] chess;
    static boolean[][][] visited;
    static Queue<int[]> movings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        chess = new char[CHESS_SIZE][CHESS_SIZE];

        visited = new boolean[CHESS_SIZE][CHESS_SIZE][2];

        movings = new LinkedList<>();

        for (int i = 0; i < CHESS_SIZE; i++) {
            chess[i] = br.readLine().toCharArray();
        }

        movings.add(new int[]{CHESS_SIZE - 1, 0});
        visited[CHESS_SIZE - 1][0][0] = true;

        while (!movings.isEmpty()) {
            // 벽이동
            char[][] movedChess = moveWall();
            // queue 사이즈만큼
            int size= movings.size();
            for (int s = 0; s < size; s++) {
                int[] current = movings.poll();
                for (int d = 0; d < DIRECTION; d++) {
                    int nx = current[0] + dx[d];
                    int ny = current[1] + dy[d];
                    if (isAnswer(nx, ny)) {
                        answer = 1;
                    }
                    if (movable(nx, ny) && !visited[nx][ny][0]) { // 현재 위치에서 다음 이동할 곳이 접근 가능하면
                        if (movedChess[nx][ny] != '#') {
                            visited[nx][ny][0] = true;
                            movings.add(new int[]{nx, ny});
                        }
                    }

                    // 가만히 있어도 될 경우
                    if (movedChess[current[0]][current[1]] != '#'
                            && !visited[current[0]][current[1]][1]) { // 가만히 있어도 될 경우에
                        visited[current[0]][current[1]][1] = true;
                        movings.add(current);
                    }
                }
            }
            // 벽교체
            chess = movedChess.clone();
        }

        System.out.println(answer);

    }

    private static boolean movable(int x, int y) {
        return x >= 0 && y >= 0 && x < CHESS_SIZE && y < CHESS_SIZE && chess[x][y] == '.';
    }

    private static char[][] moveWall() {
        char[][] movedChess = new char[CHESS_SIZE][CHESS_SIZE];

        for (int i = 0; i < CHESS_SIZE; i++) {
            if (i == 0) {
                Arrays.fill(movedChess[i], '.');
            } else {
                movedChess[i] = chess[i - 1].clone();
            }
        }
        return movedChess;
    }

    private static boolean isAnswer(int x, int y) {
        return x == 0 && y == CHESS_SIZE - 1;
    }
}
