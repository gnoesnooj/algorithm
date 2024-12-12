import java.util.*;
import java.io.*;

public class p3197_백조의호수 {
    static int R, C;
    static char[][] miro;
    static Queue<int[]> waters = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    static List<int[]> swans = new ArrayList<>();
    static Queue<int[]> swanNext;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        R = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);
        miro = new char[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                miro[i][j] = input.charAt(j);
                if (miro[i][j] == '.' || miro[i][j] == 'L') waters.add(new int[]{i, j});
                if (miro[i][j] == 'L') swans.add(new int[]{i, j});
            }
        }

        int cnt = 0;
        visited = new boolean[R][C];
        swanNext = new LinkedList<>();
        swanNext.add(swans.get(0));
        visited[swans.get(0)[0]][swans.get(0)[1]] = true;

        while (true) {
            if (isSwanMeet()) {
                System.out.println(cnt);
                return;
            }
            meltIce();
            cnt++;
        }
    }

    private static void meltIce() {
        int size = waters.size();
        for (int i = 0; i < size; i++) {
            int[] water = waters.poll();
            for (int d = 0; d < 4; d++) {
                int nx = water[0] + dx[d];
                int ny = water[1] + dy[d];
                if (isRange(nx, ny) && miro[nx][ny] == 'X') {
                    miro[nx][ny] = '.';
                    waters.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isSwanMeet() {
        Queue<int[]> nextQueue = new LinkedList<>();
        while (!swanNext.isEmpty()) {
            int[] current = swanNext.poll();
            for (int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];
                if (nx == swans.get(1)[0] && ny == swans.get(1)[1]) return true;
                if (isRange(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (miro[nx][ny] == '.') swanNext.add(new int[]{nx, ny}); // 첫날에 탐색이 가능한 부분으로, 계속 탐색을 이어 나갈 수 있도록 한다.
                    else nextQueue.add(new int[]{nx, ny}); // 첫날에 벽이었으므로 다음날엔 물이 되므로, 물이 되는 부분을 넣어줌으로서 이전날에 물이었던 부분을 재탐색할 필요를 줄여준다.
                }
            }
        }
        swanNext = nextQueue;
        return false;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }
}
